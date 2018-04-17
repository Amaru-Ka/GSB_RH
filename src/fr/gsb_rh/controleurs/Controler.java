package fr.gsb_rh.controleurs;
import java.awt.Window;

import fr.gsb_rh.modeles.QueryObject;
import fr.gsb_rh.vues.Login;

public class Controler {
	
	public Controler(){
		
	}
	
	public void lancerAppli(){	
		Login login = new Login(this);
		login.setVisible(true);
	}
	
	public boolean verifierMdp(String log, String mdp){
		return QueryObject.estConnecte(log, mdp);
	}
}
