package DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.exception.UnauthorizedException;
import DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.users.Utente;
import DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.users.UtenteService;
import DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.users.payload.UtenteLoginPayload;
import DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.users.payload.UtenteRequestPayload;

@Controller
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	UtenteService utenteService;

	@Autowired
	JTWTools jwtTools;

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public Utente salvaUtente(@RequestBody UtenteRequestPayload body) {
		Utente creato = utenteService.creaUtente(body);
		return creato;
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UtenteLoginPayload body) {
		Utente utente = utenteService.findByEmail(body.getEmail()); // verifico che l'email esista
		if (body.getPassword().equals(utente.getPassword())) { // verifico che la password sia la stessa presente nel bd
			String token = jwtTools.creaToken(utente);
			System.out.println(token);
			return new ResponseEntity<>(token, HttpStatus.OK);
		} else {
			throw new UnauthorizedException("Credenziali non valide!");
		}
	}

}
