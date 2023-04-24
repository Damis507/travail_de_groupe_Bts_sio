package Model.Classes.Systeme;

import java.sql.SQLException;

import Model.Classes.Metiers.Employe;
import Model.DAO.SystemeDAO;

/*
 * Classe qui stocke les options du menu
 * correspondant a l'utilisateur (User) connecté au système.
 * Et aussi l'objet (managerDB) qui gere les opérations dans la BD.
 */

public class Session {
	
	private Menu monMenu;
	private Employe user;
	private SystemeDAO managerDB;
	
	
	public Session()
	{
		this.monMenu = new Menu();
		this.user = new Employe();
		this.managerDB = new SystemeDAO();
	}
	
	public boolean connect(ConfigurationDB config)
	{
		return this.managerDB.connect(config);
	}
	
	public void disconnect()
	{
		this.managerDB.disconnet();
	}
	
	public boolean isConnected()
	{
		return this.managerDB.isConnected();
	}

	public Menu getMenu() {
		return monMenu;
	}

	private void setMonMenu(Menu monMenu) {
		this.monMenu = monMenu;
	}

	public Employe getUser() {
		return user;
	}

	public void setUser(Employe user) {
		this.user = user;
	}
	
	public boolean setMenu(Employe u) throws SQLException
	{
		Menu m = this.managerDB.getAllAcces(u);
		
		if (m == null)
			return false;
		else
		{
			this.setMonMenu(m);
			return true;
		}
	}
	
	public boolean exist(Employe u)
	{
		try 
		{
			return this.managerDB.exist(u);
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public String getSGBDR()
	{
		return this.managerDB.getSGBDR();
	}
	
	public String getHost()
	{
		return this.managerDB.getHost();
	}
	
	public String getDbName()
	{
		return this.managerDB.getDbName();
	}
	
	public String getUserDB()
	{
		return this.managerDB.getUserDB();
	}
	
	public String getPasswordDB()
	{
		return this.managerDB.getPasswordDB();
	}
	
	public String getPort() 
	{
		return this.managerDB.getPort();
	}
	
	public ConnectionDB getConnDBSession()
	{
		return this.managerDB.getConn_db();
	}

}
