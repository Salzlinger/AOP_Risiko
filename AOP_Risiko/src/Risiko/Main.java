package Risiko;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static Scanner input = new Scanner (System.in);
	
	public static void main(String[] args) {
		
		//RisikoGUI GUI = new RisikoGUI();
		
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
		ArrayList <Gebietskarte> DeckListe = new ArrayList<Gebietskarte>(Arrays.asList(Deck));
		
		/*
		System.out.println("Welche Farbe möchtest du? ");
		String farbe = input.nextLine();
		System.out.println("Wie lautet dein Name?");
		String username =input.nextLine();
		Spieler Horst = new Spieler(farbe, username);
		*/
		
		
		Spieler Horst = new Spieler("blau", "Horst");
		Spieler Anna = new Spieler ("rot", "Anna");
		Horst.KarteZiehen(DeckListe);
		Horst.KarteZiehen(DeckListe);
		Horst.KarteZiehen(DeckListe);
		Horst.KarteZiehen(DeckListe);
		
		System.out.println("Das Deck enthält nun nur noch: " + DeckListe);
		//System.out.println("Spielerhand: " + Spieler.getHand());
		
		Anna.KarteZiehen(DeckListe);
		Horst.KarteZiehen(DeckListe);
		Horst.KarteZiehen(DeckListe);
		Anna.KarteZiehen(DeckListe);
		Anna.KarteZiehen(DeckListe);
		
		System.out.println("Spielerhand Horst: " + Horst.getHand());
		System.out.println("Spielerhand Anna: " + Anna.getHand());
		
		
		
		if (Horst.SetKomplett() == true)
		{
			System.out.println("Horst hat ein komplettes Set");
			System.out.println("Möchtest du es einlösen?");
			
			if (input.nextLine() == "ja")
			{
				Horst.SetEinloesen(DeckListe);
				//Deck Mischen
			}
			
		}	else {System.out.println("Horst hat noch kein komplettes Set"); }
		
		System.out.println("Spielerhand Horst: " + Horst.getHand());
		System.out.println("Das Deck enthält nun nur noch: " + DeckListe);
		
		
		/*
		if (Anna.SetKomplett() == true)
		{
			System.out.println("Anna hat ein komplettes Set");
		}	else {System.out.println("Anna hat noch kein komplettes Set"); }
		
		Horst.KartenBenutzen();
		Anna.KartenBenutzen();
		*/
	}

}
