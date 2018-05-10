package fr.gsb_rh.vues;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
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
		JPanel table = new JPanel();
		JPanel content = new JPanel(new SpringLayout());
		JLabel titre = new JLabel("Pour ajouter un nouvel employé, il faut renseigner l'ensemble des champs ci-dessous");

		JPanel boutons = new JPanel();
		this.setLayout(new BorderLayout());
		table.add(titre);
		this.add(table, BorderLayout.NORTH);
		this.add(this.placerChamps(), BorderLayout.CENTER);
		this.add(boutons, BorderLayout.SOUTH);
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
												 this.listService.getComboBox().getSelectedIndex()+1); 
					JOptionPane.showMessageDialog(null, "Les données de l'employé son sauvées en base",null,JOptionPane.INFORMATION_MESSAGE);
					this.effacerChampSaisie();
			}
		}
	}

	
}
