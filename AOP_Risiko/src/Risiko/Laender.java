package Risiko;

public class Laender {
	
	private String land;
	
	public Laender (String land) {
		this.setLand(land);
	}

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}
	
	@Override
	public String toString () //Werte werden als String ausgegeben
		{
		return land;
		}
		

}
