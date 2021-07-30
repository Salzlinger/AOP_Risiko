package Risiko;

public class Main {
	

	public static void main(String[] args) {
		
		Graphen gra = new Graphen();
		RisikoGUI gui = new RisikoGUI();
		Aktionen akt = new Aktionen(gui);
	}

}
