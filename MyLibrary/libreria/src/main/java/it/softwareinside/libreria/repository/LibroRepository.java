package it.softwareinside.libreria.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.softwareinside.libreria.models.Libro;


/*
 * uso l'annotazione repository per far identificare questa interfaccia come tale
 * 
 * INTERFACCIA:
 * l'interfaccia contiene esclusivamente dei metodi, senza che abbiano definizione dei loro comportamenti all'interno
 * viene indicata la visibilità, il tipo dell'eventuale ritorno e la firma del metodo
 * 
 * FIRMA DEL METODO:
 * nomeDelMetodo(TipoParametro nomeParametro,...)
 * 
 * le classi che implementeranno l'interfaccia dovranno importare e "definire" i comportamenti di ciascuno
 * una classe può implementare più di un'interfaccia
 * 
 * all'interno delle parentesi della classe CrudRepository dobbiamo passare rispettivamente
 * il nome della classe di cui stiamo creando la repository e il tipo dell'id (non primitivo)
 */
@Repository
public interface LibroRepository extends CrudRepository<Libro,Integer>{
    
}
