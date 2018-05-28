package fr.gsb_rh.vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import fr.gsb_rh.modeles.Employe;

/**
 * Classe graphique d'affichage du panel de modification d'un utilisateur
 * @see Panneaux.java
 * @author antoine & audrey
 * @version 1.0
 */
public class PanneauModifier extends Panneaux implements ItemListener {	
	private static final long serialVersionUID = 2601867782131162318L;
	private JPanel Table2 = new JPanel(new GridLayout(1, 2));
	private ChoixEmploye listEmploye = null;
	
	/**
	 * Constructeur surcharg� qui prend en parametre un ArrayList<Employe>
	 * @param lesEmployes : ArrayList<Employe>
	 */
	public PanneauModifier(ArrayList<Employe> lesEmployes) {
		super();
		this.controlleur.setLesEmployes(lesEmployes);
		this.initComponents();
		
	}
	
	/**
	 * M�thode d'initialisation des �l�ments de la fenetre
	 */
	public void initComponents(){
		//Cr�ation des diff�rents panels pour la disposition des �l�ments.
		JPanel Button2 = new JPanel();
		JPanel content = this.placerChamps(true);
		JButton valider = new JButton("Valider");
		JButton annuler = new JButton("Annuler");
		this.setLayout(new BorderLayout());		
		Table2.setBackground(new Color(70,80,70));
		Table2.setPreferredSize(new Dimension(100,70));
		this.afficherListe();
		this.add(Table2, BorderLayout.NORTH);
		this.add(content, BorderLayout.CENTER);
		this.add(Button2, BorderLayout.SOUTH);
		Table2.add(this.listEmploye);
		valider.addActionListener(this);
		annuler.addActionListener(this);
		Button2.add(valider);
		Button2.add(annuler);
	}

	
	/**
	 * M�thode d'affichage de la liste de selection d'employe
	 */
	public void afficherListe(){
		this.listEmploye = new ChoixEmploye("Choisir un employ� :", this.controlleur.getLesEmployes());
		this.listEmploye.getComboBox().addItemListener(this);
	}
	
	/**
	 * M�thode d'�coute de la liste d'employe
	 */
	public void itemStateChanged(ItemEvent e) {
		String selection;
		if(e.getSource() == this.listEmploye.getComboBox()){
			selection = this.listEmploye.getSelection();
			String[] infosEmploye = selection.split(" ");
			this.controlleur.setEmployeSelectionne(this.controlleur.unEmploye(infosEmploye[1],infosEmploye[2]));
			this.remplirChamp();	 		
		}
	}
		
	/**
	 * M�thode d'�coute des boutons annuler et valider
	 */
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();	
		 //Permet de remettre les renseigenements de bases sur l'employ� si l'utilisateur appuie sur "annuler"
		if(action.equals("Annuler")){
			this.remplirChamp();
		}
		//Si l'utilisateur clic que "Valider", alors on enregistre les donn�es en base. 
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
			JOptionPane.showMessageDialog(null,"Modification effectu�e","Valider",JOptionPane.ERROR_MESSAGE);
			this.listEmploye.getComboBox().removeItemListener(this);
			this.listEmploye.majList(this.controlleur.getLesEmployes());
			this.listEmploye.getComboBox().addItemListener(this);
		}
	}
}
