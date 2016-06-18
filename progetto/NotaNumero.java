import java.util.*;

/** Classe che estende la classe Nota.
  * Definisce una nota contenente esclusivamente informazioni di tipo numerico.
  *
  * @see Nota
  * @author Silvia Florio, Valeria Fedel, Davide Mariuzzi 
  */
public class NotaNumero extends Nota {
  
  /* ----------------------------- ATTRIBUTI ----------------------------- */
  /** Variabile che conterrà il numero letto dalla nota. */
  private int numero;
  
  /* ---------------------------- COSTRUTTORE ---------------------------- */
  /**
    * Crea un oggetto di tipo NotaNumero contenente le informazioni id, data,
    * e numero.
    *
    * @param id        numero identificativo della nota.
    * @param data      la data di creazione o ultima modifica.
    * @param numero    numero contenuto nella nota.
    */
  public NotaNumero (int id, Date data, int numero) {
    super(id, data);
    this.numero = numero;
  }
  
  /* ------------------------------ METODI ------------------------------- */
  /** Restituisce il numero letto dalla nota in forma di numero intero */
  public int getNumero() {
    return numero;
  }
  
  /** Restituisce il numero letto dalla nota in forma di stringa */
  public String getContenuto() {
    return ""+getNumero();
  }
  
}