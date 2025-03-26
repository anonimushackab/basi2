package it.unimol.basi2.repository;

import it.unimol.basi2.entity.Prenotazione;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PrenotazioneRepository extends MongoRepository<Prenotazione, String> {

    List<Prenotazione> findByPartecipanteId(String nomeCliente);

    List<Prenotazione> findByDataPrenotazione(LocalDateTime dataPrenotazione);


    int countByEventoId(String eventoId);

    void deleteByEventoId(String eventoId);

}
