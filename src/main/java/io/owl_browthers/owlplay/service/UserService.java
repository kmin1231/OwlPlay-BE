package io.owl_browthers.owlplay.service;

import io.owl_browthers.owlplay.dto.SignUpRequest;
import io.owl_browthers.owlplay.entity.User;
import io.owl_browthers.owlplay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String signUpUser(SignUpRequest signUpRequest) {
        if (userRepository.findByUsername(signUpRequest.getUsername()) != null) {
            return "Already exitsts!";
        }

        if (!signUpRequest.getPassword().equals(signUpRequest.getPassword2())) {
            return "Passwords do not match!";
        }

        User newUser = new User();
        newUser.setUsername(signUpRequest.getUsername());
        newUser.setPassword(signUpRequest.getPassword());
        newUser.setName(signUpRequest.getName());
        newUser.setContact(signUpRequest.getContact());
        newUser.setBirthday(signUpRequest.getBirthday());

        userRepository.save(newUser);
        return "pass";
    }

    public String loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return "User not found!";
        }

        if (!user.getPassword().equals(password)) {
            return "Password does not match!";
        }

        return "pass";
    }
}