import java.io.*;

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

    public String leggiContenuto(String nome) {
		FileInputStream f;
		String s = "";
      
		try {
			f = new FileInputStream(nome);
		} catch (FileNotFoundException e) {
			System.out.println("Errore in apertura del file");
			return "Errore";
		}

		File file = new File(nome);
		long length = file.length();
		char[] array = new char[(int)length]; 
		
		// Memorizzo in un array di char il contenuto del file da leggere.
		try {
			array[0] = (char)f.read();
			for(int i=1; i<array.length; i++) {
				array[i] = (char)f.read();
			}
		} catch(IOException e) {
			System.out.println("Errore in lettura del file");
		}
      
        // Creo una stringa dall'array
		s = new String(array); 

		try {
			f.close();
		} catch (IOException e) {
			System.out.println("Errore in chiusura del file");
		}
		return s;
	}

	public void importaContenuto(String percorso, String nome) {
		String string = leggiFile(percorso); 

		File nota = new File(destinazione + "" + nome + ".json");

		try {
			nota.createNewFile();
			FileWriter output = new FileWriter(nota);
            //scrivo il contenuto del file selezionato nel nuovo file
			output.write(string); 
			output.close();
		} catch(IOException e) {
			System.out.println("Errore in scrittura del file");
		}
	}
}