import java.awt.Dimension;

import Controler.Controleur;
import Model.Modele;
import View.Test;
import View.Vue;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new Test(null, new Dimension(300, 130), "Ma fenetre test", true);
		/*
		 *  Instanciation de notre modèle.
		 *  
		 *  C'est l'objet qui implement Observable.
		 *  C'est a dire, l'objet "observé"
		 *  
		 *  Dans notre cas, il contient une Session 
		 *  (qui contient elle même un User, le Menu adapté à ce User, 
		 *  une connexion a la BD et une manager qui gere la connexion a la BD)
		 *  
		 *  L'information que cet objet doit transmettre aux objets
		 *  "observateurs" est la "session". 
		 */
		Modele model = new Modele();
		
		/*
		 *  Création du contrôleur qui contient un modèle
		 */
		Controleur controler = new Controleur(model); 
		
		/*
		 * Création de notre fenêtre avec le contrôleur en paramètre. 
		 */
		Vue fenetreprincipale = new Vue(controler);
		
		/*
		 * Ajout de la fenêtre en tant observateur de notre modèle
		 * 
		 * Dans notre cas, ce n'est pas necessaire car ce qui est 
		 * "observé" est le model et notre controleur contient déjà 
		 * le model.
		 * 
		 * Cela sera utile pour les autres interface qui devront avoir acces
		 * aux informations du modele.
		 * 
		 * Dans notre cas, la FenetrePrincipale peut ne pas être un observateur
		 * car la fenetreprincipale contient un controler qui contient un model
		 * est c'est le model qui est observé
		 */
		model.addObserver(fenetreprincipale);
	}
}
