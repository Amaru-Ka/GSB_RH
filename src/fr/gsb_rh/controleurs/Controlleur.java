package fr.gsb_rh.controleurs;
import java.util.ArrayList;


import javax.swing.JPanel;
import fr.gsb_rh.modeles.Employe;
import fr.gsb_rh.modeles.RequeteBase;
import fr.gsb_rh.vues.FenetreAppli;
import fr.gsb_rh.vues.Login;
import fr.gsb_rh.vues.PanneauAjout;
import fr.gsb_rh.vues.PanneauModifier;


public class Controlleur {
	private Employe leRhConnecte;
	private ArrayList<Employe> lesEmployes;
	private Employe employeSelectionne;
	
	public Controlleur(){
		
	}
	public void demanderLogin(){
		Login Login = new Login(this);
		Login.setVisible(true);
	}
	public Employe getEmployeConnecte(String login){
		return RequeteBase.getEmployeConnecte(login);
	}
	public Employe getEmployeSelectionne() {
		return employeSelectionne;
	}
	public void setEmployeSelectionne(Employe employeSelectionne) {
		this.employeSelectionne = employeSelectionne;
	}
	public void lancerAppli(){
		FenetreAppli appliRH = new FenetreAppli();
		appliRH.AjoutOnglets(this.listerLibelles(), this.listerPanels());
		appliRH.setVisible(true);
	}
	
	public ArrayList<Employe> getLesEmployes() {
		lesEmployes = RequeteBase.getTousLesUsers();
		return lesEmployes;
	}
	
	private JPanel[] listerPanels(){
		JPanel panels[] = {new JPanel(),new PanneauAjout(this), new PanneauModifier(RequeteBase.getTousLesUsersEnFonction(), this)};
		return panels;
	}
	
	private String[] listerLibelles(){
		String libelles[] = {"Accueil","Ajouter un utilisateur","Modifier un utilisateur"};
		return libelles;
	}
	
	public boolean verifierMdp(String log, String mdp){
		return RequeteBase.estConnecte(log, mdp);
	}
	public boolean verifierDroits(String log, String mdp){
		return RequeteBase.verifDroits(log, mdp);
	}
	
	public void ajouterEmploye(Employe employe){
		RequeteBase.AjoutEmploye(employe);
	}
	
	public Employe unEmploye(String nom, String prenom){
		return RequeteBase.unEmploye(nom, prenom);
	}
	
	/**
	 * Permet d'appeler les différents services du laboratoire. 
	 */
	public String[] listerService(){
		return RequeteBase.getLesServices();
	}
	
	/**
	 * Permet de modifier un employer
	 * @param employe
	 */
	public void majEmploye(Employe employe){
		RequeteBase.modifierEmploye(employe);
	}
	
	/**
	 * 
	 * @param nom
	 * @param prenom
	 * @param dateNaissance
	 * @param adresse
	 * @param cp
	 * @param ville
	 * @param telephone
	 * @param mail
	 * @param string 
	 * @param idService
	 */
	public void creerEmploye(String nom, String prenom, String adresse, String cp, String ville, String telephone,String mail, String dateEmbauche, int idService){
		Employe unEmploye = new Employe(nom,prenom,adresse,cp,ville,telephone,mail,dateEmbauche,idService);
		RequeteBase.AjoutEmploye(unEmploye);
	}
	
	
	/**
	 * Methode pour formater la date afin qu'elle corresponde au format date de MySQL
	 * @param date String : date au format jjmmaaaa
	 * @return date String : date au format aaaa-mm-jj
	 */
	public String formatterDate(String date){
		String modifDate = date.substring(4, 8) +"-"+ date.substring(2,4) +"-"+ date.substring(0,2);
		return modifDate;
	}
	
	/**
	 * Methode pour formater la date afin qu'elle corresponde au format de la date française.
	 * @param date String : date au format jjmmaaaa
	 * @return date String : date au format aaaa-mm-jj
	 */
	public String DateFR (String date){
		String modifDate = date.substring(8,10) +"-"+ date.substring(5,7) +"-"+ date.substring(0,4);
		return modifDate;
	}
	
	public Employe getLeRhConnecte() {
		return leRhConnecte;
	}
	
	public void setLeRhConnecte(Employe leRhConnecte) {
		this.leRhConnecte = leRhConnecte;
	}
	
	
	public void setLesEmployes(ArrayList<Employe> lesEmployes) {
		this.lesEmployes = lesEmployes;
	}

	
}
