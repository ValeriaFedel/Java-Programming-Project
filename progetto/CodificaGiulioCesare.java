	public class CodificaGiulioCesare implements Codifica {
		
        /* ----------------------------- ATTRIBUTI ----------------------------- */
        /** Variabile di tipo intero che contiene il valore dello spostamento */
		private int shift;
		private int ritorno;
        
        /* ---------------------------- COSTRUTTORE ---------------------------- */
		public CodificaGiulioCesare() {
			this(5);
		}

		public CodificaGiulioCesare(int shift) {
			this.shift = shift;
			this.ritorno = 26-shift;
		}
        
        /* ------------------------------ METODI ------------------------------- */
		public String codifica(String testo) {
			char[] arrayTesto = testo.toCharArray();
			for(int i=0; i<arrayTesto.length; i++) {
				if(Character.isAlphabetic(arrayTesto[i])) {
					int modulo = ((arrayTesto[i]-97)+shift)%26;
					arrayTesto[i] += -arrayTesto[i]+(97+modulo);
				}
			}

			return new String(arrayTesto);
		}

		public String decodifica(String testo) {
			char[] arrayTesto = testo.toCharArray();
			for(int i=0; i<arrayTesto.length; i++) {
				if(Character.isAlphabetic(arrayTesto[i])) {
					int modulo = ((arrayTesto[i]-97)+ritorno)%26;
					arrayTesto[i] = (char)(97+modulo);
				}
			}

			return new String(arrayTesto);
		}
	}