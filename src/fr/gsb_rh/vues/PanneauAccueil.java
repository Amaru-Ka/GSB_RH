package fr.gsb_rh.vues;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import fr.gsb_rh.controleurs.Controlleur;
public class panneauAccueil {
	private Controlleur controlleur;
	public panneauAccueil(Controlleur controlleur){
		this.controlleur = controlleur;
		
	}
	public void initComponents(){
		this.controlleur.getLeRhConnecte().getId();
		JLabel nomRh = new JLabel();
		JLabel prenomRh = new JLabel();
		JLabel idRh = new JLabel();
		JPanel panelInfo = new JPanel();
		SpringLayout springLayout = new SpringLayout();
		panelInfo.setLayout(springLayout);
		
		
		panelInfo.add(nomRh);
		panelInfo.add(prenomRh);
		panelInfo.add(idRh);
	}
}
