package Risiko;

public class Gebietskarte 
extends Karten
{
	private String typ; //Infanterie, Kavallerie, Artillerie
	private String name; //Osteuropa, Westafrika, Neuseeland.....
	
	
	public Gebietskarte(String name, String typ) {
		this.setTyp(typ);
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	} 

	@Override
	public String toString () //Werte werden als String ausgegeben
		{
		return name + " " + typ;
		}

}
