import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class EcouteurScores implements ActionListener {

	private JFrame fenetre;
	private BufferedReader bfReader;
	private InputStream ipStream;
	private InputStreamReader ipStreamReader;

	public EcouteurScores(JFrame fenetre, String nomFichier) {
		this.fenetre = fenetre;
		File fichier = new File(nomFichier);
		try {
			ipStream = new FileInputStream(nomFichier);
			ipStreamReader = new InputStreamReader(ipStream);
			bfReader = new BufferedReader(ipStreamReader);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JDialog config = new JDialog(fenetre,"scores");
		config.setSize(200,200);
		config.setLocationRelativeTo(null);
		config.setBackground(Color.GRAY);
		config.setLayout(new FlowLayout());
		String textLabel = "<html>";
		try {
			String ligne;
			while ((ligne = bfReader.readLine()) != null)
				textLabel += ligne+"<br/>";
			bfReader.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		textLabel += "</html>";
		config.add(new JLabel(textLabel));
		config.setVisible(true);
	}
}
