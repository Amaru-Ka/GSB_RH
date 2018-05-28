<<<<<<< HEAD
package fr.gsb_rh.vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Classe graphique d'affichage du panel d'ajout d'utilisateur
 * @see Panneaux.java
 * @author antoine & audrey
 * @version 1.0
 */
public class PanneauAjout extends Panneaux {
	private static final long serialVersionUID = -7224798746581420809L;	
	public PanneauAjout(){
		super();
		this.initComponents();
	}
	
	public void initComponents(){
		
		//Panels et Layout
		JPanel table = new JPanel();
		JPanel content = this.placerChamps();
		JPanel boutons = new JPanel();
		JLabel titre = new JLabel("Pour ajouter un nouvel employé, il faut renseigner l'ensemble des champs ci-dessous");
		JButton annuler = new JButton("Annuler");
		JButton valider = new JButton("Valider");

		titre.setForeground(Color.white);
		table.setBackground(new Color(70,80,70));
		table.setPreferredSize(new Dimension(100,70));
		valider.addActionListener(this);
		annuler.addActionListener(this);
		this.setLayout(new BorderLayout());
		this.add(table, BorderLayout.NORTH);
		this.add(content, BorderLayout.CENTER);
		this.add(boutons, BorderLayout.SOUTH);
		boutons.add(valider);
		boutons.add(annuler);
		table.add(titre);
		
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
			this.controlleur.getEmployeSelectionne().setAdresse(this.adresse.getDansSaisie());
			this.controlleur.getEmployeSelectionne().setCP(this.cp.getDansSaisie());
			this.controlleur.getEmployeSelectionne().setVille(this.ville.getDansSaisie());
			this.controlleur.getEmployeSelectionne().setTelephone(this.telephone.getDansSaisie());
			this.controlleur.getEmployeSelectionne().setMail(this.email.getDansSaisie());
			this.controlleur.getEmployeSelectionne().setIdService(this.listService.getComboBox().getSelectedIndex()+1);
			this.controlleur.majEmploye(this.controlleur.getEmployeSelectionne());
			JOptionPane.showMessageDialog(null,"Modification effectuée","Valider",JOptionPane.ERROR_MESSAGE);
			this.listService.majList(this.controlleur.getLesEmployes());
		}
	}
}
=======
package fr.gsb_rh.vues;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Spring;
import javax.swing.SpringLayout;

import fr.gsb_rh.controleurs.Controlleur;


public class PanneauAjout extends Panneaux implements ActionListener {
	private static final long serialVersionUID = -7224798746581420809L;
	protected Controlleur controleur = null;	
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
		JLabel titre = new JLabel("Pour ajouter un nouvel employé, il faut renseigner l'ensemble des champs ci-dessous");
		content.setLayout(springLayout);
		JPanel boutons = new JPanel();
		this.setLayout(new BorderLayout());
		table.add(titre);
		this.add(table, BorderLayout.NORTH);
		this.add(content, BorderLayout.CENTER);
		this.add(boutons, BorderLayout.SOUTH);
		
		//Champs
		this.nom = new Champ("Nom(*): ");
		this.prenom = new Champ("Prénom(*): ");
		this.dateEmbauche = new Champ("Date d'embauche(JJMMAAAA)(*): ");
		this.adresse = new Champ("Adresse(*): ");
		this.cp = new Champ("CP(*): ");
		this.ville = new Champ("Ville(*): ");
		this.telephone = new Champ("N° Téléphone(*): ");
		this.email = new Champ("Adresse e-mail(*): ");
		
		//Liste des services. Ajout d'un panel, pour puvoir lettre le JcomboBox qui fait appelle au Data 
		// permettant l'affichage des différents services.
		JPanel services = new JPanel();
		JLabel labelService = new JLabel("Service: ");
		String[] data = this.controleur.listerService();
		listService = new JComboBox<String>(data);
		listService.setPreferredSize(new Dimension(150,30));
		services.add(labelService);
		services.add(listService);
		
		
		//Contraintes du SpringLayout
		SpringLayout.Constraints champNom = new SpringLayout.Constraints();
		SpringLayout.Constraints champPrenom = new SpringLayout.Constraints();
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
		content.add(this.dateEmbauche,champDateE);
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
			this.effacerChampSaisie();	
		}else{
			//verification si les champs sont vides avant l'envoie de la requête.
			// Si un champ est vide alors on génère un message d'erreur. 
			if (this.verifSaisie()){
					JOptionPane.showMessageDialog(null, "Attention certains champs sont vides !",null,JOptionPane.ERROR_MESSAGE);
				}
			else {
					this.controleur.creerEmploye(this.nom.getDansSaisie(),
												 this.prenom.getDansSaisie(),
												 this.adresse.getDansSaisie(),
												 this.cp.getDansSaisie(),
												 this.ville.getDansSaisie(),
												 this.telephone.getDansSaisie(),
												 this.email.getDansSaisie(),
												 this.controleur.formatterDate(this.dateEmbauche.getDansSaisie()),
												 this.listService.getSelectedIndex()+1); 
					JOptionPane.showMessageDialog(null, "Les données de l'employé son sauvées en base",null,JOptionPane.INFORMATION_MESSAGE);
					this.effacerChampSaisie();
			}
		}
	}

	
}
>>>>>>> 84dcd0fe3ec62aedbf2f11410277de653a1dee3f
