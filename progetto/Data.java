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
public class Data {
    
    /* ------------------------------ METODI ------------------------------- */
    /** Questo metodo converte un valore di tipo <code>long</code> in una
      * stringa contenente la data nel formato voluto. <br> 
      * Modificando opportunamente la stringa <code>"dd/MM/yyyy"</code> si può
      * ottenere facilmente una data in un altro formato.
      *
      * @param millisecondi variabile che contiene il risultato dell'istruzione
      *                     <code>System.currentTimeMillis()</code>
      * @return ritorna una stringa con la data.
      */
    public static String trasformaInData(long millisecondi) {
        SimpleDateFormat dataFormattata = new SimpleDateFormat("dd/MM/yyyy");    
        Date dataStandard = new Date(millisecondi);
        String dataInStringa = dataFormattata.format(dataStandard);
        return dataInStringa;
    }
}