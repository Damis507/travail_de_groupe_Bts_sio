package Model.Classes.Systeme;

/*
 * Classe qui garde la configuration active 
 * de la connexion à la BD.
 * 
 * Par défaut cette configuration est :
 * 
 * sgdbr = "mysql";
 * host = "localhost";
 * dbname = "ecole_musique";
 * user = "root";
 * password = "root";
 * port = "8889";
 */

public class ConfigurationDB {
	
	private String sgdbr;
	private String host;
	private String dbname;
	private String user;
	private String password;
	private String port;
	
	public ConfigurationDB(String sgdbr, 
						   String host, 
						   String port,
						   String dbname, 
						   String user, 
						   String password) {
		super();
		this.sgdbr = sgdbr;
		this.host = host;
		this.port = port;
		this.dbname = dbname;
		this.user = user;
		this.password = password;
	}
	
	public ConfigurationDB()
	{
		super();
		
		this.sgdbr = "postgresql";
		this.host = "192.168.1.49";
		this.dbname = "covoiturage";
		this.user = "jovany";
		this.password = "1234";
		this.port = "5432";
	}

	public String getSgdbr() {
		return sgdbr;
	}

	public void setSgdbr(String sgdbr) {
		this.sgdbr = sgdbr;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
	
}
