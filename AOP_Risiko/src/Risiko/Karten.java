package Risiko;

import java.util.HashMap;
import java.util.Random;

public class Karten {

	private static String [] Laender = {"Alaska","Alberta","Ontario","Nordwest-Territorium","Weststaaten","Oststaaten","Mittelamerika","Quebec","Grönland","Venezuela","Peru","Brasilien","Argentinien","Island","Skandinavien","Großbritannien","Westeuropa","Mitteleuropa","Südeuropa","Ukraine","Nordwest-Afrika","Ägypten","Ost-Afrika","Kongo","Süd-Afrika","Madagaskar","Mittlerer Osten","Afghanistan","Indien","Ural","Serbien","Jakutien","Kamtschatka","Irkutsk","Mongolei","Japan","China","Siam","Indonesien","Neu-Guinea","West-Australien","Ost-Australien","Joker","Joker"};	//42 Stück
	//private String [] Jokerkarten;	//2 Stück
	//private String [] Missionskarten;
	
////////// Deck als Array aus Gebietskarten erzeugen
	public static void DeckGenerieren(Gebietskarte [] deck) {
		
		for (int i = 0; i < deck.length; i++)
		{	
		if (i < 14)
			{ deck[i] = new Gebietskarte(Laender[i], "Infanterie"); }
		if (i >= 14 && i < 28)
			{ deck[i] = new Gebietskarte(Laender[i], "Kavallerie"); }
		if (i >= 28 && i < 42)
			{ deck[i] = new Gebietskarte(Laender[i], "Artillerie"); }
		if (i >= 42 && i < 44)
			{ deck[i] = new Gebietskarte(Laender[i], "Joker"); }
		}
	}
	
	public static void DeckAnzeigen (Gebietskarte [] deck)
	{
		for (Gebietskarte k : deck)
		{ System.out.println(k); }
	}
	
	public static void DeckMischen(Gebietskarte [] deck) {
		
		Random zufall = new Random();
		
		for(int i=0; i < 100 ; i++)
			{
			int a = zufall.nextInt(44);
			int b = zufall.nextInt(44);
			Gebietskarte puffer = deck[a];
			deck[a] = deck[b];
			deck[b] = puffer;
			}
	}
	
}


/////////// Deck als Hashmap aus Gebietskarten erzeugen 
	/*
	public static void KartenGenerieren (HashMap <String, Gebietskarte> KartenDeck) {
		
			System.out.println("Es werden " + Laender.length + " Gebietskarten generiert");
		
			String typ  = "Infanterie";
			int i = 0;
			while (i < 14)
				{ KartenDeck.put(Laender[i], new Gebietskarte(Laender[i], typ)); 
				  i++;
				  }
			typ  = "Kavallerie";
			while (i < 28)
				{ KartenDeck.put(Laender[i], new Gebietskarte(Laender[i], typ)); 
				  i++;
				  }
			typ  = "Artillerie";
			while (i < 42)
				{ KartenDeck.put(Laender[i], new Gebietskarte(Laender[i], typ)); 
				  i++;
				  }
			while (i < 44)
			{ KartenDeck.put(Laender[i], new Gebietskarte(Laender[i], "Joker")); 
			  i++;
			  }
			
			System.out.println("Das Deck enthält nun folgende Karten: ");
			for (int index = 0; index < Laender.length; index++) 
			{
				System.out.println(KartenDeck.get(Laender[index]).getName() + " ist eine " +KartenDeck.get(Laender[index]).getTyp());
			}
	}
	
	public static void KarteZiehen(HashMap <String, Gebietskarte> KartenDeck) {
		Random zufall = new Random();
		int zahl = zufall.nextInt(44);
		System.out.println("zufällige Karte: " + KartenDeck.get(Laender[zahl]).getName() + " ist eine " +KartenDeck.get(Laender[zahl]).getTyp());

	}
	*/
	
	
	

