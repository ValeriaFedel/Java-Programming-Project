import java.util.*;

public class GestoreNoteSegrete {

	private Nota[] listaNote = new Nota[0];
	private Codifica codifica;
	private GestoreStream gestore;
	private String password = null;
	private final String nomeFilePsw = "Master_password";

	public GestoreNoteSegrete(Codifica codifica, GestoreStream gestore) {
		this.codifica = codifica;
		this.gestore = gestore;
		if (gestore.esisteFile(nomeFilePsw)) {
			String psw = gestore.leggiFile(nomeFilePsw);
			password = codifica.decodifica(psw);
		}

	}

	public void impostaPassword(String psw) {
		this.password = psw;
		String passwordCodificata = codifica.codifica(psw);
		gestore.creaFile(nomeFilePsw, passwordCodificata);


	}

	public boolean passwordImpostata() {
		return password != null;
	}

	public Nota[] getNote() {
		return listaNote;
	}

	public void creaNuovaNota(String path) {
		String contenuto = gestore.leggiFile(path);
		Nota nuovaNota = CreatoreNota.creaNota(contenuto);
		String id = ""+nuovaNota.getId();


		Nota[] nuovaListaNote = new Nota[listaNote.length+1];
		for (int i=0; i<listaNote.length; i++) {
			nuovaListaNote[i] = listaNote[i];
		}
		nuovaListaNote[listaNote.length] = nuovaNota;

		listaNote = nuovaListaNote;

		gestore.importaContenuto(path, id);

	}

	public String getContenutoDecifrato(Nota nota) {
		String contenutoDecifrato;
		contenutoDecifrato = codifica.decodifica(nota.getContenuto()); 

		return contenutoDecifrato;
	}

	public boolean controllaPassword(String psw) {
		return password.equals(psw);
	} 

}