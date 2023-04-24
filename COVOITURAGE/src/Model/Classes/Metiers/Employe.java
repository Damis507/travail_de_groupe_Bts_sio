package Model.Classes.Metiers;

public class Employe {
	
	private String num_secu_employe;
	private String pseudo;
	private String nom;
	private String prenom;
	private String tel_cel;
	private String mdp;

	public Employe(){
		super();
		this.num_secu_employe = "";
		this.pseudo = "";
		this.nom = "";
		this.prenom = "";
		this.tel_cel = "";
		this.mdp = "";
	}
	
	public Employe(String num_secu_employe,
					   String pseudo, 
					   String nom, 
					   String prenom, 
					   String tel_cel,
					   String mdp) {
		super();
		this.num_secu_employe = num_secu_employe;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.tel_cel = tel_cel;
		this.mdp = mdp;
	}
	
	public Employe(String nom, 
			   		   String prenom) {
		super();
		this.num_secu_employe = "";
		this.pseudo = "";
		this.nom = nom;
		this.prenom = prenom;
		this.tel_cel = "";
		this.mdp = "";
	}

	public String getnum_secu_employe() {
		return num_secu_employe;
	}

	public void setnum_secu_employe(String num_secu_employe) {
		this.num_secu_employe = num_secu_employe;
	}

	public String getTel_cel() {
		return tel_cel;
	}

	public void setTel_cel(String tel_cel) {
		this.tel_cel = tel_cel;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo.toUpperCase();
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

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	@Override
	public String toString() {
		return "Utilisateur [pseudo = " + pseudo + ", nom = " + nom + ", prenom = " + prenom + ", mdp = " + mdp + "]";
	}

}
