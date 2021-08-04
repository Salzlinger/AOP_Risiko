package Risiko;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Aktionen implements ActionListener{

	private RisikoGUI gui;
	private Graphen gra;
	
	static SpielerAnzahl spielerAnzahl;
	
	public Aktionen(Graphen gra, RisikoGUI gui) {

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
	
	static SpielereinstellungGUI einstellung;
	
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
			gra.setSpielerAnzahl(3);
			
			einstellung = new SpielereinstellungGUI(gui, true, gra.getSpielerAnzahl());
			einstellung.addActionListeners(new Spielereinstellungen(gra, einstellung));
			einstellung.setVisible(true);
			
		}
		
		else if(actionEvent.equals("vierSpielerBtn"))
		{
			gra.setSpielerAnzahl(4);
			einstellung = new SpielereinstellungGUI(gui, true, gra.getSpielerAnzahl());
			einstellung.addActionListeners(new Spielereinstellungen(gra, einstellung));
			einstellung.setVisible(true);

		}
		else if(actionEvent.equals("fuenfSpielerBtn"))
		{
			gra.setSpielerAnzahl(5);
			einstellung = new SpielereinstellungGUI(gui, true, gra.getSpielerAnzahl());
			einstellung.addActionListeners(new Spielereinstellungen(gra, einstellung));
			einstellung.setVisible(true);

		}
		else if(actionEvent.equals("zurueckBtn"))
		{
			gui.dispose();
		}
	}

}

class Spielereinstellungen implements ActionListener {

	private ArrayList<String> spielerNamen;

	private Graphen gra;
	private SpielereinstellungGUI gui;
	private Spielbrett spielbrett;

	public Spielereinstellungen(Graphen gra, SpielereinstellungGUI gui)
	{
		this.gra = gra;
		this.gui = gui;
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		String actionEvent = e.getActionCommand();
		spielerNamen = new ArrayList<String>();
		
		if(actionEvent.equals("startBtn"))
		{
			spielerNamen.add(gui.getSpielerTextField(1));
			spielerNamen.add(gui.getSpielerTextField(2));
			spielerNamen.add(gui.getSpielerTextField(3));

			if(gra.getSpielerAnzahl() == 4) 
			{
				spielerNamen.add(gui.getSpielerTextField(4));
			}
			
			if(gra.getSpielerAnzahl() == 5) 
			{
				spielerNamen.add(gui.getSpielerTextField(5));
			}
			
			spielbrett = new Spielbrett();
			gui.dispose();
			SpielerZahl.einstellung.dispose();
			Aktionen.spielerAnzahl.dispose();
			spielbrett.setVisible(true);
		}
		else if(actionEvent.equals("zurueckBtn"))
		{
			gui.dispose();
		}
		
	}
	
	/*
	@Override
	
	
	//Angriffsphase
	public void actionPerformed(ActionEvent e) {
		String actionEvent = e.getActionCommand();
		
		if(actionEvent.equals("angreifen"))
		
		if(actionEvent.equals("angreifen"))
		{
			
		}
		
		
	}
	*/
}

