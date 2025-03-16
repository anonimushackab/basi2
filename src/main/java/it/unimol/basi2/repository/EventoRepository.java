package it.unimol.basi2.repository;

import it.unimol.basi2.entity.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventoRepository extends MongoRepository<Evento, String> {
    Optional<Evento> findByNome(String nome);

    
    Optional<List<Evento>> findByLuogo(String luogo);


}
