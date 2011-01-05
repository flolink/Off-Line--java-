import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public class EcouteKeyboard implements KeyListener {
   private static final int UP = KeyEvent.VK_UP;
   private static final int DOWN = KeyEvent.VK_DOWN;
   private static final int RIGHT = KeyEvent.VK_RIGHT;
   private static final int LEFT = KeyEvent.VK_LEFT;
   private static final int P = KeyEvent.VK_P;
   private static final int N = KeyEvent.VK_N;
   private static final int A = KeyEvent.VK_A;
   private static final int B = KeyEvent.VK_B;  
   private static final int Q = KeyEvent.VK_Q;
   
   private Fenetre fenetre;
   private Offline offline;
   private LinkedList<Integer> konami;
   private LinkedList<Integer> entree;
   private boolean boolKonami = false; 
   
   public EcouteKeyboard(Fenetre fenetre,Offline offline) {
		this.fenetre = fenetre;
		this.offline = offline;
		entree = new LinkedList<Integer>();
		konami = new LinkedList<Integer>();
		konami.addLast(UP);
		konami.addLast(UP);
		konami.addLast(DOWN);
		konami.addLast(DOWN);
		konami.addLast(LEFT);
		konami.addLast(RIGHT);
		konami.addLast(LEFT);
		konami.addLast(RIGHT);
		konami.addLast(B);
		konami.addLast(A);
	}
	
	public void keyTyped(KeyEvent arg0) {
	}

	public void keyPressed(KeyEvent k) {
		entree.addLast(k.getKeyCode());
		testKonami();
		if (k.getKeyCode() == LEFT)
			offline.gauche();
		if (k.getKeyCode() == RIGHT)
			offline.droite();
		if (k.getKeyCode() == UP)
			offline.haut();
		if (k.getKeyCode() == DOWN)
			offline.bas();
		if (k.getKeyCode() == P)
		   offline.p();
		if (k.getKeyCode() == N)
		   offline.n();
		if (k.getKeyCode() == Q)
		   offline.q();
	}

	public void keyReleased(KeyEvent arg0) {
	}
	
	public void testKonami() {
		if (entree.getLast() == konami.get(entree.size()-1)) {
			if (entree.size() == konami.size()) {
				boolKonami = true;
				entree.clear();
			}
		} else
			entree.clear();
	}
	
	public boolean getKonami() {
		if (boolKonami) {
			boolKonami = false;
			return !boolKonami;
		} else
			return boolKonami;
	}
}