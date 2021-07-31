package Risiko;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		RisikoGUI GUI = new RisikoGUI();
		
		/*
		 * Kartendeck als Hashmap generieren
		HashMap <String, Gebietskarte> KartenDeck = new HashMap <String, Gebietskarte>();
		Karten.KartenGenerieren(KartenDeck);
		Karten.KarteZiehen(KartenDeck);
		*/
		
		Gebietskarte [] Deck = new Gebietskarte [44];
		System.out.println(Deck.length);
		Karten.DeckGenerieren(Deck);
		Karten.DeckAnzeigen(Deck);
		Karten.DeckMischen(Deck);
		System.out.println("------gemischt-------");
		
		Karten.DeckAnzeigen(Deck);
		Spieler Horst = new Spieler("rot", "Horst");
		Spieler.KarteZiehen(Deck);

	}

}
