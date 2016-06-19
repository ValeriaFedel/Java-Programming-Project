
import java.awt.*;
import java.awt.event.*;

class FrameNoteSegreteProva extends Frame {
	protected Nota[] listaNote;
	protected GestoreNoteSegrete gestoreNote;
	
	public FrameNoteSegreteProva(GestoreNoteSegrete gestoreNote) {
		this.gestoreNote = gestoreNote;
		listaNote = gestoreNote.getNote();

	}

	public void run() {

		setTitle("Note Segrete");

		setResizable(true);

		Panel p = new Panel();
		this.add(p);

		this.setPreferredSize(new Dimension(500,300));

		addWindowListener(new WindowAdapter() {
         	public void windowClosing(WindowEvent windowEvent){
           		dispose();
           	}
         });


		masterPassword(p);

		Button b = new Button("Scegli File");

		//p.add(b);

		pannelloNote(p);

		//importaNota(b, this);

		 //inizio il programma con il primo metodo
			
		/*if(listaNote.length == 0) {
			System.out.println("Importa la tua prima nota");
			importaNota();
		}
		
		while(true) {
			visualizzaNote(); //chiamo il prossimo step (direttamente se password già impostata)
		}
*/

		setVisible(true);

		this.pack();

	}

	public void masterPassword(Panel p) {

		if(gestoreNote.passwordImpostata()) {
			return;
		}

		Dialog dialog = new Dialog(this, "dialog");
		dialog.setPreferredSize(new Dimension(300,200));
		dialog.setVisible(true);
		dialog.addWindowListener(new WindowAdapter() {
         	public void windowClosing(WindowEvent windowEvent){
           		dialog.dispose();
           	}
         });

		Label label_1 = new Label("Imposta la tua password");
		Label label_2 = new Label("Password");
		Label label_3 = new Label("Conferma password");
		
		TextField fieldPassword_1 = new TextField();
		fieldPassword_1.setEchoChar('*');

		TextField fieldPassword_2 = new TextField();
		fieldPassword_2.setEchoChar('*');

		Button invia = new Button("invia");

		Label errore = new Label("I due inserimenti non corrispondono, riprova.");
		errore.setVisible(false);

		Panel panel = new Panel(new GridLayout(7,1));
		dialog.setLayout(new FlowLayout( FlowLayout.CENTER));
		dialog.add(panel);

		panel.add(label_1);
		panel.add(label_2);
		panel.add(fieldPassword_1);
		panel.add(label_3);
		panel.add(fieldPassword_2);
		panel.add(invia);
		panel.add(errore);

		FieldListener ascoltatorePassword_1 = new FieldListener(fieldPassword_1);
		fieldPassword_1.addTextListener(ascoltatorePassword_1);

		FieldListener ascoltatorePassword_2 = new FieldListener(fieldPassword_2);
		fieldPassword_2.addTextListener(ascoltatorePassword_2);

		invia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String password_1 = ascoltatorePassword_1.getValore();
				String password_2 = ascoltatorePassword_2.getValore();

				if(password_1.equals(password_2)) {
					gestoreNote.impostaPassword(password_1);
					dialog.dispose();
					return;
				} else {
					errore.setVisible(true);
				}
			}
		});

		dialog.pack();
	
	}


	public void importaNota(Button b) {
		Frame f = this;
		b.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				FileDialog dialog = new FileDialog(f);
				dialog.setVisible(true);

				System.out.println(dialog.getFile());

				String s = dialog.getDirectory()+dialog.getFile();

				gestoreNote.creaNuovaNota(s);
				listaNote = gestoreNote.getNote();
				
			}

		});

	}


	public void pannelloNote(Panel p) {

		List lista = new List();
		p.setLayout(new BorderLayout());	
		p.add(lista, BorderLayout.CENTER);
		for(int i=0; i<listaNote.length; i++) {
			lista.add(listaNote[i].toString());
		}
		Panel southPanel = new Panel();
		Button importa = new Button("Importa nota");
		southPanel.add(importa);
		p.add(southPanel, BorderLayout.SOUTH);

		Frame f = this;
		importa.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				FileDialog dialog = new FileDialog(f);
				dialog.setVisible(true);

				System.out.println(dialog.getFile());

				String s = dialog.getDirectory()+dialog.getFile();

				gestoreNote.creaNuovaNota(s);
				listaNote = gestoreNote.getNote();

				aggiornaLista(lista);
				
			}

		});

		lista.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int index = lista.getSelectedIndex();
				contenutoNota(index);
			}
		});


	}


	public void aggiornaLista(List lista) {
		lista.add("id: "+listaNote[listaNote.length-1].getId()+"\t::\tdata: "+listaNote[listaNote.length-1].getData());
	}


	public void contenutoNota(int index) {
		String titolo = ""+listaNote[index].getId();
		Dialog contenuto = new Dialog(this, titolo);
		contenuto.setVisible(true);



	}


	class FieldListener implements TextListener {

		private String valoreCampo;
		private TextField proprietario;

		public FieldListener(TextField proprietario) {
			this.proprietario = proprietario;
		}
 
		public void textValueChanged(TextEvent e) {
			this.valoreCampo = proprietario.getText();
		}

		public String getValore() {
			return valoreCampo;
		}

	}

}