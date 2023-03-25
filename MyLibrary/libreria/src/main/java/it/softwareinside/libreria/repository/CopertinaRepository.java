package it.softwareinside.libreria.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.softwareinside.libreria.models.Copertina;

@Repository
public interface CopertinaRepository extends CrudRepository<Copertina,Integer>{
    
}
