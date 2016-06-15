import java.util.*;
  
public class NotaNumero extends Nota {
  
  /** ATTRIBUTI di classe*/
  private int numero;
  
  /** COSTRUTTORE */
  public NotaNumero (int id, Date data, int numero) {
    super(id, data);
    this.numero = numero;
  }
  
  /** METODI di classe*/
  public int getNumero() {
    return numero;
  }
  
}