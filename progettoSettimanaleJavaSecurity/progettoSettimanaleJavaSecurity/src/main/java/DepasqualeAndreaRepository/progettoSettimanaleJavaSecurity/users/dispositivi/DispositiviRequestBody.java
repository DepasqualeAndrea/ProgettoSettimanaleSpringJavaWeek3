package DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.users.dispositivi;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DispositiviRequestBody {
	private TipoDispositivo tipoDispositivo;
	private StatoDispositivo statoDispositivo;
}
