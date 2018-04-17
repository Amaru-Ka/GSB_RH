package fr.gsb_rh.controleurs;

import fr.gsb_rh.modeles.QueryObject;

public class Controleur {
	
	public Controleur(){
		
	}
	
	public void lancerAppli(){
		System.out.println(QueryObject.getTousLesUsersService(1));	
	}
	
	public boolean verifierMdp(String log, String mdp){
		return QueryObject.estConnecte(log, mdp);
	}
}
