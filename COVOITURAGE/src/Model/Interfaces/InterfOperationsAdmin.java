package Model.Interfaces;

import java.util.List;

import Model.Classes.Metiers.Resultat;
import Model.Classes.Metiers.Employe;

public interface InterfOperationsAdmin {
	
	/*
	 * M E T H O D E S   
	 * F O N C T I O N S   
	 * D ' A D M I N I S T R A T I O N
	 */
	
	public abstract Resultat setDroits(List<String> LDroit, Employe employe);
	public abstract List<String> getDroits(Employe employe);
	
}
