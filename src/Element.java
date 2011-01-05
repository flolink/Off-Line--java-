public abstract class Element {
	Coordonnee coordonnee;
	
	public void setCooX(int x) {
		coordonnee.setCooX(x);
	}
	
	public void setCooY(int y) {
		coordonnee.setCooY(y);
	}
	
	public void setCooXY(int x, int y) {
		coordonnee.setCooXY(x,y);
	}
	
	public void setCoo(Coordonnee coordonnee) {
		coordonnee.setCoo(coordonnee);
	}
	
	public int getCooX() {
		return coordonnee.getCooX();
	}
	
	public int getCooY() {
		return coordonnee.getCooY();
	}
	
	public Coordonnee getCoo() {
		return (new Coordonnee(getCooX(),getCooY()));
	}
	
	public boolean equals(Element element) {
		return (getCoo().equals(element.getCoo()));
	}
}
