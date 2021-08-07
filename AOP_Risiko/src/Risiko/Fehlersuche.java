package Risiko;

import java.util.Scanner;

public class Fehlersuche {

	public static int eingabePruefenInt (){
	 	boolean fehler = true;
	 	int eingabe = 0;
		while (fehler)
		{
			try { 	
				System.out.println("Bitte gib eine gültige Zahl ein");
	 			Scanner input = new Scanner (System.in);
	 			eingabe = input.nextInt();
	 			fehler = false;
			} catch (Exception e) {
			System.out.println("Deine Eingabe ist keine Zahl");
			}
		}
		return eingabe;
	}

	public static String eingabeJaNein() {
		boolean fehler = true;
	 	String eingabe = "";
		while (fehler)
		{
			System.out.println("Bitte gib ja oder nein ein");
 			Scanner input = new Scanner (System.in);
 			eingabe = input.nextLine();
 				
 			if(eingabe.equals("ja"))
 				{
 					fehler = false;
 				}
 					else if(eingabe.equals("nein"))
 					{
 						fehler = false;
 					}
 						else 
 						{
 							System.out.println("Bitte tätige eine gültige Eingabe");
 						}
		}
		return eingabe;
	}


}