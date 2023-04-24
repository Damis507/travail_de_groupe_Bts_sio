package Model.Classes.Systeme;

import java.util.LinkedList;

/*
 * Classe qui represente une option du menu.
 * Elle comporte un nom et une d'options.
 * 
 * Exemple :
 * 
 * menu[0] est l'option "MENU1" qui contient une liste d'options.
 * 		option[0] est "Option11" qui PEUT elle aussi contenir des options.
 * 		option[1] est "Option12" qui PEUT elle aussi contenir des options.
 * 		option[2] est "Option13" qui PEUT elle aussi contenir des options.
 * menu[1] est l'option "MENU2" qui contient une liste d'options.
 * 		option[0] est "Option21" qui PEUT elle aussi contenir des options.
 * 		option[1] est "Option22" qui PEUT elle aussi contenir des options.
 * menu[2] est l'option "MENU3" qui contient une liste d'options.
 * 		option[0] est "Option31" qui PEUT elle aussi contenir des options.
 * 		option[1] est "Option32" qui PEUT elle aussi contenir des options.
 * 		option[2] est "Option33" qui PEUT elle aussi contenir des options.
 * menu[3] est l'option "MENU4" qui contient une liste d'options.
 *		option[0] est "Option41" qui PEUT elle aussi contenir des options.
 */

public class Option {
	
	/*
	 * A T T R I B U T S
	 */
	private String nom;
	private LinkedList<Option> options;
	
	
	/*
	 * C O N S T R U C T E U R S
	 */
	// P A R   D E F A U T
	public Option() {
		super();
		this.nom = "---";
		this.options = new LinkedList<Option>();
	}
	// I N I T I A L I S A T I O N
	public Option(String nom, LinkedList<Option> option) {
		super();
		this.nom = nom;
		this.options = option;
	}
	
	
	/*
	 * G E T T E R S  e t   S E T T E R S
	 */
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setOption(LinkedList<Option> option) {
		this.options = option;
	}
	public LinkedList<Option> getOptions() {
		return options;
	}
	public void setOptions(LinkedList<Option> options) {
		this.options = options;
	}
}
