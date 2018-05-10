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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.gsb_rh.controleurs.Controlleur;
import fr.gsb_rh.modeles.Employe;

public class PanneauModifier extends Panneaux implements ActionListener,ItemListener {	
	private static final long serialVersionUID = 2601867782131162318L;
	private ChoixEmploye Liste;
	private JPanel Table2 = new JPanel(new GridLayout(1, 2));
	public PanneauModifier(ArrayList<Employe> lesEmployes, Controlleur controlleur) {
		super();
		this.controlleur = controlleur;
		this.controlleur.setLesEmployes(lesEmployes);
		this.initComponents();
		
	}
	public void initComponents(){
		//Création des différents panels pour la disposition des éléments.
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
		Table2.add(this.Liste);
		valider.addActionListener(this);
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
			this.controlleur.getEmployeSelectionne().setIdService(this.listService.getComboBox().getSelectedIndex()+1);
			this.controlleur.majEmploye(this.controlleur.getEmployeSelectionne());
			this.Liste.majList(this.controlleur.getLesEmployes());
			JOptionPane.showMessageDialog(null,"Modification effectuée","Valider",JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
