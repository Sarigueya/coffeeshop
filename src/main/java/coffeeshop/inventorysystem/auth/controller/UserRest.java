package coffeeshop.inventorysystem.auth.controller;

import coffeeshop.inventorysystem.auth.dto.*;
import coffeeshop.inventorysystem.auth.dto.UserWrapper;
import coffeeshop.inventorysystem.auth.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/user")
public interface UserRest {

    @PostMapping(path = "/signup")
    ResponseEntity<String> signUp(@Valid @RequestBody SignupRequest request);

    @PostMapping(path = "/login")
    ResponseEntity<String> login(@Valid @RequestBody LoginRequest request);

    @GetMapping(path = "/get")
    ResponseEntity<List<UserWrapper>> getAllUser();

    @PostMapping(path = "/update")
    ResponseEntity<String> update(@Valid @RequestBody UpdateUserRequest request);

    @GetMapping(path = "/checkToken")
    ResponseEntity<String> checkToken();

    @PostMapping(path = "/changePassword")
    ResponseEntity<String> changePassword(@Valid @RequestBody ChangePasswordRequest request);

    @PostMapping(path = "/forgotPassword")
    ResponseEntity<String> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request);
}
