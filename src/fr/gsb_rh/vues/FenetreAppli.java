package fr.gsb_rh.vues;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class FenetreAppli extends JFrame {
	
	public FenetreAppli(){
		//Définir un titre pour la fenêtre.
		this.setTitle("Application GSB des Ressources Humaines");
		//Définir la taille de la fenêtre.
	    this.setSize(900, 600);
	    //La fenêtre ne peut pas être redimensionnée.
	    this.setResizable(true);
	    // Centrer la fenêtre.
	    //Pour une autre position : this.setLocation(x,y);
	    this.setLocationRelativeTo(null);
	    //Evénement pour terminer le processus à la fermeture de la fenêtre.
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	    
	    Onglet onglet = new Onglet();
	    onglet.ajouterOnglet("Accueil");
	    onglet.ajouterOnglet("Ajouter un utilisateur");
	    onglet.ajouterOnglet("Modifier un utilisateur");

	    
	    this.getContentPane().add(onglet);
	    
	    this.setVisible(true);
	    
	    
	    
	    
	}
	
//	public void addOnglets(String[] libelles, JPanel[] panels){
//		int limite = libelles.length;
//		
//		JTabbedPane onglet = new JTabbedPane();
//		
//		for(int i=0; i<limite; i++)
//			onglet.add(libelles[i], panels[i]);
//		
//		this.getContentPane().add(onglet);
//	}
}
