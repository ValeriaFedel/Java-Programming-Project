import java.util.Date;

/** Classe contenente il metodo principale. </br>
  * Avvia i processi necessari al corretto funzionamento del programma.
  *
  * @author Silvia Florio, Valeria Fedel, Davide Mariuzzi 
  */
class NoteSegrete {
	public static void main(String[] args) {
		Codifica giulio = new GiulioCesare(6);
		GestoreStream gestore = new GestoreFile("note/");
		GestoreNoteSegrete programma = new GestoreNoteSegrete(giulio, gestore);
		TerminaleNoteSegrete terminal = new TerminaleNoteSegrete(programma);

		terminal.run();
	}
}	