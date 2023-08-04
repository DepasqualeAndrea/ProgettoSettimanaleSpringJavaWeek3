package DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.users.dispositivi;

import java.util.UUID;

import DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.users.Utente;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dispositivi")
@Data
@NoArgsConstructor
public class Dispositivi {
	@Id
	@GeneratedValue
	private UUID id;
	@Enumerated(EnumType.STRING)
	private TipoDispositivo tipoDispositivo;
	@Enumerated(EnumType.STRING)
	private StatoDispositivo statoDispositivo;

	@ManyToOne
	private Utente utente;

	public Dispositivi(TipoDispositivo tipoDispositivo, StatoDispositivo statoDispositivo) {
		this.tipoDispositivo = tipoDispositivo;
		this.statoDispositivo = statoDispositivo;
	}

}
