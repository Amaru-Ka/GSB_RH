package fr.gsb_rh.modeles;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Classe de manipulation des donn�es que contient la bdd gsb_appli_frais

 * @author antoine & audrey
 * @version 1.0
 * @see DbConnect.java
 */
public class RequeteBase {
	private static Statement query;
	private static ResultSet returnQuery; 
	
	/**
	* Constructeur par d�faut de l'objet QueryObject
	*/
	public RequeteBase(){}
		
	/**
	 * M�thode de test du mot de passe et du login d'un utilisateur.
	 * Le bool�en passe � vrai si les logs concordent.
	 * 
	 * @param log : String
	 * @param mdp : String
	 * @return estConnecte : Boolean
	 */
	public static boolean estConnecte(String login , String mdp ){
		boolean estConnecte = false;
		returnQuery = null; 
		String select = "SELECT id FROM visiteur WHERE login='"+login+"'AND mdp='"+mdp+"'";
		try{
			Connection dbConnect = DbConnect.getDbConnect();
			returnQuery = dbConnect.createStatement().executeQuery(select);
			if(returnQuery.first())
				estConnecte = true;			
		}
		catch (SQLException e){
			e.printStackTrace();
		}finally{
				try{
					returnQuery.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					DbConnect.destroyDbConnect();
					returnQuery = null;
				}
			}	
		return estConnecte;	
	}
	
	/**
	 * M�thode de test des droits d'un utilisateur.
	 * Le bool�en passe � vrai si l'utilisateur est un RH (id_service = 3).
	 * 
	 * @param log : String
	 * @param mdp : String
	 * @return aLesDroits : Boolean
	 */
	public static boolean verifDroits(String login , String mdp){
		boolean aLesDroits = false;
		returnQuery = null; 
		String select = "SELECT id FROM visiteur WHERE login='"+login+"'AND mdp='"+mdp+"' AND id_service = 3";
		try{
			Connection dbConnect = DbConnect.getDbConnect();
			returnQuery = dbConnect.createStatement().executeQuery(select);
			if(returnQuery.first())
				aLesDroits = true;
		}
		catch (SQLException e){
			e.printStackTrace();
		}finally{
				try{
					returnQuery.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					DbConnect.destroyDbConnect();
					returnQuery = null;
				}
			}	
		return aLesDroits;	
	}
		
	
	/**
	 * Methode qui retourne un objet Employe par rapport � son login
	 * 
	 * @param login : String
	 * @return id : String 
	 */
	public static Employe getEmployeConnecte(String login){
		String select = "SELECT id,nom,prenom,id_service FROM visiteur WHERE login='"+login+"'";
		Employe unEmploye = null;
		try{
			Connection dbConnect = DbConnect.getDbConnect();
			returnQuery = dbConnect.createStatement().executeQuery(select);
			returnQuery.first();
			unEmploye = new Employe(returnQuery.getString("id"),
									returnQuery.getString("nom"),
									returnQuery.getString("prenom"),
									returnQuery.getInt("id_service"));
		}
		catch (SQLException e){
			e.printStackTrace();
		}finally{
			try{
				returnQuery.close();
			}catch (SQLException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DbConnect.destroyDbConnect();
				returnQuery = null;
			}		
		}
		return unEmploye;
	}
	
	/**
	 * M�thode de cr�ation d'un utilisateur. 
	 * Il faut renseigner toutes les informations personnelles de l'utilisateur afin qu'il puisse �tre ajout� en base.
	 * @param unEmploye : Employe
	 */
	public static void AjoutEmploye(Employe unEmploye){
		String insert = "INSERT INTO "
				+ "`visiteur`(`id`, `nom`, `prenom`, `login`, `mdp`, `adresse`, `cp`, `ville`, `dateEmbauche`, `id_service`, `telephone`,`email`) "
				+"VALUES ('"+ unEmploye.getId() +"',"
					   + "'"+ unEmploye.getNom() +"',"
					   + "'"+ unEmploye.getPrenom() +"',"
					   + "'"+ unEmploye.getlogin() +"',"
					   + "'"+ unEmploye.getMdp() +"',"
					   + "'"+ unEmploye.getAdresse() +"',"
					   + "'"+ unEmploye.getCP() +"',"
					   + "'"+ unEmploye.getVille() +"',"
					   + "'"+ unEmploye.getDateEmbauche() +"',"
					   + "'"+ unEmploye.getIdService() +"',"
					   + "'"+ unEmploye.getTelephone() +"',"
					   + "'"+ unEmploye.getMail() +"')"; 
		try{
			Connection dbConnect = DbConnect.getDbConnect();
			dbConnect.createStatement().executeUpdate(insert);
			DbConnect.destroyDbConnect();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * M�thode qui retourne un objet Employe complet gr�ce au nom et pr�nom d'un employ� selectionn� 
	 * @param nom : String
	 * @param prenom : String
	 * @return unEmploye : Employe
	 */
	public static Employe unEmploye (String nom,String prenom)
	{
		Employe UnEmploye = null;
		try{
			String select = "SELECT * FROM visiteur WHERE nom='"+nom+"' AND prenom='"+prenom+"'"; 
			query = DbConnect.getDbConnect().createStatement();
			returnQuery = query.executeQuery(select);
			returnQuery.first();
			UnEmploye = new Employe(returnQuery.getString("id"),
						returnQuery.getString("nom"),
						returnQuery.getString("prenom"),
						returnQuery.getString("login"),
						returnQuery.getString("Mdp"),
						returnQuery.getString("Adresse"),
						returnQuery.getString("CP"),
						returnQuery.getString("Ville"),
						returnQuery.getString("DateEmbauche"),
						returnQuery.getString("Telephone"),
						returnQuery.getString("email"),
						returnQuery.getInt("id_service"));
			
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
		return UnEmploye;	
	}
		
	/**
	 * M�thode de modification d'un utilisateur. 
	 * Il faut renseigner toutes les informations personnelles de l'utilisateur afin qu'elles puissent �tre modifi�s en base.
	 * 
	 * @param unEmploye : Employe
	 */
	public static void modifierEmploye(Employe unEmploye){
		String update = "UPDATE "
				+ "`visiteur`"
				+ "SET `id`='"+ unEmploye.getId() +"',"
				+ "`nom`='"+ unEmploye.getNom() +"',"
				+ "`prenom`='"+ unEmploye.getPrenom() +"',"
				+ "`login`='"+ unEmploye.getlogin() +"',"
				+ "`mdp`='"+ unEmploye.getMdp() +"',"
				+ "`adresse`='"+ unEmploye.getAdresse() +"',"
				+ "`cp`='"+ unEmploye.getCP() +"',"
				+ "`ville`='"+ unEmploye.getVille() +"',"
				+ "`dateEmbauche`='"+ unEmploye.getDateEmbauche() +"',"
				+ "`dateDepart`= NULL,"
				+ "`id_service`='"+ unEmploye.getIdService() +"',"
				+ "`telephone`='"+ unEmploye.getTelephone() +"',"
				+ "`email`='"+ unEmploye.getMail() +"'"
				+ "Where id='"+ unEmploye.getId() +"'";		
		try{
			Connection dbConnect = DbConnect.getDbConnect();
			dbConnect.createStatement().executeUpdate(update);
		}
		catch (SQLException e){
			JOptionPane.showMessageDialog(null,"Veuillez v�rifier l'ensemble des champs saisie puis r�essayez.\n Si le probl�me persiste veuillez contacter votre administrateur syst�mes","Valider",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}finally{
				DbConnect.destroyDbConnect();
				returnQuery = null;
				query = null;
			}
	}
	
	/**
	 * Methode qui retourne tous les utilisateurs qui ne sont plus dans l'entreprise (dateDepart < aujourd'hui).
	 * 
	 * @return lesEmployes : ArrayList<Employe>
	 */
	public static ArrayList<Employe> getLesUsersParti(){
		ArrayList<Employe> lesEmployes = new ArrayList<Employe>();
		try{
			String select = "SELECT * FROM visiteur WHERE dateDepart IS NOT NULL"; 
			query = DbConnect.getDbConnect().createStatement();
			returnQuery = query.executeQuery(select);
			while(returnQuery.next()){
				Employe unEmploye = new Employe(returnQuery.getString("id"),
						returnQuery.getString("nom"),
						returnQuery.getString("prenom"),
						returnQuery.getInt("id_service"));
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
	 * M�thode permettant de r�cup�rer les libell�s des services
	 * 
	 * @return lesServices : String[]
	 */
	public static String[] getLesServices(){
		int i = 0;
		int row = 0;
		String[] lesServices = null;
		String select = "SELECT * FROM service";
		try{
			Connection dbConnect = DbConnect.getDbConnect();
			returnQuery = dbConnect.createStatement().executeQuery(select);
			//Place le pointeur � la fin du tableau
			returnQuery.last();
			//on r�cup�re l'indice du dernier element du tableau
			row = returnQuery.getRow();
			//On reviens � l'indice 0
			returnQuery.beforeFirst();
			//On initialise le tableau avec le nombre d'elements
			lesServices = new String[row]; 
			while(returnQuery.next()){
				lesServices[i] = returnQuery.getString("libelle_service");
				i++;
			}
		}catch (SQLException e){
			e.printStackTrace();
		}finally{
			try{
				returnQuery.close();
			}catch (SQLException e){
				e.printStackTrace();
			}finally{
				DbConnect.destroyDbConnect();
				returnQuery = null;
			}	
		}
		return lesServices;
	}
	
	/**
	 * M�thode de r�cup�ration de tous les utilisateurs de la base de donn�es gsb_appli_frais
	 * 
	 * @return users : ArrayList<Employe> 
	 */
	public static ArrayList<Employe> getTousLesUsers(){
		ArrayList<Employe> lesEmployes = new ArrayList<Employe>();
		Employe unEmploye = null;
		try{
			String select = "SELECT id, nom, prenom, libelle_service FROM visiteur NATURAL JOIN service"; 
			query = DbConnect.getDbConnect().createStatement();
			returnQuery = query.executeQuery(select);
			while(returnQuery.next()){
				unEmploye = new Employe(returnQuery.getString("id"),returnQuery.getString("nom"),returnQuery.getString("prenom"),returnQuery.getString("libelle_service"));
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
	 * M�thode de r�cup�ration de tous les utilisateurs
	 * @return lesEmploy�s  : ArrayList<Employe>
	 */
	public static ArrayList<String> listerEmployes(){
		String sql= "SELECT id, nom, prenom FROM visiteur";
		ArrayList<String> lesEmployes = new ArrayList<String>();
		Statement requete = null;
		ResultSet lecture = null;
		try {
			requete = DbConnect.getDbConnect().createStatement();
			lecture = requete.executeQuery(sql);
			if(lecture != null){
				while(lecture.next())
					lesEmployes.add(lecture.getString("id") + " " + lecture.getString("nom") + " " + lecture.getString("prenom"));
			}
			
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "impossible de lister les employ�s");
		}finally{
			DbConnect.destroyDbConnect();
		}
		
		return lesEmployes;
	}
	
	/**
	 * M�thode de r�cup�ration de tous les utilisateurs encore en fonction 
	 * @return users : Hastable<String,String> :un dictionnaire o� est renseign� l'id en cl� et le nom en valeur pour chaque utilisateur
	 */
	public static ArrayList<Employe> getTousLesUsersEnFonction(){
		ArrayList<Employe>  lesEmployes = new ArrayList<Employe>();
		try{
			String select = "SELECT * FROM visiteur WHERE dateDepart IS NULL"; 
			query = DbConnect.getDbConnect().createStatement();
			returnQuery = query.executeQuery(select);
			while(returnQuery.next()){
				Employe unEmploye = new Employe(returnQuery.getString("id"),
												returnQuery.getString("nom"),
												returnQuery.getString("prenom"),
												returnQuery.getInt("id_service"));
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
	 * M�thode de r�cup�ration de tous les utilisateurs d'un service
	 * @param x : int 
	 * @return users : ArrayList<Employe>
	 */
	public static ArrayList<Employe> getTousLesUsers(int x){
		ArrayList<Employe> lesEmployes = new ArrayList<Employe>();
		try{
			String select = "SELECT * FROM visiteur WHERE id_service = "+x+""; 
			query = DbConnect.getDbConnect().createStatement();
			returnQuery = query.executeQuery(select);
			while(returnQuery.next()){
				Employe unEmploye = new Employe(returnQuery.getString("id"),
						returnQuery.getString("nom"),
						returnQuery.getString("prenom"),
						returnQuery.getInt("id_service"));
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