package DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.exception.UnauthorizedException;
import DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.users.Utente;
import DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.users.UtenteService;
import DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.users.payload.UtenteLoginPayload;
import DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.users.payload.UtenteRequestPayload;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	UtenteService utenteService;

	@Autowired
	JTWTools jwtTools;

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public Utente saveUser(@RequestBody UtenteRequestPayload body) {
		Utente created = utenteService.creaUtente(body);
		return created;
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UtenteLoginPayload body) {
		// devo provare a modificare String

		Utente utente = utenteService.findByEmail(body.getEmail());

		if (body.getPassword().equals(utente.getPassword())) {

			String token = jwtTools.creaToken(utente);
			return new ResponseEntity<>(token, HttpStatus.OK); // 200
		} else {
			throw new UnauthorizedException("Credenziali non valide"); // 401
		}
	}

}
