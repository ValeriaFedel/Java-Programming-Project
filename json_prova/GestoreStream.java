import java.io.*;

abstract class GestoreStream {
	
	String destinazione;

	public void setDestinazione(String destinazione) {
		this.destinazione = destinazione;
	}

	public 	String leggiFile(String nome) {
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

	public void importaContenuto(String percorso) {}
}