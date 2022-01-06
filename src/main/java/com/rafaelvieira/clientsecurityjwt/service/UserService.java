package com.rafaelvieira.clientsecurityjwt.service;

import com.rafaelvieira.clientsecurityjwt.entity.RoleModel;
import com.rafaelvieira.clientsecurityjwt.entity.UserModel;
import com.rafaelvieira.clientsecurityjwt.repository.RoleRepository;
import com.rafaelvieira.clientsecurityjwt.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel user = this.userRepository.findByEmail(email).orElseThrow();

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (RoleModel role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new User(user.getEmail(), user.getPassword(), authorities);
    }

    public UserModel save(UserModel userModel) {
        userModel.setPassword(this.passwordEncoder.encode(userModel.getPassword()));
        return this.userRepository.save(userModel);
    }

    public void addRoleToUser(String email, String name) {
        UserModel user = this.userRepository.findByEmail(email).orElseThrow();
        RoleModel role = this.roleRepository.findByName(name).orElseThrow();

        user.getRoles().add(role);

        this.userRepository.save(user);
    }

}
