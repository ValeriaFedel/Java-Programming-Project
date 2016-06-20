package it.twm.notesegrete.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/** La classe <code>Data</code> provvede alla conversione del tempo espresso 
  * in millisecondi in una stringa contenente una data. <br>
  * Contiene un unico metodo statico.
  *
  * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/text/
  *      SimpleDateFormat.html#SimpleDateFormat()>Come modificare il formato</a>
  * @authors Silvia Florio, Valeria Fedel, Davide Mariuzzi
  */
public class DataUtility {
    
    /* ------------------------------ METODI ------------------------------- */
    /** Questo metodo converte un valore di tipo <code>Date</code> in una
      * stringa contenente la data nel formato voluto. <br> 
      * Modificando opportunamente la stringa <code>"dd/MM/yyyy"</code> si
      * ottiene facilmente una data in un altro formato.
      *
      * @param millisecondi variabile che contiene il risultato dell'istruzione
      *                     <code>System.currentTimeMillis()</code>
      * @return ritorna una stringa con la data.
      */
    public static String trasformaInData(Date dataJson) {
        SimpleDateFormat dataFormattata = new SimpleDateFormat("dd/MM/yyyy");    
        String dataInStringa = dataFormattata.format(dataJson);
        return dataInStringa;
    }
}