package Risiko;

public class Main {

	public static void main(String[] args) {
		//Laender generieren
		
		Laender indonesien = new Laender();
		indonesien.setName("Indonesien");
		Laender ostAustralien = new Laender();
		ostAustralien.setName("Ost-Australien");
		Laender neuGuinea = new Laender();
		neuGuinea.setName("Neu-Guinea");
		Laender westAustralien = new Laender();
		westAustralien.setName("West-Australien");
		
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
		
		for(int i = 0; i < indonesien.getNachbarn().size(); i++) {
            System.out.println(indonesien.getNachbarn().get(i).getName());
        }
		} 

	}
