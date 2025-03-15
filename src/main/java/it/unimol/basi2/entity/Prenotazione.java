package it.unimol.basi2.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor

public class Prenotazione {
    @Id
    private String id;
    private String eventoId; // Riferimento all'evento prenotato
    private String partecipanteId; // Riferimento al partecipante
    private LocalDateTime dataPrenotazione;
    private int numeroBiglietti;
}
