import java.sql.Connection;

public class GSB_RH {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection connexion = DbConnect.getDbConnect();
		QueryObject querys = new QueryObject();
		System.out.println(querys.getTousLesUsersService(1));	
	}
	
}
