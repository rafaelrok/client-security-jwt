package com.rafaelvieira.clientsecurityjwt.controller;

import com.rafaelvieira.clientsecurityjwt.entity.RoleModel;
import com.rafaelvieira.clientsecurityjwt.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/roles")
public class RoleController {

    private RoleRepository roleRepository;

    @GetMapping
    public List<RoleModel> findAll() {
        return this.roleRepository.findAll();
    }

    @PostMapping
    public RoleModel save(@RequestBody RoleModel roleModel) {
        return this.roleRepository.save(roleModel);
    }

}

