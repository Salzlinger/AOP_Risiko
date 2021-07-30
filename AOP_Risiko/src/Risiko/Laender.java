package Risiko;

import java.util.ArrayList;

public class Laender {
	
	String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	ArrayList<Laender> nachbarn = new ArrayList<Laender>();
	
	public ArrayList<Laender> getNachbarn() {
		return nachbarn;
	}
	
	public void setNachbarn (Laender newNachbar) {
	nachbarn.add(newNachbar);
	}

}

