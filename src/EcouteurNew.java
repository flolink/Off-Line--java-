import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

public class EcouteurNew implements ActionListener {
	private Offline offline;
	private JDialog dialog;
	private Fenetre fenetre;

	public EcouteurNew(Offline offline, Fenetre fenetre) {
		this.offline = offline;
		this.fenetre = fenetre;
	}
	
	public EcouteurNew(Offline offline, JDialog dialog, Fenetre fenetre) {
		this.offline = offline;
		this.dialog = dialog;
		this.fenetre = fenetre;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (dialog != null) {
			if (dialog.getTitle() == "pause")
				offline.p();
			dialog.setVisible(false);
		}
		offline.n();
	}
}

