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
	private String libelleService;
	
	public String getLibelleService() {
		return libelleService;
	}
	
	public void setLibelleService(String libelleService) {
		this.libelleService = libelleService;
	}

	private int idService;
	/**
	 * Constructeur par défaut d'Employe
	 */
	public Employe(){
		
	}
	
	public Employe(String idUser, String nomUser, String prenomUser, String nomService){
		this.setId(idUser);
		this.setNom(nomUser);
		this.setPrenom(prenomUser);
		this.setLibelleService(nomService);
	}
	
	/**
	 * Constructeur surchargé d'Employe == Connaitre l'Employe qui procède à des modifications / Récupérer une liste d'employés
	 * 
	 * @param idUser
	 * @param nomUser
	 * @param prenomUser
	 * @param idServiceUser
	 * 
	 */
	public Employe(String idUser, String nomUser, String prenomUser, int idServiceUser){
		this.setId(idUser);
		this.setNom(nomUser);
		this.setPrenom(prenomUser);
		this.setIdService(idServiceUser);
	}
	
	/**
	 * Constructeur surchargé d'Employe == Création d'un Employe dans la base

	 * @param nom
	 * @param prenom
	 * @param dateNaissance
	 * @param adresse
	 * @param CP
	 * @param ville
	 * @param dateEmbauche
	 */
	
	public Employe(String nomUser, String prenomUser, String adresseUser, String CPUser, String villeUser, String telephone,String mail, String dateEmbaucheUser,int idService){
		this.setNom(nomUser);
		this.setPrenom(prenomUser);
		this.setAdresse(adresseUser);
		this.setCP(CPUser);
		this.setVille(villeUser);
		this.setMail(mail);
		this.setTelephone(telephone);
		this.setDateEmbauche(dateEmbaucheUser);
		this.setLogin();
		this.setIdService(idService);
		
		//Setter qui génèrent aléatoirement un id ou un mot de passe 
		this.setMdp();
		this.setId();
	}
	
	
	public Employe(String Id, String nomUser,String prenomUser, String Login, String Mdp, String adresseUser, String CPUser, String villeUser,String dateEmbaucheUser, String telephone,String mail, int idService){
		
		this.setId(Id);
		this.setNom(nomUser);
		this.setPrenom(prenomUser);
		this.setAdresse(adresseUser);
		this.setCP(CPUser);
		this.setVille(villeUser);
		this.setMail(mail);
		this.setTelephone(telephone);
		this.setDateEmbauche(dateEmbaucheUser);
		this.setLogin(Login);
		this.setMdp(Mdp);
		this.setIdService(idService);	
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
	
	//setter surchargé lors de la récupération des employés en base
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
	
	public void setMdp(String mdp){
		this.mdp = mdp;
	}
	
	public void setLogin(String login){
		this.login=login;
	}
	
	public String getlogin() {
		return login;
	}
	
	public void setLogin(){
		this.login = this.prenom.substring(0,1) + "" + this.nom;
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
	 * Fonction de génération de mot de passe afin de créer un mdp ou un identifiant aléatoire à la création de chaque utilisateur.
	 * 
	 * @param int : Le nombre de caractères souhaité
	 * @return String : Contient un mot de passe aléatoire sur X caractères allant de a-z A-Z 0-9
	 */
	public String genererString(int x){
		String password = "";
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		int higher = chars.length()-1; 
		int lower = 0; 
		for(int i = 0 ; i < x ; i++){
			int y = (int)(Math.random() * (higher + 1 - lower)) + lower;
			password += chars.charAt(y);
		}
		return password;
	}
}
