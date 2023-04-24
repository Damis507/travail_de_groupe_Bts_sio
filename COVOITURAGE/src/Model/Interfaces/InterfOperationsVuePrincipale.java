package Model.Interfaces;

import Model.Classes.Metiers.Employe;
import Model.Classes.Systeme.ConfigurationDB;
import Model.Classes.Systeme.Session;
import Model.Interfaces.Observer.InterfObservateur;

public interface InterfOperationsVuePrincipale {
	
	public abstract boolean remplirMenu(Employe user);
	public abstract Employe getUserSession();
	public abstract int getSizeMenu();
	public abstract String getPseudoUserSession();
	public abstract String getLibelleMenu(int index);
	public abstract int getOptionsCountMenu(int index);
	public abstract String getLibelleOption(int op, int m);
	public abstract void setSessionDefaut();
	public abstract void disconnect();
	public abstract boolean connect(ConfigurationDB config);
	public abstract boolean connectionValid();
	public abstract boolean exist(Employe user);
	public abstract void setUserSession(Employe user);
	
	public abstract void addObserverModel(InterfObservateur obs);
	public abstract void notifiyObserverModel();
	public abstract void removeObserverModel(int index);
	
	public abstract void setSessionModel(Session session);
	
}
