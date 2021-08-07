package Risiko;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Aktionen implements ActionListener{

	static RisikoGUI gui;
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

		String Klick = e.getActionCommand();
		
		if(Klick.equals("dreiSpielerBtn")) 
		{
			gra.setSpielerAnzahl(3);
			
			einstellung = new SpielereinstellungGUI(gui, true, gra.getSpielerAnzahl());
			einstellung.addActionListeners(new Spielereinstellungen(gra, einstellung));
			einstellung.setVisible(true);
			
		}
		
		else if(Klick.equals("vierSpielerBtn"))
		{
			gra.setSpielerAnzahl(4);
			einstellung = new SpielereinstellungGUI(gui, true, gra.getSpielerAnzahl());
			einstellung.addActionListeners(new Spielereinstellungen(gra, einstellung));
			einstellung.setVisible(true);

		}
		else if(Klick.equals("fuenfSpielerBtn"))
		{
			gra.setSpielerAnzahl(5);
			einstellung = new SpielereinstellungGUI(gui, true, gra.getSpielerAnzahl());
			einstellung.addActionListeners(new Spielereinstellungen(gra, einstellung));
			einstellung.setVisible(true);

		}
		else if(Klick.equals("zurueckBtn"))
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

		String Klick = e.getActionCommand();
		spielerNamen = new ArrayList<String>();
		
		if(Klick.equals("startBtn"))
		{
			spielerNamen.add(gui.getSpielerTextField(1));
			Main.spieler1 = new Spieler ("BLUE", spielerNamen.get(0) );
			Main.spieler.add(Main.spieler1);
			spielerNamen.add(gui.getSpielerTextField(2));
			Main.spieler2 = new Spieler ("RED", spielerNamen.get(1));
			Main.spieler.add(Main.spieler2);
			spielerNamen.add(gui.getSpielerTextField(3));
			Main.spieler3 = new Spieler ("YELLOW", spielerNamen.get(2));
			Main.spieler.add(Main.spieler3);

			if(gra.getSpielerAnzahl() == 4) 
			{
				spielerNamen.add(gui.getSpielerTextField(4));
				Main.spieler4 = new Spieler ("GREEN", spielerNamen.get(3));
				Main.spieler.add(Main.spieler4);
			}
			
			if(gra.getSpielerAnzahl() == 5) 
			{
				spielerNamen.add(gui.getSpielerTextField(4));
				Main.spieler4 = new Spieler ("GREEN", spielerNamen.get(3));
				Main.spieler.add(Main.spieler4);
				spielerNamen.add(gui.getSpielerTextField(5));
				Main.spieler5 = new Spieler ("WHITE", spielerNamen.get(4));
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
			System.out.println("Folgende Spielerreihenfolge wurde ausgewürfelt: " +Main.spieler);

			switch (gra.getSpielerAnzahl()) {
			case 3:			
				//Länder werden verteilt
				
				int k = 0;
				for (int i = 0; i < 42; i++) {
					Main.spieler.get(k).getLaender().add(Laender.liste[i]);
					Laender.liste[i].setBesitzer(Main.spieler.get(k));
					k++;
					if(k == 3) {
						k = 0;
					}
				}
				for (int i = 0; i < Main.spieler.size(); i++) {
					for (int j = 0; j < Main.spieler.get(i).getLaender().size(); j++) {
						System.out.println("Länder von spieler " + Main.spieler.get(i) + " : " + Main.spieler.get(i).getLaender().get(j).getName());
					}
				}
				//Truppen werden verteilt
				
				for (int i = 0; i < Main.spieler.size(); i++) {
					Main.spieler.get(i).startTruppen(10);
					//Main.spieler.get(i).startTruppen(34);
				}
				Main.spieler.get(0).istDrann = true;
				System.out.println(Main.spieler.get(0).getName() + " ist Drann mit setzten.");
				break;
			case 4:
				k = 0;
				for (int i = 0; i < 42; i++) {
					Main.spieler.get(k).getLaender().add(Laender.liste[i]);
					Laender.liste[i].setBesitzer(Main.spieler.get(k));
					k++;
					if(k == 4) {
						k = 0;
					}
				}
				for (int i = 0; i < Main.spieler.size(); i++) {
					for (int j = 0; j < Main.spieler.get(i).getLaender().size(); j++) {
						System.out.println("Länder von spieler " + Main.spieler.get(i) + " : " + Main.spieler.get(i).getLaender().get(j).getName());
					}
				}
				//Truppen werden verteilt
				
				for (int i = 0; i < Main.spieler.size(); i++) {
					Main.spieler.get(i).startTruppen(10);
					//Main.spieler.get(i).startTruppen(29);
				}
				Main.spieler.get(0).istDrann = true;
				System.out.println(Main.spieler.get(0).getName() + " ist Drann mit setzten.");
				break;
			case 5:
				k = 0;
				for (int i = 0; i < 42; i++) {
					Main.spieler.get(k).getLaender().add(Laender.liste[i]);
					Laender.liste[i].setBesitzer(Main.spieler.get(k));
					k++;
					if(k == 5) {
						k = 0;
					}
				}
				for (int i = 0; i < Main.spieler.size(); i++) {
					for (int j = 0; j < Main.spieler.get(i).getLaender().size(); j++) {
						System.out.println("Länder von spieler " + Main.spieler.get(i) + " : " + Main.spieler.get(i).getLaender().get(j).getName());
					}
				}
				//Truppen werden verteilt
				for (int i = 0; i < Main.spieler.size(); i++) {
					Main.spieler.get(i).startTruppen(10);
					//Main.spieler.get(i).startTruppen(24);
				}
				Main.spieler.get(0).istDrann = true;
				System.out.println(Main.spieler.get(0).getName() + " ist Drann mit setzten.");
				break;
				
			}
			
			// Spielbrett erstellen
			
			spielbrett = new Spielbrett();
			
			// uebrigen Fenster verschwinden lassen
			
			gui.setVisible(false);
			Aktionen.spielerAnzahl.setVisible(false);
			Aktionen.gui.setVisible(false);
			spielbrett.setVisible(true);
			
		}
		else if(Klick.equals("zurueckBtn"))
		{
			gui.dispose();
		}
		
	}
	
}

