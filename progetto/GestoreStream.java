import java.io.*;

abstract class GestoreStream {
	
	String destinazione;

	public GestoreStream(String destinazione) {
		this.destinazione = destinazione;
	}

	public void setDestinazione(String destinazione) {
		this.destinazione = destinazione;
	}

	public boolean esisteFile(String nome) {
		File f = new File(destinazione+""+nome);
		return f.exists();
	}

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