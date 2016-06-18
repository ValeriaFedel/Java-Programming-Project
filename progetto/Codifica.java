/** Questa interfaccia che contiene le dichiarazioni dei metodi utilizzabili 
  * dalle classi che la implementeranno.
  * Dà la possibilità di intercambiare agevolmente il metodo di cifratura.
  *
  * @author Silvia Florio, Valeria Fedel, Davide Mariuzzi
  */
public interface Codifica {

	/** Ritorna la stringa codificata.
      * 
      * @param testo testo da codificare.
      * @return stringa con testo codificato.
      */
	public String codifica(String testo);

	/** Ritorna la stringa decodificata.
      * 
      * @param testo testo da decodificare.
      * @return stringa con testo decodificato.
      */
	public String decodifica(String testo);
}