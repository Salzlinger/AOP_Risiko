package Risiko;

import java.util.Arrays;
import java.util.List;

public class Spieler {

	private String colour;
	private String [] cards;
	private String [] countrys = {"Quebec","Peru","Venezuela","Brasilien","Argentinien","..."};
	private int numberCountrys =  countrys.length;
	private int troups;
	
	public Spieler (String colour, String [] countrys) {
		this.colour = colour;
		this.countrys = countrys;
	}
	
	public int TruppenErhalten() {
		troups = numberCountrys/3 + gotContinent(countrys);
		return troups;
	}

	
	
	public int gotContinent(String [] countrys) {
		List <String> liste = Arrays.asList(countrys);
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
	
	//dies sind test texte
	
	

}
