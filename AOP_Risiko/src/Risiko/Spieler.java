package Risiko;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Spieler {

	private String farbe;
	private String name;
	private String [] karten;
	private String [] laender;
	private ArrayList <Gebietskarte> hand = new ArrayList <Gebietskarte>();
	private int truppen;
	
	public Spieler (String farbe, String name) {
		this.farbe = farbe;
		this.name= name;
	}
	
	
	public int TruppenErhalten() {
		truppen = laender.length/3 + gotContinent(laender);
		return truppen;
	}
	
	public void GebietskartenErhalten(ArrayList <Gebietskarte> DeckListe) {
		// if (Angreifen() == sieg)
		// Spieler erh�lt 1 Karte vom Stapel
		//Spieler.KarteZiehen(DeckListe);
	}
	
	public void KarteZiehen(ArrayList <Gebietskarte> DeckListe){
			hand.add(DeckListe.get(DeckListe.size()-1));
			DeckListe.remove(DeckListe.get(DeckListe.size()-1));
	}
	
	public ArrayList <Gebietskarte> getHand() {
		return hand;
	}

	public void setHand(ArrayList <Gebietskarte> hand) {
		this.hand = hand;
	}
	
	public boolean SetKomplett () {
		
		int i = 0;
		int k = 0;
		int a = 0;
		int j = 0;
		
		for (int z = 0; z < hand.size(); z++)
		{
			if (hand.get(z).getTyp() == "Infanterie") { i++; }
			if (hand.get(z).getTyp() == "Kavallerie") { k++; }
			if (hand.get(z).getTyp() == "Artillerie") { a++; }
			if (hand.get(z).getTyp() == "Joker")	{ j++; }
		}
		
		if (i >= 3 || k >= 3 || a >= 3 || (i >= 1 && k >= 1 && a >= 1))
		{ return true; } 	
		else if ((j==1 && (a>=1||k>=1||a>=1) && (a>=1||k>=1||a>=1)) || (j==2 && (a>=1||k>=1||a>=1))) 
			{ return true; }
				else 
				{ return false; }
	}	
	
	public void KartenBenutzen() {
		if (hand.size() > 5)
		{
			System.out.println("Spieler " + name + " muss ein Set einl�sen, da er mehr als 5 Gebietskarten besitzt!");
			//Spieler muss Set einl�sen
		}
		else if (SetKomplett() == true)
			{
			System.out.println(name + " hat die M�glichkeit ein Set einzul�sen. M�chtest du?");
			// else { Spieler kann Set einl�sen}
			
			}
	}
	
	
	public void SetEinloesen (ArrayList <Gebietskarte> DeckListe) {
		
		//Einfach einl�sen nach bester Option?
		System.out.println( "Spieler " + name + "\nWir w�hlen f�r dich das bestm�gliche Set aus!");
		
		//Karten z�hlen
		int i = 0;
		int k = 0;
		int a = 0;
		int j = 0;
		
		for (int z = 0; z < hand.size(); z++)
		{
			if (hand.get(z).getTyp() == "Infanterie") { i++; }
			if (hand.get(z).getTyp() == "Kavallerie") { k++; }
			if (hand.get(z).getTyp() == "Artillerie") { a++; }
			if (hand.get(z).getTyp() == "Joker")	{ j++; }
		}
		
		//bestes Set ausw�hlen
		if (i >= 1 && k >= 1 && a >= 1)	//Set aus 3 verschiedenen
		{
			for (int z = 0; z < hand.size(); z++)
			{
				if (hand.get(z).getTyp() == "Infanterie" && i != 0 ) 
					{  	
					DeckListe.add(hand.get(z));
					hand.remove(z); 
					i = 0;
					}
				if (hand.get(z).getTyp() == "Kavallerie" && k != 0) 
					{ 
					DeckListe.add(hand.get(z));
					hand.remove(z); 
					k = 0; 
					}
				if (hand.get(z).getTyp() == "Artillerie" && a != 0) 
					{ 
					DeckListe.add(hand.get(z));
					hand.remove(z); 
					a = 0; 
					}
			}
			
		} else 	if (i >= 3 || k >= 3 || a >= 3)	// Set aus 3 Gleichen
				{
					if (i >= 3)
					{
						int p = 0;
						for (int z = 0; z < hand.size(); z++)
						{
							if (hand.get(z).getTyp() == "Infanterie" && p < 3 ) 
								{  	
								DeckListe.add(hand.get(z));
								hand.remove(z);
								p++;
								}
						}
					}
					
					if (k >= 3)
					{
						int p = 0;
						for (int z = 0; z < hand.size(); z++)
						{
							if (hand.get(z).getTyp() == "Infanterie" && p < 3 ) 
								{  	
								DeckListe.add(hand.get(z));
								hand.remove(z);
								p++;
								}
						}
					}
					if (a >= 3)
					{
						int p = 0;
						for (int z = 0; z < hand.size(); z++)
						{
							if (hand.get(z).getTyp() == "Infanterie" && p < 3 ) 
								{  	
								DeckListe.add(hand.get(z));
								hand.remove(z);
								p++;
								}
						}
					}
					
			
				} else 	if (j>=1 && (a>=1||k>=1||a>=1) && (a>=1||k>=1||a>=1)) // Set mit Joker
						{
							if (hand.size() == 3)
							{
								for (int z = 0; z < hand.size(); z++)
								{
								DeckListe.add(hand.get(z));
								hand.remove(z);
								}
							}
							if (hand.size() >= 4)
							{
								for (int z = 0; z < hand.size(); z++)
								{
								if (hand.get(z).getTyp() == "Joker" && j != 0 ) 
								{  	
								DeckListe.add(hand.get(z));
								hand.remove(z); 
								j = 0;
								}
								}
								int p = 0;
								for (int z = 0; z < hand.size(); z++)
								{
								if (hand.get(z).getTyp() == "Infanterie" && p < 2) 
								{  	
								DeckListe.add(hand.get(z));
								hand.remove(z); 
								p++;
								}
								if (hand.get(z).getTyp() == "Kavallerie" && p < 2) 
								{ 
								DeckListe.add(hand.get(z));
								hand.remove(z); 
								p++; 
								}
								if (hand.get(z).getTyp() == "Artillerie" && p < 2) 
								{ 
								DeckListe.add(hand.get(z));
								hand.remove(z); 
								p++; 
								}
								}
							}
						}	
	}							
	
	
	
	
	
	//auf Aktionen auslagern?
	public void TruppenVerteilen(int truppen) {
		//auf GUI truppen verteilen bis 0
	}
	
	public void Angreifen() {
		// Eingabe Truppen Angreifer
		// ziehen wieviele Truppen Verteidiger hat
		// W�rfelkampf!
		// Ergebnis speichern
	}
	
	public void TruppenBewegen() {
		// beliebig viele Truppen aus einem Land in ein verbundenes Land verlagern
	}
	
	
	
	
	
	
	
	
	//eventuell in L�nder/Kontinent �berf�hren?
	public int gotContinent(String [] laender) {
		List <String> liste = Arrays.asList(laender);
		int KontinentTruppen = 0;
		
		//Nordamerika
		if (liste.contains("Quebec")) // rest erg�nzen!!!!!!!!!!!!!!1
		{
			KontinentTruppen += 5;
		}
		
		//S�damerika
		if (liste.contains("Peru") && liste.contains("Venezuela") 
				&& liste.contains("Brasilien") && liste.contains("Argentinien"))
		{
			KontinentTruppen += 2;
		}
		
		//Europa
		
		//Afrika
		
		//Asien
		
		//Australien
		
		return KontinentTruppen;
	}


	
	

}
