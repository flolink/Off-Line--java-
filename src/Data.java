import java.util.LinkedList;
import java.util.Iterator;

public class Data {
	private LinkedList<Element> elements;
	private int longueurMap = 0;
	private int largeurMap = 0;
	private Train train = new Train(this);
	
	public Data(int longueurMap, int largeurMap) {
		elements = new LinkedList<Element>();
		this.longueurMap = longueurMap;
		this.largeurMap = largeurMap;
	}
	
	public Data() {
		this(20,20);
	}
	
	public void addElement(Element element) {
		elements.addLast(element);
	}
	
	public void rmElement() {
		if (!elements.isEmpty())
			elements.removeLast();
	}
	
	public void rmElement(Element element) {
		elements.remove(element);
	}
	
	public void apparition() {
		int quelElement = ((int) (Math.random()*12));
		int randomX = ((int) (Math.random()*longueurMap));
		int randomY = ((int) (Math.random()*largeurMap));
		int apparaitre = ((int) (Math.random()*100));
		int gare = ((int) (Math.random()*10));
		setTrain(train);
		boolean canApp = false;
		if (apparaitre < 10) {
			if (!(elements.contains(new Vivant(new Coordonnee(randomX,randomY),this,train)))) {
				if (!(train.containsVivant(new Vivant(new Coordonnee(randomX,randomY),this,train))))
					canApp = true;
			}
			if (canApp) {
				switch (quelElement) {
					case 0 :
						elements.addLast(new Charbon(new Coordonnee(randomX,randomY))); break;
					case 1 :
						elements.addLast(new Charbon(new Coordonnee(randomX,randomY))); break;
					case 2 :
						elements.addLast(new Vache(new Coordonnee(randomX,randomY),this,train)); break;
					case 3 :
						elements.addLast(new Vache(new Coordonnee(randomX,randomY),this,train)); break;
					case 4 :
						elements.addLast(new Wagon(new Coordonnee(randomX,randomY))); break;
					case 5 :
						elements.addLast(new Wagon(new Coordonnee(randomX,randomY))); break;
					case 6 :
						elements.addLast(new Voyageur(new Coordonnee(randomX,randomY),this,train)); break;
					case 7 :
						elements.addLast(new Voyageur(new Coordonnee(randomX,randomY),this,train)); break;
					case 8 :
						elements.addLast(new Voyageur(new Coordonnee(randomX,randomY),this,train)); break;
					case 9 :
						elements.addLast(new Voyageur(new Coordonnee(randomX,randomY),this,train)); break;
					case 10 :
						if (gare <= 1)
							elements.addLast(new Gare(new Coordonnee(randomX,randomY),train,this)); break;
					default :
						elements.addLast(new Tronc(new Coordonnee(randomX,randomY))); break;
				}
			}
		}
		int randomRm = ((int) (Math.random()*10));
		if (randomRm < 5) {
			int indexElementASupprimer = ((int) (Math.random()*elements.size()));
			if (!(getElement(indexElementASupprimer) instanceof Cloture))
				elements.remove(indexElementASupprimer);
		}
	}
	
	public void avancer() {
		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i) instanceof Vivant)
				((Vivant) elements.get(i)).avancer();
			else if (elements.get(i) instanceof Gare)
				((Gare) elements.get(i)).distribuer();
		}
	}
	
	public void remove(Element element) {
		elements.remove(element);
	}
	
	public void setLongLargMap(int longueurMap, int largeurMap) {
		this.longueurMap = longueurMap;
		this.largeurMap = largeurMap;
	}
	
	public void setTrain(Train train) {
		this.train = train;
	}
	
	public int getLongueurMap() {
		return longueurMap;
	}
	
	public int getLargeurMap() {
		return largeurMap;
	}
	
	public Element getElement(int index) {
		return elements.get(index);
	}
	
	public int getTaille() {
		return elements.size();
	}
	
	public int getLongueur() {
	   return longueurMap;
	}
	
	public int getLargeur() {
	   return largeurMap;
	}
	
	public Element get(int k) {
		return elements.get(k);
	}
	
	public String toString() {
		String res = "";
		Iterator i = elements.iterator();
		while (i.hasNext())
			res += " "+i.next().toString();
		return res;
	}
	
	public String toStringWithoutCloture() {
		String res = "";
		Iterator i = elements.iterator();
		Element testCloture;
		while (i.hasNext()) {
		   testCloture = (Element) i.next();
			if (!(testCloture instanceof Cloture))
				res += " "+testCloture.toString();
		}
		return res;
	}
}
