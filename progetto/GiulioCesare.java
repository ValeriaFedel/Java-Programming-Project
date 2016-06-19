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
		for(int i=0; i<arrayTesto.length; i++) {
			if((int)arrayTesto[i]>47 && (int)arrayTesto[i]<58) {
				int modulo = ((arrayTesto[i]-48)+4)%10;
				arrayTesto[i] += -arrayTesto[i]+(48+modulo);
			} else {
				arrayTesto[i] +=shift;
			}
		}

		return new String(arrayTesto);
		}

	public String decodifica(String testo) {
		char[] arrayTesto = testo.toCharArray();
		for(int i=0; i<arrayTesto.length; i++) {
			if((int)arrayTesto[i]>47 && (int)arrayTesto[i]<58) {
				int modulo = ((arrayTesto[i]-48)+6)%10;
					arrayTesto[i] += -arrayTesto[i]+(48+modulo);
			} else {
				arrayTesto[i] -=shift;
			}
		}

		return new String(arrayTesto);
	}
}