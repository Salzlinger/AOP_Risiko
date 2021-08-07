package Risiko;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static Scanner input = new Scanner (System.in);
	public static int eingeloesteSets = 0;
	public static ArrayList <Gebietskarte> Deck = new ArrayList <Gebietskarte>();;
	public static Spieler spieler1;
	public static Spieler spieler2;
	public static Spieler spieler3;
	public static Spieler spieler4;
	public static Spieler spieler5;
	public static ArrayList<Spieler> spieler = new ArrayList<Spieler>();

	//Beispiel Element in Hashmap laender ("Alberta" , alberta)
	//								var		  key		value

	public static void main(String[] args) {

		// Laender initialisieren
		
		Laender.iniLaender();

		//Deck aus Gebietskarten erzeugen
		
		Karten.DeckListeGenerieren(Deck);
		Karten.DeckListeMischen(Deck);

		//GUI erzuegen/aufrufen

		Graphen gra = new Graphen();
		RisikoGUI gui = new RisikoGUI();
		Aktionen akt = new Aktionen(gra, gui);

		
//		while (spieler.size() > 1) {
//
//			for (int i = 0; i < spieler.size(); i++) {
//				//spieler verteilt Truppen
//				spieler.get(i).TruppenVerteilen();
//
//				//spieler greift an
//				laender.get("Venezuela").setTruppen(100);
//				laender.get("Mittel-Amerika").setTruppen(1);
//				//spieler.get(i).Angreifen(laender.get("Venezuela"), laender.get("Mittel-Amerika"));
//
//				System.out.println(spieler.get(i).getName() + " besitzt nun folgende L�nder: " + spieler.get(i).getLaender() );
//				System.out.println("Truppen in: " + laender.get("Venezuela").getName() +  ": " +laender.get("Venezuela").getTruppen());
//				System.out.println("Truppen in: " + laender.get("Mittel-Amerika").getName() + ": " +laender.get("Mittel-Amerika").getTruppen());
//
//				//Pr�fung ob ein Spieler ausgel�scht wurde
//				//Gegebenenfalls Gebietskarten an spieler �berschreiben und erneut TruppenVerteilen + Angreifen
//
//				//Spieler scheidet aus
//				for (int k = 0; k < spieler.size(); k++) {
//					if (spieler.get(k).getLaender().isEmpty()) {
//						spieler.remove(k);
//					}
//				}
//				//spieler bewegt Truppen
//				laender.get("Brasilien").setTruppen(100);
//				laender.get("Jakutien").setTruppen(10);
//				spieler.get(i).TruppenBewegen(
//						waitClick(),
//						liste[Weltkarte.getPointer()]
//						);
//				System.out.println(laender.get("Brasilien").getName() +  ": " +laender.get("Brasilien").getTruppen());
//				System.out.println(laender.get("Jakutien").getName() + ": " +laender.get("Jakutien").getTruppen());
//
//			}
//		}
//		System.out.println("ENDE");
	}

}
