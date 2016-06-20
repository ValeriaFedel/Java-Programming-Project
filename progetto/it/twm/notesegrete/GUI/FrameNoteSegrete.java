package it.twm.notesegrete.GUI;

import it.twm.notesegrete.utility.DataUtility;
import it.twm.notesegrete.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


/** Classe che visualizza il Frame della GUI, e i suoi Panel. 
  * Le varie facciate della GUI vengono rappresentate da Panel 
  * diversi, che sono visualizzati invocando i metodi.  
  */

class FrameNoteSegrete extends Frame {
	protected Nota[] listaNote;
	protected GestoreNoteSegrete gestoreNote;
	
	public FrameNoteSegrete(GestoreNoteSegrete gestoreNote) {
		this.gestoreNote = gestoreNote;
		listaNote = gestoreNote.getNote();

	}

/** Metodo che fa partire il Frame e i relativi Panel principali, 
  * masterPassword e pannelloNote. 
  * Viene invocato nel main.
  * 
  * @see NoteSegrete
  */
	public void run() {

		setTitle("Note Segrete");

		setResizable(true);

		this.setPreferredSize(new Dimension(500,300));

		addWindowListener(new WindowAdapter() {
         	public void windowClosing(WindowEvent windowEvent){
           		dispose();
           	}
         });

		Panel p = new Panel();
		MenuBar menu = new MenuBar();
		Menu file = new Menu("File");
		MenuItem quit = new MenuItem("Quit", new MenuShortcut(KeyEvent.VK_Q));

		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		file.add(quit);
		menu.add(file);	
		this.add(p);
		this.setMenuBar(menu);

		masterPassword(p);

		pannelloNote(p);

		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

	/** Metodo che visualizza una finestra di dialogo
	  * che permette di impostare la password del programma.  
	  */
	public void masterPassword(Panel p) {

		if(gestoreNote.passwordImpostata()) {
			return;
		}

		Dialog dialog = new Dialog(this, "dialog");
		dialog.setPreferredSize(new Dimension(300,200));
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
		dialog.setMinimumSize(new Dimension(400,250));

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

				String password_1;
				String password_2;

					password_1 = ascoltatorePassword_1.getValore();
					password_2 = ascoltatorePassword_2.getValore();
				

				try  {
					if(password_1.equals(password_2)) {
						gestoreNote.impostaPassword(password_1);
						dialog.dispose();
						return;
					}	 else {
						errore.setText("I due inserimenti non corrispondono, riprova.");
					errore.setVisible(true);
				}
			} catch (NullPointerException npe) {
					errore.setText("Non puoi lasciare in bianco i campi!");
					errore.setVisible(true);
					return;
				}
			}
		});

		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}


	/** Metodo che visualizza una finestra di dialogo da cui scegliere 
	  * le note da importare nel programma.   
	  */
	public void importaNota(Button b) {
		Frame f = this;
		b.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				FileDialog dialog = new FileDialog(f);
				dialog.setVisible(true);
				
				String s = dialog.getDirectory()+dialog.getFile();

				gestoreNote.creaNuovaNota(s);
				listaNote = gestoreNote.getNote();
				
			}

		});

	}

	/** Metodo che visualizza una finestra che mostra l'elenco delle note  
	  * caricate con importaNota. 
	  * Se la nota caricata non è del formato corretto, viene 
	  * visualizzato un messaggio di errore. 
	  */
	public void pannelloNote(Panel p) {

		List lista = new List();
		p.setLayout(new BorderLayout());	
		p.add(lista, BorderLayout.CENTER);
		for(int i=0; i<listaNote.length; i++) {
			lista.add("id: "+listaNote[i].getId()+"    ::    data: "+ DataUtility.formattaData(listaNote[i].getData()));
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

				String s = dialog.getDirectory()+dialog.getFile();

				gestoreNote.creaNuovaNota(s);
				int numeroNote = listaNote.length;
				listaNote = gestoreNote.getNote();

				if(numeroNote == listaNote.length-1) {
					aggiornaLista(lista);
				} else {
					Dialog errore = new Dialog(f, "Errore");
					

					errore.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent e) {
							errore.dispose();
						}
					});

					Panel panelErrore = new Panel(new GridLayout(2,1));
					Label labelErrore = new Label("Sembra che il file importato non sia del formato corretto");
					Button riprova = new Button("Chiudi");
					Panel panelLabel = new Panel();
					Panel panelButton = new Panel();

					panelLabel.add(labelErrore);
					panelButton.add(riprova);
					panelErrore.add(panelLabel);
					panelErrore.add(panelButton);
					errore.add(panelErrore);

					riprova.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							errore.dispose();
						}
 					});

 					errore.pack();
					errore.setLocationRelativeTo(null);
					errore.setVisible(true);
				}
				
			}

		});

		lista.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int index = lista.getSelectedIndex();
				contenutoNota(index);
			}
		});


	}

	/** Metodo che aggiorna l'array che contiene le note;
	  * aggiungendo una nota attraverso il metodo pannelloNote,
	  * viene aggiunta una nota all'array listaNote.  
	  */
	public void aggiornaLista(List lista) {
		lista.add("id: "+listaNote[listaNote.length-1].getId()+"    ::    data: "+DataUtility.formattaData(listaNote[listaNote.length-1].getData()));
	}

	/** Metodo che mostra una finestra di dialogo con le informazioni
	  * su una singola nota, in seguito alla sua selezione. 
	  * In questa finestra è possibile inserire la password,
	  * e quindi visualizzare il contenuto segreto.  
	  */
	public void contenutoNota(int index) {
		String titolo = ""+listaNote[index].getId();
		Dialog dialogContenuto = new Dialog(this, titolo);


		dialogContenuto.addWindowListener(new WindowAdapter() {
         	public void windowClosing(WindowEvent windowEvent){
           		dialogContenuto.dispose();
           	}
         });

		JPanel panelContenuto = new JPanel();
		panelContenuto.setLayout(new BoxLayout(panelContenuto, BoxLayout.Y_AXIS));
		TextArea contenutoNota = new TextArea(listaNote[index].getContenuto(), 10, 50, TextArea.SCROLLBARS_VERTICAL_ONLY);
		contenutoNota.setEditable(false);

		panelContenuto.add(contenutoNota);
		panelContenuto.add(Box.createVerticalStrut(20));
		panelContenuto.setBorder(BorderFactory.createEmptyBorder(30,30,45,30));
		dialogContenuto.add(panelContenuto, BorderLayout.CENTER);	


		Panel controllaPassword = new Panel();
		controllaPassword.setLayout(new BoxLayout(controllaPassword, BoxLayout.Y_AXIS));
		JLabel inserisciPassword = new JLabel("Inserisci la password per visualizzare in chiaro il contenuto:");
		TextField password = new TextField();
		password.setEchoChar('*');
		Button check = new Button("Decifra");
		JLabel msgErrore = new JLabel();
		
		check.setMaximumSize(new Dimension(80,80));
		password.setMaximumSize(new Dimension(250,250));
		inserisciPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
		msgErrore.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		dialogContenuto.add(controllaPassword, BorderLayout.SOUTH);
		controllaPassword.add(inserisciPassword);
		controllaPassword.add(Box.createVerticalStrut(6));
		controllaPassword.add(password);
		controllaPassword.add(Box.createVerticalStrut(10));
		controllaPassword.add(check);
		controllaPassword.add(Box.createVerticalStrut(6));
		controllaPassword.add(msgErrore);

		panelContenuto.add(controllaPassword);

		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String psw = password.getText();
				if(gestoreNote.controllaPassword(psw)) {
					contenutoNota.setText(gestoreNote.getContenutoDecifrato(listaNote[index]));
					msgErrore.setText("");
				} else {
					msgErrore.setText("Errore: Password incorretta");
				}
			}
		});

		dialogContenuto.pack();
		dialogContenuto.setLocationRelativeTo(null);
		dialogContenuto.setVisible(true);

	}

	/** Classe che definisce l'ascoltatore che viene usato  
	  * nel memorizzare la master password del programma,
	  * nel metodo masterPassword.   
	  */
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