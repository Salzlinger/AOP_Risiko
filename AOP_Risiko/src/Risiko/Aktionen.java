package Risiko;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

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
	private static Random zufall = new Random();

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
			Main.spieler1 = new Spieler ("Blau", spielerNamen.get(0) );
			Main.spieler.add(Main.spieler1);
			spielerNamen.add(gui.getSpielerTextField(2));
			Main.spieler2 = new Spieler ("Rot", spielerNamen.get(1));
			Main.spieler.add(Main.spieler2);
			spielerNamen.add(gui.getSpielerTextField(3));
			Main.spieler3 = new Spieler ("Gelb", spielerNamen.get(2));
			Main.spieler.add(Main.spieler3);

			if(gra.getSpielerAnzahl() == 4) 
			{
				spielerNamen.add(gui.getSpielerTextField(4));
				Main.spieler4 = new Spieler ("Gr�n", spielerNamen.get(3));
				Main.spieler.add(Main.spieler4);
			}
			
			if(gra.getSpielerAnzahl() == 5) 
			{
				spielerNamen.add(gui.getSpielerTextField(4));
				Main.spieler4 = new Spieler ("Gr�n", spielerNamen.get(3));
				Main.spieler.add(Main.spieler4);
				spielerNamen.add(gui.getSpielerTextField(5));
				Main.spieler5 = new Spieler ("Wei�", spielerNamen.get(4));
				Main.spieler.add(Main.spieler5);
			}
			
			
			//Spieler randomizen
			for(int i=0; i < 10 ; i++)
			{
			int a = zufall.nextInt(Main.spieler.size());
			int b = zufall.nextInt(Main.spieler.size());
			//System.out.print(Main.spieler.size());
			Spieler puffer = Main.spieler.get(a);
			Main.spieler.set(a, Main.spieler.get(b));
			Main.spieler.set(b, puffer);
			}
			System.out.println("Folgende Spielerreihenfolge wurde ausgew�rfelt: " +Main.spieler);

			switch (gra.getSpielerAnzahl()) {
			case 3:			
				//L�nder werden verteilt
				int k = 0;
				for (int i = 0; i < 42; i++) {
					Main.spieler.get(k).getLaender().add(Main.liste[i]);
					k++;
					if(k == 3) {
						k = 0;
					}
				}
				for (int i = 0; i < Main.spieler.size(); i++) {
					for (int j = 0; j < Main.spieler.get(i).getLaender().size(); j++) {
						System.out.println("L�nder von spieler " + Main.spieler.get(i) + " : " + Main.spieler.get(i).getLaender().get(j));
					}
				}
				//Truppen werden verteilt
				for (int i = 0; i < Main.spieler.size(); i++) {
					Main.spieler.get(i).startTruppen(35);
				}
				Main.spieler.get(0).istDrann = true;
				
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
	
}

