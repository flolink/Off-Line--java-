public class Champ {
	private int longueur;
	private int largeur;
	private Data data;
	private Train train;
	
	public Champ(int longueur, int largeur, Data data, Train train) {
		this.longueur = longueur;
		this.largeur = largeur;
		this.data = data;
		this.train = train;
		data.setLongLargMap(longueur, largeur);
		for (int j = 0;j < longueur;j++) {
			data.addElement(new Cloture(new Coordonnee(j,0)));
			data.addElement(new Cloture(new Coordonnee(j,largeur-1)));
		}
		for (int i = 1;i < largeur-1;i++) {
			data.addElement(new Cloture(new Coordonnee(0,i)));
			data.addElement(new Cloture(new Coordonnee(longueur-1,i)));
		}
	}
	
	public void setTrain(Train train) {
		this.train = train;
	}
	
	public void setData(Data data) {
		this.data = data;
	}
		
	public Train getTrain() {
		return train;
	}
	
	public Data getData() {
		return data;
	}
	
	public int getLongueur() {
		return longueur;
	}
	
	public int getLargeur() {
		return largeur;
	}
	
	public String toString() {
		String res = "";
		boolean wagon;
		boolean loco;
		boolean cloture;
		boolean voyageur;
		boolean vache;
		boolean charbon;
		boolean booltrain;
		boolean tronc;
		boolean gare;
		for (int j = 0;j < largeur;j++) {
			for (int i = 0;i < longueur;i++) {
				wagon = false;
				booltrain = false;
				loco = false;
				cloture = false;
				voyageur = false;
				vache = false;
				charbon = false;
				tronc = false;
				gare = false;
				int kprime = 0;
				for (int kk = 0;kk < data.getTaille();kk++) {
					if (((data.getElement(kk) instanceof Cloture) && data.getElement(kk).getCooX() == i) && (data.getElement(kk).getCooY() == j)) {
						cloture = true;
					}
					if (((data.getElement(kk) instanceof Voyageur) && data.getElement(kk).getCooX() == i) && (data.getElement(kk).getCooY() == j)) {
						voyageur = true;
					}
					if (((data.getElement(kk) instanceof Vache) && data.getElement(kk).getCooX() == i) && (data.getElement(kk).getCooY() == j)) {
						vache = true;
					}
					if (((data.getElement(kk) instanceof Charbon) && data.getElement(kk).getCooX() == i) && (data.getElement(kk).getCooY() == j)) {
						charbon = true;
					}
					if (((data.getElement(kk) instanceof Wagon) && data.getElement(kk).getCooX() == i) && (data.getElement(kk).getCooY() == j)) {
						wagon = true;
					}
					if (((data.getElement(kk) instanceof Tronc) && data.getElement(kk).getCooX() == i) && (data.getElement(kk).getCooY() == j)) {
						tronc = true;
					}
					if (((data.getElement(kk) instanceof Gare) && data.getElement(kk).getCooX() == i) && (data.getElement(kk).getCooY() == j)) {
						gare = true;
					}
					for (int k = 0;k < train.getTaille();k++) {
						if ((train.getWagon(k).getCooX() == i) && (train.getWagon(k).getCooY() == j)) {
							if (train.getWagon(k).getPlaces() == 0) {
							   loco = true;
						   } else {
							  booltrain = true;
							  kprime = k;
						   }
						}
					}
				}
				if (cloture) {
					res+="x ";
				} else if (gare) {
					res+="A ";
				} else if (tronc) {
					res+="= ";
				} else if (vache) {
					res+="m ";
				} else if (wagon) {
					res+="W ";
				} else if (voyageur) {
					res+="o ";
				} else if (loco) {
					res+="d ";
				} else if (booltrain) {
					res+=train.getWagon(kprime).getRemplissage()+" ";
				} else if (charbon) {
					res+="* ";
				} else {
					res+="  ";
				}
			}
			res = res+"\n";
		}
		return res;
	}
}
