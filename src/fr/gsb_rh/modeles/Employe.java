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
	private String dateNaissance;
	private String DateEmbauche;
	private String dateDepart;
	private String adresse; 
	private String CP; 
	private String Ville; 
	private String Telephone; 
	private String Mail; 
	private String login; 
	private String mdp; 
	
	private int idService;
	/**
	 * Constructeur par d�faut d'Employe
	 */
	public Employe(){
		
	}
	/**
	 * Constructeur surcharg� d'Employe == Connaitre l'Employe qui proc�de � des modifications / R�cup�rer une liste d'employ�s
	 * 
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
	 * Constructeur surcharg� d'Employe == Cr�ation d'un Employe dans la base

	 * @param nom
	 * @param prenom
	 * @param dateNaissance
	 * @param adresse
	 * @param CP
	 * @param ville
	 * @param dateEmbauche
	 */
	
	public Employe(String nomUser, String prenomUser,String dateNaissance, String adresseUser, String CPUser, String villeUser, String telephone,String mail, String dateEmbaucheUser,int idService){
		this.setNom(nomUser);
		this.setPrenom(prenomUser);
		this.setAdresse(adresseUser);
		this.setCP(CPUser);
		this.setVille(villeUser);
		this.setMail(mail);
		this.setTelephone(telephone);
		this.setDateEmbauche(dateEmbaucheUser);
		this.setDateNaissance(dateNaissance);
		this.setLogin();
		this.setIdService(idService);
		
		//Setter qui g�n�rent al�atoirement un id ou un mot de passe 
		this.setMdp();
		this.setId();
	}
	/**
	 * GETTERS ET SETTERS D'EMPLOYE
	 */
	public String getId(){
		return id;
	}
	public void setId(){
		this.id = this.genererString(3);
	}
	//setter surcharg� lors de la r�cup�ration des employ�s en base
	public void setId(String id){
		this.id = id;
	}
	
	public String getDateNaissance(){
		return this.dateNaissance;
	}
	public void setDateNaissance(String date){
		this.dateNaissance = date;
	}
	
	public String getDateDepart(){
		return this.dateDepart;
	}
	public void setDateDepart(String date){
		this.dateDepart = date;
	}
	
	public String getlogin() {
		return login;
	}
	public void setLogin(){
		this.login = this.prenom.substring(0,1) + " " + this.nom;
	}
	
	public String getMdp() {
		return mdp; 
	}
	public void setMdp(){
		this.mdp = this.genererString(8);
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

	public String getDateEmbauche() {
		return DateEmbauche;
	}
	public void setDateEmbauche(String dateEmbaucheUser) {
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
	 * Fonction de g�n�ration de mot de passe afin de cr�er un mdp ou un identifiant al�atoire � la cr�ation de chaque utilisateur.
	 * 
	 * @param int : Le nombre de caract�res souhait�
	 * @return String : Contient un mot de passe al�atoire sur X caract�res allant de a-z A-Z 0-9
	 */
	public String genererString(int x){
		int lower = 0; 
		int higher = 62; 
		String password = "";
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		
		for(int i = 0 ; i < x ; i++){
			int y = (int)(Math.random() * (higher + 1 - lower)) + lower;
			password += chars.charAt(y);
		}
		return password;
	}
}
