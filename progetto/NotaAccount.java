import java.util.*;
  
public class NotaAccount extends Nota {
  
  /** ATTRIBUTI di classe*/
  private String username;
  private String password;
  
  /** COSTRUTTORE */
  public NotaAccount (int id, Date data, String username, String password) {
    super(id, data);
    this.username = username;
    this.password = password;
  }
  
  /** METODI di classe*/
  public String getUsername() {
    return username;
  }
  
  public String getPassword () {
    return password;
  }
  
}