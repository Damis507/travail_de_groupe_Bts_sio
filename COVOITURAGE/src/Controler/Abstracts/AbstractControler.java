package Controler.Abstracts;

import Model.Modele;
import Model.Interfaces.InterfCRUD;
import Model.Interfaces.InterfOperationsVuePrincipale;

public abstract class AbstractControler implements InterfCRUD {
	
	protected Modele monModel;
	
	
	public AbstractControler(Modele model)
	{ 
		this.monModel = model;
	}
	
}
