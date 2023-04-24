package Model.Classes.Systeme;

import java.util.LinkedList;

import javax.swing.JMenu;

/*
 * Classe qui garde le menu auquel un utilisateur a accès.
 * 
 * Un menu est une liste d'Options.
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

public class Menu {

	/*
	 * A T T R I B U T S
	 */
	private LinkedList<Option> menu;
	
	/*
	 * C O N S T R U C T E U R S
	 */
	// I N I T I A L I S A T I O N
	public Menu(LinkedList<Option> sous_menu) {
		super();
		this.menu = sous_menu;
	}
	// P A R   D E F A U T
	public Menu() {
		super();
		this.menu = new LinkedList<Option>();
	}
	
	/*
	 * G E T T E R S   e t   S E T T E R S
	 */
	
	
	/*
	 * A U T R E S   M E T H O D E S
	 */
	public void add(Option op)
	{
		this.menu.add(op);
	}
	
	public Option getMenu(int index)
	{
		return this.menu.get(index);
	}
	
	public int getMenuCount()
	{
		return this.menu.size();
	}
	
	public int getOptionsCountMenu(int index)
	{
		return this.menu.get(index).getOptions().size();
	}
	
	public String getLibelleMenu(int index)
	{
		return this.menu.get(index).getNom();
	}
	
	public String getLibelleOption(int op, int m)
	{
		return this.menu.get(m).getOptions().get(op).getNom();
	}
	
}
