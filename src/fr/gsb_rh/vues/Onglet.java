package fr.gsb_rh.vues;

import java.awt.Component;
import java.awt.Dimension;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

public class Onglet extends JTabbedPane{
	public Onglet(){
		super();	    
	}
	public void ajouterOnglet(String nomOnglet){
		JPanel onglet = new JPanel();
		JLabel titre = new JLabel(nomOnglet);
		onglet.add(titre);
		onglet.setPreferredSize(new Dimension(900,650));
		this.addTab(nomOnglet, onglet);
	}

	
}
