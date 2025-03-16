package it.unimol.basi2.service;

import it.unimol.basi2.entity.Partecipante;
import it.unimol.basi2.repository.PartecipanteRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PartecipanteService {
    private static final Logger logger = LoggerFactory.getLogger(PartecipanteService.class);
    private final PartecipanteRepository partecipanteRepository;

    // CREATE: Inserisci un nuovo partecipante
    public Partecipante insertPartecipante(Partecipante partecipante) {
        // Verifica se esiste già un partecipante con la stessa email
        Optional<Partecipante> existingPartecipante = partecipanteRepository.findByEmail(partecipante.getEmail());
        if (existingPartecipante.isPresent()) {
            logger.warn("Partecipante con email '{}' già esistente", partecipante.getEmail());
            return null; // Oppure lancia un'eccezione personalizzata
        } else {
            logger.info("Inserimento partecipante: {}", partecipante.getEmail());
            return partecipanteRepository.save(partecipante);
        }
    }

    // READ: Ottieni un partecipante per ID
    public Optional<Partecipante> getPartecipanteById(String partecipanteId) {
        return partecipanteRepository.findById(partecipanteId);
    }

    // READ: Ottieni un partecipante per email
    public Optional<Partecipante> getPartecipanteByEmail(String email) {
        return partecipanteRepository.findByEmail(email);
    }

    // READ: Ottieni tutti i partecipanti con un determinato nome
    public Optional<List<Partecipante>> getPartecipantiByNome(String nome) {
        return partecipanteRepository.findByNome(nome);
    }

    // READ: Ottieni tutti i partecipanti
    public List<Partecipante> getAllPartecipanti() {
        return partecipanteRepository.findAll();
    }

    // UPDATE: Aggiorna un partecipante esistente
    public Optional<Partecipante> updatePartecipante(Partecipante partecipante) {
        Optional<Partecipante> existingPartecipante = partecipanteRepository.findById(partecipante.getId());
        if (existingPartecipante.isPresent()) {
            // Verifica che l'email non sia già associata a un altro partecipante
            Optional<Partecipante> partecipanteWithSameEmail = partecipanteRepository.findByEmail(partecipante.getEmail());
            if (partecipanteWithSameEmail.isPresent() && !partecipanteWithSameEmail.get().getId().equals(partecipante.getId())) {
                logger.warn("Email '{}' già associata a un altro partecipante", partecipante.getEmail());
                return Optional.empty();
            }
            logger.info("Aggiornamento partecipante con ID: {}", partecipante.getId());
            return Optional.of(partecipanteRepository.save(partecipante));
        } else {
            logger.warn("Partecipante con ID {} non trovato", partecipante.getId());
            return Optional.empty();
        }
    }

    // DELETE: Elimina un partecipante per ID
    public boolean deletePartecipanteById(String partecipanteId) {
        if (partecipanteRepository.existsById(partecipanteId)) {
            logger.info("Eliminazione partecipante con ID: {}", partecipanteId);
            partecipanteRepository.deleteById(partecipanteId);
            return true;
        } else {
            logger.warn("Partecipante con ID {} non trovato", partecipanteId);
            return false;
        }
    }
}
