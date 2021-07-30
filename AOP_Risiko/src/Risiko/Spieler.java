package Risiko;

import java.util.Arrays;
import java.util.List;

public class Spieler {

	private String farbe;
	private String [] karten;
	private String [] laender;
	private int truppen;
	
	public Spieler (String farbe, String [] laender) {
		this.farbe = farbe;
		this.laender = laender;
	}
	
	
	public int TruppenErhalten() {
		truppen = laender.length/3 + gotContinent(laender);
		return truppen;
	}
	
	public void GebietskartenErhalten() {
		// if (Angreifen() == sieg)
		// Spieler erhält 1 Karte vom Stapel
		// Spieler.karten += KarteZiehen();
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
	
	public void KartenBenutzen() {
		// if (Spieler.karten > 5)
		// { Spieler muss Set einlösen}
		// else { Spieler kann Set einlösen}
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
