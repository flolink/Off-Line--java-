import java.util.LinkedList;
import java.util.Iterator;

public class Train {
	private LinkedList<Wagon> convoi;
	private int vitesse;
	private int score;
	private Direction direction;
	private Data data;
	private int VMAX = 200;
	private int VMIN = 50;
	private boolean level1 = false;
	private boolean level2 = false;
	private boolean level3 = false;
	private boolean level4 = false;
	private boolean level5 = false;
	
	public Train(int taille, int vitesse, Data data) {
		this.vitesse = vitesse;
		this.data = data;
		this.score = 0;
		convoi = new LinkedList<Wagon>();
		convoi.addLast(new Wagon(new Coordonnee(taille,1),0));
		for (int i = taille-1;i > 0;i--)
			convoi.addLast(new Wagon(new Coordonnee(i,1)));
	}
	
	public Train(Data data) {
		this(3,100,data);
	}
	
	public void addWagon(Wagon wagon) {
		convoi.addLast(wagon);
	}
	
	public void incrementeScore() {
		score++;
	}
	
	public void decrementeScore() {
		score--;
	}
	
	public void addScore(int ajout) {
		score += ajout;
	}
	
	public void rmScore(int retrait) {
		score -= retrait;
	}
	
	public void rmWagon() {
		if (convoi.size() > 1) {
			convoi.removeLast();
		}
	}
	
	public void decrementeVitesse() {
		if (vitesse > VMIN)
			vitesse--;
	}
	
	public void reduireVitesse(int baisse) {
		if (vitesse-baisse > VMIN)
			vitesse -= baisse;
		else
			vitesse = VMIN;
	}
	
	public void incrementeVitesse() {
		if (vitesse < VMAX)
			vitesse++;
	}
	
	public void augmenterVitesse(int augmentation) {
		if (vitesse+augmentation < VMAX)
			vitesse += augmentation;
		else
			vitesse = VMAX;
	}
	
	public void stopper() {
		vitesse = 0;
	}
	
	public void avancer(Direction direction, boolean apparaitre) {
		this.direction = direction;
		data.setTrain(this);
		if (apparaitre)
			data.apparition();
		int nbWagon = 0;
		int numWagon = 0;
		if (vitesse > 0) {
			Coordonnee tmp = (getWagon(convoi.size()-1).getCoo());
			for (int i = convoi.size()-1; i > 0; i--) {
				getWagon(i).setCooX(getWagon(i-1).getCooX());
				getWagon(i).setCooY(getWagon(i-1).getCooY());
				
			}
			convoi.getFirst().setCooXY(getCooXLoco()+direction.getX(),getCooYLoco()+direction.getY());
			int kk = 1;
			while (kk < convoi.size()) {
				if ((getCooLoco().equals(convoi.get(kk).getCoo()))) {
					stopper();
					kk = convoi.size();
				}
				kk++;
			}
			for (int k = 0;k < data.getTaille();k++) {
				if ((data.get(k).getCoo().equals(getCooLoco()))) {
					if ((data.get(k) instanceof Voyageur) || (data.get(k) instanceof Vache)) {
						rmWagon();
						if (vitesse >= VMAX/2)
							rmScore(3);
						else
							rmScore(5);
						reduireVitesse(20);
				   
						nbWagon = ((int) ((Math.random())*convoi.size()));
						numWagon = 0;
						for (int i = 0;i < nbWagon;i++) {
							numWagon = ((int) ((Math.random())*nbWagon));
							convoi.get(numWagon).rmVoyageur(); 
						}
						data.rmElement(data.get(k));
					} else if (data.get(k) instanceof Wagon) {
						addWagon(new Wagon(new Coordonnee(0,0)));
						data.rmElement(data.get(k));
						incrementeScore();
						decrementeVitesse();
					} else if (data.get(k) instanceof Charbon) {
						augmenterVitesse(((Charbon) data.get(k)).getValeur());
						data.rmElement(data.get(k));
					} else if ((data.get(k) instanceof Cloture) || (data.get(k) instanceof Tronc))
						stopper();
				}
			}
			data.avancer();
			
			if (!level5) {
				if (!level4) {
					if (!level3) {
						if (!level2) {
							if (!level1) {
								if (getRemplissage() >= 10) {
									level1 = true;
									addScore(getRemplissage());
								}
							}
							if (getRemplissage() >= 20) {
								level2 = true;
								addScore(getRemplissage());
							}
						}
						if (getRemplissage() >= 30) {
							level3 = true;
							addScore(getRemplissage());
						}
					}
					if (getRemplissage() >= 40) {
						level4 = true;
						addScore(getRemplissage());
					}
				}
				if (getRemplissage() >= 50) {
					level5 = true;
					addScore(getRemplissage());
				}
			}
						
			if (level5 && getRemplissage() <= 40) {
				level5 = false;
			} else if (level4 && getRemplissage() <= 30) {
				level5 = false;
				level4 = false;
			} else if (level3 && getRemplissage() <= 20) {
				level5 = false;
				level4 = false;
				level3 = false;
			} else if (level2 && getRemplissage() <= 10) {
				level5 = false;
				level4 = false;
				level3 = false;
				level2 = false;
			} else if (level1 && getRemplissage() <= 0) {
				level5 = false;
				level4 = false;
				level3 = false;
				level2 = false;
				level1 = false;
			}
		}
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public int getCooXLoco() {
		return convoi.getFirst().getCooX();
	}
	
	public int getCooYLoco() {
		return convoi.getFirst().getCooY();
	}
	
	public Coordonnee getCooLoco() {
		return (convoi.getFirst().getCoo());
	}
	
	public int getScore() {
		return score;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public static void pause(int tps) {
		try {
			Thread.sleep(tps);
		} catch(InterruptedException e) {}
	}
	
	public int getVitesse() {
		return vitesse;
	}
	
	public int getTaille() {
		return convoi.size();
	}
	
	public Wagon getWagon(int i) {
	   return convoi.get(i);
	}
	
	public boolean containsVivant(Vivant vivant) {
		return convoi.contains(vivant);
	}
	
	public int getIndex(Wagon wagon) {
		return convoi.indexOf(wagon);
	}
	
	public int getVMAX() {
		return VMAX;
	}
	
	public int getRemplissage() {
		int nbVoyageur = 0;
		Iterator it = convoi.iterator();
		Wagon wagon;
		while (it.hasNext()) {
			wagon = (Wagon) it.next();
			nbVoyageur += wagon.getRemplissage();
		}
		return nbVoyageur;
	}
	
	public String toString() {
		String res = "";
		Iterator i = convoi.iterator();
		while (i.hasNext()) {
			res += " "+i.next();
		}
		return res;
	}
}
