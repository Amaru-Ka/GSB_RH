import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Classe de manipulation des données que contient la bdd gsb_appli_frais
 * 
 * @author antoine
 * @version 1.0
 * @see DbConnect.java
 * 
 */
public class QueryObject {
	private Statement query = null;
	private ResultSet returnQuery = null; 
	/**
	 * Constructeur par défaut de l'objet QueryObject
	 */
	public QueryObject(){
		
	}
	/**
	 * Méthode de test de connexion 
	 * 
	 * @param login String : Le login d'un RH qui veut se connecter 
	 * @param mdp String : Son mot de passe
	 * @return flag boolean : vrai si ses login/mdp correspondent et qu'il est du bon service, false sinon.
	 */
	public boolean estConnecte(String login , String mdp ){
		boolean flag = false;
		String select = "SELECT id "
				+ "FROM visiteur "
				+ "WHERE login="+login+" "
						+ "AND mdp="+mdp+""
						+ "AND id_service = 3";
		try{
			Connection dbConnect = DbConnect.getDbConnect();
			returnQuery = dbConnect.createStatement().executeQuery(select);
			if(returnQuery != null)
				flag = true;
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * 
	 * @param login String : Le login de l'utilisateur voulant se connecter
	 * @return id String : Son identifiant unique
	 */
	public String getIdConnecte(String login){
		String id = "";
		String select = "SELECT id FROM visiteur WHERE login="+login+"";
		try{
			Connection dbConnect = DbConnect.getDbConnect();
			returnQuery = dbConnect.createStatement().executeQuery(select);
			id = returnQuery.getString(0);
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return id;
	}
	
	/**
	 * Fonction de génération de mot de passe afin de créer un mdp aléatoire à la création de chaque utilisateur.
	 * @see creerUser
	 * @param int : Le nombre de caractères souhaité
	 * @return String : Contient un mot de passe aléatoire sur X caractères allant de a-z A-Z 0-9
	 */
	public String genererString(int x){
		int lower = 0; 
		int higher = 62; 
		String password = "";
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		
		for(int i = 0 ; i < x ; i++){
			int y = (int)(Math.random() * (higher + 1 -lower)) + lower;
			password += chars.charAt(y);
		}
		return password;
	}
	/**
	 * Méthode de création d'utilisateur. 
	 * Il faut renseigner toutes les informations personnelles de l'utilisateur afin qu'il puisse être ajouté en base.
	 * 
	 * @param nom : Le nom du nouvel utilisateur
	 * @param prenom : Son prénom
	 * @param adresse : Son adresse
	 * @param cp : Son code postal
	 * @param ville : Le nom de la ville associé au cp
	 * @param dateEmbauche : La date de son arrivée au sein de l'entreprise
	 * @param idService : Son numéro de Service (1 = Visiteur, 2 = Comptable ,3 = RH)
	 * 
	 */
	public void creerUser(String nom ,String prenom ,String adresse, String cp, String ville,String dateEmbauche, int idService){
		String login = nom.substring(0,1) + prenom;
		String mdp = this.genererString(8);
		String id = this.genererString(3);
		String insert = "INSERT INTO "
				+ "`visiteur`(`id`, `nom`, `prenom`, `login`, `mdp`, `adresse`, `cp`, `ville`, `dateEmbauche`, `id_service`) "
				+ "VALUES ("+id+","+ nom +","+prenom+","+login+","+mdp+","+adresse+","+cp+","+ville+","+dateEmbauche+","+idService+")"; 
		try{
			Connection dbConnect = DbConnect.getDbConnect();
			dbConnect.createStatement().executeUpdate(insert);
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	/**
	 * Méthode de modification d'un utilisateur. 
	 * Il faut renseigner toutes les informations personnelles de l'utilisateur afin qu'elles puissent être modifiés en base.
	 
	 * @param nom String : Le nom du nouvel utilisateur
	 * @param prenom String : Son prénom
	 * @param adresse String : Son adresse
	 * @param cp String : Son code postal
	 * @param ville String : Le nom de la ville associé au cp
	 * @param dateEmbauche String : La date de son arrivée au sein de l'entreprise
	 * @param dateDepart String : La date de son départ de l'entreprise
	 * @param idService int : Son numéro de Service (1 = Visiteur, 2 = Comptable ,3 = RH)
	 */
	public void modifierUser(String nom ,String prenom ,String adresse, String cp, String ville,String dateEmbauche,String dateDepart, int idService){
		String login = nom.substring(0,1) + prenom;
		String update = "UPDATE "
				+ "`visiteur`(`nom`, `prenom`, `login`, `mdp`, `adresse`, `cp`, `ville`, `dateEmbauche`,`dateDepart`, `id_service`) "
				+ "SET(`nom`="+ nom +","
				+ "`prenom`="+prenom+","
				+ "`login`="+login+","
				+ "`adresse`="+adresse+","
				+ "`cp`="+cp+","
				+ "`ville`="+ville+","
				+ "`dateEmbauche`="+dateEmbauche+","
				+ "`DateDepart`="+dateDepart+""
				+ "`id_service`="+idService+")"; 		
		try{
			Connection dbConnect = DbConnect.getDbConnect();
			dbConnect.createStatement().executeUpdate(update);
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	/**
	 * Méthode de récupération de tous les utilisateurs d'un service
	 * @param x : int : Entier correspondant au service souhaité
	 * @return users : Hastable<String,String> :un dictionnaire où est renseigné l'id en clé et son nom en valeur de chaque utilisateur
	 */
	public Hashtable<String,String> getTousLesUsersService(int x){
		Hashtable<String,String> users = new Hashtable<String,String>();
		try{
			String select = "SELECT * "
					+ "FROM visiteur "
					+ "WHERE id_service = "+x+""; 
			query = DbConnect.getDbConnect().createStatement();
			returnQuery = query.executeQuery(select);
			while(returnQuery.next())
				users.put(returnQuery.getString("id"),returnQuery.getString("nom"));	
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
		}
		return users;
	}
	/**
	 * Méthode de récupération de tous les utilisateurs de la base de données gsb_appli_frais
	 * @return users : Hastable<String,String> :un dictionnaire où est renseigné l'id en clé et le nom en valeur pour chaque utilisateur
	 */
	public Hashtable<String,String> getTousLesUsers(){
		Hashtable<String,String> users = new Hashtable<String,String>();
		try{
			String select = "SELECT * FROM visiteur"; 
			query = DbConnect.getDbConnect().createStatement();
			returnQuery = query.executeQuery(select);
			while(returnQuery.next())
				users.put(returnQuery.getString("id"),returnQuery.getString("nom"));	
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
		}
		return users;
	}
}