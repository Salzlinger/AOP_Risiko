package Risiko;

import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Aktionen implements ActionListener{

	private RisikoGUI gui;
	private Graphen gra;
	
	private SpielerAnzahl spielerAnzahl;
	
	public Aktionen(RisikoGUI gui) {

		this.gui = gui;
		this.gra = gra;
		
		gui.risikoGUIActionListeners(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String Klick = e.getActionCommand();
		
		if (Klick.equals("startBtn")) {
			System.out.println("Spiel wird gestartet ...");
			spielerAnzahl = new SpielerAnzahl(gui, true);
			spielerAnzahl.addActionListeners(new SpielerZahl(gra, spielerAnzahl));			
			spielerAnzahl.setVisible(true);
		} 
		 else if (Klick.equals("exitBtn")){
				System.exit(0);
				
		}
	}
		
}

class SpielerZahl implements ActionListener {
	
	private Graphen gra;
	private SpielerAnzahl gui;
	
	public SpielerZahl(Graphen gra, SpielerAnzahl gui)
	{
		this.gra = gra;
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String actionEvent = e.getActionCommand();
		
		if(actionEvent.equals("dreiSpielerBtn")) 
		{
			gra.setzeSpielerAnzahl(3);
		}
		
		else if(actionEvent.equals("vierSpielerBtn"))
		{
			gra.setzeSpielerAnzahl(4);
		}
		else if(actionEvent.equals("fuenfSpielerBtn"))
		{
			gra.setzeSpielerAnzahl(5);
		}
		else if(actionEvent.equals("zurueckBtn"))
		{
			gui.dispose();
		}
	}

}

