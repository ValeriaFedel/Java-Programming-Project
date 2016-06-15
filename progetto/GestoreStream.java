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
		int[] array = new int[(int)length]; 
		
		try {
			array[0] = f.read();
			for(int i=1; i<array.length; i++) {
				array[i] = f.read();
			}
		} catch(IOException e) {
			System.out.println("Errore in lettura del file");
		}

		for (int i=0; i<array.length; i++) {
			s+=(char)array[i];
		}

		try {
			f.close();
		} catch (IOException e) {
			System.out.println("Errore in chiusura del file");
		}
		return s;
	}

	public void importaContenuto(String percorso) {}
}