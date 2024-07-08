package io.owl_browthers.owlplay.api;

import io.owl_browthers.owlplay.dto.SignUpRequest;
import io.owl_browthers.owlplay.entity.User;
import io.owl_browthers.owlplay.repository.UserRepository;
import io.owl_browthers.owlplay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String signUp(@RequestBody SignUpRequest signUpRequest) {
        return userService.signUpUser(signUpRequest);
    }


    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        return userService.loginUser(username, password);
    }

}