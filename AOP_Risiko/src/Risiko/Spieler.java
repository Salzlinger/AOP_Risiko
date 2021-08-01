package Risiko;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Spieler {

	private String farbe;
	private String name;
	private ArrayList <Laender> laender = new ArrayList <Laender>();
	private ArrayList <Gebietskarte> hand = new ArrayList <Gebietskarte>();
	private boolean infanterieSet = false;
	private boolean kavallerieSet = false;
	private boolean artillerieSet = false;
	private boolean gemischtesSet = false;
	private boolean jokerSet = false;
	private int truppen = 0;
	private int eingeloesteSets = 0;
	private int setBonus = 0;
	
	public Spieler (String farbe, String name) {
		this.farbe = farbe;
		this.name= name;
	}
	
	
	public int TruppenErhalten() {
		berechneSetBonus();
		if (laender.size() < 9)
		{
		truppen = 3  + setBonus + besitztKontinent();
		return truppen;
		} else  {
				truppen = laender.size()/3 + setBonus + besitztKontinent();
				return truppen;
				}
		
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
	
	public ArrayList <Laender> getLaender() {
		return laender;
	}


	public void setLaender(ArrayList<Laender> land) {
		this.laender = land;
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
		boolean mito=false, afg=false, ura=false, ser=false, jak=false, kam=false, irk=false, mon=false, jap=false, chi=false, sia=false, indi=false; 
	//Australien
		boolean indo=false, ngui=false, waus=false, oaus=false;
		
		for (int i = 0; i<laender.size(); i++)
		{	
			switch (laender.get(i).getLand()) 
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
			case "Serbien": ser = true; break;
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
		if (mito && afg && ura && ser && jak && kam && irk && mon && jap && chi && sia && indi)
		{ kontinentTruppen += 7; }
	//Australien +2
		if (indo && ngui && waus && oaus)
		{ kontinentTruppen += 2; }
		
		System.out.println("Spieler xy erhält " + kontinentTruppen + " Truppen für Kontinente");
		return kontinentTruppen;
	}


	





}
