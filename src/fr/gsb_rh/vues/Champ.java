package fr.gsb_rh.vues;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Champ extends JPanel{
	private JLabel label;
	private JTextField saisie;
	
	public Champ(String label){
		this.label = new JLabel(label);
		this.saisie = new JTextField();
		
		this.saisie.setPreferredSize(new Dimension(150,30));	
		this.add(this.label);
		this.add(this.saisie);		
	}
//////////////////GETTERS & SETTER////////////////////////////
	public String getDansSaisie(){
		return this.saisie.getText();
	}
///////////////////////METHODS////////////////////////////////
	public void effacerSaisie(){
		this.saisie.setText("");
	}
}
