package it.unimol.basi2.controller;
import it.unimol.basi2.entity.Prenotazione;
import it.unimol.basi2.service.PrenotazioneService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/prenotazioni")
@AllArgsConstructor
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    // CREATE: Inserisci una nuova prenotazione
    @PostMapping("create")
    public ResponseEntity<Prenotazione> createPrenotazione(@RequestBody Prenotazione prenotazione) {
        Prenotazione nuovaPrenotazione = prenotazioneService.insertPrenotazione(prenotazione);
        return ResponseEntity.ok(nuovaPrenotazione);
    }

    // READ: Ottieni una prenotazione per ID
    @GetMapping("/{id}")
    public ResponseEntity<Prenotazione> getPrenotazioneById(@PathVariable String id) {
        Optional<Prenotazione> prenotazione = prenotazioneService.getPrenotazioneById(id);
        return prenotazione.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    // READ: Ottieni tutte le prenotazioni per data
    @GetMapping("/data")
    public ResponseEntity<List<Prenotazione>> getPrenotazioniByDataPrenotazione(@RequestParam LocalDateTime dataPrenotazione) {
        List<Prenotazione> prenotazioni = prenotazioneService.getPrenotazioniByDataPrenotazione(dataPrenotazione);

        if (prenotazioni.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(prenotazioni);
        }
    }

    // READ: Ottieni tutte le prenotazioni
    @GetMapping("all")
    public ResponseEntity<List<Prenotazione>> getAllPrenotazioni() {
        List<Prenotazione> prenotazioni = prenotazioneService.getAllPrenotazioni();
        return prenotazioni.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(prenotazioni);
    }

    // UPDATE: Aggiorna una prenotazione esistente
    @PutMapping("/{id}")
    public ResponseEntity<Prenotazione> updatePrenotazione(@PathVariable String eventoId, @RequestBody Prenotazione prenotazione) {
        prenotazione.setEventoId(eventoId); // Assicurati che l'ID sia impostato correttamente
        Optional<Prenotazione> prenotazioneAggiornata = prenotazioneService.updatePrenotazione(prenotazione);
        return prenotazioneAggiornata.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE: Elimina una prenotazione per ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePrenotazioneById(@PathVariable String id) {
        boolean eliminato = prenotazioneService.deletePrenotazioneById(id);
        return eliminato ? ResponseEntity.ok("Prenotazione eliminata") : ResponseEntity.notFound().build();
    }
}
