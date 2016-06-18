import java.util.*;
import com.google.gson.*;

public class CreatoreNota {
  /** La classe non ha né costruttori né attributi.
    * Contiene un unico metodo static 
    */
  public static Nota creaNota (String stringaJson) {

    JsonParser parser = new JsonParser();
    JsonObject json = new JsonObject ();
    json = (JsonObject)parser.parse(stringaJson);
    
    Nota nota = null;
    int id = json.get("id").getAsInt();
    Date data = new Date(System.currentTimeMillis());
    
    if (json.get("number") != null) {
      int numero = json.get("number").getAsInt();
      nota = new NotaNumero(id, data, numero);
    }
    
    if (json.get("username") != null && json.get("password") != null) {
      String username = "" + json.get("username").getAsString();
      String password = "" + json.get("password").getAsString();
      nota = new NotaAccount(id, data, username, password);
    }
    
    if (json.get("text") != null) {
      String testo = "" + json.get("text").getAsString();
      nota = new NotaTesto(id, data, testo);
    }
    
    return nota;
  }
}