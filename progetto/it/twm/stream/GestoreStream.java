package it.twm.stream;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/** La classe <code>GestoreStream</code> contiene i metodi necessari per leggere
  * e scrivere su file.
  * 
  * @see GestoreFile
  * @author Silvia Florio, Valeria Fedel, Davide Mariuzzi
  */

public abstract class GestoreStream {

	/* ----------------------------- ATTRIBUTI ----------------------------- */
    /** Conterrà&agrave; una stringa con l'indicazione del percorso del file. */
	protected String destinazione;

    
	/* ---------------------------- COSTRUTTORE ---------------------------- */
    /** Il costruttore della classe fornisce il percorso di destinazione del
      * file da scrivere.
      *
      * @param destinazione percorso del file da scrivere.
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
      * 
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

	/** Metodo che restituisce i nomi dei file con l'estensione voluta.
      *
      * @param estensione indica l'estensione dei file da leggere nella directory.
      * @return ritorna una array di stringhe. Ogni posizione contiene il nome di
      *         di un file con l'estensione voluta.
      */
	public String[] leggiDirectory(String estensione) {
		File dir = new File(destinazione);
		String[] files = dir.list();
		String[] contenuto = new String[0];
        
		for (int i=0; i<files.length; i++) {
			if(ottieniEstensione(files[i]).equals("nota")) {						
				// @TODO: sostituire con lista
				String[] nuovoContenuto = new String[contenuto.length+1];
				for(int j=0; j<contenuto.length; j++) {
					nuovoContenuto[j] = contenuto[j];
				}
				nuovoContenuto[nuovoContenuto.length-1] = leggiFile(files[i]);
				contenuto = nuovoContenuto;
			}
		}
		return contenuto;
	}

	/** Metodo che restituisce l'estensione di un file.
      *
      * @param nomeFile indica il nome del file di cui si vuole sapere l'estensione.
      * @return una stringa con l'estensione del file oppure un messaggio che
      *         informa l'utente che non è stata trovata alcuna estensione.
      */
	protected static String ottieniEstensione(String nomeFile) {
		if(nomeFile.lastIndexOf(".") != -1 && nomeFile.lastIndexOf(".") != 0) {
			return nomeFile.substring(nomeFile.lastIndexOf(".")+1);
		}
		return "nessuna estensione trovata";
	}

	/** Metodo che legge il contenuto di un file e lo memorizza in una variabile di
      * tipo String.
      *
      * @param nome nome del file da leggere.
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
		
		// Memorizzo in un array di char il contenuto del file da leggere.
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

	/** Procedura che rinomina un file. 
      *
      * @param vecchioNome nome del file da rinominare.
      * @param nuovoNome nome del file dopo la rinomina.
      */
	public void rinomina(String vecchioNome, String nuovoNome) {
		File vecchioFile = new File(destinazione+""+vecchioNome);
		File nuovoFile = new File(destinazione+""+nuovoNome);
		try {
			Files.move(vecchioFile.toPath(), nuovoFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch(IOException e) {
			System.out.println("Errore nella rinomina");
			System.out.println(e);
			return;
		}
	}
 	
    /** Metodo vuoto che verr&agrave; implementato dalle sottoclassi.
      * 
      */
	public abstract void importaContenuto(String percorso, String nome);
}