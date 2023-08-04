package DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.users.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UtenteRequestPayload {
	private String name;
	private String surname;
	private String email;
	private String password;

}
