package coffeeshop.inventorysystem.auth.service;

import coffeeshop.inventorysystem.auth.dto.*;
import coffeeshop.inventorysystem.auth.model.Rol;
import coffeeshop.inventorysystem.auth.model.User;
import coffeeshop.inventorysystem.auth.repository.RolDao;
import coffeeshop.inventorysystem.auth.repository.UserDao;
import coffeeshop.inventorysystem.common.CafeConstants;
import coffeeshop.inventorysystem.common.CafeUtils;
import coffeeshop.inventorysystem.common.EmailUtils;
import coffeeshop.inventorysystem.security.CustomerUsersDetailsService;
import coffeeshop.inventorysystem.security.JwtFilter;
import coffeeshop.inventorysystem.security.JwtUtil;
import coffeeshop.inventorysystem.auth.service.AuditService;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final RolDao rolDao;

    private final AuthenticationManager authenticationManager;

    private final CustomerUsersDetailsService customerUsersDetailsService;

    private final JwtUtil jwtUtil;

    private final JwtFilter jwtFilter;

    private final EmailUtils emailUtils;

    private final AuditService auditService;

    private final MessageSource messageSource;

    @Override
    public ResponseEntity<String> signUp(SignupRequest request) {
        log.info("Inside signup {}", request);
        try {
            if (request.getName() != null && request.getContactNumber() != null
                    && request.getEmail() != null && request.getPassword() != null) {
                User user = userDao.findByEmailId(request.getEmail());
                if (Objects.isNull(user)) {
                    User nuevo = userDao.save(getUserFromMap(request));
                    auditService.log(nuevo, "SIGNUP", "Usuario registrado: " + nuevo.getEmail());
                    return CafeUtils.getResponseEntity(
                            messageSource.getMessage("user.registered", null, LocaleContextHolder.getLocale()),
                            HttpStatus.OK);
                } else {
                    return CafeUtils.getResponseEntity(
                            messageSource.getMessage("user.email.exists", null, LocaleContextHolder.getLocale()),
                            HttpStatus.BAD_REQUEST);
                }
            } else {
                return CafeUtils.getResponseEntity(
                        messageSource.getMessage("common.invalid.data", null, LocaleContextHolder.getLocale()),
                        HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private final PasswordEncoder passwordEncoder;

    private User getUserFromMap(SignupRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setContactNumber(request.getContactNumber());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        if (userDao.count() == 0) {
            user.setStatus("true");
            user.setRol(rolDao.findByNombre("admin"));
        } else {
            user.setStatus("false");
            user.setRol(rolDao.findByNombre("user"));
        }

        return user;
    }

    private boolean esAdmin(User user) {
        return "admin".equalsIgnoreCase(user.getRol().getNombre());
    }

    @Override
    public ResponseEntity<String> login(LoginRequest request) {
        log.info("Inside login");
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            if (auth.isAuthenticated()) {
                if (customerUsersDetailsService
                        .getUserDetail()
                        .getStatus()
                        .equalsIgnoreCase("true")) {

                    User userLogin = customerUsersDetailsService.getUserDetail();
                    auditService.log(userLogin, "LOGIN", "Inicio de sesión exitoso");
                    return new ResponseEntity<String>(
                            "{\"token\":\"" +
                                    jwtUtil.generateToken(
                                            userLogin.getEmail(),
                                            userLogin.getRol().getNombre()
                                    ) + "\"}",
                            HttpStatus.OK);
                } else {
                    return new ResponseEntity<String>(
                            "{\"message\":\"" +
                                    messageSource.getMessage("user.login.wait.approval", null, LocaleContextHolder.getLocale())
                                    + "\"}",
                            HttpStatus.BAD_REQUEST);
                }
            }

        } catch (Exception ex) {
            log.error("{}", ex);
        }
        return new ResponseEntity<String>(
                "{\"message\":\"" +
                        messageSource.getMessage("user.login.bad.credentials", null, LocaleContextHolder.getLocale())
                        + "\"}",
                HttpStatus.BAD_REQUEST
        );
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getALLUser() {
        try {
            return new ResponseEntity<>(userDao.getALLUser(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(
                new ArrayList<>(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @Override
    public ResponseEntity<String> update(UpdateUserRequest request) {
        try {
            Optional<User> optional = userDao.findById(request.getId());
            if (!optional.isEmpty()) {
                if (esAdmin(optional.get())) {
                    return CafeUtils.getResponseEntity(
                            messageSource.getMessage("user.cannot.modify.admin", null, LocaleContextHolder.getLocale()),
                            HttpStatus.FORBIDDEN
                    );
                }

                userDao.updateStatus(
                        request.getStatus(),
                        request.getId()
                );
                User adminUser = userDao.findByEmailId(jwtFilter.getCurrentUser());
                auditService.log(adminUser, "STATUS_CHANGE",
                        "Usuario " + request.getId() + " → " + request.getStatus());
                sendMailToAllAdmin(
                        request.getStatus(),
                        optional.get().getEmail(),
                        userDao.getAllAdmin()
                );
                return CafeUtils.getResponseEntity(
                        messageSource.getMessage("user.status.updated", null, LocaleContextHolder.getLocale()),
                        HttpStatus.OK
                );

            } else {
                return CafeUtils.getResponseEntity(
                        messageSource.getMessage("user.id.not.exists", null, LocaleContextHolder.getLocale()),
                        HttpStatus.OK
                );
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return CafeUtils.getResponseEntity(
                CafeConstants.SOMETHING_WENT_WRONG,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @Override
    public ResponseEntity<String> assignAdmin(Integer id) {
        try {
            Optional<User> optional = userDao.findById(id);
            if (optional.isEmpty()) {
                return CafeUtils.getResponseEntity(
                        messageSource.getMessage("user.not.found", null, LocaleContextHolder.getLocale()),
                        HttpStatus.BAD_REQUEST);
            }

            if (esAdmin(optional.get())) {
                return CafeUtils.getResponseEntity(
                        messageSource.getMessage("user.already.admin", null, LocaleContextHolder.getLocale()),
                        HttpStatus.BAD_REQUEST
                );
            }

            optional.get().setRol(rolDao.findByNombre("admin"));
            optional.get().setStatus("true");
            userDao.save(optional.get());

            User adminUser = userDao.findByEmailId(jwtFilter.getCurrentUser());
            auditService.log(adminUser, "ASSIGN_ADMIN",
                    "Usuario " + id + " fue promovido a administrador.");

            return CafeUtils.getResponseEntity(
                    messageSource.getMessage("user.admin.promoted", null, LocaleContextHolder.getLocale()),
                    HttpStatus.OK
            );
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return CafeUtils.getResponseEntity(
                CafeConstants.SOMETHING_WENT_WRONG,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    private void sendMailToAllAdmin(String status, String user, List<String> allAdmin) {
        var locale = LocaleContextHolder.getLocale();

        allAdmin.remove(jwtFilter.getCurrentUser());

        if (status != null && status.equalsIgnoreCase("true")) {
            emailUtils.sendSimpleMessage(
                    jwtFilter.getCurrentUser(),
                    messageSource.getMessage("email.subject.approved", null, locale),
                    messageSource.getMessage("email.body.approved.admin", new Object[]{user, jwtFilter.getCurrentUser()}, locale),
                    allAdmin
            );
            sendMailToUser(user,
                    messageSource.getMessage("email.subject.approved", null, locale),
                    messageSource.getMessage("email.body.approved.user", null, locale));
        } else {
            emailUtils.sendSimpleMessage(
                    jwtFilter.getCurrentUser(),
                    messageSource.getMessage("email.subject.disabled", null, locale),
                    messageSource.getMessage("email.body.disabled.admin", new Object[]{user, jwtFilter.getCurrentUser()}, locale),
                    allAdmin
            );
            sendMailToUser(user,
                    messageSource.getMessage("email.subject.disabled", null, locale),
                    messageSource.getMessage("email.body.disabled.user", null, locale));
        }
    }

    private void sendMailToUser(String user, String subject, String text) {
        emailUtils.sendSimpleMessage(user, subject, text, null);
    }

    @Override
    public ResponseEntity<String> checkToken() {
        return CafeUtils.getResponseEntity("true", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> changePassword(ChangePasswordRequest request) {
        try {
            User userObj = userDao.findByEmail(jwtFilter.getCurrentUser());

            if (userObj != null) {
                if (passwordEncoder.matches(request.getOldPassword(), userObj.getPassword())) {
                    userObj.setPassword(passwordEncoder.encode(request.getNewPassword()));
                    userDao.save(userObj);

                    auditService.log(userObj, "PASSWORD_CHANGE", "Contraseña actualizada");

                    return CafeUtils.getResponseEntity(
                            messageSource.getMessage("user.password.updated", null, LocaleContextHolder.getLocale()),
                            HttpStatus.OK
                    );
                }

                return CafeUtils.getResponseEntity(
                        messageSource.getMessage("user.password.incorrect.old", null, LocaleContextHolder.getLocale()),
                        HttpStatus.BAD_REQUEST
                );
            }
            return CafeUtils.getResponseEntity(
                    CafeConstants.SOMETHING_WENT_WRONG,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return CafeUtils.getResponseEntity(
                CafeConstants.SOMETHING_WENT_WRONG,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @Override
    public ResponseEntity<String> forgotPassword(ForgotPasswordRequest request) {
        try {
            User user = userDao.findByEmail(request.getEmail());

            if (!Objects.isNull(user) && !Strings.isNullOrEmpty(user.getEmail())) {
                String tempPassword = UUID.randomUUID().toString().substring(0, 8);

                user.setPassword(passwordEncoder.encode(tempPassword));
                userDao.save(user);

                emailUtils.forgotMail(
                        user.getEmail(),
                        messageSource.getMessage("email.subject.forgot", null, LocaleContextHolder.getLocale()),
                        tempPassword
                );
            }
            return CafeUtils.getResponseEntity(
                    messageSource.getMessage("user.check.mail", null, LocaleContextHolder.getLocale()),
                    HttpStatus.OK
            );

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return CafeUtils.getResponseEntity(
                CafeConstants.SOMETHING_WENT_WRONG,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
