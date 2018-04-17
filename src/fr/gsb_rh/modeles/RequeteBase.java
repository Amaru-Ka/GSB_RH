package fr.gsb_rh.modeles;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;


/**
 * Classe de manipulation des données que contient la bdd gsb_appli_frais
 * 
 * @author antoine & audrey
 * @version 1.0
 * @see DbConnect.java
 * 
 */
public class RequeteBase {
	private static Statement query = null;
	private static ResultSet returnQuery = null; 
	
	/**
	 * Constructeur par défaut de l'objet QueryObject
	 */
	public RequeteBase(){
		
	}
	
	/**
	 * Méthode de test de connexion 
	 * 
	 * @param login String : Le login d'un RH qui veut se connecter 
	 * @param mdp String : Son mot de passe
	 * @return flag boolean : vrai si ses login/mdp correspondent et qu'il est du bon service, false sinon.
	 */
	public static boolean estConnecte(String login , String mdp ){
		boolean flag = false;
		String select = "SELECT login, mdp "
				+ "FROM visiteur "
				+ "WHERE login='"+login+"'"
				+ "AND mdp='"+mdp+"'";
		try{
			Connection dbConnect = DbConnect.getDbConnect();
			returnQuery = dbConnect.createStatement().executeQuery(select);
			if(returnQuery.first())
				flag = true;	
		}
		catch (SQLException e){
			e.printStackTrace();
		}finally{
				try{
					returnQuery.close();
					query.close();
				}catch (SQLException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					DbConnect.destroyDbConnect();
					returnQuery = null;
					query = null;
				}
			}	
		return flag;	
		}
	/**
	 * Methode retournant l'id d'un utilisateur par rapport à son login
	 * 
	 * @param login String : Le login de l'utilisateur voulant se connecter
	 * @return id String : Son identifiant unique
	 */
	public static Employe getIdConnecte(String login){
		String select = "SELECT id,nom,prenom,id_service FROM visiteur WHERE login="+login+"";
		Employe unEmploye = null;
		try{
			Connection dbConnect = DbConnect.getDbConnect();
			returnQuery = dbConnect.createStatement().executeQuery(select);
			unEmploye = new Employe(returnQuery.getString("id"),returnQuery.getString("nom"),returnQuery.getString("prenom"),returnQuery.getInt("id_service"));
		}
		catch (SQLException e){
			e.printStackTrace();
		}finally{
			try{
				returnQuery.close();
				query.close();
			}catch (SQLException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DbConnect.destroyDbConnect();
				returnQuery = null;
				query = null;
			}
			
		}
		return unEmploye;
	}
	
	/**
	 * Méthode de création d'utilisateur. 
	 * Il faut renseigner toutes les informations personnelles de l'utilisateur afin qu'il puisse être ajouté en base.
	 * 
	 * @param unEmploye
	 */
	public static void creerUser(Employe unEmploye){
		String insert = "INSERT INTO "
				+ "`visiteur`(`id`, `nom`, `prenom`, `login`, `mdp`, `adresse`, `cp`, `ville`, `dateEmbauche`, `id_service`) "
				+ "VALUES ("+ unEmploye.getId() +","
						+ ""+ unEmploye.getNom() +","
						+ ""+ unEmploye.getPrenom() +","
						+ ""+ unEmploye.getlogin() +","
						+ ""+ unEmploye.getMdp() +","
						+ ""+ unEmploye.getAdresse() +","
						+ ""+ unEmploye.getCP() +","
						+ ""+ unEmploye.getVille() +","
						+ ""+ unEmploye.getDateEmbauche() +","
						+ ""+ unEmploye.getIdService() +")"; 
		try{
			Connection dbConnect = DbConnect.getDbConnect();
			dbConnect.createStatement().executeUpdate(insert);
		}
		catch (SQLException e){
			e.printStackTrace();
		}finally{
			try{
				returnQuery.close();
				query.close();
			}catch (SQLException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DbConnect.destroyDbConnect();
				returnQuery = null;
				query = null;
			}
			
		}
	}
	
	/**
	 * Méthode de modification d'un utilisateur. 
	 * Il faut renseigner toutes les informations personnelles de l'utilisateur afin qu'elles puissent être modifiés en base.
	 * 
	 * @param unEmploye
	 */
	public static void modifierEmploye(Employe unEmploye){
		String update = "UPDATE "
				+ "`visiteur`(`nom`, `prenom`, `login`, `adresse`, `cp`, `ville`, `dateEmbauche`,`id_service`) "
				+ "SET(`nom`="+ unEmploye.getNom() +","
				+ "`prenom`="+ unEmploye.getPrenom() +","
				+ "`login`="+ unEmploye.getlogin() +","
				+ "`adresse`="+ unEmploye.getAdresse() +","
				+ "`cp`="+ unEmploye.getCP() +","
				+ "`ville`="+ unEmploye.getVille() +","
				+ "`dateEmbauche`="+ unEmploye.getDateEmbauche() +","
				+ "`id_service`="+ unEmploye.getIdService() +")"; 		
		try{
			Connection dbConnect = DbConnect.getDbConnect();
			dbConnect.createStatement().executeUpdate(update);
		}
		catch (SQLException e){
			e.printStackTrace();
		}finally{
			try{
				returnQuery.close();
				query.close();
			}catch (SQLException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DbConnect.destroyDbConnect();
				returnQuery = null;
				query = null;
			}
			
		}
	}
	
	/**
	 * Methode qui renvoie tous les utilisateurs qui ne sont plus dans l'entreprise.
	 * @return lesEmployes ArrayList<Employe>: Contient les objets Employe 
	 */
	public static ArrayList<Employe> getLesUsersParti(){
		ArrayList<Employe> lesEmployes = new ArrayList<Employe>();
		try{
			String select = "SELECT * FROM visiteur WHERE dateDepart IS NOT NULL"; 
			query = DbConnect.getDbConnect().createStatement();
			returnQuery = query.executeQuery(select);
			while(returnQuery.next()){
				Employe unEmploye = new Employe(returnQuery.getString("nom"),
												returnQuery.getString("prenom"),
												returnQuery.getString("adresse"),
												returnQuery.getString("cp"),
												returnQuery.getString("ville"),
												returnQuery.getInt("DateEmbauche"));
				lesEmployes.add(unEmploye);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				returnQuery.close();
				query.close();
			}catch (SQLException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DbConnect.destroyDbConnect();
				returnQuery = null;
				query = null;
			}
			
		}
		return lesEmployes;
	}
	
	/**
	 * Méthode permettant de récupérer l'ensemeble des id et nom des services
	 * 
	 * @return lesServices Hashtable<Integer,String> : Dictionnaire contenant les services
	 */
	public static Hashtable<Integer,String> getLesServices(){
		Hashtable<Integer,String> lesServices = new Hashtable<Integer,String>();
		String select = "SELECT * FROM service";
		try{
			Connection dbConnect = DbConnect.getDbConnect();
			returnQuery = dbConnect.createStatement().executeQuery(select);
			while(returnQuery.next()){
				lesServices.put(returnQuery.getInt("id_service"), returnQuery.getString("libelle_service"));
			}
		}catch (SQLException e){
			e.printStackTrace();
		}finally{
			try{
				returnQuery.close();
				query.close();
			}catch (SQLException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DbConnect.destroyDbConnect();
				returnQuery = null;
				query = null;
			}	
		}
		return lesServices;
	}
	
	/**
	 * Méthode de récupération de tous les utilisateurs de la base de données gsb_appli_frais
	 * 
	 * @return users : Hastable<String,String> :un dictionnaire où est renseigné l'id en clé et le nom en valeur pour chaque utilisateur
	 */
	public static ArrayList<Employe> getTousLesUsers(){
		ArrayList<Employe> lesEmployes = new ArrayList<Employe>();
		Employe unEmploye = null;
		try{
			String select = "SELECT * FROM visiteur"; 
			query = DbConnect.getDbConnect().createStatement();
			returnQuery = query.executeQuery(select);
			while(returnQuery.next()){
				unEmploye = new Employe(returnQuery.getString("id"),returnQuery.getString("nom"),returnQuery.getString("prenom"),returnQuery.getInt("id_service"));
				lesEmployes.add(unEmploye);
			}				
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				returnQuery.close();
				query.close();
			}catch (SQLException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DbConnect.destroyDbConnect();
				returnQuery = null;
				query = null;
			}
			
		}
		return lesEmployes;
	}
	
	/**
	 * Méthode de récupération de tous les utilisateurs encore en fonction 
	 * @return users : Hastable<String,String> :un dictionnaire où est renseigné l'id en clé et le nom en valeur pour chaque utilisateur
	 */
	public static ArrayList<Employe> getTousLesUsersEnFonction(){
		ArrayList<Employe>  lesEmployes = new ArrayList<Employe>();
		try{
			String select = "SELECT * FROM visiteur WHERE dateDepart IS NULL"; 
			query = DbConnect.getDbConnect().createStatement();
			returnQuery = query.executeQuery(select);
			while(returnQuery.next()){
				Employe unEmploye = new Employe(returnQuery.getString("nom"),
												returnQuery.getString("prenom"),
												returnQuery.getString("adresse"),
												returnQuery.getString("cp"),
												returnQuery.getString("ville"),
												returnQuery.getInt("DateEmbauche"));
				lesEmployes.add(unEmploye);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				returnQuery.close();
				query.close();
			}catch (SQLException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DbConnect.destroyDbConnect();
				returnQuery = null;
				query = null;
			}
			
		}
		return lesEmployes;
	}
	
	/**
	 * Méthode de récupération de tous les utilisateurs d'un service
	 * @param x : int : Entier correspondant au service souhaité
	 * @return users : Hastable<String,String> :un dictionnaire où est renseigné l'id en clé et son nom en valeur de chaque utilisateur
	 */
	public static ArrayList<Employe> getTousLesUsers(int x){
		ArrayList<Employe> lesEmployes = new ArrayList<Employe>();
		try{
			String select = "SELECT * FROM visiteur WHERE id_service = "+x+""; 
			query = DbConnect.getDbConnect().createStatement();
			returnQuery = query.executeQuery(select);
			while(returnQuery.next()){
				Employe unEmploye = new Employe(returnQuery.getString("nom"),
												returnQuery.getString("prenom"),
												returnQuery.getString("adresse"),
												returnQuery.getString("cp"),
												returnQuery.getString("ville"),
												returnQuery.getInt("DateEmbauche"));
				lesEmployes.add(unEmploye);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				returnQuery.close();
				query.close();
			}catch (SQLException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DbConnect.destroyDbConnect();
				returnQuery = null;
				query = null;
			}
		}
		return lesEmployes;
	}

}