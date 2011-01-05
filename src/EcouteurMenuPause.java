
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

public class EcouteurMenuPause implements ActionListener {
	private Offline offline;
	private JDialog pause;
	private Fenetre fenetre;
	
	public EcouteurMenuPause(JDialog pause, Offline offline, Fenetre fenetre) {
		this.offline = offline;
		this.pause = pause;
		this.fenetre = fenetre;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		offline.p();
	}
}
