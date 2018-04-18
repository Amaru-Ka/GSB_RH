package fr.gsb_rh.vues;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class FenetreAppli extends JFrame {
	
	public FenetreAppli(){
		//D�finir un titre pour la fen�tre.
		this.setTitle("Application GSB des Ressources Humaines (GSBRH)");
		//D�finir la taille de la fen�tre.
	    this.setSize(500, 600);
	    //La fen�tre ne peut pas �tre redimensionn�e.
	    this.setResizable(false);
	    // Centrer la fen�tre.
	    //Pour une autre position : this.setLocation(x,y);
	    this.setLocationRelativeTo(null);
	    //Ev�nement pour terminer le processus � la fermeture de la fen�tre.
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	}
	
	public void addOnglets(String[] libelles, JPanel[] panels){
		int limite = libelles.length;
		
		JTabbedPane onglet = new JTabbedPane();
		
		for(int i=0; i<limite; i++)
			onglet.add(libelles[i], panels[i]);
		
		this.getContentPane().add(onglet);
	}
}
