package fr.gsb_rh.vues;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import fr.gsb_rh.controleurs.Controlleur;
import fr.gsb_rh.modeles.Employe;

public class PanneauAjout extends JPanel implements ActionListener {

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
	private String idService;
	private JComboBox<String> listService;
	public PanneauAjout(Controlleur Controlleur){
		super();
		this.controleur = Controlleur;
		this.initComponents();
	}
	public void initComponents(){
		
		//Panels et Layout
		JPanel table = new JPanel(new GridLayout(3,0));
		JPanel content = new JPanel(new GridLayout(10,0));
		JPanel boutons = new JPanel();
		this.setLayout(new BorderLayout());
		this.add(table, BorderLayout.NORTH);
		this.add(content, BorderLayout.CENTER);
		this.add(boutons, BorderLayout.SOUTH);
		//Champs
		this.nom = new Champ("Nom: ");
		this.prenom = new Champ("Prénom: ");
		this.dateNaissance = new Champ("Date de naissance(JJMMAAAA):");
		this.DateEmbauche = new Champ("Date d'embauche(JJMMAAAA): ");
		this.adresse = new Champ("Adresse: ");
		this.cp = new Champ("CP: ");
		this.ville = new Champ("Ville: ");
		this.telephone = new Champ("N° Téléphone: ");
		this.email = new Champ("Adresse e-mail: ");
		//Liste des services
		String[] data = this.controleur.listerService();
		listService = new JComboBox<String>(data);
		listService.setPreferredSize(new Dimension(150,30));
		
		JLabel titre = new JLabel("Ajouter un nouvel employé");
		//Add dans les différents panneaux.
		table.add(titre);
		
		content.add(this.nom);
		content.add(this.prenom);
		content.add(this.dateNaissance);
		content.add(this.DateEmbauche);
		content.add(this.adresse);
		content.add(this.cp);
		content.add(this.ville);
		content.add(this.telephone);
		content.add(this.email);
		content.add(listService);
		
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
			this.controleur.creerEmploye(this.nom.getDansSaisie(),
					this.prenom.getDansSaisie(),
					this.controleur.formatterDate(this.dateNaissance.getDansSaisie()),
					this.adresse.getDansSaisie(),
					this.cp.getDansSaisie(),
					this.ville.getDansSaisie(),
					this.telephone.getDansSaisie(),
					this.email.getDansSaisie(),
					this.controleur.formatterDate(this.DateEmbauche.getDansSaisie()),
					this.listService.getSelectedIndex()+1);

		}
	}

	
}
