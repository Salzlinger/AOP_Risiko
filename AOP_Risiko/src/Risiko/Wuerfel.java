package Risiko;

import java.awt.Image;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;

public class Wuerfel {
	
	private static Image image1;
	private static Image newimage1;
	private static Image image2;
	private static Image newimage2;
	private static Image image3;
	private static Image newimage3;
	private static Image image4;
	private static Image newimage4;
	private static Image image5;
	private static Image newimage5;
	private static Image image6;
	private static Image newimage6;

	static ImageIcon wuerfel1;
	static ImageIcon wuerfel2;
	static ImageIcon wuerfel3;
	static ImageIcon wuerfel4;
	static ImageIcon wuerfel5;
	static ImageIcon wuerfel6;
	
	public static Scanner input = new Scanner (System.in);
	
	public static void Laden() {
		
		 wuerfel1 = new ImageIcon("src\\img\\Wuerfel1.png");
	        image1 = wuerfel1.getImage();
	        newimage1 = image1.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
	        wuerfel1 = new ImageIcon(newimage1);

	        wuerfel2 = new ImageIcon("src\\img\\Wuerfel2.png");
	        image2 = wuerfel2.getImage();
	        newimage2 = image2.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
	        wuerfel2 = new ImageIcon(newimage2);

	        wuerfel3 = new ImageIcon("src\\img\\Wuerfel3.png");
	        image3 = wuerfel3.getImage();
	        newimage3 = image3.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
	        wuerfel3 = new ImageIcon(newimage3);

	        wuerfel4 = new ImageIcon("src\\img\\Wuerfel4.png");
	        image4 = wuerfel4.getImage();
	        newimage4 = image4.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
	        wuerfel4 = new ImageIcon(newimage4);

	        wuerfel5 = new ImageIcon("src\\img\\Wuerfel5.png");
	        image5 = wuerfel5.getImage();
	        newimage5 = image5.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
	        wuerfel5 = new ImageIcon(newimage5);

	        wuerfel6 = new ImageIcon("src\\img\\Wuerfel6.png");
	        image6 = wuerfel6.getImage();
	        newimage6 = image6.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
	        wuerfel6 = new ImageIcon(newimage6);
	}
	public static boolean Wuerfelkampf(Laender a, Laender b)//int angreifer, int verteidiger) 
	{
		
		Laden();
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
		
		// BlitzKampf (alle Runden mit einmal) oder NormalKampf (jede Runde einzeln auswürfeln)
		
		System.out.println("Möchtest du die Runden einzeln auswürfeln? Oder sollen alle Runden mit einem mal ausgewürfelt werden?");
		if (Weltkarte.getblitz()) // hier button einfügen
		{
			blitzKampf = true; 	
			System.out.println("Blitzkampf startet");
		}
		else 
		{ 
			normalKampf = true; 		
			System.out.println("Normalkampf startet");
		}
		
		while (angreifer > 1 && verteidiger != 0 && weitermachen)
		{	
			if (normalKampf)	//Wenn ein NormalKampf ausgewählt wird, kann vor jeder Runde entschieden werden ob mit 1,2, oder 3 Truppen angegriffen wird
			{
			}
			System.out.println("---------------------------------\nRunde: " + runde);
			runde += 1;
			
			angreiferwuerfe = wuerfelfeld(angreifer);
			verteidigerwuerfe = wuerfelfeld(verteidiger);
			
			//Ausgabe der Wuerfelergebnisse
			if (angreifer > 2)
				{
					System.out.println("Angreifer Wuerfe  : [ " + angreiferwuerfe[0] + " | " + angreiferwuerfe[1] + " | " + angreiferwuerfe[2] + " ] ");
					if(angreiferwuerfe[0] == 6){Weltkarte.w1.setIcon(wuerfel6);}
						else if(angreiferwuerfe[0] == 5){Weltkarte.w1.setIcon(wuerfel5);}
							else if(angreiferwuerfe[0] == 4){Weltkarte.w1.setIcon(wuerfel4);}
								else if(angreiferwuerfe[0] == 3){Weltkarte.w1.setIcon(wuerfel3);}
									else if(angreiferwuerfe[0] == 2){Weltkarte.w1.setIcon(wuerfel2);}
										else if(angreiferwuerfe[0] == 1){Weltkarte.w1.setIcon(wuerfel1);}
					if(angreiferwuerfe[1] == 6){Weltkarte.w2.setIcon(wuerfel6);}
						else if(angreiferwuerfe[1] == 5){Weltkarte.w2.setIcon(wuerfel5);}
							else if(angreiferwuerfe[1] == 4){Weltkarte.w2.setIcon(wuerfel4);}
								else if(angreiferwuerfe[1] == 3){Weltkarte.w2.setIcon(wuerfel3);}
									else if(angreiferwuerfe[1] == 2){Weltkarte.w2.setIcon(wuerfel2);}
										else if(angreiferwuerfe[1] == 1){Weltkarte.w2.setIcon(wuerfel1);}
					
					if(angreiferwuerfe[2] == 6){Weltkarte.w3.setIcon(wuerfel6);}
					else if(angreiferwuerfe[2] == 5){Weltkarte.w3.setIcon(wuerfel5);}
						else if(angreiferwuerfe[2] == 4){Weltkarte.w3.setIcon(wuerfel4);}
							else if(angreiferwuerfe[2] == 3){Weltkarte.w3.setIcon(wuerfel3);}
								else if(angreiferwuerfe[2] == 2){Weltkarte.w3.setIcon(wuerfel2);}
									else if(angreiferwuerfe[2] == 1){Weltkarte.w3.setIcon(wuerfel1);}
				}
			if (angreifer == 2)
				{
				System.out.println("Angreifer Wuerfe  : [ " + angreiferwuerfe[0] + " | " + angreiferwuerfe[1] + " ] ");
				if(angreiferwuerfe[0] == 6){Weltkarte.w1.setIcon(wuerfel6);}
				else if(angreiferwuerfe[0] == 5){Weltkarte.w1.setIcon(wuerfel5);}
					else if(angreiferwuerfe[0] == 4){Weltkarte.w1.setIcon(wuerfel4);}
						else if(angreiferwuerfe[0] == 3){Weltkarte.w1.setIcon(wuerfel3);}
							else if(angreiferwuerfe[0] == 2){Weltkarte.w1.setIcon(wuerfel2);}
								else if(angreiferwuerfe[0] == 1){Weltkarte.w1.setIcon(wuerfel1);}
			if(angreiferwuerfe[1] == 6){Weltkarte.w2.setIcon(wuerfel6);}
				else if(angreiferwuerfe[1] == 5){Weltkarte.w2.setIcon(wuerfel5);}
					else if(angreiferwuerfe[1] == 4){Weltkarte.w2.setIcon(wuerfel4);}
						else if(angreiferwuerfe[1] == 3){Weltkarte.w2.setIcon(wuerfel3);}
							else if(angreiferwuerfe[1] == 2){Weltkarte.w2.setIcon(wuerfel2);}
								else if(angreiferwuerfe[1] == 1){Weltkarte.w2.setIcon(wuerfel1);}
				}	
			if (angreifer == 1)
				{
				System.out.println("Angreifer Wuerfe  : [ " + angreiferwuerfe[0] + " ] ");
				if(angreiferwuerfe[0] == 6){Weltkarte.w1.setIcon(wuerfel6);}
				else if(angreiferwuerfe[0] == 5){Weltkarte.w1.setIcon(wuerfel5);}
					else if(angreiferwuerfe[0] == 4){Weltkarte.w1.setIcon(wuerfel4);}
						else if(angreiferwuerfe[0] == 3){Weltkarte.w1.setIcon(wuerfel3);}
							else if(angreiferwuerfe[0] == 2){Weltkarte.w1.setIcon(wuerfel2);}
								else if(angreiferwuerfe[0] == 1){Weltkarte.w1.setIcon(wuerfel1);}
				}	

			if (verteidiger > 2 && angreifer > 2)
				{
				System.out.println("Verteidiger Wuerfe: [ " + verteidigerwuerfe[0] + " | " + verteidigerwuerfe[1] + " | " + verteidigerwuerfe[2] + " ]");
				if(verteidigerwuerfe[0] == 6){Weltkarte.w4.setIcon(wuerfel6);}
				else if(verteidigerwuerfe[0] == 5){Weltkarte.w4.setIcon(wuerfel5);}
					else if(verteidigerwuerfe[0] == 4){Weltkarte.w4.setIcon(wuerfel4);}
						else if(verteidigerwuerfe[0] == 3){Weltkarte.w4.setIcon(wuerfel3);}
							else if(verteidigerwuerfe[0] == 2){Weltkarte.w4.setIcon(wuerfel2);}
								else if(verteidigerwuerfe[0] == 1){Weltkarte.w4.setIcon(wuerfel1);}
			if(verteidigerwuerfe[1] == 6){Weltkarte.w5.setIcon(wuerfel6);}
				else if(verteidigerwuerfe[1] == 5){Weltkarte.w5.setIcon(wuerfel5);}
					else if(verteidigerwuerfe[1] == 4){Weltkarte.w5.setIcon(wuerfel4);}
						else if(verteidigerwuerfe[1] == 3){Weltkarte.w5.setIcon(wuerfel3);}
							else if(verteidigerwuerfe[1] == 2){Weltkarte.w5.setIcon(wuerfel2);}
								else if(verteidigerwuerfe[1] == 1){Weltkarte.w5.setIcon(wuerfel1);}
			
			if(verteidigerwuerfe[2] == 6){Weltkarte.w6.setIcon(wuerfel6);}
			else if(verteidigerwuerfe[2] == 5){Weltkarte.w6.setIcon(wuerfel5);}
				else if(verteidigerwuerfe[2] == 4){Weltkarte.w6.setIcon(wuerfel4);}
					else if(verteidigerwuerfe[2] == 3){Weltkarte.w6.setIcon(wuerfel3);}
						else if(verteidigerwuerfe[2] == 2){Weltkarte.w6.setIcon(wuerfel2);}
							else if(verteidigerwuerfe[2] == 1){Weltkarte.w6.setIcon(wuerfel1);}
				}
			if (verteidiger > 2 && angreifer == 2)
				{
				System.out.println("Verteidiger Wuerfe: [ " + verteidigerwuerfe[0] + " | " + verteidigerwuerfe[1] + " ]");
				if(verteidigerwuerfe[0] == 6){Weltkarte.w4.setIcon(wuerfel6);}
				else if(verteidigerwuerfe[0] == 5){Weltkarte.w4.setIcon(wuerfel5);}
					else if(verteidigerwuerfe[0] == 4){Weltkarte.w4.setIcon(wuerfel4);}
						else if(verteidigerwuerfe[0] == 3){Weltkarte.w4.setIcon(wuerfel3);}
							else if(verteidigerwuerfe[0] == 2){Weltkarte.w4.setIcon(wuerfel2);}
								else if(verteidigerwuerfe[0] == 1){Weltkarte.w4.setIcon(wuerfel1);}
				if(verteidigerwuerfe[1] == 6){Weltkarte.w5.setIcon(wuerfel6);}
					else if(verteidigerwuerfe[1] == 5){Weltkarte.w5.setIcon(wuerfel5);}
						else if(verteidigerwuerfe[1] == 4){Weltkarte.w5.setIcon(wuerfel4);}
							else if(verteidigerwuerfe[1] == 3){Weltkarte.w5.setIcon(wuerfel3);}
								else if(verteidigerwuerfe[1] == 2){Weltkarte.w5.setIcon(wuerfel2);}
									else if(verteidigerwuerfe[1] == 1){Weltkarte.w5.setIcon(wuerfel1);}
				}
			if (verteidiger == 2 && angreifer > 1)
				{
				System.out.println("Verteidiger Wuerfe: [ " + verteidigerwuerfe[0] + " | " + verteidigerwuerfe[1] + " ]");
				if(verteidigerwuerfe[0] == 6){Weltkarte.w4.setIcon(wuerfel6);}
				else if(verteidigerwuerfe[0] == 5){Weltkarte.w4.setIcon(wuerfel5);}
					else if(verteidigerwuerfe[0] == 4){Weltkarte.w4.setIcon(wuerfel4);}
						else if(verteidigerwuerfe[0] == 3){Weltkarte.w4.setIcon(wuerfel3);}
							else if(verteidigerwuerfe[0] == 2){Weltkarte.w4.setIcon(wuerfel2);}
								else if(verteidigerwuerfe[0] == 1){Weltkarte.w4.setIcon(wuerfel1);}
				if(verteidigerwuerfe[1] == 6){Weltkarte.w5.setIcon(wuerfel6);}
					else if(verteidigerwuerfe[1] == 5){Weltkarte.w5.setIcon(wuerfel5);}
						else if(verteidigerwuerfe[1] == 4){Weltkarte.w5.setIcon(wuerfel4);}
							else if(verteidigerwuerfe[1] == 3){Weltkarte.w5.setIcon(wuerfel3);}
								else if(verteidigerwuerfe[1] == 2){Weltkarte.w5.setIcon(wuerfel2);}
									else if(verteidigerwuerfe[1] == 1){Weltkarte.w5.setIcon(wuerfel1);}
				}	
			if (verteidiger == 1 || angreifer == 1 )
				{
				System.out.println("Verteidiger Wuerfe: [ " + verteidigerwuerfe[0] + " ]");
				if(verteidigerwuerfe[0] == 6){Weltkarte.w4.setIcon(wuerfel6);}
				else if(verteidigerwuerfe[0] == 5){Weltkarte.w4.setIcon(wuerfel5);}
					else if(verteidigerwuerfe[0] == 4){Weltkarte.w4.setIcon(wuerfel4);}
						else if(verteidigerwuerfe[0] == 3){Weltkarte.w4.setIcon(wuerfel3);}
							else if(verteidigerwuerfe[0] == 2){Weltkarte.w4.setIcon(wuerfel2);}
								else if(verteidigerwuerfe[0] == 1){Weltkarte.w4.setIcon(wuerfel1);}
				}
			
			//Vergleich der Wuerfel und Abzug der Einheiten
			
			for (int i = 0; i<3; i++)
			{	
				if (angreifer >= 1 && angreiferwuerfe[i] != 0 && verteidigerwuerfe[i] != 0)
				{	
					if (angreiferwuerfe[i] <= verteidigerwuerfe[i])
						{
							angreifer -= 1;
							angreiferGesamt -= 1;
						} 
					else
					{
						verteidiger -= 1; 
					}
				}
			}	
			angreifer = angreiferGesamt;
			System.out.println("\nverbliebene Einheiten");
			System.out.println("des Angreifers  : " + angreifer); 
			System.out.println("des Verteidigers: " + verteidiger);
			
		
			weitermachen = false;
		}
		
		a.setTruppen(angreifer);
		b.setTruppen(verteidiger);
		
		//Bekanntgabe des Siegers
		System.out.println("---------------------------------");
		if (angreifer == 1)
		{	
			System.out.println("Der Angreifer hat zu wenig Einheiten um angreifen zu koennen.\nDer Verteidiger siegt.");	
			return false;
		} else if (verteidiger != 0 && weitermachen == false) 
		{		
			System.out.println("Der Angreifer hat den Angriff abgebrochen\n Der Verteidiger siegt.");	
			return false;
		} else 	
			{ 	
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
		{	
			for ( int i=0; i < 3; i++)
				{ 
					feld [i] = wuerfeln(); 
				}
			Arrays.sort(feld, Collections.reverseOrder());			
		} 
		else 							
		{	
			for ( int i=0; i < av; i++)
				{
					feld [i] = wuerfeln();
				}
			for (int i = av; i < 3; i++ ) // Rest auffuellen mit nullen auffuellen, damit Sort keinen Fehler wirft
				{
					feld [i] = 0;
				}
			Arrays.sort(feld, Collections.reverseOrder());
		}
		return feld; 
	}

}
