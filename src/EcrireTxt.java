import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class EcrireTxt {
	private File fichier;
	private FileWriter fw;
	private Train train;
	private String newLine = System.getProperty("line.separator"); 

	public EcrireTxt(String file, Train train) {
		try {
			fichier = new File(file);
			fw = new FileWriter(fichier,true);
        } catch (IOException e) {
            e.printStackTrace();
		}
	}
	
	public void ecrireScore(int score) {
		try {
			Date date = new Date();
			SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
			fw.write(simpleDate.format(date)+" || score : "+score+newLine);		//mettre dans le buffer
			fw.flush();															//imprimer dans le fichier
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void fermer() {
		try {
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}