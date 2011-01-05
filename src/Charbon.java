public class Charbon extends Element {
	private int valeur;
	
	public Charbon(Coordonnee coordonnee) {
		valeur = ((int) (Math.random()*51));
		this.coordonnee = coordonnee;
	}
	
	public int getValeur() {
		return valeur;
	}
	
	public String toString() {
		return ("Charbon,"+coordonnee.toString());
	}
}