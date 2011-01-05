public class Coordonnee {
	private int x;
	private int y;
	
	public Coordonnee(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setCooX(int x) {
		this.x = x;
	}
	
	public void setCooY(int y) {
		this.y = y;
	}
	
	public void setCooXY(int x, int y) {
	   this.x = x;
	   this.y = y;
	}
	
	public void setCoo(Coordonnee coordonnee) {
		x = coordonnee.getCooX();
		y = coordonnee.getCooY();
	}

	public int getCooX() {
		return x;
	}
	
	public int getCooY() {
		return y;
	}
	
	public String toString() {
		return (x+"."+y);
	}
	
	public boolean equals(Coordonnee coordonnee) {
		return ((x == coordonnee.getCooX()) && (y == coordonnee.getCooY()));
	}
}
