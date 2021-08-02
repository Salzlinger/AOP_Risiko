package Risiko;

import java.util.ArrayList;

public class Laender {
	
	String name;
	Boolean istBekannt = true;
	int truppen = 0;
	
	public int getTruppen() {
		return truppen;
	}

	public void setTruppen(int truppen) {
		this.truppen = truppen;
	}

	public Boolean getIstBekannt() {
		return istBekannt;
	}

	public void setIstBekannt(Boolean istBekannt) {
		this.istBekannt = istBekannt;
	}

	public Laender (String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	ArrayList<Laender> nachbarn = new ArrayList<Laender>();
	
	public ArrayList<Laender> getNachbarn() {
		return nachbarn;
	}
	
	public void setNachbarn (Laender newNachbar) {
	nachbarn.add(newNachbar);
	}

}

