package fr.gsb_rh.modeles;

/**
 * 
 * @author 
 *
 */
public class Employe {
	private String id; 
	private String nom; 
	private String prenom; 
	private int DateEmbauche; 
	private String adresse; 
	private String CP; 
	private String Ville; 
	private String Telephone; 
	private String Mail; 
	private String login; 
	private String mdp; 
	private int idService;
	/**
	 * Constructeur par défaut d'Employe
	 */
	public Employe(){
		
	}
	/**
	 * Constructeur surchargé d'Employe 
	 * @param idUser
	 * @param nomUser
	 * @param prenomUser
	 * @param idServiceUser
	 */
	public Employe(String idUser, String nomUser, String prenomUser, int idServiceUser){
		this.setId(idUser);
		this.setNom(nomUser);
		this.setPrenom(prenomUser);
		this.setIdService(idServiceUser);
	}
	
	/**
	 * Constructeur surchargé d'Employe
	 * @param id
	 * @param nom
	 * @param prenom
	 * @param login
	 * @param mdp
	 * @param adresse
	 * @param CP
	 * @param ville
	 * @param dateEmbauche
	 * @param telephone
	 * @param email
	 */
	public Employe(String idUser, String nomUser, String prenomUser, String adresseUser, String CPUser, String villeUser, int dateEmbaucheUser){
		this.setId(idUser);
		this.setNom(nomUser);
		this.setPrenom(prenomUser);
		this.setAdresse(adresseUser);
		this.setCP(CPUser);
		this.setVille(villeUser);
		this.setDateEmbauche(dateEmbaucheUser);
	}
	/**
	 * GETTERS ET SETTERS D'EMPLOYE
	 */
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getlogin() {
		return login;
	}	
	public String getMdp() {
		return mdp; 
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getDateEmbauche() {
		return DateEmbauche;
	}
	public void setDateEmbauche(int dateEmbaucheUser) {
		DateEmbauche = dateEmbaucheUser;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getCP() {
		return CP;
	}
	public void setCP(String cP) {
		CP = cP;
	}
	public String getVille() {
		return Ville;
	}
	public void setVille(String ville) {
		Ville = ville;
	}
	public String getTelephone() {
		return Telephone;
	}
	public void setTelephone(String telephone) {
		Telephone = telephone;
	}
	public String getMail() {
		return Mail;
	}
	public void setMail(String mail) {
		Mail = mail;
	}
	public int getIdService() {
		return idService;
	}
	public void setIdService(int idService) {
		this.idService = idService;
	}

	
	/**
	 * Fonction de génération de mot de passe afin de créer un mdp aléatoire à la création de chaque utilisateur.
	 * @see creerUser
	 * @param int : Le nombre de caractères souhaité
	 * @return String : Contient un mot de passe aléatoire sur X caractères allant de a-z A-Z 0-9
	 */
	public static String genererString(int x){
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

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
		
	
}
