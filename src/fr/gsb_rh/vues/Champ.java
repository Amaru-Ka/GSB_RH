package fr.gsb_rh.vues;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Champ extends JPanel{
	private static final long serialVersionUID = -5498376968363977941L;

	private JLabel label;
	private JTextField saisie;
	private JPasswordField saisieMdp;

	public Champ(String label){
		this.label = new JLabel(label);
		this.saisie = new JTextField();
		this.saisie.setPreferredSize(new Dimension(150,30));	
		this.add(this.label);
		this.add(this.saisie);		
	}
	public Champ(String label,String gg){
		this.label = new JLabel(label);
		this.saisieMdp = new JPasswordField();
		this.saisieMdp.setPreferredSize(new Dimension(150,30));	
		this.add(this.label);
		this.add(this.saisieMdp);		
	}
//////////////////GETTERS & SETTER////////////////////////////
	public String getDansSaisie(){
		return this.saisie.getText();
	}
	@SuppressWarnings("deprecation")
	public String getDansMdp(){
		return this.saisieMdp.getText();
	}
///////////////////////METHODS////////////////////////////////
	public void effacerSaisie(){
		this.saisie.setText("");
	}
	/**
	 * Vérifie la valeur du champs
	 * @return @param flag Vrai si le champs est vide. 
	 */
	public Boolean VerifierChamps(){
		boolean flag;
		if(this.getDansSaisie().toString().equals(""));
			flag=true;
		return flag;
	}


}
