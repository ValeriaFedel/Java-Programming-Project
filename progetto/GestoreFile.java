

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/** La classe <code>GestoreFile</code> contiene i metodi necessari per leggere
  * e scrivere su file.
  * 
  * @see GestoreStream
  * @author Silvia Florio, Valeria Fedel, Davide Mariuzzi
  */
public class GestoreFile extends GestoreStream {

    /* ---------------------------- COSTRUTTORE ---------------------------- */
    /** Il costruttore di <code>GestoreFile</code> richiama quello della 
      * sopraclasse <code>GestoreStream</code>.
      *
      * @param destinazione contiene il percorso del file di destinazione.
      */
	public GestoreFile(String destinazione) {
		super(destinazione);
	}
  
  
    /* ------------------------------ METODI ------------------------------- */
    /** Procedura che estrae il contenuto di un file json e lo salva in un
      * nuovo file.
      *
      * @see GestoreStream
      * @param percorso indica il percorso del file sul filesystem.
      * @param nome     indica il nome del file da cui leggere il contenuto.
      * @throws IOException se occorre qualche errore in fase di scrittura.
      */
	public void importaContenuto(String percorso, String nome) {

		/* File nota = new File(destinazione + "" + nome + ".json");

		try {
			nota.createNewFile();
			FileWriter output = new FileWriter(nota);
            //scrivo il contenuto del file selezionato nel nuovo file
			output.write(string); 
			output.close();
		} catch(IOException e) {
			System.out.println("Errore in scrittura del file");
		}

		/* nuova implementazione */
		File originale = new File(percorso);
		File inCartella = new File(destinazione+""+nome);		
		try {
			Files.copy(originale.toPath(), inCartella.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			System.out.println("Errore durante la copia del file");
			return;
		} 


	}
}