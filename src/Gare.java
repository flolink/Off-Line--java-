public class Gare extends Element {
	private int nbVoyageurs;
	private Train train;
	private Data data;

	public Gare(Coordonnee coordonnee, Train train, Data data) {
		this.coordonnee = coordonnee;
		nbVoyageurs = ((int) (Math.random()*15));
		this.train = train;
		this.data = data;
	}
	
	public void distribuer() {
		int veutMonter = 0;		//0 - ne monte pas; 1 - monte
		for (int i = 0; i < train.getTaille(); i++) {
			veutMonter = (int) (Math.random()*2);
			if (train.getWagon(i).getCooX() == coordonnee.getCooX() && train.getWagon(i).getCooY() == coordonnee.getCooY()) {
				for (int k = 0; k < nbVoyageurs; k++) {
					if (train.getWagon(i).getRemplissage() < train.getWagon(i).getPlaces()) {
						if (veutMonter == 1) {
							nbVoyageurs--;
							train.getWagon(i).addVoyageur();
						}
					}
				}
			}
		}
		if (nbVoyageurs <= 0)
			data.remove(this);
	}
	
	public int getnbV() {
		return nbVoyageurs;
	}

	public String toString() {
		return "Gare,"+coordonnee;
	}
}