package it.unimol.basi2.controller;
import it.unimol.basi2.entity.Evento;
import it.unimol.basi2.service.EventoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/eventi")
@AllArgsConstructor
public class EventoController {

    @Autowired      // questo cerca automaticamente un bean compatibile e lo inietta
    private EventoService eventoService;

    // CREATE: Inserisci un nuovo evento
    @PostMapping("create")
    public ResponseEntity<Evento> createEvento(@RequestBody Evento evento) {
        Evento nuovoEvento = eventoService.insertEvento(evento);
        return nuovoEvento != null ? ResponseEntity.ok(nuovoEvento) : ResponseEntity.badRequest().build();
    }

    // READ: Ottieni un evento per ID
    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable String id) {
        Optional<Evento> evento = eventoService.getEventoById(id);
        return evento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // READ: Ottieni un evento per nome (case-insensitive)
    @GetMapping("/nome/{nome}")
    public ResponseEntity<Evento> getEventoByNome(@PathVariable String nome) {
        Optional<Evento> evento = eventoService.getEventoByNome(nome);
        return evento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // READ: Ottieni tutti gli eventi
    @GetMapping("all")
    public ResponseEntity<List<Evento>> getAllEventi() {
        List<Evento> eventi = eventoService.getAllEventi();
        return ResponseEntity.ok(eventi);
    }

    // READ: Ottieni tutti gli eventi in un determinato luogo
    @GetMapping("/luogo/{luogo}")
    public ResponseEntity<List<Evento>> getEventiByLuogo(@PathVariable String luogo) {
        List<Evento> eventi = eventoService.getEventiByLuogo(luogo);
        return ResponseEntity.ok(eventi);
    }

    // READ: Ottieni tutti gli eventi futuri
    @GetMapping("/futuri")
    public ResponseEntity<List<Evento>> getEventiFuturi() {
        List<Evento> eventiFuturi = eventoService.getEventiFuturi();
        return eventiFuturi.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(eventiFuturi);
    }


    // READ: Ottieni tutti gli eventi con un prezzo inferiore a un certo valore
    @GetMapping("/prezzo-inferiore")
    public ResponseEntity<List<Evento>> getEventiConPrezzoInferioreA(@RequestParam double prezzo) {
        List<Evento> eventi = eventoService.getEventiConPrezzoInferioreA(prezzo);
        return eventi.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(eventi);
    }

    // READ: Ottieni tutti gli eventi il cui nome contiene una stringa (case-insensitive)
    @GetMapping("/cerca")
    public ResponseEntity<List<Evento>> getEventiByNomeContaining(@RequestParam String nome) {
        List<Evento> eventi = eventoService.getEventiByNomeContaining(nome);
        return eventi.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(eventi);
    }

    //READ: Ottineni titti gli eventi con posti disponibili

    @GetMapping("/eventi-disponibili")
    public ResponseEntity<List<Evento>> getEventiDisponibili() {
        List<Evento> eventi = eventoService.findEventiConPostiDisponibili();
        return eventi.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(eventi);
    }

    // UPDATE: Aggiorna un evento esistente
    @PutMapping("/{id}")
    public ResponseEntity<Evento> updateEvento(@PathVariable String id, @RequestBody Evento evento) {
        evento.setId(id); // Assicurati che l'ID sia impostato correttamente
        Optional<Evento> eventoAggiornato = eventoService.updateEvento(evento);
        return eventoAggiornato.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE: Elimina un evento per ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEventoById(@PathVariable String id) {
        boolean eliminato = eventoService.deleteEventoById(id);
        return eliminato ? ResponseEntity.ok("Evento eliminato") : ResponseEntity.notFound().build();
    }
    
}