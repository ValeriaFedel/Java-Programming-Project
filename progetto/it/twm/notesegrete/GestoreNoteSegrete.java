package it.twm.notesegrete;

import it.twm.stream.GestoreStream;
import it.twm.codifica.Codifica;

import java.util.*;

/** La classe <code>GestoreNoteSegrete</code> gestisce gran parte delle 
  * operazioni necessarie al funzionamento del programma.
  * 
  * @see GestoreStream
  * @see Codifica
  * @author Silvia Florio, Valeria Fedel, Davide Mariuzzi
  */
public class GestoreNoteSegrete {
  
  /* ----------------------------- ATTRIBUTI ----------------------------- */
  /** Array di note di tipo <code>Nota</code>.
    */
  private Nota[] listaNote;
  /** Oggetto che servir&agrave; ad applicare le procedure di codifica e 
    * decodifica al contenuto della nota.
    */
  private Codifica codifica;
  /** Oggetto di tipo gestore che permette di operare con i metodi della
    * classe gestore.
    */
  private GestoreStream gestore;
  /** Variabile che conterr&agrave; la password.
    */
  private String password = null;
  /** Costante che specifica quale nome dovr&agrave; avere il file contenente la
    * master password.
    */
  private final String NOME_FILE_PSW = "Master_password.txt";
  
  
  /* ---------------------------- COSTRUTTORE ---------------------------- */
  /** Il costruttore di <code>GestoreNoteSegrete</code> inizializza <code>
    * codifica</code> e <code>gestore</code>, poi effettua un controllo
    * sull'esistenza del file contenente la master password e nel caso di
    * un riscontro positivo, la decodifica per fare in modo di confrontarla
    * successivamente con quella inserita dall'utente.
    *
    * @param codifica contiene le informazioni relative al tipo di codifica
    *        da utilizzare.
    * @param gestore permette di utilizzare i metodi di gestione del flusso
    *        dei dati.
    */
  public GestoreNoteSegrete(Codifica codifica, GestoreStream gestore) {
    this.codifica = codifica;
    this.gestore = gestore;

    String[] contenuti = gestore.leggiDirectory("nota");
    listaNote = new Nota[contenuti.length];

    for(int i=0; i<contenuti.length; i++) {
      listaNote[i] = CreatoreNota.creaNota(contenuti[i]);
    }

    if (gestore.esisteFile(NOME_FILE_PSW)) {
      String psw = gestore.leggiFile(NOME_FILE_PSW);
      password = codifica.decodifica(psw);
    }
  }
  
  
    /* ------------------------------ METODI ------------------------------- */
    /** Procedura che memorizza la master password inserita dall'utente, la 
      * codifica e crea un file che la contenga. 
      *
      * @param psw &egrave; la password inserita dall'utente.
      */
  public void impostaPassword(String psw) {
    this.password = psw;
    String passwordCodificata = codifica.codifica(psw);
	  gestore.creaFile(NOME_FILE_PSW, passwordCodificata);
  }
    
  /** Metodo che restituisce vero o falso a seconda che la password sia stata
    * impostata o meno.
    *
    * @return <code>true</code> se la master password è stata impostata, 
    * <code>false</code> altrimenti.
    */
  public boolean passwordImpostata() {
    return password != null;
  }
    
  /** Metodo che permette di ottenere le note memorizzate nell'array.
    *
    * @return <code>listaNote</code> ovvero tutte le note presenti nell'array.
    */
  public Nota[] getNote() {
    return listaNote;
  }
    
  /** Procedura che crea una nuova nota e la inserisce nell'array modificandone
    * dinamicamente le dimensioni.
    * 
    * @param path indica il percorso del file nota che deve essere
    *             inserita nell'array di note.
    */
  public void creaNuovaNota(String path) {
    String temp = "temp.json";
    gestore.importaContenuto(path, temp);
    String contenuto = gestore.leggiFile(temp);
    Nota nuovaNota = CreatoreNota.creaNota(contenuto);

    if(nuovaNota == null) {
      return;
    }

    if(nuovaNota.getId() == -1) {
      return;
    }

    String id = "" + nuovaNota.getId()+".nota";
    gestore.rinomina(temp, id);
    Nota[] nuovaListaNote = new Nota[listaNote.length+1];
    for (int i=0; i<listaNote.length; i++) {
      nuovaListaNote[i] = listaNote[i];
    }
    nuovaListaNote[listaNote.length] = nuovaNota;
    listaNote = nuovaListaNote;
  
  }
    
  /** Metodo che permette di ottenere il contenuto della nota dopo la 
    * decodifica.
    * 
    * @param nota è la nota che bisogna visualizzare dopo la decodifica.
    * @return una stringa con il contenuto della nota decifrato.
    */
  public String getContenutoDecifrato(Nota nota) {
    String contenutoDecifrato;
	contenutoDecifrato = codifica.decodifica(nota.getContenuto());
    return contenutoDecifrato;
  }
  
  /** Metodo che controlla la correttezza della master password inserita.
    *
    * @param psw &egrave; la password inserita dall'utente.
    * @return <code>true</code> se la password inserita corrisponde alla master
    *         password impostata, <code>false</code> altrimenti.
    */
  public boolean controllaPassword(String psw) {
    return password.equals(psw);
  } 
}