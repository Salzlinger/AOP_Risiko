package Risiko;

import java.util.ArrayList;
import java.util.Random;

public class Karten {

	private static Random zufall = new Random();
	private static String [] Laender = {"Alaska","Alberta","Ontario","Nordwest-Territorium","Weststaaten","Oststaaten","Mittelamerika","Quebec","Grönland","Venezuela","Peru","Brasilien","Argentinien","Island","Skandinavien","Großbritannien","Westeuropa","Mitteleuropa","Südeuropa","Ukraine","Nordwest-Afrika","Ägypten","Ost-Afrika","Kongo","Süd-Afrika","Madagaskar","Mittlerer Osten","Afghanistan","Indien","Ural","Sibirien","Jakutien","Kamtschatka","Irkutsk","Mongolei","Japan","China","Siam","Indonesien","Neu-Guinea","West-Australien","Ost-Australien","Joker","Joker"};	//42 Stück
	//private String [] Missionskarten;
		
	// Deck als Array aus Gebietskarten erzeugen
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
	
	//Deck ausgeben
	public static void DeckAnzeigen (Gebietskarte [] deck)
	{
		for (Gebietskarte k : deck)
		{ System.out.println(k); }
	}
	
	//Deck Mischen
	public static void DeckMischen(Gebietskarte [] deck) {
		
		for(int i=0; i < 100 ; i++)
			{
			int a = zufall.nextInt(44);
			int b = zufall.nextInt(44);
			Gebietskarte puffer = deck[a];
			deck[a] = deck[b];
			deck[b] = puffer;
			}
	}
	
	// Deck (als ArrayList) mischen
	public static void DeckListeMischen(ArrayList <Gebietskarte> DeckListe) {
		
		for(int i=0; i < 1000 ; i++)
			{
			int a = zufall.nextInt(DeckListe.size()-1);
			DeckListe.add(DeckListe.get(a));
			DeckListe.remove(DeckListe.get(a));
			}
	}
}

	
	
	

