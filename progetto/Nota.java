import java.util.*;
  
public abstract class Nota {
  
  /** ATTRIBUTI di classe*/
  protected int id;
  protected Date data;
  
  
  /** COSTRUTTORE */
  public Nota (int id, Date data) {
    this.id = id;
    this.data = data;
  }
  
  
  /** METODI di classe*/
  public int getId () {
    return id;
  }
  
  public Date getData () {
    return data;
  }
  
  // abstract String getContenuto ();
  
}