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
			Spieler.spieler1 = new Spieler ("BLUE", spielerNamen.get(0) );
			Spieler.spieler.add(Spieler.spieler1);
			spielerNamen.add(gui.getSpielerTextField(2));
			Spieler.spieler2 = new Spieler ("RED", spielerNamen.get(1));
			Spieler.spieler.add(Spieler.spieler2);
			spielerNamen.add(gui.getSpielerTextField(3));
			Spieler.spieler3 = new Spieler ("YELLOW", spielerNamen.get(2));
			Spieler.spieler.add(Spieler.spieler3);

			if(gra.getSpielerAnzahl() > 3) 
			{
				spielerNamen.add(gui.getSpielerTextField(4));
				Spieler.spieler4 = new Spieler ("GREEN", spielerNamen.get(3));
				Spieler.spieler.add(Spieler.spieler4);
			}
			
			if(gra.getSpielerAnzahl() > 4) 
			{
				spielerNamen.add(gui.getSpielerTextField(5));
				Spieler.spieler5 = new Spieler ("WHITE", spielerNamen.get(4));
				Spieler.spieler.add(Spieler.spieler5);
			}
			
			//Spieler randomizen
			
			for(int i=0; i < 10 ; i++)
			{
			int a = zufall.nextInt(Spieler.spieler.size());
			int b = zufall.nextInt(Spieler.spieler.size());
			//System.out.print(Spieler.spieler.size());
			Spieler puffer = Spieler.spieler.get(a);
			Spieler.spieler.set(a, Spieler.spieler.get(b));
			Spieler.spieler.set(b, puffer);
			}
			System.out.println("Folgende Spielerreihenfolge wurde ausgewürfelt: " + Spieler.spieler);

			switch (gra.getSpielerAnzahl()) {
			case 3:	
				for (int n = 0; n < Spieler.spieler.size(); n++) {
					Spieler.spieler.get(n).startTruppen(35);
				}
				//Länder werden verteilt
				
				int k = 0;
				ArrayList<Laender> drinn = new ArrayList<Laender>();
				for (int i = 0; i < 42; i++) {
						int z = zufall.nextInt(42);
						if (i > 0) {
							while(drinn.contains(Laender.liste[z])) {
								System.out.println("While");
								z = zufall.nextInt(42);
							}
						}
					Spieler.spieler.get(k).getLaender().add(Laender.liste[z]);
					Laender.liste[z].setBesitzer(Spieler.spieler.get(k));
					Spieler.spieler.get(k).startTruppen(Spieler.spieler.get(k).getTruppen() - 1);
					drinn.add(Laender.liste[z]);
					k++;
					if(k == 3) {
						k = 0;
					}
				}
				for (int i = 0; i < Spieler.spieler.size(); i++) {
					for (int j = 0; j < Spieler.spieler.get(i).getLaender().size(); j++) {
						System.out.println("Länder von spieler " + Spieler.spieler.get(i) + " : " + Spieler.spieler.get(i).getLaender().get(j).getName());
					}
				}
				Spieler.spieler.get(0).istDran = true;
				System.out.println(Spieler.spieler.get(0).getName() + " ist Drann mit setzten.");
				break;
			case 4:
				//Länder werden verteilt
				for (int n = 0; n < Spieler.spieler.size(); n++) {
					Spieler.spieler.get(n).startTruppen(30);
				}
				int u = 0;
				ArrayList<Laender> drinn1 = new ArrayList<Laender>();
				for (int i = 0; i < 42; i++) {
						int z = zufall.nextInt(42);
						if (i > 0) {
							while(drinn1.contains(Laender.liste[z])) {
								System.out.println("While");
								z = zufall.nextInt(42);
							}
						}
					Spieler.spieler.get(u).getLaender().add(Laender.liste[z]);
					Laender.liste[z].setBesitzer(Spieler.spieler.get(u));
					Spieler.spieler.get(u).startTruppen(Spieler.spieler.get(u).getTruppen() - 1);
					drinn1.add(Laender.liste[z]);
					u++;
					if(u == 4) {
						u = 0;
					}
				}
				for (int i = 0; i < Spieler.spieler.size(); i++) {
					for (int j = 0; j < Spieler.spieler.get(i).getLaender().size(); j++) {
						System.out.println("Länder von spieler " + Spieler.spieler.get(i) + " : " + Spieler.spieler.get(i).getLaender().get(j).getName());
					}
				}
				Spieler.spieler.get(0).istDran = true;
				System.out.println(Spieler.spieler.get(0).getName() + " ist Drann mit setzten.");
				break;
			case 5:
				//Länder werden verteilt
				for (int n = 0; n < Spieler.spieler.size(); n++) {
					Spieler.spieler.get(n).startTruppen(25);
				}
				int a = 0;
				ArrayList<Laender> drinn2 = new ArrayList<Laender>();
				for (int i = 0; i < 42; i++) {
						int z = zufall.nextInt(42);
						if (i > 0) {
							while(drinn2.contains(Laender.liste[z])) {
								System.out.println("While");
								z = zufall.nextInt(42);
							}
						}
					Spieler.spieler.get(a).getLaender().add(Laender.liste[z]);
					Laender.liste[z].setBesitzer(Spieler.spieler.get(a));
					Spieler.spieler.get(a).startTruppen(Spieler.spieler.get(a).getTruppen() - 1);
					drinn2.add(Laender.liste[z]);
					a++;
					if(a == 5) {
						a = 0;
					}
				}
				for (int i = 0; i < Spieler.spieler.size(); i++) {
					for (int j = 0; j < Spieler.spieler.get(i).getLaender().size(); j++) {
						System.out.println("Länder von spieler " + Spieler.spieler.get(i) + " : " + Spieler.spieler.get(i).getLaender().get(j).getName());
					}
				}
				Spieler.spieler.get(0).istDran = true;
				System.out.println(Spieler.spieler.get(0).getName() + " ist Drann mit setzten.");
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