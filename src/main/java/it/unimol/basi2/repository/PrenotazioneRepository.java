package it.unimol.basi2.repository;

import it.unimol.basi2.entity.Prenotazione;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PrenotazioneRepository extends MongoRepository<Prenotazione, String> {
    Optional<Prenotazione> findById(String id);

    Optional<List<Prenotazione>> findByNomeCliente(String nomeCliente);

    Optional<List<Prenotazione>> findByDataPrenotazione(LocalDateTime dataPrenotazione);

    Prenotazione save(Prenotazione prenotazione);

    boolean existsById(String prenotazioneId);

    void deleteById(String prenotazioneId);
}
