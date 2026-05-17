package coffeeshop.inventorysystem.auth.service;

import coffeeshop.inventorysystem.auth.dto.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<String> signUp(SignupRequest request);

    ResponseEntity<String> login(LoginRequest request);

    ResponseEntity<List<UserWrapper>> getALLUser();

    ResponseEntity<String> update(UpdateUserRequest request);

    ResponseEntity<String> checkToken();

    ResponseEntity<String> changePassword(ChangePasswordRequest request);

    ResponseEntity<String> forgotPassword(ForgotPasswordRequest request);
}
