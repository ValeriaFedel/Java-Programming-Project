import java.io.*;

public class GestoreFile {

	public void copia(String s)	 {
		File f = new File(s);
		System.out.println(f);
		char[] content = new char[(int)f.length()];
		try {
		    FileReader reader = new FileReader(f);
			reader.read(content); //leggo il contenuto del primo file e le metto nell'array
			reader.close(); //chiudo il flusso
			} catch(IOException e) { //se incontro un problema di I/O
			System.out.println("Errore in lettura del file");
			return;
		}
		File nuovo = new File("destinazione.txt");
		try {
			nuovo.createNewFile();
		} catch (IOException e) {
			System.out.println(e);
			return;
		}

		try {
			FileWriter writer = new FileWriter(nuovo);
			writer.write(content); //scrivo la stringa invertita e maiuscola nel nuovo file
			writer.close(); //chiudo il flusso
		} catch(IOException e) { //se incontro un problema di I/O
		System.out.println("Errore in scrittura del file");
		}

	}
}