package DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.users;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.users.payload.UtenteRequestPayload;

@RestController
@RequestMapping("/utenti")
public class UtenteController {
	private final UtenteService utenteService;

	@Autowired
	public UtenteController(UtenteService utenteService) {
		this.utenteService = utenteService;
	}

	@GetMapping
	public Page<Utente> getUsers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return utenteService.find(page, size, sortBy);
	}

	@GetMapping("/{utenteId}")
	public Utente findById(@PathVariable UUID usId) {
		return utenteService.findById(usId);

	}

	@PutMapping("/{utenteId}")
	public Utente updateUser(@PathVariable UUID userId, @RequestBody UtenteRequestPayload body) {
		return utenteService.findByIdAndUpdate(userId, body);
	}

	@DeleteMapping("/{utenteId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUtente(@PathVariable UUID usId) {
		utenteService.findByIdAndDelete(usId);
	}

}
