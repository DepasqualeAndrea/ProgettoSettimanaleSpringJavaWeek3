package DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.users.dispositivi;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/device")
@RestController
public class DispositivoController {
	private final DispositivoService dispositivoService;

	@Autowired
	public DispositivoController(DispositivoService dispositivoService) {
		this.dispositivoService = dispositivoService;
	}

	@GetMapping
	public Page<Dispositivi> getDispositivo(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return dispositivoService.find(page, size, sortBy);
	}

	// POST
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Dispositivi saveDispositivo(@RequestBody Dispositivi body) {
		UUID idUtente = body.getUtente().getId();
		Dispositivi dispositiviCreato = dispositivoService.save(body, idUtente);
		return dispositiviCreato;
	}

	@GetMapping("/{deviceId}")
	public Dispositivi findById(@PathVariable UUID deviceId) {
		return dispositivoService.findById(deviceId);

	}

	@PutMapping("/{deviceId}")
	public Dispositivi updateDispositivo(@PathVariable UUID deviceId, @RequestBody DispositiviRequestBody body) {
		return dispositivoService.findByIdAndUpdate(deviceId, body);
	}

	@DeleteMapping("/{deviceId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUtente(@PathVariable UUID deviceId) {
		dispositivoService.findByIdAndDelete(deviceId);
	}

}
