import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class QueryObject {
	private Statement query = null;
	private ResultSet returnQuery = null; 
	
	public QueryObject(){
		
	}
	
	public String generatePassword(){
		String password = "";
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		for(int i=0;i>chars.length();i++){
			int x = (int)Math.floor(Math.random() * 8);
			password += chars.charAt(x);
			System.out.println(password);
		}
		System.out.println(password);
		return password;
	}
	
	public void creerUser(String identifiant,String nom ,String prenom ,String adresse, String cp, String ville,String dateEmbauche, int idService){
		String login = nom.substring(0,1) + prenom;
		String insert = "INSERT INTO "
				+ "`utilisateurs`(`id`, `nom`, `prenom`, `login`, `mdp`, `adresse`, `cp`, `ville`, `dateEmbauche`, `id_service`) "
				+ "VALUES ("+identifiant+","+ nom +","+prenom+","+login+",'1MOT2PASSE',"+adresse+","+cp+","+ville+","+dateEmbauche+","+idService+")"; 
		try{
			Connection dbConnect = DbConnect.getDbConnect();
			dbConnect.createStatement().executeUpdate(insert);
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void modifierUser(String id,String nom ,String prenom ,String adresse, String cp, String ville,String dateEmbauche,String dateDepart, int idService){
		String login = nom.substring(0,1) + prenom;
		String update = "UPDATE "
				+ "`utilisateurs`(`id`, `nom`, `prenom`, `login`, `mdp`, `adresse`, `cp`, `ville`, `dateEmbauche`,`dateDepart`, `id_service`) "
				+ "SET(`id`="+id+","
				+ "`nom`="+ nom +","
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
	
	public ArrayList<String> getTousLesUsersService(int x){
		ArrayList<String> users = new ArrayList<String>();
		try{
			String select = "SELECT * "
					+ "FROM utilisateurs "
					+ "WHERE id_service = "+x+""; 
			query = DbConnect.getDbConnect().createStatement();
			returnQuery = query.executeQuery(select);
			while(returnQuery.next())
				users.add(returnQuery.getString("id"));	
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
		}
		return users;
	}
}