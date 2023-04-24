package Model.Classes.Abstracts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Model.Classes.Metiers.Resultat;
import Model.Classes.Metiers.Employe;
import Model.Classes.Systeme.ConnectionDB;
import Model.Interfaces.InterfCRUD;
import Model.Interfaces.InterfOperationsAdmin;

public abstract class AbstractCRUD implements InterfCRUD {
	
	/*
	 * Classe "supertype" ClasseMetier
	 */
	
	protected ConnectionDB conn_db;
	protected String requete;  
	protected ArrayList<String> listValeurs;
	
	/*
	 * 0 : COMMANDE NON RECONNUE
	 * 1 : INSERT INTO
	 * 2 : SELECT
	 * 3 : UPDATE
	 * 4 : DELETE
	 */
	private byte commandesql;
	/*
	 * Cette variable sera "true"
	 * si on a pu remplacer les "?" 
	 * par leurs valeurs respectives.
	 */
	private boolean prepared;
	
	// Constructeur d'initialisation
	public AbstractCRUD(ConnectionDB conn_db,
						String requete,
						ArrayList<String> listValeurs) {
		super();
		
		this.conn_db = conn_db;
		this.requete = requete.trim();
		/*
		 * Méthode privée qui va valoriser
		 * l'attribut "commandesql"
		 */
		this.setCommandesql();
		this.prepared = false;
		this.listValeurs = listValeurs;
	}
	
	// Constructeur d'initialisation 
	public AbstractCRUD(ConnectionDB conn_db) {
		super();
		
		this.conn_db = conn_db;
		
		/*
		 * Methode privée qui valorise
		 * les autres attributs avec 
		 * des valeurs neutres
		 */
		this.setAttributsNeutre();
	}
	
	public void setRequete(String requete) {
		this.requete = requete.trim();
		this.setCommandesql();
	}
	
	public String getRequete() {
		return requete;
	}

	public void setListValeurs(ArrayList<String> listValeurs) {
		this.listValeurs = listValeurs;
	}
	
	public boolean prepare()
	{
		/*
		 * Methode qui "prepare" (remplace les "?" par des valeurs) les requetes complexes.
		 * 
		 * Cette méthode est le complement des 4 méthodes abstracts.
		 * 
		 * Requetes complexes :
		 * 
		 * 		- requetes imbriquées,
		 * 		- requetes avec des WHERE, INNER JOIN, HAVING, GROUP BY, etc.
		 * 
		 * - select nom, prenom, date from utilisateur where nomchamp = ? and nomchamp2 = '?';
		 * 
		 * - DELETE FROM utilisateur WHERE date_inscription < '?'
		 * 
		 * - UPDATE client SET rue = '?', ville = '?', code_postal = '?' WHERE id = ?
		 * 
		 * - INSERT INTO utilisateur (nom, prenom) values (select nom, prenom 
		 * 												   from utilisateur_bis 
		 * 												   where id > ?)
		 *  
		 *  On doit remplacer tous les "?" par les valeurs réelles
		 *  
		 *  Préalablement il faut verifier que :
		 *  
			 *  1 ) tous les attributs de la classe ont bien une valeur; OK 
			 *  2 ) vérifier que la quantite de "?" corresponds a la taille 
			 *  	de la liste de valeurs; OK
			 *  3 ) determiner de quelle type de requete s'agit-il ? OK
			 *  4 ) remplacer les "?" par les valeur; A FAIRE
		 */
		
		this.prepared = false;
		int qteValeurs = this.listValeurs.size();
		
		/*
		 * si la connexion n'est pas null et on s'est bien connecte
		 * et la requete n'est pas vide
		 * et la quantité de marqueurs est egale à la quantite de valeurs
		 * de la liste
		 */
		if (this.conn_db != null && this.conn_db.isConnected()
			 && !this.requete.equals("") 
			 && this.countMarqueursSQL() == qteValeurs)
		{
			/*
			 *  on met dans this.commandesql
			 *  0/1/2/3/4 en fonction de la commande
			 *  de la requete
			 */
			this.setCommandesql();
			
			/*
			 * S'il y a des valeurs qui vont remplacer les marqueurs
			 */
			if (this.listValeurs != null && this.listValeurs.size() != 0)
			{
				// un SELECT, INSERT, UPDATE, DELETE
				if (this.commandesql != 0)
				{
					/*
					 * remplacer les "?" par les valeurs
					 * 
					 * UPDATE client SET rue = '?', ville = '?', code_postal = '?' WHERE id = ?
					 */
					
					for(String valeur : this.listValeurs) 
					{
						this.requete = this.monReplaceFirst("?", valeur, this.requete);
					}
				}
				else // la commande n'est pas SELECT, INSERT, UPDATE, DELETE
					return false;
			}
			
			this.prepared = true;
			
			return true;
	
		}
		
		return false;
	}
	
	public Resultat execute()
	{
		if (this.prepared)
		{	
			try 
			{
				PreparedStatement prepare = this.conn_db // Objet ConnectionDB
														.getConnexion_db() // la variable qui pointe vers la BD
														.prepareStatement(this.requete);
				
				ResultSet reponse = null; 
				
				/* 
				 *  executeUpdate() : INSERT / UPDATE / DELETE
				 *  executeQuery() : SELECT
				 */
				switch (this.commandesql)
				{
					case 2 : reponse = prepare.executeQuery();
						break;
					default : prepare.executeUpdate();
				}		
				/*
				 * Pour eviter qu'on fasse une
				 * nouvelle execution de la requete
				 */
				this.setAttributsNeutre();
				
				// Tout s'est bien passé
				return new Resultat("", true, reponse);
			} 
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				return new Resultat(e.getMessage(), false, null);
				//e.printStackTrace();
			}
		}
		return null;
	}
	
	private int countMarqueursSQL()
	{
		/*
		 * Cette fonction qui retourne la 
		 * quantité de "?" qui comporte
		 * la requete
		 * 
		 * this.requete = "select nom, prenom, date 
		 * from utilisateur where nomchamp = ? and nomchamp2 = '?'";
		 */
		
		int compteurMarquers = 0;
		int fromIndex = 0;
		
		fromIndex = this.requete.indexOf("?", fromIndex); 
		
		while (fromIndex != -1)
		{
			fromIndex = this.requete.indexOf("?", fromIndex); 
			 
			if (fromIndex != -1)
			{
				compteurMarquers++;
				fromIndex++;
			}	
		}
		
		return compteurMarquers;
	}
	
	private String getCommandeSql()
	{
		// trouver la position du premier espapce
		int premierespace = this.requete.indexOf(" ");
		
		return this.requete.substring(0, premierespace).trim().toUpperCase();
	}
	
	public void setCommandesql() 
	{
		String commandeSql = this.getCommandeSql();
		
		this.commandesql = 0;
		
		if (commandeSql.equals("INSERT"))
			this.commandesql = 1;
		 else if (commandeSql.equals("SELECT"))
			this.commandesql = 2;
		 else if (commandeSql.equals("UPDATE"))
			this.commandesql = 3;
		 else if (commandeSql.equals("DELETE"))
			this.commandesql = 4;
	}

	private void setAttributsNeutre()
	{
		this.requete = "";
		this.commandesql = 0;
		this.prepared = false;
		this.listValeurs = new ArrayList<String>();
	}
	
	private String monReplaceFirst(String aremplacer, String par, String dans)
	{
		int pos = dans.indexOf(aremplacer);
		
		dans = dans.substring(0, pos) + par + dans.substring(pos+1);
		
		return dans; 
	}
}
