class CifraTesto {

	public static void main(String[] args) {
		Codifica giulio = new GiulioCesare(6);
		String string = Leggi.unoString();
		System.out.println(giulio.codifica(string));
	}	
}