import com.google.gson.*;

class ProvaJson {
		
	public static void main(String[] args)  {
		JsonParser parser = new JsonParser();
		JsonObject json;
		GestoreStream gestore = new GestoreFile(".");
		String string = gestore.leggiFile("prova.json"	);

		json = (JsonObject)parser.parse(string);
	
		String id = ""+json.get("id");

		System.out.println(id);
	}
}