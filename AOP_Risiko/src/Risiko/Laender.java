package Risiko;

import java.util.ArrayList;

public class Laender {
	
	String name;
	Boolean istBekannt = true;
	int truppen = 0;
	
	public Laender (String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	//truppen set/get
	public int getTruppen() {
		return truppen;
	}
	public void setTruppen(int truppen) {
		this.truppen = truppen;
	}

	//ist bekannt
	public Boolean getIstBekannt() {
		return istBekannt;
	}
	public void setIstBekannt(Boolean istBekannt) {
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
	

}

