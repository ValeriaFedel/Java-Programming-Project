import java.util.*;

/** Classe astratta che definisce una nota nelle sue caratteristiche generali. 
  * Viene usata come base per costruire note di diversi tipi, attraverso 
  * sottoclassi che aggiungeranno ulteriori dati.
  *
  * @see NotaAccount
  * @see NotaNumero
  * @see NotaTesto
  * @author Silvia Florio, Valeria Fedel, Davide Mariuzzi 
  */
public abstract class Nota {
  
  /* ----------------------------- ATTRIBUTI ----------------------------- */
  /** Contiene il numero identificativo della nota. */
  protected int id;
  /** Contiene la data della nota. */
  protected long data;
  /** Contiene la stringa con il contenuto del file. */
  protected String contenuto;
  
  
  /* ---------------------------- COSTRUTTORE ---------------------------- */
  /** Crea un oggetto di tipo Nota contenente le informazioni id e data.
    *
    * @param id    numero identificativo della nota.
    * @param data  la data di creazione o ultima modifica.
    */
  public Nota (int id, long data) {
    this.id = id;
    this.data = data;
  }
  
  
  /* ------------------------------ METODI ------------------------------- */
  /** Ritorna l'id della nota.
    *
    * @return un numero intero che rappresenta l'id della nota.
    */
  public int getId () {
    return id;
  }
  
  /** Ritorna la data della nota.
    *
    * @return il valore della data (in millisecondi).
    */
  public long getData () {
    return data;
  }
  /** Metodo astratto che verr&agrave; implementato nelle sottoclassi.
    *
    * @return una stringa con il contenuto della nota.
    */
  abstract String getContenuto ();
  
}