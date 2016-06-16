import java.io.*;

public class GestoreFile extends GestoreStream {

	public GestoreFile(String destinazione) {
		this.destinazione = destinazione;
	}

	public void importaContenuto(String percorso, String nome) {


		String string = leggiFile(percorso); 

		File nota = new File(nome);

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