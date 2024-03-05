package io.getarrays.JWT;

import io.getarrays.JWT.domain.Rol;
import io.getarrays.JWT.domain.Utilizator;
import io.getarrays.JWT.service.UtilizatorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class JwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtApplication.class, args);
	}

@Bean
	CommandLineRunner run(UtilizatorService userService)
	{
		return args -> {
			userService.saveRole(new Rol(null, "ROLE_USER"));
			userService.saveRole(new Rol(null, "ROLE_MANAGER"));
			userService.saveRole(new Rol(null, "ROLE_ADMIN"));
			userService.saveRole(new Rol(null, "ROLE_SUPER_ADMIN"));

			userService.saveUser(new Utilizator(null, "bla blabla", "bla", "email1@sa", "5351", new ArrayList<>()));
			userService.saveUser(new Utilizator(null, "bla blabla1", "bla1", "email2@sa", "5351", new ArrayList<>()));
			userService.saveUser(new Utilizator(null, "bla blabla2", "bla2", "email3@sa", "5351", new ArrayList<>()));

			userService.adaugaRol("bla", "ROLE_USER");
			userService.adaugaRol("bla1", "ROLE_ADMIN");
			userService.adaugaRol("bla2", "ROLE_ADMIN");
			userService.adaugaRol("bla1", "ROLE_USER");
		};
	}
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
