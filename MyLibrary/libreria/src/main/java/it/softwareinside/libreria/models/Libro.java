package it.softwareinside.libreria.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * uso lombok per generare i getters, i setters
 * e il costruttore di default
 * 
 * !!!
 * -- NB se il costruttore di default viene omessosi creano problemi nel service
 */
@Getter
@Setter
@NoArgsConstructor

/*
 * uso l'annotation per indicare che questa classe genera una tabella all'interno del database
 * userò h2 per visualizzarla
 */
@Entity
public class Libro {
    
    /*
     * uso la prima annotazione per indicare che l'attributo che segue è una primary_key
     * cioè il codice univoco che identifica ciascun elemento inserito nella tabella del database
     * 
     * la seconda serve per fare in modo che l'id dell'elemento venga generato automaticamente
     * senza che noi dobbiamo inserirlo quando aggiungiamo un nuovo elemento
     * IDENTITY fa sì che l'id abbia l'incremento pari a 1 per ciascun nuovo elemento
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /*
     * dichiaro i vari attributi della classe
     * 
     * !!!
     * -- NB i valori booleani devono essere di tipo Boolean,
     * altrimenti apparirà sempre il valore false a prescindere da quello assegnato al momento della creazione
     */
    private String autore, titolo, genere, casaEditrice;
    private Boolean isLetto, isAcquistato, isPreferiti;

    /*
     * gli oggetti istanziati dalla classe Libro hanno anche un attributo che è un oggetto di tipo Copertina
     * del quale non vedrò gli attributi all'interno della tabella Libro,
     * ma avrò una colonna chiamata copertina_id che riporterà il valore dell'id dell'oggetto copertina
     * associato al dato libro
     * 
     * la prima annotazione definisce il tipo di relazione che intercorre tra la classe Libro e la classe Copertina
     * cascade indica che le azioni compiute sull'elemento libro (esempio l'eliminazione)
     * ricadrà anche sull'elemento copertina a lui associato
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "copertina_id")
    private Copertina copertina;

    /**
     * devo definire il costruttore parzialmente parametrizzato poichè non posso usare quello completamente parametrizzato
     * dato che mi troverei a dover inserire anche il valore dell'id,
     * ma come ho chiesto a riga 35, voglio che venga autogenerato per evitare eventuali ambiguità ed errori
     */
    public Libro(   String autore, String titolo, String genere, String casaEditrice,
                    Boolean isLetto, Boolean isAcquistato, Boolean isPrefetiri, Copertina copertina){
        setAutore(autore);
        setTitolo(titolo);
        setGenere(genere);
        setCasaEditrice(casaEditrice);
        setIsLetto(isLetto);
        setIsAcquistato(isAcquistato);
        setIsPreferiti(isPrefetiri);
        setCopertina(copertina);
    }

    /**
     * questo metodo restituisce una stringa che "decodifica" il valore booleano passato come parametro
     * per rendere più scorrevole la lettura nel caso di un return di tipo String
     * come avverrà nell'override del toString()
     */
    public String stringaBooleano(boolean valore){
        if(valore == true)
         return "sì";
        return "no";

    }

    /**
     * faccio l'override del toString() per rendere più ordinata la stampa della reference dell'oggetto di tipo libro
     * inserita nel pdf che verrà creato nel browser richiamando il metodo preposto
     */
    @Override
    public String toString(){
        return  "autore: "          +   this.autore                         +   "\n"    +
                "titolo: "          +   this.titolo                         +   "\n"    +
                "genere: "          +   this.genere                         +   "\n"    +
                "casa editrice: "   +   this.casaEditrice                   +   "\n"    +
                "letto: "           +   stringaBooleano(this.isLetto)       +   "\n"    +
                "acquistato: "      +   stringaBooleano(this.isAcquistato)  +   "\n"    +
                "tra i preferiti: " +   stringaBooleano(this.isPreferiti)   ;
                

    }
}
