package DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.users.dispositivi;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.exception.NotFoundException;
import DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.users.Utente;
import DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.users.UtenteService;

@Service
public class DispositivoService {
	private final DispositivoRepo dispositivoRepo;

	@Autowired
	UtenteService utenteService;

	@Autowired
	public DispositivoService(DispositivoRepo dispositivoRepo) {
		this.dispositivoRepo = dispositivoRepo;
	}

	public Dispositivi save(Dispositivi dispositivo, UUID idUtente) {
		Utente utente = utenteService.findById(idUtente);
		dispositivo.setUtente(utente);
		return dispositivoRepo.save(dispositivo);
	}

//	public Dispositivi creaDispositivo(Dispositivi body) {
//		dispositivoRepo.findById(body.getId()).ifPresent(u -> {
//			throw new BadRequestException("Dispositivo non trovato");
//		});
//		Dispositivi nuovoDispositivo = new Dispositivi(TipoDispositivo.TABLET, StatoDispositivo.ASSEGNATO);
//		return dispositivoRepo.save(nuovoDispositivo);
//	}

	public Page<Dispositivi> find(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

		return dispositivoRepo.findAll(pageable);
	}

	public Dispositivi findById(UUID id) throws NotFoundException {
		return dispositivoRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	public Dispositivi findByIdAndUpdate(UUID id, DispositiviRequestBody body) throws NotFoundException {
		Dispositivi found = this.findById(id);
		found.setStatoDispositivo(body.getStatoDispositivo());
		found.setTipoDispositivo(body.getTipoDispositivo());
		return dispositivoRepo.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Dispositivi found = this.findById(id);
		dispositivoRepo.delete(found);
	}

}
