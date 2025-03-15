package it.unimol.basi2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor

public class Partecipante {
    @Id
    private String id;
    private String nome;
    private String cognome;
    private String telefono;
    
    @Indexed(unique = true)
    private String email;
}
