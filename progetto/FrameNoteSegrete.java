import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.CardLayout;
import java.util.*;


class FrameNoteSegrete extends Frame {

	private Nota[] listaNote;
	private GestoreNoteSegrete gestore;

	Panel mainPanel;
	SetPassword setPassword;
	CaricaNote caricaNote;
	ElencoNote elencoNote;
	CardLayout card;
	NotaSelezionata notaSelezionata;

	public FrameNoteSegrete(GestoreNoteSegrete gestore) {
		
		this.gestore = gestore;

		setSize(400,70);
	    setLayout(new GridLayout(1,3));
	    
	    // posiziona la finestra al centro dello schermo
	    Dimension dimensioneSchermo = Toolkit.getDefaultToolkit().getScreenSize();
	    int height = dimensioneSchermo.height;
	    int width = dimensioneSchermo.width;
	    setSize(width/2, height/2);
		setLocationRelativeTo(null);
	    
	    addWindowListener(new WindowAdapter(){
	    	public void windowClosing(WindowEvent e){
	    		System.exit(0);
	    	}
	    });

		card = new CardLayout(); // imposto il layout CardLayout per tutti i panel
	    mainPanel = new Panel(); // creo il panel principale
		
		setPassword = new SetPassword(); // primo panel che viene aggiunto a quello principale
		caricaNote = new CaricaNote(); // secondo panel aggiunto	
		elencoNote = new ElencoNote();
		notaSelezionata = new NotaSelezionata();
			
		mainPanel.setLayout(card);
		mainPanel.add(setPassword, "setPassword");
		mainPanel.add(caricaNote, "caricaNote");
		mainPanel.add(elencoNote, "elencoNote");
		mainPanel.add(notaSelezionata, "notaSelezionata");

		card.show(mainPanel, "setPassword");

		this.add(mainPanel);
		setVisible(true);
	    setResizable(true);
	}


	// Impostare la password
	class SetPassword extends Panel {
		
		String str;
		String password;
		Label msgPassword;
		TextField fieldPassword;
		Button invioPassword;
		TextField msgPasswordImpostata;
		
		

		public SetPassword() {
			
			this.setLayout(new FlowLayout());
			msgPassword = new Label("Scegli una password:");
			fieldPassword = new TextField();
			fieldPassword.setColumns(20);
			invioPassword = new Button("Imposta");
			
			this.add(msgPassword);
			this.add(fieldPassword);
			this.add(invioPassword);
			
    		// prende la password dalla casella di testo
    		fieldPassword.addTextListener(new TextListener() {
    			public void textValueChanged(TextEvent e) {
    				str = fieldPassword.getText();	
    			}
    		});

    		// premuto il pulsante, viene memorizzata la password 
    		invioPassword.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
	 				password = new String(str);
	 				gestore.impostaPassword(password);
	 				

	 				card.show(mainPanel, "caricaNote");
				}
			});
		}

	}

	
	// Carica le note
	class CaricaNote extends Panel {

		public CaricaNote() {
			
			Button b = new Button("Scegli File");
			Frame f = new Frame();

			add(b);
			b.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent click) {

					System.out.println(click);
					
					FileDialog dialog = new FileDialog(f);
					dialog.setVisible(true);

					System.out.println("io ti farò scegliere il file");

					System.out.println(dialog.getFile());
					

					String s = dialog.getDirectory()+dialog.getFile();

					// gestore.copia(s);
					
				}

			});

			TextField file = new TextField("TextArea");
			add(file);

			Button b1 = new Button("Finito");
			add(b1);
			b1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent click) {
					card.show(mainPanel, "elencoNote");
				}
			});
		}
	} 

	class ElencoNote extends Panel {

		GestoreNoteSegrete gestore;
		TextField tipo;
		TextField id;
		TextField data;
		Panel[] casella;
		Panel panel;
		Nota[] listaNote;
		Date d;
		int q;


		public ElencoNote() {
			
			try {

			if (gestore.getNote() == null) {
				
				// bottone "torna indietro"
				Button b2 = new Button("Torna Indietro");
				add(b2);
				b2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						card.show(mainPanel, "caricaNote");
					}
				});		
			} else {

			listaNote = gestore.getNote();

			casella = new Panel[listaNote.length];
			
			// creo le caselle
			for (int i = 0; i <= listaNote.length; i++) {
				panel = new Panel();
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				panel.setPreferredSize(new Dimension(50, 25));
				casella[i] = panel;					
				add(panel);
				panel.setVisible(true);
			}


			// inserisco i dati all'interno di ciascuna casella
			for (int i = 0; i <= listaNote.length; i++) {
	
				q = listaNote[i].getId();
				d = listaNote[i].getData();
				id = new TextField(q);
				// data = new TextField(d);
				panel.add(id);
				panel.add(data);
			}

			// cliccando su una nota, appare un dialog con le informazioni su quella nota
			panel.addMouseListener(new MouseAdapter() {
                private Color background;

                public void mousePressed(MouseEvent e) {
                    background = getBackground();
                    setBackground(Color.GRAY);
                    repaint();
                }

                public void mouseReleased(MouseEvent e) {
                    setBackground(background);
                    card.show(mainPanel, "notaSelezionata");
                }
 				});
			}

			} catch (NullPointerException e) {
				System.out.println(e);
			}	
		}
	}


	class NotaSelezionata extends Panel {

		public NotaSelezionata() {
			
      		setVisible(true);
			setResizable(true);
		}
	}
}



public class Esegui {

	public static void main(String[] args) {
		Codifica codifica = new CodificaGiulioCesare();
		GestoreStream stream = new GestoreFile("./");
		GestoreNoteSegrete gestore = new GestoreNoteSegrete(codifica, stream);
		FrameNoteSegrete newGUI = new FrameNoteSegrete(gestore); // creo la finestra
		
		}
}





