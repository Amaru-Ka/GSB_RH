<<<<<<< HEAD
package fr.gsb_rh.vues;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.gsb_rh.modeles.Employe;

public class ChoixEmploye extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel label;
	private JComboBox<String> choix;
	
	/**
	 * Constructeur par d�faut
	 */
	public ChoixEmploye(){
		
	}
	
	/**
	 * Constructeur surcharg� qui construit la liste d�roulante d'employ�
	 * @param label : String
	 * @param lesChoix : ArrayList<Employe>
	 */
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
	
	/**
	 * Constructeur surcharg� qui construit la liste des services 
	 * @param label : String
	 * @param data : String[]
	 */
	public ChoixEmploye(String label, String[] data) {
		this.label = new JLabel(label);
		this.choix = new JComboBox<String>();
		for(int i = 0;i<data.length;i++)
			this.choix.addItem(data[i]);
		this.choix.setPreferredSize(new Dimension(150,30));
		this.add(this.label);
		this.add(this.choix);
	}
	
	public JComboBox<String> getComboBox(){
		return this.choix;
	}
	
	/**
	 * M�thode qui retourne l'�l�ment selectionn� par l'utilisateur
	 * @return (String)this.choix.getSelectedItem() : String
	 */
	public String getSelection(){
		return (String)this.choix.getSelectedItem();
	}
	
	/**
	 * M�thode qui met � jour la liste d'employ� 
	 * @param lesChoix : ArrayList<Employe> 
	 */
	public void majList(ArrayList<Employe> lesChoix){
		this.choix.removeAllItems();
		this.choix = this.getComboBox();
		for(Employe unChoix : lesChoix)
			this.choix.addItem(unChoix.getId()+" "+ unChoix.getNom()+" "+ unChoix.getPrenom());
	}

=======
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
	
	
>>>>>>> 84dcd0fe3ec62aedbf2f11410277de653a1dee3f
}