package Model.Interfaces.Observer;

import Model.Classes.Systeme.Session;

/*
 * Interface qui définit toutes les méthodes
 * qui pourront être programmées dans les 
 * classes qui vont implementer cette interface,
 * c.a.d. qui seront des classes "observables".
 */

public interface InterfObservable {
	/*
	 * Methode qui seront implementés dans la 
	 * classe implementant Observable "AbstractModel". 
	 */
	// Ajouter un nouveau "observateur" a la classe qui implementera cette interface
	public abstract void addObserver(InterfObservateur obs); 
	// Supprime la liste des "observateurs".
	public abstract void removeObserver();
	// Supprime l'i-eme "observateur" de la liste des observateurs.
	public abstract void removeObserver(int i);
	// Envoie l'information (une session spécifique) qui est importante pour les observateurs pour faire une M.A.J.
	public abstract void notifyObserver(Session session);
	// Met a jour tous les observateurs avec la session de l'objet observable.
	public abstract void notifyObserver();

}
