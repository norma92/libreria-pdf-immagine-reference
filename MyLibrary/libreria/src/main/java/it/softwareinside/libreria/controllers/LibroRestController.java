package it.softwareinside.libreria.controllers;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.softwareinside.libreria.models.Libro;
import it.softwareinside.libreria.services.LibroService;
import it.softwareinside.libreria.services.PdfService;

@RestController
public class LibroRestController {
    
    @Autowired
    LibroService lS;

    @Autowired
    PdfService pdfS;

    @PostMapping("/add-l")
    public String addL(@RequestBody Libro l){
       return lS.add(l); 
    }

    @GetMapping("/find-l")
    public Libro findL(@RequestParam Integer id){
        return lS.find(id);
    }

    @GetMapping("/database")
    public Iterable<Libro> databaseL(){
        return lS.database();
    }

    @DeleteMapping("/delete-l")
    public String deleteL(@RequestParam Integer id){
        return lS.delete(id);
    }

    @DeleteMapping("/delete")
    public String deleteAll(){
        return lS.deleteAll();
    }

    @GetMapping("/find-link")
    public String findLink(@RequestParam Integer id){
        return lS.findLinkCopertina(id);
    }

    @RequestMapping(value = "/pdf", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generaNota(@RequestParam("id") int id) {
    	
    	try {

            ByteArrayInputStream bis =  pdfS.pdf(id) ; 

            var headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=example.pdf");
    
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(bis));
    		
    	}catch (Exception e) {
			return null;
    	}
    }
}
