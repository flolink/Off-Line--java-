import javax.swing.*;
import java.awt.*;

public class Fenetre {
	private JFrame fenetre;
	private Data data;
	private Train train;
	private JLabel[][] tab;
	private JPanel panel;
	
	private	boolean wagon;
	private	boolean booltrain;
	private	boolean loco;
	private	boolean cloture;
	private	boolean voyageur;
	private	boolean vache;
	private	boolean charbon;
	private boolean tronc;
	private boolean gare;
	
	private String voyageurD = "";
	private int indexOfWagon = 0;
	private Offline offline;
	
	private JLabel score;
	private JMenuBar menu;
	private JMenu menuFile;
	private JMenu menuWut;
	
	private boolean refresh;
	private boolean konami = false;
	private boolean clignote = false;
	private String colorClignote = "red";
	private String w0 = "d";
	private String w1 = "0";
	private String w2 = "0";
	private String w3 = "0";
	private String w4 = "0";
	private String w5 = "0";
	private String w6 = "0";
	private String w7 = "0";
	
	public Fenetre(Data data, Train train, Offline offline) {
		refresh = true;
		this.data = data;
		this.train = train;
		this.offline = offline;
		fenetre = new JFrame("Off-Line");
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setSize(500,500);
		fenetre.setResizable(false);
		fenetre.setLocationRelativeTo(null);
		fenetre.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Off-Line.png")));
		panel = new JPanel();
		
		menu = new JMenuBar();
		JToolBar toolBarScore = new JToolBar();
		fenetre.setJMenuBar(menu);
		toolBarScore.setFloatable(false);
		menuFile = new JMenu("File");
		menuWut = new JMenu("?");
		score = new JLabel("<html><b style='color: red'>0 pt</b></html>");
		toolBarScore.add(score);
		menu.add(menuFile);
		menu.add(menuWut);
		menu.add(toolBarScore);
		
		JMenuItem nouveau = new JMenuItem("nouveau jeu (n)");
		JMenuItem pause = new JMenuItem("pause (p)");
		JMenuItem propos = new JMenuItem("a propos");
		JMenuItem regles = new JMenuItem("regles");
		JMenuItem config = new JMenuItem("config");
		//JMenuItem scores = new JMenuItem("scores");
		JMenuItem quitter = new JMenuItem("quitter (q)");
		config.addActionListener(new EcouteurConfig(fenetre));
		//scores.addActionListener(new EcouteurScores(fenetre, "score.txt"));
		regles.addActionListener(new EcouteurRegles(fenetre));
		propos.addActionListener(new EcouteurPropos(fenetre));
		pause.addActionListener(new EcouteurPause(offline));
		nouveau.addActionListener(new EcouteurNew(offline,this));
		quitter.addActionListener(new EcouteurQuitter(offline.getScoreTxt()));
		menuFile.add(nouveau);
		menuFile.add(pause);
		menuFile.add(quitter);
		menuWut.add(propos);
		menuWut.add(regles);
		menuWut.add(config);
		//menuWut.add(scores);
		
		panel.setBackground(Color.green);
		panel.setLayout(new GridLayout(data.getLongueur(),data.getLargeur(),0,0));
		fenetre.add(panel);
		tab = new JLabel[data.getLongueur()][data.getLargeur()];
		for (int j = 0;j < data.getLargeur();j++) {
			for (int i = 0;i < data.getLongueur();i++) {
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
					if (((data.getElement(kk) instanceof Cloture) && data.getElement(kk).getCooX() == i) && (data.getElement(kk).getCooY() == j))
						cloture = true;
					if (((data.getElement(kk) instanceof Gare) && data.getElement(kk).getCooX() == i) && (data.getElement(kk).getCooY() == j))
						gare = true;
					if (((data.getElement(kk) instanceof Voyageur) && data.getElement(kk).getCooX() == i) && (data.getElement(kk).getCooY() == j))
						voyageur = true;
					if (((data.getElement(kk) instanceof Vache) && data.getElement(kk).getCooX() == i) && (data.getElement(kk).getCooY() == j))
						vache = true;
					if (((data.getElement(kk) instanceof Charbon) && data.getElement(kk).getCooX() == i) && (data.getElement(kk).getCooY() == j))
						charbon = true;
					if (((data.getElement(kk) instanceof Wagon) && data.getElement(kk).getCooX() == i) && (data.getElement(kk).getCooY() == j))
						wagon = true;
					if (((data.getElement(kk) instanceof Tronc) && data.getElement(kk).getCooX() == i) && (data.getElement(kk).getCooY() == j))
						tronc = true;
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
				if (cloture)
					tab[j][i] = new JLabel("<html><b style='color: black'>X</b></html>");
				else if (gare)
					tab[j][i] = new JLabel("<html><b style='color: #0000FF'>A</b></html>");
				else if (vache)
					tab[j][i] = new JLabel("<html><b style='color: white'>m</b></html>");
				else if (wagon)
					tab[j][i] = new JLabel("<html><b style='color: yellow'>W</b></html>");
				else if (voyageur)
					tab[j][i] = new JLabel("<html><b style='color: #FFCCCC'>o</b></html>");
				else if (loco)
					tab[j][i] = new JLabel("<html><b style='color: red'>"+w0+"</b></html>");
				else if (booltrain)
					tab[j][i] = new JLabel("<html><b style='color: yellow'>"+w1+"</b></html>");
				else if (charbon)
					tab[j][i] = new JLabel("<html><b style='color: gray'>@</b></html>");
				else if (tronc)
					tab[j][i] = new JLabel("<html><b style='color: maroon'>=</b></html>");
				else
					tab[j][i] = new JLabel(" ");
				panel.add(tab[j][i]);
			}
		}
		fenetre.setVisible(true);
	}
	
	public void refresh() {
		if (refresh) {
			for (int j = 0;j < data.getLargeur();j++) {
				for (int i = 0;i < data.getLongueur();i++) {
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
						if (((data.getElement(kk) instanceof Cloture) && data.getElement(kk).getCooX() == i) && (data.getElement(kk).getCooY() == j))
							cloture = true;
						if (((data.getElement(kk) instanceof Gare) && data.getElement(kk).getCooX() == i) && (data.getElement(kk).getCooY() == j))
							gare = true;
						if (((data.getElement(kk) instanceof Voyageur) && data.getElement(kk).getCooX() == i) && (data.getElement(kk).getCooY() == j)) {
							voyageur = true;
							if (((Voyageur) data.getElement(kk)).getDirection() == Direction.EST)
								voyageurD = "e";
							else if (((Voyageur) data.getElement(kk)).getDirection() == Direction.OUEST)
								voyageurD = "o";
							else if (((Voyageur) data.getElement(kk)).getDirection() == Direction.SUD)
								voyageurD = "s";
							else
								voyageurD = "n";
						}
						if (((data.getElement(kk) instanceof Vache) && data.getElement(kk).getCooX() == i) && (data.getElement(kk).getCooY() == j))
							vache = true;
						if (((data.getElement(kk) instanceof Charbon) && data.getElement(kk).getCooX() == i) && (data.getElement(kk).getCooY() == j))
							charbon = true;
						if (((data.getElement(kk) instanceof Tronc) && data.getElement(kk).getCooX() == i) && (data.getElement(kk).getCooY() == j))
							tronc = true;
						if (((data.getElement(kk) instanceof Wagon) && data.getElement(kk).getCooX() == i) && (data.getElement(kk).getCooY() == j))
							wagon = true;
						for (int k = 0;k < train.getTaille();k++) {
							if ((train.getWagon(k).getCooX() == i) && (train.getWagon(k).getCooY() == j)) {
								if (train.getWagon(k).getPlaces() == 0)
								   loco = true;
								else {
								  booltrain = true;
								  indexOfWagon = train.getIndex(train.getWagon(k));
								  kprime = k;
								}
							}
						}
					}
					if (cloture)
						tab[j][i].setText("<html><b style='color: black'>X</b></html>");
					else if (gare)
						tab[j][i].setText("<html><b style='color: #0000FF'>A</b></html>");
					else if (vache)
						tab[j][i].setText("<html><b style='color: white'>m</b></html>");
					else if (wagon)
						tab[j][i].setText("<html><b style='color: yellow'>W</b></html>");
					else if (voyageur)
						tab[j][i].setText("<html><b style='color: #FFCCCC'>o</b></html>");
					else if (loco) {
						if (!konami) {
						   if (train.getDirection() == Direction.NORD || train.getDirection() == Direction.SUD)
							   tab[j][i].setText("<html><b style='color: red'>i</b></html>");
							else if (train.getDirection() == Direction.OUEST)
							   tab[j][i].setText("<html><b style='color: red'>b</b></html>");
							else
							   tab[j][i].setText("<html><b style='color: red'>d</b></html>");
						} else
							tab[j][i].setText("<html><b style='color: "+colorClignote+"'>"+w0+"</b></html>");
					} else if (booltrain) {
						if (!konami) {
							if (train.getWagon(kprime).getRemplissage() < 1)
								tab[j][i].setText("<html><b style='color: yellow'>"+train.getWagon(kprime).getRemplissage()+"</b></html>");
							else if (train.getWagon(kprime).getRemplissage() < 5)
								tab[j][i].setText("<html><b style='color: #FF9900'>"+train.getWagon(kprime).getRemplissage()+"</b></html>");	
							else tab[j][i].setText("<html><b style='color: red'>"+train.getWagon(kprime).getRemplissage()+"</b></html>");
						} else {
							if (indexOfWagon == 1)
								tab[j][i].setText("<html><b style='color: "+colorClignote+"'>"+w1+"</b></html>");
							else if (indexOfWagon == 2)
								tab[j][i].setText("<html><b style='color: "+colorClignote+"'>"+w2+"</b></html>");	
							else if (indexOfWagon == 3)
								tab[j][i].setText("<html><b style='color: "+colorClignote+"'>"+w3+"</b></html>");
							else if (indexOfWagon == 4)
								tab[j][i].setText("<html><b style='color: "+colorClignote+"'>"+w4+"</b></html>");
							else if (indexOfWagon == 5)
								tab[j][i].setText("<html><b style='color: "+colorClignote+"'>"+w5+"</b></html>");	
							else if (indexOfWagon == 6)
								tab[j][i].setText("<html><b style='color: "+colorClignote+"'>"+w6+"</b></html>");
							else if (indexOfWagon == 7)
								tab[j][i].setText("<html><b style='color: "+colorClignote+"'>"+w7+"</b></html>");
							else {
								if (train.getWagon(kprime).getRemplissage() < 1)
									tab[j][i].setText("<html><b style='color: yellow'>"+train.getWagon(kprime).getRemplissage()+"</b></html>");
								else if (train.getWagon(kprime).getRemplissage() < 5)
									tab[j][i].setText("<html><b style='color: #FF9900'>"+train.getWagon(kprime).getRemplissage()+"</b></html>");	
								else tab[j][i].setText("<html><b style='color: red'>"+train.getWagon(kprime).getRemplissage()+"</b></html>");
							}
						}
					} else if (charbon)
						tab[j][i].setText("<html><b style='color: gray'>@</b></html>");
					else if (tronc)
						tab[j][i].setText("<html><b style='color: maroon'>=</b></html>");
					else
						tab[j][i].setText(" ");
				}
			}
			if (train.getTaille() == 8 && konami)
				clignote = true;
			else {
				clignote = false;
			   colorClignote = "red";
			}
			clignote();
		}
	}
	
	public void clignote() {
		if (clignote) {
			if (colorClignote == "red")
				colorClignote = "yellow";
			else
				colorClignote = "red";
		}
	}
	
	public void setData(Data data) {
	   this.data = data;
	}
	
	public void setTrain(Train train) {
	   this.train = train;
	}
   
	public JFrame getJFrame() {
		return fenetre;
	}
	
	public void setEnabled(boolean bool) {
		menuFile.setEnabled(bool);
	}
	
	public void setFocus() {
		fenetre.requestFocus();
	}
	
	public void setKonami() {
		if (w0 != "B") {
			konami = true;
			w0 = "B";
			w1 = "L";
			w2 = "G";
			w3 = "g";
			w4 = "a";
			w5 = "m";
			w6 = "e";
			w7 = "s";
		} else {
			konami = false;
			colorClignote = "red";
			w0 = "d";
			w1 = "0";
			w2 = "0";
			w3 = "0";
			w4 = "0";
			w5 = "0";
			w6 = "0";
			w7 = "0";
		}
	}
	
	public void rmKonami() {
		w0 = "d";
		w1 = "0";
		w2 = "0";
		w3 = "0";
		w4 = "0";
		w5 = "0";
		w6 = "0";
		w7 = "0";
	}
	
	public void changerScore() {
		String points;
		if (train.getScore() >= -1 && train.getScore() <= 1)
			points = "pt";
		else
			points = "pts";
		score.setText("<html><b style='color: red'>"+train.getScore()+" "+points+"</b></html>");
	}
}
