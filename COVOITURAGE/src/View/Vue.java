package View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import Controler.Controleur;
import Controler.Abstracts.AbstractControler;
import Model.Classes.Systeme.Session;
import Model.Interfaces.Observer.InterfObservateur;
import View.Abstracts.AbstractVuePersonnalisable;

/*
 * Vue principal qui contient un menu et un controler.
 * 
 * Cette vue est un observateur du modele, mais dans ce cas cela 
 * n'est pas necessaire car l'objet "observé" est le modele et 
 * il est contenu dans le controleur.
 * 
 * Cette classe implemente ActionListener pour gerer les evenements du menu.
 * Et implement aussi la methode (update) de la interface Observateur.
 */

public class Vue extends JFrame 
				 implements InterfObservateur, 
				 			ActionListener {
	
	private JMenuBar barreMenu;
	private ArrayList<JMenu> monMenu;
	
	private Controleur controler;
	
	
	public Vue(Controleur controler) {
		
		this.controler = controler;
		
		this.setSize(new Dimension(800, 300));
		
		this.setTitle("- - - S y s t è m e   COVOITURAGE - - -"); 
		/*
		 *  Desactiver le bouton qui sert a fermer la fenetre.
		 *  
		 *  Dans notre cas, on le programme avec le bouton Fermer
		 */
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
		// Centrer la fenetre
		this.setLocationRelativeTo(null); 
		// La fenetre ne pourra pas changer de taille
		this.setResizable(false);
		
		this.barreMenu = new JMenuBar();
		this.monMenu = new ArrayList<JMenu>();
		
		this.monMenu.add(new JMenu("Session"));
		JMenuItem monJMI = new JMenuItem("Pseudo : ");
		monJMI.setEnabled(false);
		monJMI.addActionListener(this);
		this.monMenu.get(0).add(monJMI);
		monJMI = new JMenuItem("Configuration");
		/*
		 * L'ecouteur se trouve dans la meme classe.
		 * Methode actionPerformed.
		 */
		monJMI.addActionListener(this);
		this.monMenu.get(0).add(monJMI);
		monJMI = new JMenuItem("Se connecter");
		monJMI.addActionListener(this);
		this.monMenu.get(0).add(monJMI);
		monJMI = new JMenuItem("Quitter");
		monJMI.addActionListener(this);
		
		this.monMenu.get(0).add(monJMI);
		this.barreMenu.add(this.monMenu.get(0));;
		
		this.setJMenuBar(this.barreMenu);

		this.setVisible(true);
	}

	public boolean remplirMenu() throws SQLException
	{
		// Si le menu n'est pas vide
		if (this.controler.remplirMenu(this.controler.getUserSession()))
		{
			// combien de menu il y a
			int tailleMenu = this.controler.getSizeMenu();
			
			/*
			 * La premiere option du premiere menu est personnalisée avec
			 * le pseudo de l'utilisateur de la session.
			 * Puis, elle est activée.
			 */
			this.monMenu.get(0).getItem(0).setText("Pseudo : " + this.
																	 controler.
																	 getPseudoUserSession());
			this.monMenu.get(0).getItem(0).setEnabled(true);
			
			/*
			 * Si on est ici c'est parce que l'utilisateur existe dans la BD.
			 * Par consequent, on peut assumer une connexion reussie.
			 * 
			 * La troisième option du premiere menu passe de "Se connecter"
			 * à "Se déconnecter".
			 */
			this.monMenu.get(0).getItem(2).setText("Se déconnecter");
			
			// remplir les autres menus avec leurs options
			for (int i = 0; i < tailleMenu; i++)
			{
				// ajouter un nouveau menu
				this.monMenu.add(new JMenu(this.controler.getLibelleMenu(i)));
				// quantité d'options de ce menu
				int qteOptions = this.controler.getOptionsCountMenu(i);
				
				for (int j = 0; j < qteOptions; j++)
				{
					// creer une option de menu
					JMenuItem monJMI = new JMenuItem(this.controler.getLibelleOption(j, i));
					monJMI.addActionListener(this);
					/*
					 *  (i+1) car le premier menu (=0) est fixe, 
					 *  celui de la session.
					 *  
					 *  ajouter l'option au menu
					 */
					this.monMenu.get(i+1).add(monJMI); 
				}
				
				// ajouter le menu au JMenuBar
				this.barreMenu.add(this.monMenu.get(i+1));
			}
	
			/*
			 * Il faut rendre invisible le menu, 
			 * puis visible pour pouvoir afficher correctement 
			 */
			this.barreMenu.setVisible(false);
			this.barreMenu.setVisible(true);
			
			/*
			 *  retourne true car :
			 *  1) la connexion s'est bien passe
			 *  2) l'utilisateur existe dans la BD
			 *  3) il a des options, donc un menu
			 */
			return true;
		}
		else
			/*
			 * retourne false car l'une des deux
			 * premieres conditions
			 * n'ont pas été satisfaites.
			 */
			return false;
	}
	
	public void viderMenu()
	{
		int tailleMenu = this.controler.getSizeMenu();
		
		this.monMenu.get(0).getItem(0).setText("Pseudo : ");
		this.monMenu.get(0).getItem(0).setEnabled(false);
		
		this.monMenu.get(0).getItem(2).setText("Se connecter");
		
		for (int i = tailleMenu; i > 0; i--)
		{
			// Supprimer tous les JMenuItem du i-eme JMenu
			this.monMenu.get(i).removeAll();
			// Supprimer le i-eme JMenu de la liste
			this.monMenu.remove(i);
			// Supprimer le i-eme JMenu du JMenuBar
			this.barreMenu.remove(i);
		}
		
		this.barreMenu.setVisible(false);
		this.barreMenu.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getActionCommand().equals("Se connecter"))
		{
			new VueConnexion(null, new Dimension(300, 130), "Connexion", true, this.controler);
			
			try 
			{
				/*
				 *  Si le ménu a rempli, rendre inactive
				 *  l'option "Configuration"
				 */
				if (this.remplirMenu())
					this.monMenu.get(0).getItem(1).setEnabled(false);
			} 
			catch (SQLException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (e.getActionCommand().equals("Configuration"))
		{
			VueConfiguration config = new VueConfiguration(null, 
														   new Dimension(300, 270), 
														   "Configuration", 
														   true, 
														   this.controler);
			
			/*
			 * On fait que l'interface observe au modele.
			 * Puis on met a jour à tous les observateur
			 * pour pouvoir lire les informations de la Session
			 */
			this.controler.addObserverModel(config);
			this.controler.notifiyObserverModel();
			
			// Rendre visible la vue.
			config.setVisible(true);
		}
		else if (e.getActionCommand().equals("Se déconnecter"))
		{
			// vider le menu de l'interface
			this.viderMenu();
			// met la session par defaut
			this.controler.setSessionDefaut();
			// rendre inactive l'option "Configuration"
			this.monMenu.get(0).getItem(1).setEnabled(true);
		}
		else if (e.getActionCommand().equals("Quitter"))
		{
			// se deconnecter de la BD
			this.controler.disconnect();
			dispose();
		}
		else if (e.getActionCommand().equals("Changer password"))
		{
			new VueChangerPassword(null, 
								   new Dimension(320, 210), 
								   "Changer mot de passe",
								   true,
								   this.controler);
		}
		else if (e.getActionCommand().equals("Ajouter vehicule"))
		{
			new VueAjouterVehicule(null,
								   new Dimension(300, 300),
								   "Ajouter vehicule",
								   true,
								   this.controler);
		}
		else
			System.out.println(e.getActionCommand());
	}

	/*
	 * Implementation du pattern Observable
	 * 
	 * Cette méthode est appelée quand le model fait "notifyObserver".
	 * Cette dernière méthode est dans AbstractModel et on fait la mise 
	 * à jour vers tous les objects qui observent la sesson du model.
	 * 
	 * (non-Javadoc)
	 * @see Model.Observer.Observateur#update(Model.Session)
	 */
	public void update(Session session) {
		// TODO Auto-generated method stub
		/*
		 * Dans notre cas ce n'est pas necessaire car ce qu'il faut "observer" est le model
		 * et notre FenetrePrincipale a le controleur qui contient le modèle.
		 * 
		 * Cela sera utile pour les autres interface qui devront avoir acces
		 * aux informations du modele.
		 * 
		 * Dans notre cas, la FenetrePrincipale peut ne pas être un observateur
		 */
		this.controler.setSessionModel(session);
	}

}
