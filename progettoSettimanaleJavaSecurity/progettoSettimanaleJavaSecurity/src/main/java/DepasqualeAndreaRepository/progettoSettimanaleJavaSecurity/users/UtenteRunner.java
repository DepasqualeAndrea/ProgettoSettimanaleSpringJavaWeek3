package DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.users;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.users.payload.UtenteRequestPayload;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UtenteRunner implements CommandLineRunner {
	@Autowired
	UtenteService utenteService;

	@Override
	public void run(String... args) throws Exception {
//		try {
//
//			// UtenteRequestPayload ivan = new UtenteRequestPayload("Ivan", "Iasing",
//			// "Ivankof@Gmail.com", "Mamba123");
//			// utenteService.creaUtente(ivan);
//			log.info("utente salvato con successo");
//
//		} catch (NotFoundException e) {
//			log.error(e.getMessage());
//		}
		Faker faker = new Faker(new Locale("it"));

		for (int i = 0; i < 30; i++) {
			String name = faker.name().firstName();
			String surname = faker.name().lastName();
			String email = faker.internet().emailAddress();
			String password = "1234";
			UtenteRequestPayload utente = new UtenteRequestPayload(name, surname, email, password);
			// utenteService.creaUtente(utente);
		}
	}
}
