package com.literAlura.challenge;

import com.literAlura.challenge.principal.Principal;
import com.literAlura.challenge.repository.AutorRepository;
import com.literAlura.challenge.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeApplication implements CommandLineRunner {

	@Autowired
	private AutorRepository autorRepository;
	private LibroRepository libroRepository;


	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(libroRepository, autorRepository);
		principal.menu();
	}

}
