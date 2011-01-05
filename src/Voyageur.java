import java.util.Iterator;

public class Voyageur extends Vivant {

	public Voyageur(Coordonnee coordonnee, Data data, Train train) {
		super(coordonnee,data,train);
	}

	public void avancer() {
		super.avancer();
		for (int i = 0;i < train.getTaille();i++) {
			if (!(train.getWagon(i).estPlein())) {
				if (	((getCooX()+1 == train.getWagon(i).getCooX())
						&&
						(getCooY() == train.getWagon(i).getCooY()))
					||
						((getCooX()-1 == train.getWagon(i).getCooX())
						&&
						(getCooY() == train.getWagon(i).getCooY()))
					||
						((getCooX() == train.getWagon(i).getCooX())
						&&
						(getCooY()+1 == train.getWagon(i).getCooY()))
					||
						((getCooX() == train.getWagon(i).getCooX())
						&&
						(getCooY()-1 == train.getWagon(i).getCooY()))
					) {
					train.getWagon(i).addVoyageur();
					train.decrementeVitesse();
					int score = 0;
					if (age <=33) 
						score = 3;
					else if (age <= 66)
						score = 2;
					else
						score = 1;
					if (train.getVitesse() >= train.getVMAX()/2)
						score *= score;
					train.addScore(score);
					rmFromData();
				}
			}
		}
	}
	
	public String toString() {
		return "Voyageur,"+coordonnee+","+age+","+direction;
	}
}