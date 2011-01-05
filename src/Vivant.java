public class Vivant extends Element {
	protected int age;
	protected Data data;
	protected Train train;
	protected Direction direction;
	
	public Vivant(Coordonnee coordonnee, Data data, Train train) {
		this.coordonnee = coordonnee;
		this.data = data;
		this.train = train;
		age = (int) (Math.random()*100);
		changerDirection();
	}
	
	Direction getDirection() {
		return direction;
	}
	
	public void changerDirection() {
		int rdmDirection = ((int) (Math.random()*4));
		switch (rdmDirection) {
			case 0 :
				direction = Direction.NORD; break;
			case 1 :
				direction = Direction.EST; break;
			case 2 :
				direction = Direction.OUEST; break;
			default :
				direction = Direction.SUD; break;
		}
	}
	
	public void avancer() {
		boolean isObstacle = false;
		boolean wantToMove = false;
		Direction directionTmp = direction;
		changerDirection();
		int vivacite = (int) (Math.random()*(age+1));
		if (vivacite < 5)
			wantToMove = true;
		for (int k = 0;k < data.getTaille();k++) {
			for (int kk = 0;kk < train.getTaille();kk++) {
				if (((data.getElement(k).getCooX() == getCooX()+direction.getX()) 
				&& (data.getElement(k).getCooY() == getCooY()+direction.getY())) 
					|| 
				((train.getWagon(kk).getCooX() == getCooX()+direction.getX()) 
				&& (train.getWagon(kk).getCooY() == getCooY()+direction.getY()))) {	
					isObstacle = true;
					direction = directionTmp;
				}
			}
		}
		if (wantToMove && !isObstacle)
			coordonnee.setCooXY(getCooX()+direction.getX(),getCooY()+direction.getY());
	}
	
	public void setData(Data data) {
		this.data = data;
	}
	
	public void setTrain(Train train) {
		this.train = train;
	}
	
	public void setDataTrain(Data data, Train train) {
		setData(data);
		setTrain(train);
	}
	
	public void rmFromData() {
		data.remove(this);
	}
	
	public int getAge() {
		return age;
	}
}
