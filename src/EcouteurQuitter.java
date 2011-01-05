import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

public class EcouteurQuitter implements ActionListener {
	private EcrireTxt scoreTxt;

	public EcouteurQuitter(EcrireTxt scoreTxt) {
		this.scoreTxt = scoreTxt;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		scoreTxt.fermer();
		System.exit(0);
	}
}
