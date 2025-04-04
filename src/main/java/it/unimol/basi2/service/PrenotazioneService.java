package it.unimol.basi2.service;

import it.unimol.basi2.entity.Evento;
import it.unimol.basi2.entity.Prenotazione;
import it.unimol.basi2.repository.EventoRepository;
import it.unimol.basi2.repository.PrenotazioneRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PrenotazioneService {
    private static final Logger logger = LoggerFactory.getLogger(PrenotazioneService.class);

    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    // CREATE: Inserisci una nuova prenotazione
    @Transactional
    public Prenotazione insertPrenotazione(Prenotazione prenotazione) {
        // Logica per controllare la disponibilità dei posti
        Evento evento = eventoRepository.findById(prenotazione.getEventoId())
                .orElseThrow(() -> new RuntimeException("Evento non trovato"));

        if (evento.getCapacitaMassima() - evento.getPostiDisponibili() >= prenotazione.getNumeroBiglietti()) {
            throw new RuntimeException("Posti non disponibili");
        }

        evento.setPostiDisponibili(evento.getPostiDisponibili() - prenotazione.getNumeroBiglietti());

        eventoRepository.save(evento);
        // Inserisci la prenotazione
        return prenotazioneRepository.save(prenotazione);
    }

    // READ: Ottieni una prenotazione per ID
    public Optional<Prenotazione> getPrenotazioneById(String prenotazioneId) {
        return prenotazioneRepository.findById(prenotazioneId);
    }


    // READ: Ottieni tutte le prenotazioni per data
    public List<Prenotazione> getPrenotazioniByDataPrenotazione(LocalDateTime dataPrenotazione) {
        return prenotazioneRepository.findByDataPrenotazione(dataPrenotazione);
    }

    // READ: Ottieni tutte le prenotazioni
    public List<Prenotazione> getAllPrenotazioni() {
        return prenotazioneRepository.findAll();
    }

    // UPDATE: Aggiorna una prenotazione esistente
    public Optional<Prenotazione> updatePrenotazione(Prenotazione prenotazione) {
        Optional<Prenotazione> existingPrenotazione = prenotazioneRepository.findById(prenotazione.getEventoId());
        if (existingPrenotazione.isPresent()) {
            logger.info("Aggiornamento prenotazione con ID: {}", prenotazione.getEventoId());
            return Optional.of(prenotazioneRepository.save(prenotazione));
        } else {
            logger.warn("Prenotazione con ID {} non trovata", prenotazione.getEventoId());
            return Optional.empty();
        }
    }

    // DELETE: Elimina una prenotazione per eventoId
    public boolean deletePrenotazioneByEventoId(String eventoId) {
        if (prenotazioneRepository.existsById(eventoId)) {
            logger.info("Eliminazione prenotazione con ID: {}", eventoId);
            prenotazioneRepository.deleteByEventoId(eventoId);
            return true;
        } else {
            logger.warn("Prenotazione con ID {} non trovata", eventoId);
            return false;
        }
    }


}
