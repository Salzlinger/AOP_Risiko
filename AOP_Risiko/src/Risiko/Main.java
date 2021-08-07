package Risiko;

import java.util.ArrayList;

public class Main {

	public static int eingeloesteSets = 0;
	public static ArrayList <Gebietskarte> Deck = new ArrayList <Gebietskarte>();;

	public static void main(String[] args) {

		// Laender initialisieren
		
		Laender.iniLaender();

		//Deck aus Gebietskarten erzeugen
		
		Karten.DeckListeGenerieren(Deck);
		Karten.DeckListeMischen(Deck);

		//GUI erzuegen/aufrufen

		Graphen gra = new Graphen();
		RisikoGUI gui = new RisikoGUI();
		Aktionen akt = new Aktionen(gra, gui);
	}
}
