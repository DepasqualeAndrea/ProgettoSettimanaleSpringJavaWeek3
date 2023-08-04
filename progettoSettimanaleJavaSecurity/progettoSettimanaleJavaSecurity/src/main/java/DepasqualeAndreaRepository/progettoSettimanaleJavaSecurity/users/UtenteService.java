package DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.users;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.exception.BadRequestException;
import DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.exception.NotFoundException;
import DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.users.payload.UtenteRequestPayload;

@Service
public class UtenteService {
	private final UtenteRepo utenteRepo;

	@Autowired
	public UtenteService(UtenteRepo utenteRepo) {
		this.utenteRepo = utenteRepo;
	}

	public Utente creaUtente(UtenteRequestPayload body) {
		utenteRepo.findByEmail(body.getEmail()).ifPresent(u -> {
			throw new BadRequestException("l'email inserita non è valida");
		});
		Utente newUtente = new Utente(body.getName(), body.getSurname(), body.getEmail(), body.getPassword(),
				Role.UTENTE);
		return utenteRepo.save(newUtente);
	}

	public Page<Utente> find(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

		return utenteRepo.findAll(pageable);
	}

	public Utente findById(UUID id) throws NotFoundException {
		return utenteRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	public Utente findByIdAndUpdate(UUID id, UtenteRequestPayload body) throws NotFoundException {
		Utente found = this.findById(id);
		found.setEmail(body.getEmail());
		found.setName(body.getName());
		found.setSurname(body.getSurname());

		return utenteRepo.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Utente found = this.findById(id);
		utenteRepo.delete(found);
	}

	public Utente findByEmail(String email) {
		return utenteRepo.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato"));
	}

}
