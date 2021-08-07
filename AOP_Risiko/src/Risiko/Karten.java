package Risiko;

import java.util.ArrayList;
import java.util.Random;

public class Karten {

	private static Random zufall = new Random();
	private static String [] Laender = {"Alaska","Alberta","Ontario","Nordwest-Territorium","Weststaaten","Oststaaten","Mittelamerika","Quebec","Grönland","Venezuela","Peru","Brasilien","Argentinien","Island","Skandinavien","Großbritannien","Westeuropa","Mitteleuropa","Südeuropa","Ukraine","Nordwest-Afrika","Ägypten","Ost-Afrika","Kongo","Süd-Afrika","Madagaskar","Mittlerer Osten","Afghanistan","Indien","Ural","Sibirien","Jakutien","Kamtschatka","Irkutsk","Mongolei","Japan","China","Siam","Indonesien","Neu-Guinea","West-Australien","Ost-Australien","Joker","Joker"};	//42 Stück
		
	// Deck als ArrayList aus Gebietskarten erzeugen
	public static void DeckListeGenerieren(ArrayList <Gebietskarte> DeckListe) {
		
		for (int i = 0; i < Laender.length; i++)
		{	
		if (i < 14)
			{ DeckListe.add(new Gebietskarte(Laender[i], "Infanterie")); }
		if (i >= 14 && i < 28)
			{ DeckListe.add(new Gebietskarte(Laender[i], "Kavallerie")); }
		if (i >= 28 && i < 42)
			{ DeckListe.add(new Gebietskarte(Laender[i], "Artillerie")); }
		if (i >= 42 && i < 44)
			{ DeckListe.add(new Gebietskarte(Laender[i], "Joker")); }
		}
	}
	
	//Deck ausgeben	
	public static void DeckListeAnzeigen (ArrayList <Gebietskarte> DeckListe)
	{
		for (Gebietskarte k : DeckListe)
		{ 
			System.out.println(k); 
		}
	}
	
	//Deck mischen
	public static void DeckListeMischen(ArrayList <Gebietskarte> DeckListe) {
		
		for(int i=0; i < 1000 ; i++)
			{
				int a = zufall.nextInt(DeckListe.size()-1);
				DeckListe.add(DeckListe.get(a));
				DeckListe.remove(DeckListe.get(a));
			}
	}
}