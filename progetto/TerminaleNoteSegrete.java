class TerminaleNoteSegrete {

	GestoreNoteSegrete gestoreNote; //il gestoreNote è il cuore del programma
	Nota[] listaNote; 

	public TerminaleNoteSegrete(GestoreNoteSegrete gestore) {
		this.gestoreNote = gestore;
		listaNote = gestore.getNote();
	}

	/** Metodo che fa effettivamente partire il programma */
	public void run() {

		masterPassword(); //inizio il programma con il primo metodo
		
		if(listaNote.length == 0) {
		
			System.out.println("Importa la tua prima nota");
			importaNota();

		}
		
		while(true) {
			visualizzaNote(); //chiamo il prossimo step (direttamente se password già impostata)
		
			int scelta = scegliAzione(); 

			switch(scelta) {
				case 1: importaNota();
						break;

				case 2: visualizzaContenuto();
						break;

				default: scegliAzione();
						 break;
			}
		}


	}

	/** Impostare la master password da utilizzare per la decodifica */
	public void masterPassword() {
		
		if(!gestoreNote.passwordImpostata()) { //se la password non è ancora stata impostata

			String input_1;
			String input_2;

			do {
				System.out.println("Imposta una master password, sarà necessaria per la decodifica delle note");
				input_1 = Leggi.unoString();
				System.out.println("Iseriscila di nuovo");
				input_2 = Leggi.unoString(); 
			} while(!(input_1.equals(input_2))); //finchè sono diverse

			gestoreNote.impostaPassword(input_1); //chiamo il gestore ad impostare la password

		}

		return;
		

	}

	public void visualizzaNote() {

		listaNote = gestoreNote.getNote(); //importo le note dal gestore

		for(int i=0; i<listaNote.length; i++) {
			System.out.println("id: "+listaNote[i].getId()+"	|	data: "+listaNote[i].getData());
		} //per ogni nota ne stampo id e data

		System.out.println();

		return;

		
	}

	public int scegliAzione() {
		
		int scelta = 0;

		System.out.println("Vuoi importare una nota (1) o visualizzare il contenuto di una nota (2)?");
		do {
			scelta = Leggi.unInt();
		} while(scelta != 1 && scelta !=2);
		
		return scelta;

		

	}

	public void importaNota() {
		
		System.out.println("Inserisci il file da importare (formato .json)");
		String path;
		
		path = Leggi.unoString();

		gestoreNote.creaNuovaNota(path);

		return;
		
	}

	public void visualizzaContenuto() {

		System.out.println("Di quale nota? (Inserisci ID)");
		int scelta = Leggi.unInt();

		for(int i=0; i<listaNote.length; i++) {
			if(listaNote[i].getId() == scelta) {
				System.out.println(listaNote[i].getContenuto());
				scelta = i;
				break;
			}
		}

		System.out.println("Inserisci la password per visualizzare il contenuto decifrato");
		String psw = Leggi.unoString();

		if(gestoreNote.controllaPassword(psw)) {
			System.out.println(gestoreNote.getContenutoDecifrato(listaNote[scelta]));
		}

		System.out.println("Torna all'elenco delle note...");
		Leggi.unoString();

		return;

	}
}