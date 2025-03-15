package it.unimol.basi2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;


@Data
@Document
@AllArgsConstructor

public class Evento {
    @Id
    private String id;
    private String nome;
    private String descrizione;
    private LocalDateTime data;
    private String luogo;
    private int capacitaMassima;
    private int postiDisponibili;
    private double prezzo;
}
