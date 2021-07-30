package Risiko;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		RisikoGUI GUI = new RisikoGUI();
		
		HashMap <String, Gebietskarte> KartenDeck = new HashMap <String, Gebietskarte>();
		Karten.KartenGenerieren(KartenDeck);
		Karten.KarteZiehen(KartenDeck);
		

	}

}
