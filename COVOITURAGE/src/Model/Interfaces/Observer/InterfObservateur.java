package Model.Interfaces.Observer;

import Model.Classes.Systeme.Session;;

/*
 * Interface qui definie la methode qui 
 * sera programmée dans les classes qui vont 
 * implémenter cette interface. C.a.d. des 
 * classes qui seront des "observateurs" 
 * d'une classe qui implemente "observable".
 */

public interface InterfObservateur {
	
	public void update(Session session);

}
