package it.softwareinside.libreria.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.softwareinside.libreria.models.Libro;
import it.softwareinside.libreria.repository.LibroRepository;

/*
 * uso l'annotazione per far riconoscere la classe come Service
 */
@Service
public class LibroService {
    
    /*
     * uso l'annotazione autowired per non dover inizializzare l'elemento che creo
     * infatti sarà sufficiente scrivere il nome della classe dell'elemento e il nome che vogliamo dare alla variabile
     * senza usare "new" ed eventuali costruttori
     * 
     * sto creando un oggetto di tipo LibroRepository
     * per poter chiamare i metodi inseriti nell'interfaccia repository (legati al database)
     * all'interno dei metodi creerò
     */
    @Autowired
    LibroRepository libroR;

    /**
     * il metodo chiede in ingresso un oggetto di tipo Libro
     * e prova (try-catch) ad aggiungerlo al database tramite il metodo .save(Libro) che sfruttiamo grazie all'oggetto di tipo LibroRepository 
     * 
     * inizialmente viene fatto un controllo sul valore del parametro passato
     * se vale null viene stampato un messaggio che indica che il parametro non rispettava i criteri imposti
     * altrimenti viene inserito nel database
     * 
     * se si verificano altri errori viene stampato in console un messaggio che indica il tipo di errore,
     * mentre nel browser verrà visualizzato un messaggio sull'esito negativo
     * 
     * @param l
     * @return
     */
    public String add(Libro l){
        try {
            if(l == null)
                return "il libro inserito non rispetta i criteri";
            libroR.save(l);
            return "il libro è stato aggiunto al database";
        } catch (Exception e) {
            System.out.println("errore:" + e);
            return "non è stato possibile aggiungere il libro al database";
        }
    }
   
    /**
     * il metodo chiede in ingresso un id e restituisce una stringa
     * permette di cercare tramite l'id un determinato oggetto all'interno del database 
     * 
     * se l'elemento viene trovato verrò stampata la reference del libro, nel caso in cui non sia prensente verrà restituito 
     * altrimenti viene restituito un messaggio negativo
     * @param id
     * @return
     */
    public Libro find(Integer id){
        try {
            return libroR.findById(id).get();
        } catch (Exception e) {
            System.out.println("errore:" + e);
            return null;
        }
    }

    /**
     * il metodo permette di visualizzare tutti gli attributi di tutti gli elementi presenti nel database
     * e li restituisce sotto forma di Iterable
     * una sorta di ArrayList
     * @return
     */
    public Iterable<Libro> database(){
        try {
            return libroR.findAll();
            
        } catch (Exception e) {
            System.out.println("errore:" + e);
            return null;
        }
    }

    /**
     * il metodo permette di eliminare l'elemento di cui passiamo l'id dal database
     * nel caso in cui l'operazione sia andata a buon fine verrà stampato un messaggio affermativo
     * altrimenti in console verrà scritto l'errore e sul browser verrà stampato un messaggio negativo
     * 
     * @param id
     * @return
     */
    public String delete(Integer id){
        try {
            libroR.deleteById(id);
            return "il libro è stato rimosso dal database";
        } catch (Exception e) {
            System.out.println("errore:" + e);
            return "non è stato possibile rimuovere il libro dal database";
        }
    }

    /**
     * il metodo permette di eliminare tutti gli elementi presenti nella tabella
     * nel caso in cui l'operazione sia andata a buon fine verrà stampato un messaggio affermativo
     * altrimenti in console verrà scritto l'errore e sul browser verrà stampato un messaggio negativo
     * @return
     */
    public String deleteAll(){
        try {
            libroR.deleteAll();
            return "tutti gli elementi sono stati rimossi dal database";
        } catch (Exception e) {
            System.out.println("errore:" + e);
            return "non è stato possibile rimuovere tutti gli elementi dal database";
        }
    }

    /**
     * il metodo restituisce una stringa che rappresenta il nome del file dell'immagine di copertina del libro,
     * verrà invocato nel PdfService per poter stampare nel pdf l'immagine in modo dinamico
     * 
     * l'immagine ha bisogno del path del file, composto da una parte del percorso statico,
     * che ho inserito in una stringa all'interno del metodo nel Service  edi una parte dinamica,
     * che corrisponde all'attributo link dell'oggetto di tipo copertina, contenuto nell'oggetto di tipo Libro
     * @param idLibro
     * @return
     */
    public String findLinkCopertina(Integer idLibro){
        String link = libroR.findById(idLibro).get().getCopertina().getLink();
        return link;
        
    }
}


