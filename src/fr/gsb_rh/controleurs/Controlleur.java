package fr.gsb_rh.controleurs;

import javax.swing.JPanel;

import fr.gsb_rh.modeles.Employe;
import fr.gsb_rh.modeles.RequeteBase;
import fr.gsb_rh.vues.Login;

public class Controlleur {
	
	public Controlleur(){
		
	}
	public void demanderLogin(){
		Login Login = new Login(this);
		Login.setVisible(true);
	}
	
//	public void lancerAppli(){	
//		FenetreAppli appliRH = new FenetreAppli();
//		FenetreAppli.addOnglets(this.listerLibelles(), this.AjoutPanels());
//		FenetreAppli.setVisible(true);
//	}
	
//	private JPanel[] AjoutPanels(){
//		JPanel panels[] = {new PanneauAjout(RequeteBase.listerIdStatut(),this), new PanneauModification(RequeteBase.listerEmployes(), this)};
//		return panels;
//	}
	
	public boolean verifierMdp(String log, String mdp){
		return RequeteBase.estConnecte(log, mdp);
	}
	
	public void ajouterEmploye(Employe employe){
		RequeteBase.creerUser(employe);
	}
	
//	public Employe rechercherEmploye(String selection){
//		return RequeteBase.lireEmploye(selection);
//	}
	
	public void majEmploye(Employe employe){
		RequeteBase.modifierEmploye(employe);
	}
	
//	private String[] listerLibelles(){
//		String libelles[] = {"Ajout","Modification"};
//		return libelles;
//	}
//	
//	public void ListerServices (){
//		
//	}
	
}
