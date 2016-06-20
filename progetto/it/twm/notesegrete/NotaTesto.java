package it.twm.notesegrete;

import java.util.*;

/** Classe che estende la classe Nota. </br>
  * Rappresenta una nota contenente del testo semplice.
  *
  * @see Nota
  * @author Silvia Florio, Valeria Fedel, Davide Mariuzzi
  */
public class NotaTesto extends Nota {
  
  /* ----------------------------- ATTRIBUTI ----------------------------- */
  /** Variabile che conterr&agrave; il testo letto dal file. */
  private String testo;
  
  
  /* ---------------------------- COSTRUTTORE ---------------------------- */
  /** Crea un oggetto di tipo NotaAccount contenente le informazioni id, data,
    * username e password.
    *
    * @param id        numero identificativo della nota.
    * @param data      la data di creazione o ultima modifica.
    * @param testo     testo contenuto nella nota.
    */
  public NotaTesto (int id, Date data, String testo) {
    super(id, data);
    this.testo = testo;
  }
  
  
  /* ------------------------------ METODI ------------------------------- */
  /** Restituisce il testo contenuto nella nota.
    *
    * @return ritorna una stringa con il contenuto del campo "testo".
    */
  public String getTesto() {
    return testo;
  }
  
  /** Restituisce il testo contenuto nella nota.
    *
    * @see getTesto() il risultato &egrave; lo stesso.
    * @return ritorna una stringa con il contenuto del campo "testo". 
    */
  public String getContenuto() {
    return getTesto();
  }
  
}