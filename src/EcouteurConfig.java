import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

public class EcouteurConfig implements ActionListener {
   JFrame fenetre;

   public EcouteurConfig(JFrame fenetre) {
      this.fenetre = fenetre;
   } 
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JDialog config = new JDialog(fenetre,"config");
		config.setSize(300,200);
		config.setLocationRelativeTo(null);
		config.setBackground(Color.GRAY);
		config.setLayout(new FlowLayout());
		JLabel text = new JLabel("<html>haut (^)<br/>bas (v)<br/>gauche (&lt)<br/>droite (>)<br/><br/>pause (p)<br/>nouveau jeu (en pause) (n)<br/>quitter (en pause) (q)</html>");
		config.add(text);
		config.setVisible(true);
	}
}
