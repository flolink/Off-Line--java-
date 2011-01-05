public class Wagon extends Element {
	private int places;
	private int remplissage;
	
	public Wagon(Coordonnee coordonnee, int places, int remplissage) {
		this.coordonnee = coordonnee;
		this.places = places;
		this.remplissage = remplissage;
	}
	
	public Wagon(Coordonnee coordonnee, int places) {
		this(coordonnee,places,0);
	}
	
	public Wagon(Coordonnee coordonnee) {
		this(coordonnee,5,0);
	}
	
	public void addVoyageur() {
		if (!estPlein()) {
			remplissage++;
		}
	}
	
	public void rmVoyageur() {
		if (!estVide()) {
			remplissage--;
		}
	}

	public int getPlaces() {
		return places;
	}
	
	public int getRemplissage() {
		return remplissage;
	}
	
	public boolean estPlein() {
		return (places<=remplissage);
	}
	
	public boolean estVide() {
		return (remplissage<=0);
	}
	
	public String toString() {
		if (places == 0)
			return ("Loco,"+coordonnee);
		return ("Wagon,"+coordonnee+","+places+","+remplissage);
	}
}
