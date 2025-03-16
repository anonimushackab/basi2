package it.unimol.basi2.service;

import it.unimol.basi2.entity.Prenotazione;
import it.unimol.basi2.repository.PrenotazioneRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PrenotazioneService {
    private static final Logger logger = LoggerFactory.getLogger(PrenotazioneService.class);
    private final PrenotazioneRepository prenotazioneRepository;

    // CREATE: Inserisci una nuova prenotazione
    public Prenotazione insertPrenotazione(Prenotazione prenotazione) {
        logger.info("Inserimento prenotazione per il cliente: {}", prenotazione.getNomeCliente());
        return prenotazioneRepository.save(prenotazione);
    }

    // READ: Ottieni una prenotazione per ID
    public Optional<Prenotazione> getPrenotazioneById(String prenotazioneId) {
        return prenotazioneRepository.findById(prenotazioneId);
    }

    // READ: Ottieni tutte le prenotazioni per nome cliente
    public Optional<List<Prenotazione>> getPrenotazioniByNomeCliente(String nomeCliente) {
        return prenotazioneRepository.findByNomeCliente(nomeCliente);
    }

    // READ: Ottieni tutte le prenotazioni per data
    public Optional<List<Prenotazione>> getPrenotazioniByDataPrenotazione(LocalDateTime dataPrenotazione) {
        return prenotazioneRepository.findByDataPrenotazione(dataPrenotazione);
    }

    // READ: Ottieni tutte le prenotazioni
    public List<Prenotazione> getAllPrenotazioni() {
        return prenotazioneRepository.findAll();
    }

    // UPDATE: Aggiorna una prenotazione esistente
    public Optional<Prenotazione> updatePrenotazione(Prenotazione prenotazione) {
        Optional<Prenotazione> existingPrenotazione = prenotazioneRepository.findById(prenotazione.getId());
        if (existingPrenotazione.isPresent()) {
            logger.info("Aggiornamento prenotazione con ID: {}", prenotazione.getId());
            return Optional.of(prenotazioneRepository.save(prenotazione));
        } else {
            logger.warn("Prenotazione con ID {} non trovata", prenotazione.getId());
            return Optional.empty();
        }
    }

    // DELETE: Elimina una prenotazione per ID
    public boolean deletePrenotazioneById(String prenotazioneId) {
        if (prenotazioneRepository.existsById(prenotazioneId)) {
            logger.info("Eliminazione prenotazione con ID: {}", prenotazioneId);
            prenotazioneRepository.deleteById(prenotazioneId);
            return true;
        } else {
            logger.warn("Prenotazione con ID {} non trovata", prenotazioneId);
            return false;
        }
    }
}
