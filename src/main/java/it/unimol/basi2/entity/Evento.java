package it.unimol.basi2.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document
@Data
@AllArgsConstructor
public class Evento {
    @Id
    private String id;
    private String nome;
    private String descrizione;
    private LocalDateTime data;
    private String luogo;
    private int capacitaMassima;
    private double prezzo;
}



