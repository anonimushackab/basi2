package it.unimol.basi2.repository;

import it.unimol.basi2.entity.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventoRepository extends MongoRepository<Evento, String> {

    Optional<Evento> findByNomeIgnoreCase(String nome);

    List<Evento> findByLuogo(String luogo);

    List<Evento> findByDataGreaterThanEqual(LocalDateTime data);

    List<Evento> findByDataBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Evento> findByPostiDisponibiliGreaterThan(int postiDisponibili);

    @Query("{ 'prezzo': { $lt: ?0 } }")
    List<Evento> findEventiConPrezzoInferioreA(double prezzo);

    @Query("{ 'nome': { $regex: ?0, $options: 'i' } }")
    List<Evento> findEventiByNomeContaining(String nome);

    Optional<Evento> findById(String id);
}
