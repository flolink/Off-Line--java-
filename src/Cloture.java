public class Cloture extends Element {
	
	public Cloture(Coordonnee coordonnee) {
		this.coordonnee = coordonnee;
	}
	
	public String toString() {
		return ("Cloture,"+coordonnee.toString());
	}
}