package Risiko;

import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		//Laender generieren

		Laender indonesien = new Laender("Indonesien");
		Laender ostAustralien = new Laender("Ost-Australien");
		Laender neuGuinea = new Laender("Neu-Guinea");
		Laender westAustralien = new Laender("West-Australien");
		
		//Nachbarn einfügen
		
		indonesien.setNachbarn(neuGuinea);
		indonesien.setNachbarn(westAustralien);
		
		ostAustralien.setNachbarn(westAustralien);
		ostAustralien.setNachbarn(neuGuinea);
		
		neuGuinea.setNachbarn(indonesien);
		neuGuinea.setNachbarn(ostAustralien);
		neuGuinea.setNachbarn(westAustralien);
		westAustralien.setNachbarn(indonesien);
		westAustralien.setNachbarn(ostAustralien);
		westAustralien.setNachbarn(neuGuinea);
	
		
//		for(int i = 0; i < indonesien.getNachbarn().size(); i++) {
//            System.out.println(indonesien.getNachbarn().get(i).getName());
//        }
		
		HashMap <String, Laender> laender = new HashMap<String, Laender>();
		
		laender.put(new String(indonesien.getName()), indonesien);
		laender.put(new String(ostAustralien.getName()), ostAustralien);
		laender.put(new String(neuGuinea.getName()), neuGuinea);
		laender.put(new String(westAustralien.getName()), westAustralien);
		
		//System.out.println(laender.entrySet());
		
		Graphen graphen = new Graphen();
		System.out.println(graphen.verbunden(laender, ostAustralien, westAustralien));
		
		
	} 

}
