package it.twm.codifica;

/** <code>GiulioCesare</code> &egrave; un'implementazione 
  * dell'interfaccia <code>Codifica</code> che permette una
  * codifica in base al Cifrario di Giulio Cesare.
  * 
  * @see Codifica
  * @author Silvia Florio, Valeria Fedel, Davide Mariuzzi
  */
public class GiulioCesare implements Codifica {
    /* ----------------------------- ATTRIBUTI ----------------------------- */
    /** Variabile che conterr&agrave; il numero di traslazione all'interno del codice ASCII. */
	private int shift;

    /* ---------------------------- COSTRUTTORE ---------------------------- */
    /** Crea un oggetto di tipo GiulioCesare con shift pari a 5.
    */
	public GiulioCesare() {
		this(5);
	}

	/** Crea un oggetto di tipo GiulioCesare con shift pari a 5.
	  *@param shift		lo scostamento all'interno dell'ASCII.
      */
	public GiulioCesare(int shift) {
		this.shift = shift;
	}

	/* ------------------------------ METODI ------------------------------- */
  	/** Ritorna la stringa codificata.
      * 
      * @param testo testo da codificare.
      * @return stringa con testo codificato.
      */
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

	/** Ritorna la stringa decodificata.
      * 
      * @param testo testo da decodificare.
      * @return stringa con testo decodificato.
      */
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

	/** Ritorna <code>true</code> se la stringa &egrave; un numero.
      * 
      * @param stirng 		stringa da controllare.
      * @return boolean se numerico o meno.
      */
	private boolean numerico(String string)  {  
	  try  {  

	    double d = Double.parseDouble(string);  

	  } catch(NumberFormatException e) {  

	    return false;  
	  } 
	  return true;  
	}
}