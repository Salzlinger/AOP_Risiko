package Risiko;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import java.util.HashMap;


public class Main {


	public static Scanner input = new Scanner (System.in);
	public static int eingeloesteSets = 0;
	public static HashMap <String, Laender> laender = new HashMap<String, Laender>();
	public static Gebietskarte [] Deck = new Gebietskarte [44];
	public static ArrayList <Gebietskarte> DeckListe;
	//Beispiel Element in Hashmap laender ("Alberta" , alberta)
	//								var		  key		value

	public static void main(String[] args) {

		iniLaender();

		//Deck aus Gebietskarten erzeugen
		Gebietskarte [] Deck = new Gebietskarte [44];
		Karten.DeckGenerieren(Deck);
		//Karten.DeckAnzeigen(Deck);
		Karten.DeckMischen(Deck);
		DeckListe = new ArrayList<Gebietskarte>(Arrays.asList(Deck)); //Deck als flexible veränderbare ArrayList
		
		/*
		//GUI erzuegen/aufrufen
		Graphen gra = new Graphen();
		RisikoGUI gui = new RisikoGUI();
		Aktionen akt = new Aktionen(gra, gui);
		*/
		
		//Spieler initialisieren
		Spieler Horst = new Spieler("blau", "Horst");

		//Horst bekommt Laender zugewiesen
		Horst.getLaender().add(laender.get("Peru"));
		Horst.getLaender().add(laender.get("Argentinien"));
		Horst.getLaender().add(laender.get("Venezuela"));
		Horst.getLaender().add(laender.get("Brasilien"));
		Horst.getLaender().add(laender.get("Island"));
		Horst.getLaender().add(laender.get("Indonesien"));
		Horst.getLaender().add(laender.get("Nordwest-Afrika"));
		Horst.getLaender().add(laender.get("Gross-Britannien"));
		Horst.getLaender().add(laender.get("West-Europa"));
		Horst.getLaender().add(laender.get("Ukraine"));
		Horst.getLaender().add(laender.get("Skandinavien"));
		Horst.getLaender().add(laender.get("Weststaaten"));
		Horst.getLaender().add(laender.get("Alberta"));
		Horst.getLaender().add(laender.get("Alaska"));
		Horst.getLaender().add(laender.get("Kamtschatka"));
		Horst.getLaender().add(laender.get("Jakutien"));
		System.out.println("Horst besitzt folgende Lï¿½nder: " + Horst.getLaender() );
		
		
		//Horst verteilt Truppen
		Horst.TruppenVerteilen();

		//Horst greift an
		laender.get("Venezuela").setTruppen(100);
		laender.get("Mittel-Amerika").setTruppen(1);
		Horst.Angreifen(laender.get("Venezuela"), laender.get("Mittel-Amerika"));
		
		System.out.println("Horst besitzt nun folgende Lï¿½nder: " + Horst.getLaender() );
		System.out.println("Truppen in: " + laender.get("Venezuela").getName() +  ": " +laender.get("Venezuela").getTruppen());
		System.out.println("Truppen in: " + laender.get("Mittel-Amerika").getName() + ": " +laender.get("Mittel-Amerika").getTruppen());

		//Horst zieht Karten
		Horst.KarteZiehen(DeckListe);
		Horst.KarteZiehen(DeckListe);
		Horst.KarteZiehen(DeckListe);
		
		//Prüfung ob ein Spieler ausgelöscht wurde
		//Gegebenenfalls Gebietskarten an Horst überschreiben und erneut TruppenVerteilen + Angreifen
		
		//Horst bewegt Truppen
		laender.get("Brasilien").setTruppen(100);
		laender.get("Jakutien").setTruppen(10);
		Horst.TruppenBewegen(laender.get("Brasilien"), laender.get("Jakutien"));
		System.out.println(laender.get("Brasilien").getName() +  ": " +laender.get("Brasilien").getTruppen());
		System.out.println(laender.get("Jakutien").getName() + ": " +laender.get("Jakutien").getTruppen());

		//Spielerwechsel

		
		
		//Spieler scheidet aus
		//if (Spieler.laender == empty)
			//delete Spieler

		//Spiel endet
		//if Spieleranzahl == 1


		System.out.println("ENDE");
	}






	private static void iniLaender () {
		//Laender generieren

				//Australien
				Laender indonesien = new Laender("Indonesien");
				Laender ostAustralien = new Laender("Ost-Australien");
				Laender neuGuinea = new Laender("Neu-Guinea");
				Laender westAustralien = new Laender("West-Australien");

				//Asien
				Laender siam = new Laender("Siam");
				Laender china = new Laender("China");
				Laender japan = new Laender("Japan");
				Laender mongolei = new Laender("Mongolei");
				Laender irkutsk = new Laender("Irkutsk");
				Laender kamtschatka = new Laender("Kamtschatka");
				Laender jakutien = new Laender("Jakutien");
				Laender sibirien = new Laender("Sibirien");
				Laender ural = new Laender("Ural");
				Laender indien = new Laender("Indien");
				Laender afghanistan = new Laender("Afghanistan");
				Laender mittlererOsten = new Laender("Mittlerer Osten");

				//Afrika
				Laender madagaskar = new Laender("Madagaskar");
				Laender suedAfrika = new Laender("Sï¿½d-Afrika");
				Laender kongo = new Laender("Kongo");
				Laender ostAfrika = new Laender("Ost-Afrika");
				Laender aegypten = new Laender("ï¿½gypten");
				Laender nordwestAfrika = new Laender("Nordwest-Afrika");

				//Europa
				Laender ukraine = new Laender("Ukraine");
				Laender suedEuropa = new Laender("Sï¿½d-Europa");
				Laender mittelEuropa = new Laender("Mittel-Europa");
				Laender westEuropa = new Laender("West-Europa");
				Laender grossBritannien = new Laender("Gross-Britannien");
				Laender skandinavien = new Laender("Skandinavien");
				Laender island = new Laender("Island");

				//Sï¿½damerika
				Laender argentinien = new Laender("Argentinien");
				Laender brasilien = new Laender("Brasilien");
				Laender peru = new Laender("Peru");
				Laender venezuela = new Laender("Venezuela");

				//Nordamerika
				Laender groenland = new Laender("Grï¿½nland");
				Laender quebec = new Laender("Quebec");
				Laender mittelAmerika = new Laender("Mittel-Amerika");
				Laender oststaaten = new Laender("Oststaaten");
				Laender weststaaten = new Laender("Weststaaten");
				Laender nordwestTerritorium = new Laender("Nordwest-Territorium");
				Laender ontario = new Laender("Ontario");
				Laender alberta = new Laender("Alberta");
				Laender alaska = new Laender("Alaska");


				//Nachbarn einfï¿½gen

				//Australien
				indonesien.setNachbarn(neuGuinea);
				indonesien.setNachbarn(westAustralien);
				indonesien.setNachbarn(siam);

				ostAustralien.setNachbarn(westAustralien);
				ostAustralien.setNachbarn(neuGuinea);

				neuGuinea.setNachbarn(indonesien);
				neuGuinea.setNachbarn(ostAustralien);
				neuGuinea.setNachbarn(westAustralien);

				westAustralien.setNachbarn(indonesien);
				westAustralien.setNachbarn(ostAustralien);
				westAustralien.setNachbarn(neuGuinea);

				//Asien
				siam.setNachbarn(china);
				siam.setNachbarn(indien);
				siam.setNachbarn(indonesien);

				china.setNachbarn(siam);
				china.setNachbarn(indien);
				china.setNachbarn(mongolei);
				china.setNachbarn(afghanistan);
				china.setNachbarn(ural);
				china.setNachbarn(sibirien);

				mongolei.setNachbarn(japan);
				mongolei.setNachbarn(irkutsk);
				mongolei.setNachbarn(china);
				mongolei.setNachbarn(kamtschatka);
				mongolei.setNachbarn(sibirien);

				japan.setNachbarn(mongolei);
				japan.setNachbarn(kamtschatka);


				kamtschatka.setNachbarn(alaska);
				kamtschatka.setNachbarn(jakutien);
				kamtschatka.setNachbarn(irkutsk);
				kamtschatka.setNachbarn(mongolei);
				kamtschatka.setNachbarn(japan);

				irkutsk.setNachbarn(kamtschatka);
				irkutsk.setNachbarn(mongolei);
				irkutsk.setNachbarn(jakutien);
				irkutsk.setNachbarn(sibirien);

				jakutien.setNachbarn(kamtschatka);
				jakutien.setNachbarn(sibirien);
				jakutien.setNachbarn(irkutsk);

				sibirien.setNachbarn(jakutien);
				sibirien.setNachbarn(irkutsk);
				sibirien.setNachbarn(mongolei);
				sibirien.setNachbarn(ural);
				sibirien.setNachbarn(china);

				ural.setNachbarn(sibirien);
				ural.setNachbarn(china);
				ural.setNachbarn(afghanistan);
				ural.setNachbarn(ukraine);

				afghanistan.setNachbarn(ukraine);
				afghanistan.setNachbarn(ural);
				afghanistan.setNachbarn(mittlererOsten);
				afghanistan.setNachbarn(indien);
				afghanistan.setNachbarn(china);

				indien.setNachbarn(siam);
				indien.setNachbarn(china);
				indien.setNachbarn(mittlererOsten);
				indien.setNachbarn(afghanistan);

				mittlererOsten.setNachbarn(indien);
				mittlererOsten.setNachbarn(afghanistan);
				mittlererOsten.setNachbarn(ukraine);
				mittlererOsten.setNachbarn(ostAfrika);
				mittlererOsten.setNachbarn(aegypten);
				mittlererOsten.setNachbarn(suedEuropa);

				//Afrika
				aegypten.setNachbarn(mittlererOsten);
				aegypten.setNachbarn(ostAfrika);
				aegypten.setNachbarn(suedEuropa);
				aegypten.setNachbarn(nordwestAfrika);

				nordwestAfrika.setNachbarn(westEuropa);
				nordwestAfrika.setNachbarn(suedEuropa);
				nordwestAfrika.setNachbarn(aegypten);
				nordwestAfrika.setNachbarn(ostAfrika);
				nordwestAfrika.setNachbarn(kongo);
				nordwestAfrika.setNachbarn(brasilien);

				ostAfrika.setNachbarn(mittlererOsten);
				ostAfrika.setNachbarn(aegypten);
				ostAfrika.setNachbarn(kongo);
				ostAfrika.setNachbarn(suedAfrika);
				ostAfrika.setNachbarn(nordwestAfrika);
				ostAfrika.setNachbarn(madagaskar);

				kongo.setNachbarn(nordwestAfrika);
				kongo.setNachbarn(ostAfrika);
				kongo.setNachbarn(suedAfrika);

				madagaskar.setNachbarn(ostAfrika);
				madagaskar.setNachbarn(suedAfrika);

				suedAfrika.setNachbarn(madagaskar);
				suedAfrika.setNachbarn(ostAfrika);
				suedAfrika.setNachbarn(kongo);

				//Europa
				ukraine.setNachbarn(ural);
				ukraine.setNachbarn(afghanistan);
				ukraine.setNachbarn(mittlererOsten);
				ukraine.setNachbarn(suedEuropa);
				ukraine.setNachbarn(mittelEuropa);
				ukraine.setNachbarn(skandinavien);

				skandinavien.setNachbarn(ukraine);
				skandinavien.setNachbarn(island);
				skandinavien.setNachbarn(grossBritannien);
				skandinavien.setNachbarn(mittelEuropa);

				island.setNachbarn(skandinavien);
				island.setNachbarn(grossBritannien);
				island.setNachbarn(groenland);

				grossBritannien.setNachbarn(island);
				grossBritannien.setNachbarn(skandinavien);
				grossBritannien.setNachbarn(mittelEuropa);
				grossBritannien.setNachbarn(westEuropa);

				mittelEuropa.setNachbarn(ukraine);
				mittelEuropa.setNachbarn(skandinavien);
				mittelEuropa.setNachbarn(grossBritannien);
				mittelEuropa.setNachbarn(suedEuropa);
				mittelEuropa.setNachbarn(westEuropa);

				westEuropa.setNachbarn(mittelEuropa);
				westEuropa.setNachbarn(suedEuropa);
				westEuropa.setNachbarn(grossBritannien);
				westEuropa.setNachbarn(nordwestAfrika);

				suedEuropa.setNachbarn(mittelEuropa);
				suedEuropa.setNachbarn(westEuropa);
				suedEuropa.setNachbarn(ukraine);
				suedEuropa.setNachbarn(nordwestAfrika);
				suedEuropa.setNachbarn(aegypten);
				suedEuropa.setNachbarn(mittlererOsten);

				//Sï¿½damerika
				venezuela.setNachbarn(mittelAmerika);
				venezuela.setNachbarn(brasilien);
				venezuela.setNachbarn(peru);
				//venezuela.setNachbarn(argentinien);

				brasilien.setNachbarn(nordwestAfrika);
				brasilien.setNachbarn(venezuela);
				brasilien.setNachbarn(peru);
				brasilien.setNachbarn(argentinien);

				peru.setNachbarn(venezuela);
				peru.setNachbarn(brasilien);
				peru.setNachbarn(argentinien);

				argentinien.setNachbarn(brasilien);
				argentinien.setNachbarn(peru);

				//Nordamerika
				groenland.setNachbarn(island);
				groenland.setNachbarn(quebec);
				groenland.setNachbarn(ontario);
				groenland.setNachbarn(nordwestTerritorium);

				quebec.setNachbarn(groenland);
				quebec.setNachbarn(ontario);
				quebec.setNachbarn(oststaaten);

				alaska.setNachbarn(kamtschatka);
				alaska.setNachbarn(nordwestTerritorium);
				alaska.setNachbarn(alberta);

				nordwestTerritorium.setNachbarn(alaska);
				nordwestTerritorium.setNachbarn(alberta);
				nordwestTerritorium.setNachbarn(ontario);
				nordwestTerritorium.setNachbarn(groenland);

				alberta.setNachbarn(alaska);
				alberta.setNachbarn(nordwestTerritorium);
				alberta.setNachbarn(ontario);
				alberta.setNachbarn(weststaaten);

				weststaaten.setNachbarn(alberta);
				weststaaten.setNachbarn(oststaaten);
				weststaaten.setNachbarn(ontario);
				weststaaten.setNachbarn(mittelAmerika);

				ontario.setNachbarn(nordwestTerritorium);
				ontario.setNachbarn(alberta);
				ontario.setNachbarn(quebec);
				ontario.setNachbarn(oststaaten);
				ontario.setNachbarn(weststaaten);
				ontario.setNachbarn(groenland);

				oststaaten.setNachbarn(quebec);
				oststaaten.setNachbarn(ontario);
				oststaaten.setNachbarn(weststaaten);
				oststaaten.setNachbarn(mittelAmerika);

				mittelAmerika.setNachbarn(weststaaten);
				mittelAmerika.setNachbarn(oststaaten);
				mittelAmerika.setNachbarn(venezuela);




				Laender [] liste = {alaska,alberta,ontario,nordwestTerritorium,weststaaten,oststaaten,mittelAmerika,quebec,groenland,venezuela,peru,brasilien,argentinien,island,skandinavien,grossBritannien,westEuropa,mittelEuropa,suedEuropa,ukraine,nordwestAfrika,aegypten,ostAfrika,kongo,suedAfrika,madagaskar,mittlererOsten,afghanistan,indien,ural,sibirien,jakutien,kamtschatka,irkutsk,mongolei,japan,china,siam,indonesien,neuGuinea,westAustralien,ostAustralien};
				for (int i = 0; i < 42; i++) {
					laender.put(new String(liste[i].getName()), liste[i]);
				}
				//Hashmap laender ("Alberta" , alberta)

				/*
				Graphen graphen = new Graphen();
				for (int i = 0; i < 42; i++) {
					for (int k = 0; k < 42; k++) {
						System.out.println(graphen.verbunden(laender, liste[i], liste[k]));
					}
				}
				*/

		//RisikoGUI gui = new RisikoGUI();
		//Aktionen akt = new Aktionen(gui);

	}

}
