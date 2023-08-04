package DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.users;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "utenti")
@Data
@NoArgsConstructor
public class Utente implements UserDetails {
	@Id
	@GeneratedValue
	private UUID id;
	private String name;
	private String surname;
	private String password;
	@Column(nullable = true, unique = true)
	private String email;
	@Enumerated(EnumType.STRING)
	private Role role;

	public Utente(String name, String surname, String password, String email) {
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.email = email;
		this.role = Role.UTENTE;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));

	}

	@Override
	public String getUsername() {

		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

}
