import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

public class EcouteurRegles implements ActionListener {
   private JFrame fenetre;

   public EcouteurRegles(JFrame fenetre) {
      this.fenetre = fenetre;
   } 
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JDialog regles = new JDialog(fenetre,"regles");
		regles.setSize(700,300);
		regles.setLocationRelativeTo(null);
		regles.setBackground(Color.GRAY);
		regles.setLayout(new FlowLayout());
		JLabel text = new JLabel("<html><h2><u>Off-Line</u></h2><br/>Le train a deraille !<br/>Vous controllez une loco dans un champ.<br/>Vous possedez des wagons.<br/>Chaque wagon peut contenir 0->5 passagers.<br/>Pour ramasser un voyageur, frolez le, mais ne l'ecrasez surtout pas !<br/>Evitez aussi d'ecraser les vaches...<br/>Gagnez de nouveaux wagons en foncant dessus.<br/>Le charbon vous permet d'aller plus vite !<br/>Vous perdez quand vous touchez la barriere, un tronc ou vos propres wagons.<br/>N'hesitez pas a passer dans les gares pour faire monter de nouveaux voyageurs !</html>");
		regles.add(text);
		regles.setVisible(true);
	}
}
