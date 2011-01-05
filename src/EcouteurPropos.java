import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

public class EcouteurPropos implements ActionListener {
   JFrame fenetre;

   public EcouteurPropos(JFrame fenetre) {
      this.fenetre = fenetre;
   } 
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JDialog config = new JDialog(fenetre,"a propos");
		config.setSize(150,250);
		config.setLocationRelativeTo(null);
		config.setBackground(Color.GRAY);
		config.setLayout(new FlowLayout());
		JLabel text = new JLabel("<html><h2><u><b style='color: red'>Off-Line</b></u></h2><br/>Lepinay Florian<br/><br/><br/><h4><u><b style='color: blue'>BLG Games</b></u></h4><br/>Lepinay Florian<br/>Ceillier Baptiste</html>");
		config.add(text);
		config.setVisible(true);
	}
}
