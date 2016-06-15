import java.io.*;

class GestoreFile extends GestoreStream {

	public GestoreFile(String destinazione) {
		this.destinazione = destinazione;
	}

	public void importaContenuto(String percorso) {

		int counter = 0; //serve per il nome del file in cui salvare la copia

		String string = leggiFile(percorso); 

		File nota = new File("_0");
		
		while(nota.exists()) { //finché i numeri per il nome del file sono già stati presi
			counter++;
			String id = "_"+counter;
			nota = new File(id);
		}

		try {
			nota.createNewFile();
			FileWriter output = new FileWriter(nota);
			output.write(string); //scrivo il contenuto del file selezionato nel nuovo file
			output.close();
		} catch(IOException e) {
			System.out.println("Errore in scrittura del file");
		}

	}

}