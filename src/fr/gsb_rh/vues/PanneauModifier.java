package fr.gsb_rh.vues;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanneauModifier extends JPanel implements ActionListener {
	private Champ id; 
	private Champ nom; 
	private Champ prenom; 
	private Champ Login; 
	private Champ mpd;
	private Champ DateEmbauche;
	private Champ adresse;
	private Champ cp;
	private Champ ville;
	private Champ dateEmbauche;
	private Champ telephone;
	private Champ email;
	private String idStatut;
	
	public PanneauModifier(){
		super();	
	}
	
	public void initComponents(){
		
		JButton valider = new JButton("Valider");
		valider.addActionListener(this);
		JButton annuler = new JButton("Annuler");
		annuler.addActionListener(this);
		this.add(valider);
		this.add(annuler);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
