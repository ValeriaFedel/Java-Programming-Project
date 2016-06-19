import java.io.*;

/** La classe <code>GestoreStream</code> contiene i metodi necessari per leggere
  * e scrivere su file.
  * 
  * @see GestoreFile
  * @author Silvia Florio, Valeria Fedel, Davide Mariuzzi
  */

abstract class GestoreStream {

	/* ----------------------------- ATTRIBUTI ----------------------------- */
    /** Conterrà una stringa con l'indicazione del percorso del file. */
	protected String destinazione;

	/* ---------------------------- COSTRUTTORE ---------------------------- */
    /** Il costruttore della classe fornisce il percorso di destinazione del
      * file da scrivere.
      *
      * @param destinazione percorso del file da scrivere.
      *
      */
	public GestoreStream(String destinazione) {
		this.destinazione = destinazione;
	}

	 /* ------------------------------ METODI ------------------------------- */
    /** Procedura che assegna alla variabile destinazione una stringa contenente
      * il percorso di destinazione del file.
      *
      * @param destinazione indica il percorso di destinazione del file da
      *                     scrivere.
      */
	public void setDestinazione(String destinazione) {
		this.destinazione = destinazione;
	}

	/** Verifica se esiste o meno un file.
      *
      * @return <code>true</code> se esiste o <code>false</code> non esiste
      *         il file con il nome specificato.
      * @param nome indica il nome del file per controllarne l'esistenza.
      */	
	public boolean esisteFile(String nome) {
		File f = new File(destinazione+""+nome);
		return f.exists();
	}

	 /** Procedura che crea il file di destinazione in un determinato percorso.
      * 
      * @param nome       nome del nuovo file.
      * @param contenuto  stringa con il contenuto da scrivere nel file.
      * @throws IOException se occorre qualche errore in fase di scrittura oppure
      *                     in fase di creazione del file.
      */
	public void creaFile(String nome, String contenuto) {
		File f = new File(destinazione+""+nome);
		if(f.exists()) {
			System.out.println("Un file con quel nome esiste già");
			return;
		}

		FileWriter output;

		try {
			output = new FileWriter(f);
			output.write(contenuto);
			output.close();
		} catch (IOException e) {
			System.out.println("errore in scrittura");
			return;
		}

		try {
			f.createNewFile();
		} catch (IOException e) {
			System.out.println("errore nella creazione del file");
			return;
		}
		
		return;

	}

	/* nuovo metodo */
	
	public String[] leggiDirectory() {
		File dir = new File(destinazione);
		String[] files = dir.list();
		String[] contenuto = new String[files.length];

		for (int i=0; i<files.length; i++) {
			contenuto[i] = leggiFile(file);
		}
	}

	/** Metodo che legge il contenuto di un file e lo memorizza in una variabile di
      * tipo String.
      *
      * @param nome nome del file da leggere.
      * @throws FileNotFounfexception se occorre qualche errore in fase di apertura
      *         del file.
      * @throws IOException se occorre qualche errore in fase di lettura del file
      *                     o chiusura del file.
      * @return ritorna una stringa con il contenuto letto dal file.
      */
	public 	String leggiFile(String nome) {
		FileInputStream f;
		String s = "";	
		try {
			f = new FileInputStream(destinazione+""+nome);
		} catch (FileNotFoundException e) {
			System.out.println("Errore in apertura del file");
			return "Errore";
		}

		File file = new File(destinazione+""+nome);
		long length = file.length();
		char[] array = new char[(int)length]; 
		
		/* 
		  di seguito memorizzo in un array di char il contenuto
		  del file da leggere 
		*/
		try {
			array[0] = (char)f.read();
			for(int i=1; i<array.length; i++) {
				array[i] = (char)f.read();
			}
		} catch(IOException e) {
			System.out.println("Errore in lettura del file");
		}

		s = new String(array); //creo una stringa dall'array

		try {
			f.close();
		} catch (IOException e) {
			System.out.println("Errore in chiusura del file");
		}
		return s;
	}

	/*nuovo metodo*/
	public void rename(String vecchioNome, String nuovoNome) {
		File file = new File(vecchioNome);
		File nuovo = new File(nuovoNome);
		System.out.println(file.renameTo(nuovo));
	}

 	
	public void importaContenuto(String percorso, String nome) {}
}