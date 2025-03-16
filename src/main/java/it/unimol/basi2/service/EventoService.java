package it.unimol.basi2.service;

import it.unimol.basi2.entity.Evento;
import it.unimol.basi2.repository.EventoRepository;
import lombok.AllArgsConstructor;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventoService {
    private static final Logger logger = LoggerFactory.getLogger(EventoService.class);
    private final EventoRepository eventoRepository;

    // CREATE: Inserisci un nuovo evento
    public Evento insertEvento(Evento evento) {
        // Verifica se esiste già un evento con lo stesso nome (case-insensitive)
        Optional<Evento> existingEvento = eventoRepository.findByNomeIgnoreCase(evento.getNome());
        if (existingEvento.isPresent()) {
            logger.warn("Evento con nome '{}' già esistente", evento.getNome());
            return null; // Oppure lancia un'eccezione personalizzata
        } else {
            logger.info("Inserimento evento: {}", evento.getNome());
            return eventoRepository.save(evento);
        }
    }

    // READ: Ottieni un evento per ID
    public Optional<Evento> getEventoById(String eventoId) {
        return eventoRepository.findById(eventoId);
    }

    // READ: Ottieni un evento per nome (case-insensitive)
    public Optional<Evento> getEventoByNome(String nome) {
        return eventoRepository.findByNomeIgnoreCase(nome);
    }

    // READ: Ottieni tutti gli eventi
    public List<Evento> getAllEventi() {
        return eventoRepository.findAll();
    }

    // READ: Ottieni tutti gli eventi in un determinato luogo
    public List<Evento> getEventiByLuogo(String luogo) {
        return eventoRepository.findByLuogo(luogo);
    }

    // READ: Ottieni tutti gli eventi futuri
    public List<Evento> getEventiFuturi() {
        LocalDateTime now = LocalDateTime.now();
        return eventoRepository.findByDataGreaterThanEqual(now);
    }

    // READ: Ottieni tutti gli eventi con posti disponibili maggiori di un certo numero
    public List<Evento> getEventiConPostiDisponibili(int postiDisponibili) {
        return eventoRepository.findByPostiDisponibiliGreaterThan(postiDisponibili);
    }

    // READ: Ottieni tutti gli eventi con un prezzo inferiore a un certo valore
    public List<Evento> getEventiConPrezzoInferioreA(double prezzo) {
        return eventoRepository.findEventiConPrezzoInferioreA(prezzo);
    }

    // READ: Ottieni tutti gli eventi il cui nome contiene una stringa (case-insensitive)
    public List<Evento> getEventiByNomeContaining(String nome) {
        return eventoRepository.findEventiByNomeContaining(nome);
    }

    // UPDATE: Aggiorna un evento esistente
    public Optional<Evento> updateEvento(Evento evento) {
        Optional<Evento> existingEvento = eventoRepository.findById(evento.getId());
        if (existingEvento.isPresent()) {
            // Verifica che il nome non sia già associato a un altro evento (case-insensitive)
            Optional<Evento> eventoWithSameNome = eventoRepository.findByNomeIgnoreCase(evento.getNome());
            if (eventoWithSameNome.isPresent() && !eventoWithSameNome.get().getId().equals(evento.getId())) {
                logger.warn("Nome '{}' già associato a un altro evento", evento.getNome());
                return Optional.empty();
            }
            logger.info("Aggiornamento evento con ID: {}", evento.getId());
            return Optional.of(eventoRepository.save(evento));
        } else {
            logger.warn("Evento con ID {} non trovato", evento.getId());
            return Optional.empty();
        }
    }

    // DELETE: Elimina un evento per ID
    public boolean deleteEventoById(String eventoId) {
        if (eventoRepository.existsById(eventoId)) {
            logger.info("Eliminazione evento con ID: {}", eventoId);
            eventoRepository.deleteById(eventoId);
            return true;
        } else {
            logger.warn("Evento con ID {} non trovato", eventoId);
            return false;
        }
    }
}