import java.awt.*;
import java.awt.event.*;


public class GUI extends Frame {

	public GUI() {

		setTitle("GUI");

		addWindowListener(new WindowAdapter() {
         	public void windowClosing(WindowEvent windowEvent){
           		dispose();
           	}
         });

		Panel p = new Panel();
		Button b = new Button("Scegli File");
		Frame f = this;

		p.add(b);
		b.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent click) {

				System.out.println(click);
				
				FileDialog dialog = new FileDialog(f);
				dialog.setVisible(true);

				System.out.println("io ti farò scegliere il file");

				System.out.println(dialog.getFile());
				GestoreFile gestore = new GestoreFile();

				String s = dialog.getDirectory()+dialog.getFile();

				gestore.copia(s);
				
			}

		});

		TextField file = new TextField("TextArea");

		this.add(p);
		p.add(file);

		setVisible(true);

		this.pack();

	}
}