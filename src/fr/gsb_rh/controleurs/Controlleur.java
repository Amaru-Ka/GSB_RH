package fr.gsb_rh.controleurs;
import java.awt.Window;

import fr.gsb_rh.modeles.RequeteBase;
import fr.gsb_rh.vues.Login;

public class Controlleur {
	
	public Controlleur(){
		
	}
	
	public void lancerAppli(){	
		Login login = new Login(this);
		login.setVisible(true);
	}
	
	public boolean verifierMdp(String log, String mdp){
		return RequeteBase.estConnecte(log, mdp);
	}
}
