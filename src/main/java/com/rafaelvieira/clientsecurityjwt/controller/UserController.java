package com.rafaelvieira.clientsecurityjwt.controller;

import com.rafaelvieira.clientsecurityjwt.entity.UserModel;
import com.rafaelvieira.clientsecurityjwt.repository.UserRepository;
import com.rafaelvieira.clientsecurityjwt.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private UserRepository userRepository;

    @GetMapping
    public List<UserModel> findAll() {
        return this.userRepository.findAll();
    }

    @PostMapping
    public UserModel save(@RequestBody UserModel userModel) {
        return this.userService.save(userModel);
    }

}
