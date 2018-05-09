package fr.gsb_rh.vues;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import fr.gsb_rh.controleurs.Controlleur;

public abstract class Panneaux extends JPanel {
	private static final long serialVersionUID = -733261604364482113L;
	protected Champ nom; 
	protected Champ prenom; 
	protected Champ adresse;
	protected Champ cp;
	protected Champ ville;
	protected Champ dateEmbauche;
	protected Champ telephone;
	protected Champ email;
	protected Champ Login; 
	protected Champ mdp;
	protected JComboBox<String> listService;
	protected Controlleur controlleur = null;
	
	public void remplirChamp(){
		this.nom.setDansSaisie(this.controlleur.getEmployeSelectionne().getNom());
		this.prenom.setDansSaisie(this.controlleur.getEmployeSelectionne().getPrenom());
		this.adresse.setDansSaisie(this.controlleur.getEmployeSelectionne().getAdresse());
		this.Login.setDansSaisie(this.controlleur.getEmployeSelectionne().getlogin());
		this.mdp.setDansSaisie(this.controlleur.getEmployeSelectionne().getMdp());
		this.cp.setDansSaisie(this.controlleur.getEmployeSelectionne().getCP());
		this.ville.setDansSaisie(this.controlleur.getEmployeSelectionne().getVille());
		this.dateEmbauche.setDansSaisie(this.controlleur.DateFR(this.controlleur.getEmployeSelectionne().getDateEmbauche()));
		this.telephone.setDansSaisie(this.controlleur.getEmployeSelectionne().getTelephone());
		this.email.setDansSaisie(this.controlleur.getEmployeSelectionne().getMail());
		this.listService.setSelectedIndex(this.controlleur.getEmployeSelectionne().getIdService()-1);
	}
	
	public void effacerChampSaisie(){
		this.nom.effacerSaisie();
		this.prenom.effacerSaisie();
		this.dateEmbauche.effacerSaisie();
		this.adresse.effacerSaisie();
		this.cp.effacerSaisie();
		this.ville.effacerSaisie();
		this.telephone.effacerSaisie();
		this.email.effacerSaisie();	
		this.Login.effacerSaisie();
	}
	
	public boolean verifSaisie(){
		boolean flag = false;
		if(this.nom.VerifierChamps()&&this.prenom.VerifierChamps()&&this.email.VerifierChamps()&&
		   this.adresse.VerifierChamps()&&this.cp.VerifierChamps()&&this.ville.VerifierChamps()&&
		   this.dateEmbauche.VerifierChamps()&&this.telephone.VerifierChamps())
			flag = true;
		return flag;
		
	}
	
}
