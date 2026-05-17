package coffeeshop.inventorysystem.security;

import coffeeshop.inventorysystem.auth.model.User;
import coffeeshop.inventorysystem.auth.repository.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class CustomerUsersDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    private User userDetail;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Inside loadUserByUsername {}", username);
        userDetail = userDao.findByEmailId(username);

        if (!Objects.isNull(userDetail)) {
            return new org.springframework.security.core.userdetails.User(
                    userDetail.getEmail(),
                    userDetail.getPassword(),
                    List.of(new SimpleGrantedAuthority("ROLE_" + userDetail.getRol().getNombre().toUpperCase()))
            );
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
    }

    public User getUserDetail() {
        return userDetail;
    }
}
