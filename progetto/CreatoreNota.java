import java.util.*;
import com.google.gson.*;

public class CreatoreNota {
  /** La classe non ha costruttori. Contiene un unico metodo static */
  public static Nota creaNota (String stringaJson) {

    JsonParser parser = new JsonParser();
    JsonObject json = new JsonObject ();
    json = (JsonObject)parser.parse(stringaJson);
    
    Nota nota = null;
    int id = json.get("id").getAsInt();
    Date data = new Date(System.currentTimeMillis()); //json.get("date"); // json.get("date") è di tipo JsonElement...
    /*
    La classe Data ha un metodo toString che trasforma la data "scomposta" in una 
    stringa di forma yyyy-mm-dd. Tutti gli altri metodi della classe sono deprecati..
    Se all'inizio sembrava buona la possibilità di usare il tipo Data, guardando la
    documentazione (edizione 8) non mi sembra il caso.
    Secondo me sarebbe meglio provare così: 
    String data = json.get("date").getAsString();
    Usiamo la data come stringa e togliamo il problema. Attendo feedback!! :)
    */
    
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