package DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.exception.UnauthorizedException;
import DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.users.Utente;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JTWTools {
	@Value("${spring.jwt.secret}")
	private String secret;

	public String creaToken(Utente user) {
		String token = Jwts.builder().setSubject(user.getId().toString()) // A chi appartiene il token (Subject)
				.setIssuedAt(new Date(System.currentTimeMillis())) // Quando è stato emesso il token (IAT - Issued At)
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)) // Quando scadrà il
				// token
				.signWith(Keys.hmacShaKeyFor(secret.getBytes())) // Genero la firma del token
				.compact(); // Crea il token con quanto detto prima
		return token;
	}

	public void verificaToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new UnauthorizedException("Token non valido, rieffettua il login");
		}
	}

	public String extractSubject(String token) {
		return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parseClaimsJws(token)
				.getBody().getSubject(); // sub id utente

	}

}
