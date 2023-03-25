package it.softwareinside.libreria.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;

import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;


import it.softwareinside.libreria.repository.LibroRepository;

@Service
public class PdfService {

    @Autowired
    LibroRepository lR;

    @Autowired
    LibroService lS;
    
    public  ByteArrayInputStream pdf(Integer id) throws MalformedURLException, IOException {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
          
        try {

            PdfWriter.getInstance(document, out);
            document.open();
            
            //percorso del font per titolo
            String papaya = "Employed.ttf";
            BaseFont titolo = BaseFont.createFont(papaya, BaseFont.WINANSI, BaseFont.EMBEDDED);
            Font fontT = new Font(titolo, 25);

            //creo un paragrafo che conterrà il titolo
            Paragraph testoTitolo = new Paragraph();
            //imposto il font per il paragrafo che contiene il titolo
            testoTitolo.setFont(fontT);
            //inserisco il testo nel paragrafo
            testoTitolo.add("Dettagli del libro");
            //inserisco il testo del paragrafo nel documento
            document.add(testoTitolo);

            //percordo del font per il corpo
            String simple = "SimpleHandmade.ttf";
            //imposto dei valori che servono per creare il font
            //ho modificato solo il primo parametro passando la stringa che contiene il percorso per il file .ttf
            BaseFont corpo = BaseFont.createFont(simple, BaseFont.WINANSI, BaseFont.EMBEDDED);
            //creo il font per il corpo
            Font fontC = new Font(corpo, 15);

            Paragraph testoCorpo = new Paragraph();
            testoCorpo.setFont(fontC);
            testoCorpo.add(lS.find(id).toString());
            //inserisco il testo del paragrafo nel documento
            document.add(testoCorpo);

            //creo una stringa che mi restituisce il path del file dell'immagine della copertina, che passo al metodo getInstance
            String link = "/Users/normasantella/Desktop/MyLibrary/Immagini/" + lS.findLinkCopertina(id);
            Image copertina = Image.getInstance(link);
            copertina.scalePercent(50, 50);
            //inserisco la copertina nel documento
            document.add(copertina);

            //chiudo il documento
            document.close();

        } catch (DocumentException ex) {

           return null;
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

}
