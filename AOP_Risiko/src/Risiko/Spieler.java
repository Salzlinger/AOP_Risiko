package Risiko;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Spieler {

	private String farbe;
	private String name;
	private String [] laender;
	private ArrayList <Gebietskarte> hand = new ArrayList <Gebietskarte>();
	private boolean infanterieSet = false;
	private boolean kavallerieSet = false;
	private boolean artillerieSet = false;
	private boolean gemischtesSet = false;
	private boolean jokerSet = false;
	private int truppen = 0;
	private int eingeloesteSets = 0;
	
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
		// Spieler erhält 1 Karte vom Stapel
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
		{ 
			if (i >= 3)
			{ this.infanterieSet = true; }
			if (k >= 3)
			{ this.kavallerieSet = true; }
			if (a >= 3)
			{ this.artillerieSet = true; }
			if (i >= 1 && k >= 1 && a >= 1)
			{ this.gemischtesSet = true; }
			
			return true; 
		} 	
		else if ((j==1 && (a>=1||k>=1||a>=1) && (a>=1||k>=1||a>=1)) || (j==2 && (a>=1||k>=1||a>=1))) 
			{ 
				this.jokerSet = true;
				return true; 
			}
				else 
				{ return false; }
	}	
	
	
	public void KartenBenutzen() {
		if (hand.size() > 5)
		{
			System.out.println("Spieler " + name + " muss ein Set einlösen, da er mehr als 5 Gebietskarten besitzt!");
			//Spieler muss Set einlösen
		}
		else if (SetKomplett() == true)
			{
			System.out.println(name + " hat die Möglichkeit ein Set einzulösen. Möchtest du?");
			// else { Spieler kann Set einlösen}
			
			}
	}
	
	
	// hand.add(DeckListe.get(DeckListe.size()-1));
	// DeckListe.remove(DeckListe.get(DeckListe.size()-1));
	
	public boolean SetEinloesen (ArrayList <Gebietskarte> DeckListe) {

		System.out.println( "Spieler " + name + "\nWir wählen für dich das bestmögliche Set aus!");
		
		int p = 0;
		int size = hand.size()-1;
		if (infanterieSet == true)
		{ 
			System.out.println( name + " hat " + hand.size() + " Karten und ein vollständiges Infanterie Set"); 
			for (int z = size; z>=0; z-- )
			{
				if (hand.get(z).getTyp() == "Infanterie" && p < 3 ) 
				{  	
				DeckListe.add(hand.get(z));
				hand.remove(z);
				p++;
				}	
			}
		}
		if (kavallerieSet == true)
		{ 
			System.out.println( name + " hat " + hand.size() + " Karten und ein vollständiges Kavallerie Set"); 
			for (int z = size; z>=0; z-- )
			{	
				if (hand.get(z).getTyp() == "Kavallerie" && p < 3 ) 
				{  	
				DeckListe.add(hand.get(z));
				hand.remove(z);
				p++;
				}	
			}
		}
		if (artillerieSet == true)
		{ 
			System.out.println( name + " hat " + hand.size() + " Karten und ein vollständiges Artillerie Set");
			for (int z = size; z>=0; z-- )
			{
				if (hand.get(z).getTyp() == "Artillerie" && p < 3 ) 
				{  
				DeckListe.add(hand.get(z));
				hand.remove(z);
				p++;
				}
			}
		}
		
		
		if (gemischtesSet == true)
		{ System.out.println( name + " hat " + hand.size() + " Karten und ein vollständiges gemischtes Set"); 
		boolean infaEntnommen = false;
		boolean kavaEntnommen = false;
		boolean artiEntnommen = false;
		
		for (int z = size; z >= 0; z--)
			{
				switch (hand.get(z).getTyp()) 
				{
				case "Infanterie": if (infaEntnommen == false) 
										{
										DeckListe.add(hand.get(z));
										hand.remove(z); 
										infaEntnommen = true;
										break;
										}
				case "Kavallerie": if (kavaEntnommen == false) 
										{
										DeckListe.add(hand.get(z));
										hand.remove(z); 
										kavaEntnommen = true;
										break;
										}
				case "Artillerie": if (artiEntnommen == false) 
										{
										DeckListe.add(hand.get(z));
										hand.remove(z); 
										artiEntnommen = true;
										break;
										}
				}
			} 
		} 
		
		if (jokerSet == true)
		{ System.out.println( name + " hat ein vollständiges Joker Set"); 
		boolean jokerEntnommen = false;
		for (int z = size; z >= 0; z--)
			{ 	if (hand.get(z).getTyp() == "Joker" && jokerEntnommen == false)	
				{
				DeckListe.add(hand.get(z));
				hand.remove(z); 
				jokerEntnommen = true;
				}
				else if (hand.get(z).getTyp() != "Joker" && p < 2)
						{
						DeckListe.add(hand.get(z));
						hand.remove(z);
						p++;
						} 
			}
		}
	eingeloesteSets++;
	return true;	
	//eingelöste Sets hoch zählen
	}
			
	
	//auf Aktionen auslagern?
	public void TruppenVerteilen(int truppen) {
		//auf GUI truppen verteilen bis 0
	}
	
	public void Angreifen() {
		// Eingabe Truppen Angreifer
		// ziehen wieviele Truppen Verteidiger hat
		// Würfelkampf!
		// Ergebnis speichern
	}
	
	public void TruppenBewegen() {
		// beliebig viele Truppen aus einem Land in ein verbundenes Land verlagern
	}
	
	
	
	
	
	
	
	
	//eventuell in Länder/Kontinent überführen?
	public int gotContinent(String [] laender) {
		List <String> liste = Arrays.asList(laender);
		int KontinentTruppen = 0;
		
		//Nordamerika
		if (liste.contains("Quebec")) // rest ergänzen!!!!!!!!!!!!!!1
		{
			KontinentTruppen += 5;
		}
		
		//Südamerika
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
