package Risiko;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Spieler {


	public static Scanner input = new Scanner (System.in);
	
	private String farbe;
	private String name;
	private Graphen laenderVerbunden = new Graphen();
	private HashMap <String, Laender> laenderHash = new HashMap <String, Laender>();
	private ArrayList <Laender> laenderArray = new ArrayList <Laender>();
	private static ArrayList <Gebietskarte> hand = new ArrayList <Gebietskarte>();
	private boolean infanterieSet = false;
	private boolean kavallerieSet = false;
	private boolean artillerieSet = false;
	private boolean gemischtesSet = false;
	private boolean jokerSet = false;
	private boolean setEingeloest = false;
	private boolean wait = false;
	private int truppen = 0;
	static int gebiet = 0;
	public boolean istDran = false;
	public static Spieler spieler1;
	public static Spieler spieler2;
	public static Spieler spieler3;
	public static Spieler spieler4;
	public static Spieler spieler5;
	public static ArrayList<Spieler> spieler = new ArrayList<Spieler>();
	
	
	public int getGebiet() {
		return gebiet;
	}
	
	public void setGebiet(int g) {
		this.gebiet = g;
	}
	public int getTruppen() {
		return truppen;
	}
	
	public void startTruppen(int t) {
		this.truppen = t;
	}


	private int setBonus = 0;
	
	public boolean isWait() {
		return wait;
	}


	public void setWait(boolean wait) {
		this.wait = wait;
	}
	
	public String getName() {
		return name;
	}


	public Spieler (String farbe, String name) {
		this.farbe = farbe;
		this.name= name;
	}
	
	public static void KarteZiehen(ArrayList <Gebietskarte> Deck){
		hand.add(Deck.get(Deck.size()-1));
		Deck.remove(Deck.get(Deck.size()-1));		
		}


	//Spieler Hand getter/setter
	public ArrayList <Gebietskarte> getHand() {
		return hand;
	}
	public void setHand(ArrayList <Gebietskarte> hand) {
		this.hand = hand;
	}

	//Spieler Länder getter/setter
	public ArrayList <Laender> getLaender() {
		return laenderArray;
	}
	public void setLaender(ArrayList<Laender> land) {
		this.laenderArray = land;
		for (int i = 0; i < land.size();i++)
		{
		laenderHash.put(land.get(i).getName(), land.get(i));
		}
	}
	
	public void TruppenErhalten() {
		if (SetKomplett())
		{
			if (hand.size() > 5)
			{
				System.out.println("Spieler " + name + " hat mehr als 5 Karten auf der Hand und muss ein Set einlösen!");
				SetEinloesen(Main.Deck);
				setEingeloest=true;
				Gebietskarten.eingeloesteSets++;
			} else 
				{
				System.out.println("Spieler " + name + " hat ein komplettes Set auf der Hand. Möchtest du es einlösen?");
				if (input.next().equals("ja")  )
				{
					SetEinloesen(Main.Deck);
					setEingeloest=true;
					Gebietskarten.eingeloesteSets++;
				}
			}
		}
		if(setEingeloest)
		{
		berechneSetBonus();
		System.out.println("Spieler: " + name + " erhält " + setBonus + " Truppen für eingeloeste Sets");
		setEingeloest = false;
		}
		if (laenderArray.size() < 9)
		{
		System.out.println("Spieler " + name + "erhält 3 Truppen für besetzte Länder");
		truppen = 3  + setBonus + Kontinent.besitztKontinent();
		} else  {
				System.out.println("Spieler " + name + " erhält " + (laenderArray.size()/3) + " Truppen für besetzte Länder");
				truppen = laenderArray.size()/3 + setBonus + Kontinent.besitztKontinent();
				}		
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
		else if ((j==1 && (a >= 1|| k >= 1|| a >= 1) && (a >=1 || k >= 1|| a >= 1)) || (j == 2 && (a >=1 || k >=1 || a >= 1))) 
			{ 
				this.jokerSet = true;
				return true; 
			}
				else 
				{ 
					return false; 
				}
	}	
	
	
	public boolean SetEinloesen (ArrayList <Gebietskarte> Deck) {

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
				Deck.add(hand.get(z));
				hand.remove(z);
				p++;
				}	
			}
		}
		else if (kavallerieSet == true)
		{ 
			System.out.println( name + " hat " + hand.size() + " Karten und ein vollständiges Kavallerie Set"); 
			for (int z = size; z>=0; z-- )
			{	
				if (hand.get(z).getTyp() == "Kavallerie" && p < 3 ) 
				{  	
				Deck.add(hand.get(z));
				hand.remove(z);
				p++;
				}	
			}
		}
		else if (artillerieSet == true)
		{ 
			System.out.println( name + " hat " + hand.size() + " Karten und ein vollständiges Artillerie Set");
			for (int z = size; z>=0; z-- )
			{
				if (hand.get(z).getTyp() == "Artillerie" && p < 3 ) 
				{  
				Deck.add(hand.get(z));
				hand.remove(z);
				p++;
				}
			}

		}
		
		else if (gemischtesSet == true)
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
										Deck.add(hand.get(z));
										hand.remove(z); 
										infaEntnommen = true;
										break;
										}
				case "Kavallerie": if (kavaEntnommen == false) 
										{
										Deck.add(hand.get(z));
										hand.remove(z); 
										kavaEntnommen = true;
										break;
										}
				case "Artillerie": if (artiEntnommen == false) 
										{
										Deck.add(hand.get(z));
										hand.remove(z); 
										artiEntnommen = true;
										break;
										}
				}
			} 
		} 
		
		else if (jokerSet == true)
		{ System.out.println( name + " hat ein vollständiges Joker Set"); 
		boolean jokerEntnommen = false;
		for (int z = size; z >= 0; z--)
			{ 	
				if (hand.get(z).getTyp() == "Joker" && jokerEntnommen == false)	
				{
					Deck.add(hand.get(z));
					hand.remove(z); 
					jokerEntnommen = true;
				}
						else if (hand.get(z).getTyp() != "Joker" && p < 2)
						{
							Deck.add(hand.get(z));
							hand.remove(z);
							p++;
						} 
			}
		}
	Karten.DeckListeMischen(Deck);
	return true;	
	}
			
	public void berechneSetBonus() {
		int a = Gebietskarten.eingeloesteSets;
		if (a == 0)
		{
			this.setBonus = 0;
		}
		if (a > 0 && a < 6 )
		{ 
			this.setBonus = 4 + (a-1)*2; 
		}
		if (a>= 6)
		{ 
			this.setBonus = 10 + 5*(a-5); 
		}
	}
	
	
	//auf Aktionen auslagern?
	public void TruppenVerteilen(Laender land) {
		if(laenderArray.contains(land)) 
		{
			truppen  -= 1;
			int m = land.getTruppen();
			land.setTruppen(m + 1);
		} else 
			{
				System.out.println("Dir gehört dieses Land nicht.");
			}
		//<<<<<Funktion in GUI zum verteilen der Truppen
	}
	
	public void TruppenEntfernen(Laender land) {
		if(laenderArray.contains(land)) 
		{
			truppen  += 1;
			int m = land.getTruppen();
			land.setTruppen(m - 1);
		} else 
			{
				System.out.println("Dir gehört dieses Land nicht.");
			}
		//<<<<<Funktion in GUI zum verteilen der Truppen
	}
	
	public boolean Angreifen(Laender a, Laender b) {
		setLaender(laenderArray); //Länder werden in Hashmap umgeschrieben
		if (a.getNachbarn().contains(b))
		{
		System.out.println("Ein Angriff ist möglich, da die angegebenen Länder Nachbarn sind");
			
			if (Wuerfel.Wuerfelkampf(a,b)== true)
				{
				b.setBesitzer(this);
				System.out.println("Neuer Besitzer von " + b.name + " ist " + b.getBesitzer());
				laenderArray.add(b);	// erobertes Land der Liste hinzufügen
				System.out.println("Stationierte Truppen in " + a.getName() + ": " + a.getTruppen());
				System.out.println("Stationierte Truppen in " + b.getName() + ": " + b.getTruppen());
				return true;
				}
								
		} else 
			{ 
			return false;
			//System.out.println("Angriff nicht möglich, da keine Nachbarn. Bitte anderes Land auswählen!"); 
			}
		return false;
	}
	
	public boolean TruppenBewegen(Laender von, Laender nach) {
		// beliebig viele Truppen aus einem Land in ein verbundenes Land verlagern
		wait = false;
		setLaender(laenderArray);
		
		if (laenderArray.contains(von) && laenderArray.contains(nach))
		{
			System.out.println(von.getBesitzer().getName() +  " gehören die angebebenen Länder");
			if (laenderVerbunden.verbunden(laenderHash,von,nach))
			{
				wait = true;
			System.out.println("Die angegebenen Länder sind direkt verbunden oder über Länder, die ebenfalls " + von.getBesitzer().getName() + " gehören");
				boolean nochmal = true;
				while (nochmal)
				{			
				
				if (Weltkarte.getTruppen() > von.getTruppen()-1)
				{
				System.out.println("Du kannst nur maximal " + (von.getTruppen()-1) + " Truppen verschieben"); 
				} 
				else if (Weltkarte.getTruppen() < 1)
				{
				System.out.println("Bitte gib eine größere Zahl ein");
				} else  { 
						von.setTruppen(von.getTruppen()-Weltkarte.getTruppen());
						nach.setTruppen(nach.getTruppen()+Weltkarte.getTruppen());
						nochmal = false;
						return true;
						}
				}
			} 	else 
				{ 
					System.out.println(von.getBesitzer().getName() + " gehören zwar die Länder, aber sie sind nicht verbunden"); 
					return false;
				}
		} 	else 
			{ 
				System.out.println("Verschieben nicht möglich, da" + von.getBesitzer().getName() + " eines oder beide Länder nicht gehören"); 
				return false; 
			}
		return false;
	}

	public String getFarbe() {
		return farbe;
	}
	@Override
	public String toString () //Werte werden als String ausgegeben
		{
		return name;
		}
}