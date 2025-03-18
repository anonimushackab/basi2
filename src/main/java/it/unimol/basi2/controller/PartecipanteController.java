package it.unimol.basi2.controller;

import it.unimol.basi2.entity.Partecipante;
import it.unimol.basi2.service.PartecipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/partecipanti")
public class PartecipanteController {

    @Autowired
    private PartecipanteService partecipanteService;

    // CREATE: Inserisci un nuovo partecipante
    @PostMapping("create")
    public ResponseEntity<Partecipante> createPartecipante(@RequestBody Partecipante partecipante) {
        Partecipante nuovoPartecipante = partecipanteService.insertPartecipante(partecipante);
        return nuovoPartecipante != null ? ResponseEntity.ok(nuovoPartecipante) : ResponseEntity.badRequest().build();
    }

    // READ: Ottieni un partecipante per ID
    @GetMapping("/{id}")
    public ResponseEntity<Partecipante> getPartecipanteById(@PathVariable String id) {
        Optional<Partecipante> partecipante = partecipanteService.getPartecipanteById(id);
        return partecipante.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // READ: Ottieni un partecipante per email
    @GetMapping("/email/{email}")
    public ResponseEntity<Partecipante> getPartecipanteByEmail(@PathVariable String email) {
        Optional<Partecipante> partecipante = partecipanteService.getPartecipanteByEmail(email);
        return partecipante.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // READ: Ottieni tutti i partecipanti con un determinato nome
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Partecipante>> getPartecipantiByNome(@PathVariable String nome) {
        Optional<List<Partecipante>> partecipanti = partecipanteService.getPartecipantiByNome(nome);
        return partecipanti.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    // READ: Ottieni tutti i partecipanti
    @GetMapping("all")
    public ResponseEntity<List<Partecipante>> getAllPartecipanti() {
        List<Partecipante> partecipanti = partecipanteService.getAllPartecipanti();
        return partecipanti.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(partecipanti);
    }

    // UPDATE: Aggiorna un partecipante esistente
    @PutMapping("/{id}")
    public ResponseEntity<Partecipante> updatePartecipante(@PathVariable String id, @RequestBody Partecipante partecipante) {
        partecipante.setId(id); // Assicurati che l'ID sia impostato correttamente
        Optional<Partecipante> partecipanteAggiornato = partecipanteService.updatePartecipante(partecipante);
        return partecipanteAggiornato.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE: Elimina un partecipante per ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePartecipanteById(@PathVariable String id) {
        boolean eliminato = partecipanteService.deletePartecipanteById(id);
        return eliminato ? ResponseEntity.ok("Partecipante eliminato") : ResponseEntity.notFound().build();
    }
}