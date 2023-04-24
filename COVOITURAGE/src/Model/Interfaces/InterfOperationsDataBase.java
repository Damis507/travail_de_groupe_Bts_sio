package Model.Interfaces;

import java.sql.SQLException;

import Model.Classes.Metiers.Employe;
import Model.Classes.Systeme.ConfigurationDB;
import Model.Classes.Systeme.Menu;

public interface InterfOperationsDataBase {

	public boolean connect(ConfigurationDB config);
	public void disconnet();
	public boolean isConnected();
	public boolean exist(Employe user) throws SQLException;
	public Menu getAllAcces(Employe user) throws SQLException;
	
}
