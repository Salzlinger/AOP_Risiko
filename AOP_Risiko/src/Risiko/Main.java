package Risiko;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static Scanner input = new Scanner (System.in);
	public static int eingeloesteSets = 0;
	
	public static void main(String[] args) {
		
		
		Gebietskarte [] Deck = new Gebietskarte [44];
		Karten.DeckGenerieren(Deck);
		//Karten.DeckAnzeigen(Deck);
		Karten.DeckMischen(Deck);

		ArrayList <Gebietskarte> DeckListe = new ArrayList<Gebietskarte>(Arrays.asList(Deck));
		
		Spieler Horst = new Spieler("blau", "Horst");
		
		//Horst bekommt Laender zugewiesen
		String [] laender =  {"Peru", "Argentinien", "Venezuela", "Brasilien", "Island", "Nordwest-Afrika"};
		for (int i = 0; i < laender.length; i++)
		{
			Horst.getLaender().add(new Laender (laender[i]));
		}
		System.out.println("Horst besitzt folgende Länder: " + Horst.getLaender() );

		//Set einlösen?
				if (Horst.SetKomplett() == true)
				{
					System.out.println("Horst hat ein komplettes Set. Möchtest du es einlösen?");
					if (input.next().equals("ja")  )
					{
						Horst.SetEinloesen(DeckListe);				
						eingeloesteSets++;
						System.out.println("Das Deck enthält nun wieder: " + DeckListe);
					}
				}	else {System.out.println("Horst hat noch kein komplettes Set"); }
				
		System.out.println("Horst bekommt " + Horst.TruppenErhalten() + " Truppen");
		
		//Horst greift an
		Laender a = new Laender ("A");
		Laender b = new Laender ("B");	
		Horst.Angreifen(a,b);		
		
		//Horst zieht Karte nach gewonnenem Kampf
		Horst.KarteZiehen(DeckListe);
		System.out.println("Das Deck enthält nun nur noch: " + DeckListe);
		System.out.println("Spielerhand Horst: " + Horst.getHand());
			
		
	}

}
