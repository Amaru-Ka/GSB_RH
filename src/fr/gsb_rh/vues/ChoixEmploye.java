package fr.gsb_rh.vues;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.gsb_rh.modeles.Employe;

public class ChoixEmploye extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel label;
	private JComboBox<String> choix;
	
	public String getSelection()
	{
		return (String)this.choix.getSelectedItem();
	}
	
	public JComboBox<String> getComboBox(){
		return this.choix;
	}
	
	public ChoixEmploye(String label, ArrayList<Employe> lesChoix) {
		
		this.label = new JLabel(label);
		this.choix = new JComboBox<String>();
		
		for(Employe unChoix : lesChoix)
			this.choix.addItem(unChoix.getId() +" "+unChoix.getNom()+" "+unChoix.getPrenom());
		
		this.label.setPreferredSize(new Dimension(170,40));
		this.label.setForeground(Color.white);
		this.choix.setPreferredSize(new Dimension(170,40));
		this.setBackground(new Color(70,80,70));
		this.add(this.label);
		this.add(this.choix);
	}
	
	
}