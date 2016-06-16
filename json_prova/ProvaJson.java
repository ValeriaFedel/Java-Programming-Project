import com.google.gson.*;
import java.io.*;

class ProvaJson {
		
	public static void main(String[] args)  {
		JsonParser parser = new JsonParser();
		JsonObject json;
		try {
			json = (JsonObject)parser.parse(new FileReader("prova.json"));
		} catch(IOException e) {
			System.out.println(e);
			return;
		}

		String id = ""+json.get("id");

		System.out.println(id);
	}
}