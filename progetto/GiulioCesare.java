public class GiulioCesare implements Codifica {
	private int shift;

	public GiulioCesare() {
		this(5);
	}

	public GiulioCesare(int shift) {
		this.shift = shift;
	}

	public String codifica(String testo) {
		char[] arrayTesto = testo.toCharArray();
		String nuovoTesto;
		
		if(numerico(testo)) {
			int numero = Integer.parseInt(testo);
			int numeroCifrato = numero + shift;

			nuovoTesto = ""+numeroCifrato;


		} else {
			for(int i=0; i<arrayTesto.length; i++) {
				arrayTesto[i] +=shift;
			}

			nuovoTesto = new String(arrayTesto);
		}

		return nuovoTesto;

	}

	public String decodifica(String testo) {
		char[] arrayTesto = testo.toCharArray();
		String nuovoTesto;

		if(numerico(testo)) {
			
			int numero = Integer.parseInt(testo);
			int numeroDecifrato = numero - shift;

			nuovoTesto = ""+numeroDecifrato;

		} else {

			for(int i=0; i<arrayTesto.length; i++) {
				arrayTesto[i] -=shift;
			}
			
			nuovoTesto = new String(arrayTesto);
		}

		return nuovoTesto;
	}

	private boolean numerico(String str)  {  
	  try  {  

	    double d = Double.parseDouble(str);  

	  } catch(NumberFormatException nfe) {  

	    return false;  
	  } 
	  return true;  
	}
}