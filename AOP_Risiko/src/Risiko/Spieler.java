package Risiko;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Spieler {


	public static Scanner input = new Scanner (System.in);
	
	private String farbe;
	private String name;
	private Graphen laenderVerbunden = new Graphen();
	private HashMap <String, Laender> laenderHash = new HashMap <String, Laender>();
	private ArrayList <Laender> laenderArray = new ArrayList <Laender>();
	private ArrayList <Gebietskarte> hand = new ArrayList <Gebietskarte>();
	private boolean infanterieSet = false;
	private boolean kavallerieSet = false;
	private boolean artillerieSet = false;
	private boolean gemischtesSet = false;
	private boolean jokerSet = false;
	private boolean setEingeloest = false;
	private boolean wait = false;
	private int truppen = 0;
	public boolean istDrann = false;
	
	
	public int getTruppen() {
		return truppen;
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
	
	public void KarteZiehen(ArrayList <Gebietskarte> Deck){
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
				Main.eingeloesteSets++;
			} else 
				{
				System.out.println("Spieler " + name + " hat ein komplettes Set auf der Hand. Möchtest du es einlösen?");
				if (input.next().equals("ja")  )
				{
					SetEinloesen(Main.Deck);
					setEingeloest=true;
					Main.eingeloesteSets++;
				}
			}
		}
		if(setEingeloest)
		{
		berechneSetBonus();
		System.out.println("Spieler xy erhält " + setBonus + " Truppen für eingeloeste Sets");
		setEingeloest = false;
		}
		if (laenderArray.size() < 9)
		{
		System.out.println("Spieler xy erhält " + 3 + " Truppen für besetzte Länder");
		truppen = 3  + setBonus + besitztKontinent();
		} else  {
				System.out.println("Spieler xy erhält " + (laenderArray.size()/3) + " Truppen für besetzte Länder");
				truppen = laenderArray.size()/3 + setBonus + besitztKontinent();
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
		else if ((j==1 && (a>=1||k>=1||a>=1) && (a>=1||k>=1||a>=1)) || (j==2 && (a>=1||k>=1||a>=1))) 
			{ 
				this.jokerSet = true;
				return true; 
			}
				else 
				{ return false; }
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
			{ 	if (hand.get(z).getTyp() == "Joker" && jokerEntnommen == false)	
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
		int a = Risiko.Main.eingeloesteSets;
		if (a == 0)
		{this.setBonus = 0;}
		if (a > 0 && a < 6 )
		{ this.setBonus = 4 + (a-1)*2; }
		if (a>= 6)
		{ this.setBonus = 10 + 5*(a-5); }
	}
	
	
	//auf Aktionen auslagern?
	public void TruppenVerteilen(Laender land) {
		land.setTruppen(+1);
		truppen  -= 1;

		System.out.println("Spieler " + name + " hat " + truppen + " Truppen zum verteilen zur Verfügung.");
		//<<<<<Funktion in GUI zum verteilen der Truppen
	}
	
	public void Angreifen(Laender a, Laender b) {
		System.out.println("Mit wie vielen Truppen möchtest du angreifen? ");
		int angriffszahl = 3; //input.nextInt();
		
		setLaender(laenderArray); //Länder werden in Hashmap umgeschrieben
			
		if (laenderHash.get(a.getName()).getNachbarn().contains(b))
		{
			System.out.println("Ein Angriff ist möglich, da die angegebenen Länder Nachbarn sind");
			
				
			int ang = a.getTruppen();
			int ver = b.getTruppen();
			if (angriffszahl > ang-1)
			{ System.out.println("du hast nur max " + (ang-1) + " Truppen zum Angreifen. Bitte gültige Zahl auswählen");}
				else if (angriffszahl < 1)
						{ System.out.println("du musst mit mindestens 1 Truppe angreifen"); }
						else 	{
								if (Wuerfel.Wuerfelkampf(a,b)== true)
									{
									KarteZiehen(Main.Deck);
									b.setBesitzer(this);
									System.out.println("Neuer Besitzer von " + b.name + " ist " + b.getBesitzer());
									laenderArray.add(b);	// erobertes Land der Liste hinzufügen
									System.out.println(a.getName() + ": " + a.getTruppen());
									System.out.println(b.getName() + ": " +b.getTruppen());
									TruppenBewegen(a,b);
									System.out.println(a.getName() + ": " +a.getTruppen());
									System.out.println(b.getName() + ": " +b.getTruppen());
									}
								}
		} else { System.out.println("Angriff nicht möglich, da keine Nachbarn. Bitte anderes Land auswählen!"); }
	}
	
	public void TruppenBewegen(Laender von, Laender nach) {
		// beliebig viele Truppen aus einem Land in ein verbundenes Land verlagern
		wait = false;
		setLaender(laenderArray);
		if (laenderArray.contains(von) && laenderArray.contains(nach))
		{
			System.out.println("Horst gehören die angebebenen Länder");
			if (laenderVerbunden.verbunden(laenderHash,von,nach))
			{
				wait = true;
			System.out.println("Die angegebenen Länder sind direkt verbunden oder über Länder, die ebenfalls Horst gehören");
				boolean nochmal = true;
				while (nochmal)
				{
				System.out.println("Wie viele Truppen möchtest du verschieben?");			
				
				int eingabe = input.nextInt();
				if (eingabe > von.getTruppen()-1)
				{
				System.out.println("Du kannst nur maximal " + (von.getTruppen()-1) + " Truppen verschieben"); 
				} 
				else if (eingabe < 1)
				{
				System.out.println("Bitte gib eine größere Zahl ein");
				} else  { 
						von.setTruppen(von.getTruppen()-eingabe);
						nach.setTruppen(nach.getTruppen()+eingabe);
						nochmal = false;
						}
				}
			} else { System.out.println("Horst gehören zwar die Länder, aber sie sind nicht verbunden");}
		} else { System.out.println("Verschieben nicht möglich, da Horst eines oder beide Länder nicht gehören"); }
	}

	
	

	//eventuell in Länder/Kontinent überführen?
	public int besitztKontinent() {
		
		int kontinentTruppen = 0;
				
	//Nordamerika
		boolean ala=false, alb=false, nwter=false, ont=false, que=false, groe=false, ostst=false, westst=false, mita=false;
	//Südamerika
		boolean arg=false, bra=false, per=false, ven=false;
	//Europa
		boolean isl=false, gbr=false, weu=false, meu=false, seu=false, ska=false, ukr=false;
	//Afrika
		boolean nwa=false, aeg=false, oaf=false, kon=false, saf=false, mada=false; 
	//Asien
		boolean mito=false, afg=false, ura=false, sib=false, jak=false, kam=false, irk=false, mon=false, jap=false, chi=false, sia=false, indi=false; 
	//Australien
		boolean indo=false, ngui=false, waus=false, oaus=false;
		
		for (int i = 0; i<laenderArray.size(); i++)
		{	
			switch (laenderArray.get(i).getName()) 
			{
		//Nordamerika ala, alb, nwter, ont, que, groe, ostst, westst, mita
			case "Alaska": ala = true; break;
			case "Alberta": alb = true; break;
			case "Ontario": ont = true; break;
			case "Nordwest-Territorium": nwter = true; break;
			case "Weststaaten": westst = true; break;
			case "Oststaaten": ostst = true; break;
			case "Mittelamerika": mita = true; break;
			case "Quebec": que = true; break;
			case "Grönland": groe = true; break;
		//Südamerika arg, bra, per, ven
			case "Argentinien": arg = true; break;
			case "Brasilien": bra = true; break;
			case "Peru": per = true; break;
			case "Venezuela": ven = true; break;
		//Europa isl, gbr, weu, meu, seu, ska, ukr
			case "Island": isl = true; break;
			case "Skandinavien": ska = true; break;
			case "Großbritannien": gbr = true; break;
			case "Westeuropa": weu = true; break;
			case "Mitteleuropa": meu = true; break;
			case "Südeuropa": seu = true; break;
			case "Ukraine": ukr = true; break;
		//Afrika nwa, aeg, oaf, kon, saf, mada
			case "Nordwest-Afrika": nwa = true; break;
			case "Ägypten": aeg = true; break;
			case "Ost-Afrika": oaf = true; break;
			case "Kongo": kon = true; break;
			case "Süd-Afrika": saf = true; break;
			case "Madagaskar": mada = true; break;
		//Asien mito, afg, ura, ser, jak, kam, irk, mon, jap, chi, sia, indi
			case "Mittlerer Osten": mito = true; break;
			case "Afghanistan": afg = true; break;
			case "Indien": indi = true; break;
			case "Ural": ura = true; break;
			case "Sibirien": sib = true; break;
			case "Jakutien": jak = true; break;
			case "Kamtschatka": kam = true; break;
			case "Irkutsk": irk = true; break;
			case "Mongolei": mon = true; break;
			case "Japan": jap = true; break;
			case "China": chi = true; break;
			case "Siam":sia = true; break;
		//Australien indo, ngui, waus, oaus
			case "Indonesien": indo = true; break;
			case "Neu-Guinea": ngui = true; break;
			case "West-Australien": waus = true; break;
			case "Ost-Australien": oaus = true; break;
			
			default: 
			}
		}	
		
	//Nordamerika +5
		if (ala && alb && nwter && ont && que && groe && ostst && westst && mita)
		{ kontinentTruppen += 5; }
	//Südamerika +2
		if ( arg && bra && per && ven)
		{ kontinentTruppen += 2; }
	//Europa +5
		if (isl && gbr && weu && meu && seu && ska && ukr)
		{ kontinentTruppen += 5; }
	//Afrika +3
		if (nwa && aeg && oaf && kon && saf && mada)
		{ kontinentTruppen += 3; }
	//Asien +7
		if (mito && afg && ura && sib && jak && kam && irk && mon && jap && chi && sia && indi)
		{ kontinentTruppen += 7; }
	//Australien +2
		if (indo && ngui && waus && oaus)
		{ kontinentTruppen += 2; }
		
		System.out.println("Spieler xy erhält " + kontinentTruppen + " Truppen für Kontinente");
		return kontinentTruppen;
	}


	@Override
	public String toString () //Werte werden als String ausgegeben
		{
		return name;
		}

}
