package it.twm.notesegrete;

import java.util.*;
import com.google.gson.*;

/** Classe <code>CreatoreNota</code>. <br>
  * Contiene un metodo statico che permette la creazione di una nota.
  * È priva di costruttori e attributi.
  *
  * @see Nota
  * @author Silvia Florio, Valeria Fedel, Davide Mariuzzi 
  */
public class CreatoreNota {

  /* ------------------------------ METODI ------------------------------- */
  /** Istanzia e ritorna una nota del tipo adeguato.
    *
    * @param stringaJson stringa letta dal file json in input.
    * @return un oggetto di tipo Nota istanziato con il giusto tipo di nota.
    */
  public static Nota creaNota (String stringaJson) {

    JsonParser parser = new JsonParser();
    JsonObject json = new JsonObject();
    Nota nota = new NotaNumero(-1,new Date(0),0);

    try {
      json = (JsonObject)parser.parse(stringaJson);
    } catch(JsonSyntaxException e) {
      return null;
    }
    catch( ClassCastException e) {
      return nota;
    }
    
    int id = json.get("id").getAsInt();
    Date data; 
    try {
      data = new Date(json.get("date").getAsLong());
    } catch(NumberFormatException e) {
      data = new Date(0);
    }
    
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