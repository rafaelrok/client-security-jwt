package com.rafaelvieira.clientsecurityjwt;

import com.rafaelvieira.clientsecurityjwt.entity.RoleModel;
import com.rafaelvieira.clientsecurityjwt.entity.UserModel;
import com.rafaelvieira.clientsecurityjwt.repository.RoleRepository;
import com.rafaelvieira.clientsecurityjwt.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ClientSecurityJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientSecurityJwtApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner runner(RoleRepository roleRepository, UserService userService) {
		return args -> {
			roleRepository.save(new RoleModel("ROLE_MASTER"));
			roleRepository.save(new RoleModel("ROLE_ADMIN"));
			roleRepository.save(new RoleModel("ROLE_USER"));

			userService.save(new UserModel("Rafael", "rafael@gmail.com", "123456"));
			userService.save(new UserModel("Mayara", "mayara@gmail.com", "123456"));
			userService.save(new UserModel("Ana", "ana@gmail.com", "123456"));

			userService.addRoleToUser("rafael@gmail.com", "ROLE_ADMIN");
			userService.addRoleToUser("mayara@gmail.com", "ROLE_MASTER");
			userService.addRoleToUser("ana@gmail.com", "ROLE_USER");
		};
	}
}
