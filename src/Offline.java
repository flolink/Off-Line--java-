import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class Offline extends Thread {

		private int longueur = 20;
		private int largeur = 20;
		private Direction direction = Direction.EST;
		private Train train;
		private Champ champ;
		private Data data;
		private boolean droitEcoute = false;
		private boolean p = false;
		private boolean n = false;
		private EcouteKeyboard ecouteK;
		private Fenetre fenetre;
		private boolean gameOver = false;
		private boolean boolKonami = false;
		private JDialog menuOffline;
		private JDialog pause;
		private JDialog theEnd;
		private EcrireTxt scoreTxt;

	public Offline() {
		pause = new JDialog();
		theEnd = new JDialog();
		data = new Data(longueur, largeur);
		train = new Train(data);
		data.setTrain(train);
		champ = new Champ(largeur, longueur, data, train);
		//scoreTxt = new EcrireTxt("score.txt",train);
		fenetre = new Fenetre(data,train,this);
		ecouteK = new EcouteKeyboard(fenetre,this);
		start();
	}
	
	public void run() {
		p = true;
		fenetre.getJFrame().addKeyListener(ecouteK);
		while (true) {
			boolKonami = ecouteK.getKonami();
			if (boolKonami)
				fenetre.setKonami();
			while(p) {
				droitEcoute = false;
				Thread.yield();
			}
			gameOver();
			fenetre.refresh();
			fenetre.changerScore();
			train.pause(train.getVMAX()+40-train.getVitesse());
			droitEcoute = true;
			avancer();
			if (n) {
				fenetre.rmKonami();
				data = new Data(longueur, largeur);
				train = new Train(data);
				direction = Direction.EST;
				champ = new Champ(largeur, longueur, data, train);
				fenetre.setData(data);
				fenetre.setTrain(train);
				fenetre.refresh();
				n = false;
			}
		}
	}
	
	public void gameOver() {
		if (train.getVitesse() <= 0) {
			gameOver = true;
			//scoreTxt.ecrireScore(train.getScore());
			try {
				Thread.sleep(1500);
			} catch(InterruptedException e) {}
			n = true;
			p = true;
		}
	}

	private synchronized void avancer() {
		train.avancer(direction,true);
	}
	
	public synchronized void gauche() {
		if ((direction != Direction.EST) && droitEcoute) {
			direction = Direction.OUEST;
			droitEcoute = false;
		}			
	}

	public synchronized void droite() {
		if ((direction != Direction.OUEST) && droitEcoute) {
			direction = Direction.EST;
			droitEcoute = false;
		}
		p = false;
	}

	public synchronized void haut() {
		if ((direction != Direction.SUD) && droitEcoute) {
			direction = Direction.NORD;
			droitEcoute = false;
		}
	}

	public synchronized void bas() {
		if ((direction != Direction.NORD) && droitEcoute) {
			direction = Direction.SUD;
			droitEcoute = false;
		}
	}

	public synchronized void p() {
			p = !p;
	}
	
	public synchronized void n() {
		if (p)
		{
			n = true;
			p = false;
		}
	}
	
	public synchronized void q() {
		if (p) {
			System.exit(0);
			scoreTxt.fermer();
		}
	}
	
	public EcrireTxt getScoreTxt() {
		return scoreTxt;
	}
	
	public static void main(String[] args) {
		new Offline();
	}
}
