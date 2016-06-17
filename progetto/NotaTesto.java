import java.util.*;
  
public class NotaTesto extends Nota {
  
  /** ATTRIBUTI di classe*/
  private String testo;
  
  /** COSTRUTTORE */
  public NotaTesto (int id, Date data, String testo) {
    super(id, data);
    this.testo = testo;
  }
  
  /** METODI di classe*/
  public String getTesto() {
    return testo;
  }

  public String getContenuto() {
    return getTesto();
  }
  
}