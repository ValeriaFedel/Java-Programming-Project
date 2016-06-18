import java.util.Date;

class NoteSegrete {
	public static void main(String[] args) {
		Codifica giulio = new GiulioCesare(6);
		GestoreStream gestore = new GestoreFile("./");
		GestoreNoteSegrete programma = new GestoreNoteSegrete(giulio, gestore);
		TerminaleNoteSegrete terminal = new TerminaleNoteSegrete(programma);

		terminal.run();
	}
}	