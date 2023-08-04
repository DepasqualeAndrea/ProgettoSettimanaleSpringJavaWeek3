package DepasqualeAndreaRepository.progettoSettimanaleJavaSecurity.users.dispositivi;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispositivoRepo extends JpaRepository<Dispositivi, UUID> {
	Optional<Dispositivi> findById(UUID id);
}
