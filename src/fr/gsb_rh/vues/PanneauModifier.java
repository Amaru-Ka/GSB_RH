package fr.gsb_rh.vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.gsb_rh.controleurs.Controlleur;
import fr.gsb_rh.modeles.Employe;

public class PanneauModifier extends Panneaux implements ActionListener,ItemListener {	
	private static final long serialVersionUID = 2601867782131162318L;
	private Champ DateDepart;
	private ChoixEmploye Liste;
	private JPanel Table2 = new JPanel(new GridLayout(1, 2));
	public PanneauModifier(ArrayList<Employe> lesEmployes, Controlleur controlleur) {
		super();
		this.controlleur = controlleur;
		this.controlleur.setLesEmployes(lesEmployes);
		this.initComponents();
		
	}
	public void initComponents(){
		//Création de mes différents panels pour la disposition de mes éléments.
		JPanel Table2 = new JPanel(new GridLayout(1, 2));
		JPanel Content2 = new JPanel((new GridLayout(12, 2)));
		JPanel Button2 = new JPanel();
//		JLabel titre = new JLabel("Choisir d'abord l'employé et ensuite la modification que vous voulez apporter");
		
		this.setLayout(new BorderLayout());
		this.add(Table2, BorderLayout.NORTH);
		this.add(Content2, BorderLayout.CENTER);
		this.add(Button2, BorderLayout.SOUTH);
		
		//Liste des services. Ajout d'un panel, pour puvoir lettre le JcomboBox qui fait appelle au Data 
		// permettant l'affichage des différents services.
		JPanel services = new JPanel();
		JLabel labelService = new JLabel("Service: ");
		String[] data = this.controlleur.listerService();
		listService = new JComboBox<String>(data);
		listService.setPreferredSize(new Dimension(150,30));
		services.add(labelService);
		services.add(listService);
		
		
		//Maliste
		Table2.setBackground(new Color(70,80,70));
		Table2.setPreferredSize(new Dimension(100,70));
		this.afficherListe();
		Table2.add(this.Liste);
		
		//Instanciation de mes champs.
		this.nom = new Champ("Nom :");
		this.prenom = new Champ("Prénom :");
		this.adresse = new Champ("Adresse :");
		this.Login = new Champ("Identifiant :");
		this.mdp = new Champ("Mot de passe"); 
		this.cp = new Champ("CP :");
		this.ville = new Champ("Ville :");
		this.dateEmbauche = new Champ("Date d'embauche :");
		this.telephone = new Champ("Téléphone :");
		this.email = new Champ("Email :");
		this.DateDepart = new Champ("Date de départ :");
		
		//Ajout de chaque champs sur un mot de passe.	
		Content2.add(this.nom);
		Content2.add(this.prenom);
		Content2.add(this.adresse);
		Content2.add(this.Login);
		Content2.add(this.mdp);
		Content2.add(this.cp);
		Content2.add(this.ville);
		Content2.add(this.dateEmbauche);
		Content2.add(this.telephone);
		Content2.add(this.email);
		Content2.add(this.DateDepart);
		Content2.add(services);
		
		//Boutons 
		JButton valider = new JButton("Valider");
		valider.addActionListener(this);
		JButton annuler = new JButton("Annuler");
		annuler.addActionListener(this);
		Button2.add(valider);
		Button2.add(annuler);
	}
	public void afficherListe(){
		this.Liste = new ChoixEmploye("Choisir un employé :", this.controlleur.getLesEmployes());
		this.Liste.getComboBox().addItemListener(this);
	}
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		String selection;
		if(e.getSource() == this.Liste.getComboBox()){
			selection = this.Liste.getSelection();
			String[] infosEmploye = selection.split(" ");
			this.controlleur.setEmployeSelectionne(this.controlleur.unEmploye(infosEmploye[1],infosEmploye[2]));
			this.remplirChamp();	 		
		}
	}
		
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();	
		 //Permet de remettre les renseigenements de bases sur l'employé si l'utilisateur appuie sur "annuler"
		if(action.equals("Annuler")){
			this.remplirChamp();
		}
		//Si l'utilisateur clic que "Valider", alors on enregistre les données en base. 
		if(action.equals("Valider")){
			this.controlleur.getEmployeSelectionne().setNom(this.nom.getDansSaisie());
			this.controlleur.getEmployeSelectionne().setPrenom(this.prenom.getDansSaisie());
			this.controlleur.getEmployeSelectionne().setLogin(this.Login.getDansSaisie());
			this.controlleur.getEmployeSelectionne().setMdp(this.mdp.getDansSaisie());
			this.controlleur.getEmployeSelectionne().setAdresse(this.adresse.getDansSaisie());
			this.controlleur.getEmployeSelectionne().setCP(this.cp.getDansSaisie());
			this.controlleur.getEmployeSelectionne().setVille(this.ville.getDansSaisie());
			this.controlleur.getEmployeSelectionne().setTelephone(this.telephone.getDansSaisie());
			this.controlleur.getEmployeSelectionne().setMail(this.email.getDansSaisie());
			this.controlleur.getEmployeSelectionne().setIdService(this.listService.getSelectedIndex()+1);
			this.controlleur.majEmploye(this.controlleur.getEmployeSelectionne());
			JOptionPane.showMessageDialog(null,"Modification effectuée","Valider",JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
