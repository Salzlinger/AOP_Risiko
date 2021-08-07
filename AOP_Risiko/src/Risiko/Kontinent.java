package Risiko;

import java.util.ArrayList;

public class Kontinent {
	
	private static ArrayList <Laender> laenderArray = new ArrayList <Laender>();
	private static String name;

	
	public static int besitztKontinent() {
		
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
		
		for (int i = 0; i < laenderArray.size(); i++)
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
		
		System.out.println("Spieler" + name + "erhält " + kontinentTruppen + " Truppen für Kontinente");
		return kontinentTruppen;
	}
}
