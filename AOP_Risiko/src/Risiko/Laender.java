package Risiko;

import java.util.ArrayList;

public class Laender {
	
	String name;
	boolean istBekannt = true;
	int truppen = 0;
	private static String besitzer= "";
	private static int shape = 0;
	
	
	public Laender (String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	//shape getter/setter
	public static int getShape() {
		return shape;
	}

	public static void setShape(int shape) {
		Laender.shape = shape;
	}
	
	
	//truppen set/get
	public int getTruppen() {
		return truppen;
	}
	public void setTruppen(int truppen) {
		this.truppen = truppen;
	}

	//ist bekannt
	public boolean getIstBekannt() {
		return istBekannt;
	}
	public void setIstBekannt(boolean istBekannt) {
		this.istBekannt = istBekannt;
	}
	
	//Nachbarn
	ArrayList<Laender> nachbarn = new ArrayList<Laender>();
	public ArrayList<Laender> getNachbarn() {
		return nachbarn;
	}
	public void setNachbarn (Laender newNachbar) {
	nachbarn.add(newNachbar);
	}
	
	@Override
	public String toString () //Werte werden als String ausgegeben
		{
		return name;
		}

	
	public String getBesitzer() {
		return besitzer;
	}

	public void setBesitzer(String besitzer) {
		Laender.besitzer = besitzer;
	}

	
	

}

