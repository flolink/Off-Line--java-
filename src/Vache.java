public class Vache extends Vivant {

	public Vache(Coordonnee coordonnee, Data data, Train train) {
		super(coordonnee,data,train);
	}
	
	public String toString() {
		return ("Vache,"+coordonnee+","+age+","+direction);
	}
}
