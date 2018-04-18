package fr.gsb_rh.vues;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.gsb_rh.controleurs.Controlleur;

public class Login extends JFrame implements ActionListener{
	protected Controlleur controleur;
	protected Champ saisieLogin;
	protected Champ saisieMdp;
	
	
	public Login(Controlleur controleur){
		super();
		this.controleur = controleur;
		this.initComponents();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Application GSB_RH");
		setSize(300,300);
		setResizable(false);
//		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public void initComponents(){
		JPanel panelLogin = new JPanel();
		JButton boutonValider = new JButton("Valider");
		boutonValider.addActionListener(this);
		
		saisieLogin = new Champ("Login: ");
		saisieMdp = new Champ("Mot de passe :","");
		
		panelLogin.add(saisieLogin);
		panelLogin.add(saisieMdp);
		panelLogin.add(boutonValider);
		
		this.add(panelLogin);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(this.controleur.verifierMdp(this.saisieLogin.getDansSaisie(),this.saisieMdp.getDansMdp())){
			//Lancement de la fenetre principale
			this.controleur.lancerAppli();
			//Fermeture de la fenêtre de login
			this.dispose();
		}
		else JOptionPane.showMessageDialog(null,this.saisieMdp.getDansMdp(),"Valider",JOptionPane.ERROR_MESSAGE);
		
		
	}
}
