package DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.users;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.users.dispositivi.DispositivoService;
import DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.users.payload.UtenteRequestPayload;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UtenteRunner implements CommandLineRunner {
	@Autowired
	UtenteService utenteService;
	@Autowired
	DispositivoService dispositivoService;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));

		for (int i = 0; i < 30; i++) {
			String name = faker.name().firstName();
			String surname = faker.name().lastName();
			String username = faker.name().username();
			String email = faker.internet().emailAddress();
			String password = faker.lorem().characters(6, 12);
			UtenteRequestPayload utente = new UtenteRequestPayload(name, surname, username, email, password);
			// utenteService.creaUtente(utente);

		}
//		try {
//
//			UtenteRequestPayload dottore = new UtenteRequestPayload("mio", "dio", "asntocielo@Gmail.com",
//					"terribilie12");
//			utenteService.creaUtente(dottore);
//			log.info("utente salvato con successo");
//
//			Dispositivi dispositivo = new Dispositivi(TipoDispositivo.TABLET, StatoDispositivo.ASSEGNATO);
//			// dispositivoService.creaDispositivo(dispositivo);
//			log.info("utente salvato con successo");
//
//		} catch (NotFoundException e) {
//			log.error(e.getMessage());
//		}
	}
}
