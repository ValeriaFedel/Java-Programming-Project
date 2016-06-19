import java.util.*;

/** Classe che estende la classe <code>Nota</code>. </br>
  * Rappresenta una nota contenente due campi di testo quali <b>username</b>
  * e <b>password</b>.
  * 
  * @see Nota
  * @author Silvia Florio, Valeria Fedel, Davide Mariuzzi
  */
public class NotaAccount extends Nota {
  
  /* ----------------------------- ATTRIBUTI ----------------------------- */
  /** Variabile che contiene il nome utente. */
  private String username;
  /** Variabile che contiene la password. */
  private String password;
  
  
  /* ---------------------------- COSTRUTTORE ---------------------------- */
  /** Crea un oggetto di tipo NotaAccount contenente le informazioni id, data,
    * username e password.
    *
    * @param id        numero identificativo della nota.
    * @param data      la data di creazione o ultima modifica.
    * @param username  nome utente.
    * @param password  password.
    */
  public NotaAccount (int id, long data, String username, String password) {
    super(id, data);
    this.username = username;
    this.password = password;
  }
  
  
  /* ------------------------------ METODI ------------------------------- */
  /** Restituisce il nome utente.
    *
    * @return ritorna una stringa con il contenuto del campo <code>"username"
    *         </code> del file json.
    */ 
  public String getUsername() {
    return username;
  }
  
  /** Restituisce la password.
    *
    * @return ritorna una stringa con il contenuto del campo <code>"password"
    *         </code> del file json.
    */
  public String getPassword () {
    return password;
  }
  
  /** Restituisce i dati contenuti nella nota.
    *
    * @return ritorna una stringa con i dati <b>username</b> e <b>password</b>
    *         contenuti nel file json.
    */
  public String getContenuto() {
    String s = "username: "+getUsername()+"\n"+"password: "+getPassword();
    return s;
  }
  
}