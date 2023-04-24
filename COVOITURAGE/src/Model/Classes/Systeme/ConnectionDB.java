package Model.Classes.Systeme;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Classe qui prenne en chage la connexion à la BD 
 * a partir d'une configuration
 */

public class ConnectionDB {
	
	private Connection connection_db;
	
	private ConfigurationDB configuration;
	
	private boolean connected;
	
	public ConnectionDB(ConfigurationDB configuration) {
		super();
		
		this.configuration = configuration;
		
		this.connectDB();    
	}
	
	public ConnectionDB() {
		super();
		
		this.configuration = new ConfigurationDB();
		
		this.connectDB();  
	}
	
	public boolean connectDB()
	{
		this.connected = false;
		
		try 
		{
			/*
			 * On crée une instance de l'objet Driver présent 
			 * dans le fichier .jar que nous avons téléchargé. 
			 * Il est inutile de créer une véritable instance 
			 * de ce type d'objet 
			 * ( com.mysql.cj.jdbc.Driver driver = new com.mysql.cj.jdbc.Driver() ) 
			 * n'est pas nécessaire. 
			 */
			if (this.configuration.getSgdbr().equals("mysql"))
				Class.forName("com.mysql.cj.jdbc.Driver");
			else
				Class.forName("org.postgresql.Driver");
			
			System.out.println("D r i v e r   O . K .");
			
			System.out.println();
			
			String url = "jdbc:"+
					     this.configuration.getSgdbr()+
					     "://"+this.configuration.getHost()+
					     ":"+this.configuration.getPort()+"/"+
					     this.configuration.getDbname();
			
			System.out.println("E t a b i s s a n t   u n e   c o n n e x i o n   v e r s  :  " + url);
			
			// On crée un liens entre Java et la BDD
			this.connection_db = DriverManager.getConnection(url, 
																	 this.configuration.getUser(),
																	 this.configuration.getPassword());
			System.out.println();
			System.out.println();
			System.out.println("-------------------------------------------------------------------------------------");
			System.out.println();
			System.out.println("C o n n e x i o n   e f f e c t u é e   a v e c    s u c c è s  ! ! ! ! ! ! ! ! ! ! !");  
			System.out.println();
			System.out.println("-------------------------------------------------------------------------------------");
			System.out.println();
			
			this.connected = true;
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
		
		return this.connected;
	}
	
	public void disconnectDB()
	{
		try 
		{
			this.connection_db.close();
			
			System.out.println();
			System.out.println("-------------------------------------------------------------------------------------");
			System.out.println();			
			System.out.println("D é c o n n e x i o n   e f f e c t u é e   a v e c    s u c c è s  ! ! ! ! ! ! ! ! !");
			System.out.println();
			System.out.println("-------------------------------------------------------------------------------------");
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public Connection getConnexion_db() {
		return this.connection_db;
	}
	
	public boolean isConnected()
	{	
		return this.connected;
	}
	
	public String getSGBDR()
	{
		return this.configuration.getSgdbr();
	}
	
	public String getHost()
	{
		return this.configuration.getHost();
	}
	
	public String getDbName()
	{
		return this.configuration.getDbname();
	}
	
	public String getUserDB()
	{
		return this.configuration.getUser();
	}
	
	public String getPasswordDB()
	{
		return this.configuration.getPassword();
	}
	
	public String getPort() 
	{
		return this.configuration.getPort();
	}

	public ConfigurationDB getConfiguration() {
		return configuration;
	}

	public void setConfiguration(ConfigurationDB configuration) {
		this.configuration = configuration;
	}
}
