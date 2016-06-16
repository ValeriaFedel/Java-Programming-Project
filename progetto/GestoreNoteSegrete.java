import java.util.*;

public class GestoreNoteSegrete {

	private Nota[] listaNote = new Nota[0];
	private Codifica codifica;
	private GestoreStream gestore;
	private String password = null;

	public GestoreNoteSegrete(Codifica codifica, GestoreStream gestore) {
		this.codifica = codifica;
		this.gestore = gestore;
	}

	public void impostaPassword(String psw) {
		this.password = psw;
	}

	public boolean passwordImpostata() {
		return password != null;
	}

	public Nota[] getNote() {
		return listaNote;
	}

	public void creaNuovaNota(String path) {
		String contenuto = gestore.leggiFile(path);
		Nota nuovaNota = CreatoreNote.creaNota(contenuto);
		String id = ""+nuovaNota.getId();

		listaNote = new Nota[listaNote.length+1];
		listaNote[listaNote.length-1] = nuovaNota;

		gestore.importaContenuto(path, id);

	}

	public String getContenutoDecifrato(NotaTesto nota) {
		String contenutoDecifrato;
		contenutoDecifrato = codifica.decodifica(nota.getTesto()); 

		return contenutoDecifrato;
	}

	public boolean controllaPasword(String psw) {
		return psw == password;
	} 

}