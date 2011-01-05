import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

public class EcouteurPause implements ActionListener {
	private Offline offline;

	public EcouteurPause(Offline offline) {
		this.offline = offline;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		offline.p();
	}
}
