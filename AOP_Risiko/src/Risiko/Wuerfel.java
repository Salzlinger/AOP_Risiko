package Risiko;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;


public class Wuerfel {
	
	public static Scanner input = new Scanner (System.in);
	
	public static boolean Wuerfelkampf(Laender a, Laender b)//int angreifer, int verteidiger) 
	{
		Integer [] angreiferwuerfe = new Integer [3];
		Integer [] verteidigerwuerfe = new Integer [3];	
				
		int angreifer = a.getTruppen();
		int angreiferGesamt = angreifer;
		int verteidiger = b.getTruppen();
		int runde = 1;
		boolean weitermachen = true;
		boolean ungueltigeEingabe = true;
		boolean blitzKampf = false;
		boolean normalKampf = false;
		
		//BlitzKampf (alle Runden mit einmal) oder NormalKampf (jede Runde einzeln ausw�rfeln)
		System.out.println("M�chtest du die Runden einzeln ausw�rfeln? Oder sollen alle Runden mit einem mal ausgew�rfelt werden?");
		if (input.next().equals("blitz")) // hier button einf�gen
		{blitzKampf = true; 	System.out.println("Blitzkampf startet");}
		else { normalKampf = true; 		System.out.println("Normalkampf startet");}
		
		while (angreifer > 1 && verteidiger != 0 && weitermachen)
		{	
			if (normalKampf)	//Wenn ein NormalKampf ausgew�hlt wird, kann vor jeder Runde entschieden werden ob mit 1,2, oder 3 Truppen angegriffen wird
			{
				while (ungueltigeEingabe)
				{
				System.out.println("W�hle aus ob du mit 1,2 oder 3 Truppen angreifen m�chtest");
				System.out.println("3 ist standard m��ig ausgew�hlt");
				
				angreifer = Fehlersuche.eingabePruefenInt();
				
				if (angreifer > 3)
					{System.out.println("Du kannst nur mit max 3 Truppen angreifen");}
				else if (angreiferGesamt == 3 && angreifer > 2)
					{System.out.println("Du kannst nur noch mit max 2 Truppen angreifen");}
				else if (angreiferGesamt == 2 && angreifer > 1)
					{System.out.println("Du kannst nur noch mit max 1 Truppe angreifen");}
				else	
					{ungueltigeEingabe = false;}
				}
			}
			System.out.println("---------------------------------\nRunde: " + runde);
			runde += 1;
			
			angreiferwuerfe = wuerfelfeld(angreifer);
			verteidigerwuerfe = wuerfelfeld(verteidiger);
			
			//Ausgabe der W�rfelergebnisse
			if (angreifer > 2)
				{System.out.println("Angreifer W�rfe  : [ " + angreiferwuerfe[0] + " | " + angreiferwuerfe[1] + " | " + angreiferwuerfe[2] + " ] ");}
			if (angreifer == 2)
				{System.out.println("Angreifer W�rfe  : [ " + angreiferwuerfe[0] + " | " + angreiferwuerfe[1] + " ] ");}	
			if (angreifer == 1)
				{System.out.println("Angreifer W�rfe  : [ " + angreiferwuerfe[0] + " ] ");}	

			if (verteidiger > 2 && angreifer > 2)
				{System.out.println("Verteidiger W�rfe: [ " + verteidigerwuerfe[0] + " | " + verteidigerwuerfe[1] + " | " + verteidigerwuerfe[2] + " ]");}
			if (verteidiger > 2 && angreifer == 2)
				{System.out.println("Verteidiger W�rfe: [ " + verteidigerwuerfe[0] + " | " + verteidigerwuerfe[1] + " ]");}
			if (verteidiger == 2 && angreifer > 1)
				{System.out.println("Verteidiger W�rfe: [ " + verteidigerwuerfe[0] + " | " + verteidigerwuerfe[1] + " ]");}	
			if (verteidiger == 1 || angreifer == 1 )
				{System.out.println("Verteidiger W�rfe: [ " + verteidigerwuerfe[0] + " ]");}
			
			//Vergleich der W�rfel und Abzug der Einheiten
			for (int i = 0; i<3; i++)
			{	if (angreifer >= 1 && angreiferwuerfe[i] != 0 && verteidigerwuerfe[i] != 0)
				{	
				if (angreiferwuerfe[i] <= verteidigerwuerfe[i])
					{
					angreifer -= 1;
					angreiferGesamt -= 1;
					} else {verteidiger -= 1; }
				}
			}	
			angreifer = angreiferGesamt;
			System.out.println("\nverbliebene Einheiten");
			System.out.println("des Angreifers  : " + angreifer); 
			System.out.println("des Verteidigers: " + verteidiger);
			
			
			if ( angreifer > 1 && verteidiger > 0)
			{ if (normalKampf)
				{	System.out.println("M�chtest du weiter angreifen? ");
					if (Fehlersuche.eingabeJaNein().equals("ja"))
					{ ungueltigeEingabe = true;	} 	
					else { weitermachen = false;}
				}  	
			} else { weitermachen = false;}
		}
		
		a.setTruppen(angreifer);
		b.setTruppen(verteidiger);
		
		//Bekanntgabe des Siegers
		System.out.println("---------------------------------");
		if (angreifer == 1)
		{	
			System.out.println("Der Angreifer hat zu wenig Einheiten um angreifen zu k�nnen.\nDer Verteidiger siegt.");	
			return false;
		} else if (verteidiger != 0 && weitermachen == false) {		
			System.out.println("Der Angreifer hat den Angriff abgebrochen\nDer Verteidiger siegt.");	
			return false;
		} else 	{ 	
			System.out.println("Der Angreifer siegt.");
			return true;
		}		
	}
	
	
	public static int wuerfeln ()
	{
		Random zufall = new Random();
		int augen = zufall.nextInt(6)+1;
		return augen;
	}
	
	public static Integer [] wuerfelfeld(int av)
	{
		Integer [] feld = new Integer [3];
		if (av > 2)
		{	for ( int i=0; i < 3; i++)
				{ feld [i] = wuerfeln(); }
			Arrays.sort(feld, Collections.reverseOrder());			
		} 
		else 							
		{	for ( int i=0; i < av; i++)
				{feld [i] = wuerfeln();}
			for (int i = av; i < 3; i++ ) // Rest auff�llen mit nullen auff�llen, damit Sort keinen Fehler wirft
				{feld [i] = 0;}
			Arrays.sort(feld, Collections.reverseOrder());
		}
		return feld; 
	}

}
