import java.util.*;

/** 
  * Classe che estende la classe Nota. </br>
  * Rappresenta una nota contenente due campi di testo quali <b>username</b>
  * e <b>password</b>.
  *
  * @author Silvia Florio, Valeria Fedel, Davide Mariuzzi
  * 
  */

public class NotaAccount extends Nota {
  
  /* ----------------------------- ATTRIBUTI ----------------------------- */
  /** Variabile che contiene il nome utente. */
  private String username;
  /** Variabile che contiene la password. */
  private String password;
  
  
  /* ---------------------------- COSTRUTTORE ---------------------------- */
  /**
    * Crea un oggetto di tipo NotaAccount contenente le informazioni id, data,
    * username e password.
    *
    * @param id        numero identificativo della nota.
    * @param data      la data di creazione o ultima modifica.
    * @param username  nome utente.
    * @param password  password.
    */
  public NotaAccount (int id, Date data, String username, String password) {
    super(id, data);
    this.username = username;
    this.password = password;
  }
  
  
  /* ------------------------------ METODI ------------------------------- */
  /** Metodo che restituisce il nome utente. */ 
  public String getUsername() {
    return username;
  }
  
  /** Metodo che restituisce la password. */
  public String getPassword () {
    return password;
  }
  
  /** Metodo che restituisce il contenuto della nota in formato stringa. */
  public String getContenuto() {
    String s = "username: "+getUsername()+"\n"+"password: "+getPassword();
    return s;
  }
  
}