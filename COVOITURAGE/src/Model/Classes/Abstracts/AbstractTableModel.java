package Model.Classes.Abstracts;

import java.util.ArrayList;

import Model.Classes.Metiers.Employe;
import Model.Classes.Systeme.ConfigurationDB;
import Model.Classes.Systeme.Option;
import Model.Classes.Systeme.Session;
import Model.Interfaces.InterfCRUD;
import Model.Interfaces.InterfOperationsAdmin;
import Model.Interfaces.Observer.InterfObservable;
import Model.Interfaces.Observer.InterfObservateur;

/*
 * Classe qui implemente l'interface "Observable".
 * 
 * Elle contient : 
 * 
 * - une liste d'"observateurs"
 * 
 * - une Session
 * 
 * - un managerDAO qui va se comportent differemment en fonction de
 *   la classe métier concernée.
 */

public abstract class AbstractTableModel implements InterfObservable, 
										       InterfCRUD,
										       InterfOperationsAdmin {

	protected Session session = new Session();
	protected ArrayList<InterfObservateur> listObserver = new ArrayList<InterfObservateur>();
	protected AbstractCRUD managerDAO;
	
	/*
	 * M E T H O D E S   A B S T R A C T S
	 */
	
	// Interface Principal
	public abstract boolean connectionValid();
	public abstract boolean connect(ConfigurationDB config);
	public abstract boolean exist(Employe user);
	public abstract boolean remplirMenu(Employe u);
	public abstract int getSizeMenu();
	public abstract Option getOptionsMenu(int index);
	public abstract int getOptionsCountMenu(int index);
	public abstract String getLibelleMenu(int index);
	public abstract String getLibelleOption(int op, int m);
	public abstract void setSessionDefaut();
	public abstract Employe getUserSession();
	public abstract String getPseudoSession();
	public abstract void disconnect();
	public abstract void setUserSession(Employe user);
	public abstract String deleteUserSession();
	
	public Session getSession() {
		return session;
	}
	
	public void setSession(Session session)
	{
		this.session = session;
	}

}


