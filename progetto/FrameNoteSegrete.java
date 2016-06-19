import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.CardLayout;
import java.util.*;

/* FrameNoteSegrete è il frame principale. È stato creato un panel contenitore (mainPanel) per utilizzare 
il CardLayout, a cui sono stati aggiunti i vari tipi di panel (SetPassword, CaricaNote, ecc.). */

class FrameNoteSegrete extends Frame {

	private Nota[] listaNote;
	private GestoreNoteSegrete gestore;

	Panel mainPanel;
	SetPassword setPassword;
	CaricaNote caricaNote;
	ElencoNote elencoNote;
	NotaSelezionata notaSelezionata;
	CardLayout card;

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
		
		setPassword = new SetPassword(card,mainPanel,gestore); // primo panel che viene aggiunto a quello principale
		caricaNote = new CaricaNote(card,mainPanel,gestore); // secondo panel aggiunto	
		elencoNote = new ElencoNote(card,mainPanel,gestore);
		notaSelezionata = new NotaSelezionata(card,mainPanel,gestore);
			
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
}


	// Impostare la password
	class SetPassword extends Panel {
		
		String str;
		String password;
		Label msgPassword;
		TextField fieldPassword;
		Button invioPassword;
		TextField msgPasswordImpostata;
		
		CardLayout card;
		Panel mainPanel;

		GestoreNoteSegrete gestore;
		
		public SetPassword(final CardLayout card, final Panel mainPanel,GestoreNoteSegrete gestore) {
			
			this.card = card;
			this.mainPanel = mainPanel;
			this.gestore = gestore;

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


	
	// Caricare le note
	class CaricaNote extends Panel {

		CardLayout card;
		Panel mainPanel;
		GestoreNoteSegrete gestore;

		public CaricaNote(final CardLayout card, final Panel mainPanel,GestoreNoteSegrete gestore) {
			
			this.card = card;
			this.mainPanel = mainPanel;

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

					gestore.creaNuovaNota(s);
					
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

	// Visualizzare l'elenco delle note caricate
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

		CardLayout card;
		Panel mainPanel;
		

		public ElencoNote(final CardLayout card, final Panel mainPanel,GestoreNoteSegrete gestore) {
			
			this.card = card;
			this.mainPanel = mainPanel;


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
			for (int i = 0; i < listaNote.length; i++) {
				panel = new Panel();
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				panel.setPreferredSize(new Dimension(50, 25));
				casella[i] = panel;					
				add(panel);
				panel.setVisible(true);
			}


			// inserisco i dati all'interno di ciascuna casella
			for (int i = 0; i < listaNote.length; i++) {
	
				q = listaNote[i].getId();
				d = listaNote[i].getData();
				id = new TextField(q);
				// data = new TextField(d);
				panel.add(id);
				panel.add(data);
			}

			// questo panel evita che venga gettata l'eccezione NullPointerException da panel.addMouseListener
			panel = new Panel();
			add(panel);

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
		}
	}

	// Visualizzare una nota singola, dopo la sua selezione
	class NotaSelezionata extends Panel {

		CardLayout card;
		Panel mainPanel;
		GestoreNoteSegrete gestore;

		public NotaSelezionata(final CardLayout card, final Panel mainPanel,GestoreNoteSegrete gestore) {
			
			this.card = card;
			this.mainPanel = mainPanel;

      		setVisible(true);
			
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





