package fr.gsb_rh.modeles;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Classe de connexion � la base de donn�es de GSB.
 * @author antoine
 * @version 1.0
 * @see QueryObject
 * */
public class DbConnect{
	private static Connection connexion;
	private String url ="jdbc:mysql://localhost/gsb_appli_frais";
	private String login = "GSB";
	private String password = "GSB";
	/**
	 * Constructeur priv� de l'objet DBConnect.
	 * priv� car on utilise un singleton pour y acc�der. 
	 **/ 
	private DbConnect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connexion = DriverManager.getConnection(this.url,this.login, this.password);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	/**
	 * M�thode d'acc�s au singleton
	 * */
	public static Connection getDbConnect(){
		if(connexion == null)
			new DbConnect();
		return connexion;
	}
	/**
	 * M�thode de destruction de la connexion � la base 
	 * */
	public static void destroyDbConnect(){
		try {
			connexion.close();
		}
		catch(SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			connexion = null;
		}
	}
}