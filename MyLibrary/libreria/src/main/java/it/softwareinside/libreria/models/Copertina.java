package it.softwareinside.libreria.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * uso lombok per generare setters, i getters, l'override del toString()
 * e il costruttore di default
 */
@Data
@NoArgsConstructor

/*
 * uso l'annotazione entity per indicare che questa classe verrà rappresentata da una tabella 
 * all'interno del database
 */
@Entity
public class Copertina {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    private String link;
    private Boolean isColored, isRigida;

    
    public Copertina(String link, Boolean isColored, Boolean isRigida){
        setLink(link);
        setIsColored(isColored);
        setIsRigida(isRigida);
    }

}