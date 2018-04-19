package fr.gsb_rh.controleurs;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;

import fr.gsb_rh.modeles.Employe;
import fr.gsb_rh.modeles.RequeteBase;
import fr.gsb_rh.vues.FenetreAppli;
import fr.gsb_rh.vues.Login;
import fr.gsb_rh.vues.PanneauAjout;
import fr.gsb_rh.vues.PanneauModifier;

public class Controlleur {
	
	public Controlleur(){
		
	}
	public void demanderLogin(){
		Login Login = new Login(this);
		Login.setVisible(true);
	}
	
	public void lancerAppli(){
		FenetreAppli appliRH = new FenetreAppli();
		appliRH.AjoutOnglets(this.listerLibelles(), this.listerPanels());
		appliRH.setVisible(true);
	}
	
	private JPanel[] listerPanels(){
		JPanel panels[] = {new PanneauAjout(this), new PanneauModifier()};
		return panels;
	}
	
	private String[] listerLibelles(){
		String libelles[] = {"Ajout","Modification"};
		return libelles;
	}
	
	public boolean verifierMdp(String log, String mdp){
		return RequeteBase.estConnecte(log, mdp);
	}
	
	public void ajouterEmploye(Employe employe){
		RequeteBase.AjoutEmploye(employe);
	}
	
//	public Employe rechercherEmploye(String selection){
//		return RequeteBase.lireEmploye(selection);
//	}
	
	public String[] listerService(){
		return RequeteBase.getLesServices();
	}
	
	public void majEmploye(Employe employe){
		RequeteBase.modifierEmploye(employe);
	}
	
	public void creerEmploye(String nom, String prenom,String dateNaissance, String adresse, String cp, String ville, String telephone,String mail, String dateEmbauche,int idService){
		Employe unEmploye = new Employe(nom,prenom,dateNaissance,adresse,cp,ville,telephone,mail,dateEmbauche,idService);
		RequeteBase.AjoutEmploye(unEmploye);
	}
	/**
	 * Methode de formattage de la date afin qu'elle corresponde au format date de MySQL
	 * @param date String : date au format jjmmaaa
	 * @return date String : date au format aaaa-mm-jj
	 */
	public String formatterDate(String date){
		String modifDate = date.substring(4, 8) +"-"+ date.substring(2,4) +"-"+ date.substring(0,2);
		return modifDate;
	}
	
}
