package it.unimol.basi2.repository;
import it.unimol.basi2.entity.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartecipanteRepository extends MongoRepository<Partecipante, String> {
    Optional<Partecipante> findByEmail(String email);

    Optional<Partecipante> findById(String id);

    Optional<List<Partecipante>> findByNome(String nome);

    Partecipante save(Partecipante partecipante);

    boolean existsById(String partecipanteId);

    void deleteById(String partecipanteId);
}
