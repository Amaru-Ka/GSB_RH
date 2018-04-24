package fr.gsb_rh.vues;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Spring;
import javax.swing.SpringLayout;

import fr.gsb_rh.controleurs.Controlleur;


public class PanneauAjout extends JPanel implements ActionListener {
	private static final long serialVersionUID = -7224798746581420809L;
	protected Controlleur controleur = null;
	
	private Champ nom; 
	private Champ prenom; 
	private Champ dateNaissance;
	private Champ DateEmbauche;
	private Champ adresse;
	private Champ cp;
	private Champ ville;
	private Champ telephone;
	private Champ email;
	private JComboBox<String> listService;
	ArrayList<Champ> lesChamps = new ArrayList<Champ>();
	public PanneauAjout(Controlleur Controlleur){
		super();
		this.controleur = Controlleur;
		this.initComponents();
	}
	public void initComponents(){
		
		//Panels et Layout
		SpringLayout springLayout = new SpringLayout();
		JPanel table = new JPanel();
		JPanel content = new JPanel();
		JLabel titre = new JLabel("Pour ajouter un nouvel employ�, il faut renseigner l'ensemble des champs ci-dessous");
		content.setLayout(springLayout);
		JPanel boutons = new JPanel();
		this.setLayout(new BorderLayout());
		table.add(titre);
		this.add(table, BorderLayout.NORTH);
		this.add(content, BorderLayout.CENTER);
		this.add(boutons, BorderLayout.SOUTH);
		//Champs
		this.nom = new Champ("Nom: ");
		this.prenom = new Champ("Pr�nom: ");
		this.dateNaissance = new Champ("Date de naissance(JJMMAAAA):");
		this.DateEmbauche = new Champ("Date d'embauche(JJMMAAAA): ");
		this.adresse = new Champ("Adresse: ");
		this.cp = new Champ("CP: ");
		this.ville = new Champ("Ville: ");
		this.telephone = new Champ("N� T�l�phone: ");
		this.email = new Champ("Adresse e-mail: ");
		
		//Liste des services
		JPanel services = new JPanel();
		JLabel labelService = new JLabel("Service: ");
		//Recup des data services
		String[] data = this.controleur.listerService();
		listService = new JComboBox<String>(data);
		listService.setPreferredSize(new Dimension(150,30));
		services.add(labelService);
		services.add(listService);
		//Contraintes du SpringLayout
		SpringLayout.Constraints champNom = new SpringLayout.Constraints();
		SpringLayout.Constraints champPrenom = new SpringLayout.Constraints();
		SpringLayout.Constraints champDate = new SpringLayout.Constraints();
		SpringLayout.Constraints champDateE = new SpringLayout.Constraints();
		SpringLayout.Constraints champAdr = new SpringLayout.Constraints();
		SpringLayout.Constraints champCP = new SpringLayout.Constraints();
		SpringLayout.Constraints champVille = new SpringLayout.Constraints();
		SpringLayout.Constraints champMail = new SpringLayout.Constraints();
		SpringLayout.Constraints champTel = new SpringLayout.Constraints();
		SpringLayout.Constraints champListService = new SpringLayout.Constraints();
		//nom
		Spring yPadding = Spring.constant(40);
		champNom.setX(Spring.constant(179));
		champNom.setY(yPadding);
		//prenom
		yPadding = Spring.constant(80);
		champPrenom.setX(Spring.constant(160));
		champPrenom.setY(yPadding);
	    //date de naissance
	    yPadding = Spring.constant(120);
	    champDate.setX(Spring.constant(30));
	    champDate.setY(yPadding);	
	    //date embauche
	    yPadding = Spring.constant(160);
	    champDateE.setX(Spring.constant(33));
	    champDateE.setY(yPadding);
	    //Telephone
	    yPadding = Spring.constant(200);
	    champTel.setX(Spring.constant(130));
	    champTel.setY(yPadding);
		//Adresse
	    yPadding = Spring.constant(40);
	    champAdr.setX(Spring.constant(500));
	    champAdr.setY(yPadding);
	    //CP
	    yPadding = Spring.constant(80);
	    champCP.setX(Spring.constant(532));
	    champCP.setY(yPadding);
	    //Ville
	    yPadding = Spring.constant(120);
	    champVille.setX(Spring.constant(525));
	    champVille.setY(yPadding);
	    //Mail
	    yPadding = Spring.constant(160);
	    champMail.setX(Spring.constant(463));
	    champMail.setY(yPadding);
	    //Service
	    yPadding = Spring.constant(200);
		champListService.setX(Spring.constant(505));
		champListService.setY(yPadding);
	    //Add au panel principal
		content.add(this.nom,champNom);
		content.add(this.prenom,champPrenom);
		content.add(this.dateNaissance,champDate);		
		content.add(this.DateEmbauche,champDateE);
		content.add(this.adresse,champAdr);
		content.add(this.cp,champCP);	
		content.add(this.ville,champVille);
		content.add(this.email,champMail);
		content.add(this.telephone,champTel);
		content.add(services,champListService);
		//Boutons 
		JButton valider = new JButton("Valider");
		valider.addActionListener(this);
		JButton annuler = new JButton("Annuler");
		annuler.addActionListener(this);
		boutons.add(valider);
		boutons.add(annuler);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String action = arg0.getActionCommand();
		if(action.equals("Annuler")){
			this.nom.effacerSaisie();
			this.prenom.effacerSaisie();
			this.dateNaissance.effacerSaisie();
			this.DateEmbauche.effacerSaisie();
			this.adresse.effacerSaisie();
			this.cp.effacerSaisie();
			this.ville.effacerSaisie();
			this.telephone.effacerSaisie();
			this.email.effacerSaisie();			
			
		}else{				
			if (this.nom.VerifierChamps() && this.prenom.VerifierChamps() && this.dateNaissance.VerifierChamps() &&
				this.email.VerifierChamps() && this.adresse.VerifierChamps() && this.cp.VerifierChamps() &&
				this.ville.VerifierChamps() && this.DateEmbauche.VerifierChamps() && this.telephone.VerifierChamps())
					JOptionPane.showMessageDialog(null, "Attention certains champs sont vides !",null,JOptionPane.ERROR_MESSAGE);
			else{
				this.controleur.creerEmploye(
												this.nom.getDansSaisie(),
												this.prenom.getDansSaisie(),
												this.controleur.formatterDate(this.dateNaissance.getDansSaisie()),
												this.adresse.getDansSaisie(),
												this.cp.getDansSaisie(),
												this.ville.getDansSaisie(),
												this.telephone.getDansSaisie(),
												this.email.getDansSaisie(),
												this.controleur.formatterDate(this.DateEmbauche.getDansSaisie()),
												this.listService.getSelectedIndex()+1);
				
				JOptionPane.showMessageDialog(null,"L'utilisateur a bien �t� enregistr�",null, JOptionPane.INFORMATION_MESSAGE);
				
			}
			
		}
	}

	
}
