package Risiko;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static Scanner input = new Scanner (System.in);
	public static int eingeloesteSets = 0;
	
	@SuppressWarnings("unlikely-arg-type")
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
		
		//Laender zuweisen
		String [] laender =  {"Peru", "Argentinien", "Venezuela", "Brasilien", "Mittelamerika", "Nordwest-Afrika"};
		ArrayList <Laender> lands = new ArrayList <Laender>();
		for (int i = 0; i < laender.length; i++)
		{
			lands.add(new Laender (laender[i]));
		}
		Horst.setLaender(lands);
		
		System.out.println("Horst besitzt folgende Länder: " + Horst.getLaender() );
		System.out.println("Horst hat " + Horst.getLaender().get(0).getLand().contains("Peru"));
		
		
		
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
		
		System.out.println("Horst bekommt " + Horst.TruppenErhalten() + " Truppen");
		
		
		//Set einlösen
		if (Horst.SetKomplett() == true)
		{
			String eingabe = "";
			System.out.println("Horst hat ein komplettes Set");
			System.out.println("Möchtest du es einlösen?");
			//eingabe = input.next();
			//if (eingabe.equals("ja")  )
			{
				
				Horst.SetEinloesen(DeckListe);				
				eingeloesteSets++;
				Karten.DeckListeMischen(DeckListe);
				System.out.println("Das Deck enthält nun wieder: " + DeckListe);
				
				//Deck Mischen
			}
			
		}	else {System.out.println("Horst hat noch kein komplettes Set"); }
		
		
		
		
		System.out.println("Spielerhand Horst: " + Horst.getHand());
		System.out.println("Das Deck enthält nun nur noch: " + DeckListe);
		
		
		
		System.out.println("Horst bekommt " + Horst.TruppenErhalten() + " Truppen");
		
		
		
		
		
		
		
		
		
		
		
		
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
