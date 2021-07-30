package Risiko;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Aktionen implements ActionListener{

	private RisikoGUI gui;
	
	private SpielerAnzahl spielerAnzahl;
	
	public Aktionen(RisikoGUI gui) {

		this.gui = gui;
		
		gui.risikoGUIActionListeners(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String Klick = e.getActionCommand();
		
		if (Klick.equals("startBtn")) {
			System.out.println("Spiel wird gestartet ...");
			spielerAnzahl = new SpielerAnzahl();
			spielerAnzahl.setVisible(true);
		} 
		 else if (Klick.equals("exitBtn")){
				System.exit(0);
				
		}
	}
		
}

