package it.twm.notesegrete.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/** La classe <code>Data</code> provvede alla conversione del tempo espresso 
  * in millisecondi in una stringa contenente una data. <br>
  * Contiene un unico metodo statico.
  *
  * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html#SimpleDateFormat().html">
  * Cliccami per spare come modificare il formato della data.</a>
  * @author Silvia Florio, Valeria Fedel, Davide Mariuzzi
  */
public class DataUtility {
    
    /* ------------------------------ METODI ------------------------------- */
    /** Questo metodo converte un valore di tipo <code>Date</code> in una
      * stringa contenente la data nel formato voluto. <br> 
      * Modificando opportunamente la stringa <code>"dd/MM/yyyy"</code> si
      * ottiene facilmente una data in un altro formato.
      *
      * @param data data formattata in formato standard.
      * @return ritorna una stringa con la data.
      */
    public static String trasformaInData(Date data) {
        SimpleDateFormat dataFormattata = new SimpleDateFormat("dd/MM/yyyy");    
        String dataInStringa = dataFormattata.format(data);
        return dataInStringa;
    }
}