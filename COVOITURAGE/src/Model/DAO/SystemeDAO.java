package Model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import Model.Classes.Metiers.Employe;
import Model.Classes.Systeme.ConfigurationDB;
import Model.Classes.Systeme.ConnectionDB;
import Model.Classes.Systeme.Menu;
import Model.Classes.Systeme.Option;
import Model.Interfaces.InterfOperationsDataBase;

/*
 * Classe qui gere les opérations sur la BD
 */

public class SystemeDAO implements InterfOperationsDataBase {
	
	private ConnectionDB conn_db;

	public SystemeDAO() {
		super();
		this.conn_db = new ConnectionDB();
	}
	
	public boolean connect(ConfigurationDB config)
	{
		this.conn_db.setConfiguration(config);
		return this.conn_db.connectDB();
	}
	
	public void disconnet()
	{
		this.conn_db.disconnectDB();
	}
	
	public boolean isConnected()
	{
		return this.conn_db.isConnected();
	}
	
	public boolean exist(Employe user) throws SQLException
	{
		String requete = "select pseudo "
				   + "from employe "
				   + "where pseudo = '" + user.getPseudo().toUpperCase() + "' and "
				   + "mot_de_passe = '" + user.getMdp() + "'";
		
		PreparedStatement prepare = null;
		
		try {
			prepare = this.conn_db
											.getConnexion_db()
											.prepareStatement(requete,
															  ResultSet.TYPE_SCROLL_INSENSITIVE, 
															  ResultSet.CONCUR_UPDATABLE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		ResultSet reponse = prepare.executeQuery();
	
		if (reponse.first())
			return reponse.getString(1).trim().equals(user.getPseudo().toUpperCase());
		
		return false;
	}

	public Menu getAllAcces(Employe user) throws SQLException
	{
		/*
		 * Obtenir toutes les options pour lesquelles l'utilisateur "user"
		 * a accès.
		 * 
		 * select O1.libelle_option as libelle_menu, IDMIDOPLOPP.libelle_option 
		 * from (select C.id_option as id_menu, OPTIONS_PSEUDO.id_option as id_option, 
		 * 		 OPTIONS_PSEUDO.libelle as libelle_option, OPTIONS_PSEUDO.pseudo as pseudo 
		 * 		 from (select A.id_option, libelle_option as libelle, pseudo 
		 * 			   from autorisation A 
		 * 			   inner join utilisateur U on U.id_utilisateur = A.id_utilisateur 
		 * 			   inner join options O on O.id_option = A.id_option where pseudo = 'FABIO') as OPTIONS_PSEUDO 
		 *       inner join comporte C on C.id_option_OPTIONS = OPTIONS_PSEUDO.id_option 
		 *       order by id_menu) as IDMIDOPLOPP 
		 * inner join options O1 on O1.id_option = IDMIDOPLOPP.id_menu
		 * 
		 * On obtient premierement (OPTIONS_PSEUDO) : id de l'option, le libelle et le pseudo
		 * des options auxquel l'utilisateur qui a ce pseudo.
		 * 
		 * IDMIDOPLOPP : On ajoute l'id du menu pour chacune de ces options.
		 * 
		 * On ajoute le libelle du menu de la vue précédente
		 */
		/*String requete = "select O1.libelle_option as libelle_menu, IDMIDOPLOPP.libelle_option "
					   + "from "
					   		+ "(select C.id_option as id_menu, OPTIONS_PSEUDO.id_option as id_option, "
					   		+ "OPTIONS_PSEUDO.libelle as libelle_option, OPTIONS_PSEUDO.pseudo as pseudo "
					   		+ "from (select A.id_option, libelle_option as libelle, pseudo " 
					   				+ "from autorisation A "
					   				+ "inner join utilisateur U on U.id_utilisateur = A.id_utilisateur "
					   				+ "inner join options O on O.id_option = A.id_option "
					   				+ "where pseudo = '" + user.getPseudo() + "') as OPTIONS_PSEUDO "
					   		+ "inner join comporte C on C.id_option_options = OPTIONS_PSEUDO.id_option "
					   		+ "order by id_menu) as IDMIDOPLOPP "
					   + "inner join options O1 on O1.id_option = IDMIDOPLOPP.id_menu";*/
		
		String requete = "select O.libelle_option as libelle_menu, IDMENUIDOPTIONSPSEUDO.libelle_option, IDMENUIDOPTIONSPSEUDO.lien as lien"
				+ "	from (select COMP.id_option_m as id_menu, IDOPTIONSPSEUDO.id_option as id_option, IDOPTIONSPSEUDO.libelle_option as libelle_option, IDOPTIONSPSEUDO.pseudo as pseudo, IDOPTIONSPSEUDO.lien as lien"
				+ "			from (select OD.id_option as id_option, libelle_option, pseudo, lien"
				+ "				from option OD"
				+ "				inner join autoriser_option_demenageur AOD on AOD.id_option = OD.id_option"
				+ "				inner join employe ED on AOD.num_secu_demenageur = ED.num_secu_employe"
				+ "				where pseudo = '" + user.getPseudo() + "' and lien is null"
				+ "				union"
				+ "				select OC.id_option as id_option, libelle_option, pseudo, lien"
				+ "				from option OC"
				+ "				inner join autoriser_option_commercial AOC on AOC.id_option = OC.id_option"
				+ "				inner join employe EC on AOC.num_secu_commercial = EC.num_secu_employe"
				+ "				where pseudo = '" + user.getPseudo() + "' and lien is null) as IDOPTIONSPSEUDO"
				+ "			inner join comporte COMP on COMP.id_option_o = IDOPTIONSPSEUDO.id_option"
				+ "         order by id_menu, id_option) as IDMENUIDOPTIONSPSEUDO"
				+ " inner join option O on O.id_option = IDMENUIDOPTIONSPSEUDO.id_menu";
		
		//System.out.println(requete);
		PreparedStatement prepare = this.conn_db
												.getConnexion_db()
												.prepareStatement(requete,
																  ResultSet.TYPE_SCROLL_INSENSITIVE, 
																  ResultSet.CONCUR_UPDATABLE);
		
		ResultSet reponse = prepare.executeQuery();
		
		String nommenu;
		LinkedList<Option> menu = new LinkedList<Option>();
		LinkedList<Option> options = new LinkedList<Option>();
		
		
		/* 
		 * tant qu'il y a de registres
		 */
		while ( reponse.next() )
		{
			nommenu =  new String(reponse.getString(1));
			
			// ajouter l'option
			options.add(new Option(new String(reponse.getString(2)), null));
			
			// s'il y a encore des registres
			if (reponse.next())
			{
				// si le nom du menu du registre prochain change
				if (!nommenu.equals(reponse.getString(1)))
				{
					// ajouter la liste d'options à ce menu
					menu.add(new Option(new String(nommenu), options));
					// mettre à zéro la liste d'options
					options = new LinkedList<Option>();
				}
				
				/*
				 * comme on avait avancé pour savoir le nom du menu suivant,
				 * il faut faire marche arriere
				 */
				reponse.previous();
			}
			else // si on est arrivé à la fin
			{
				// avec les derniers options
				
				// ajouter la liste d'options à ce menu
				menu.add(new Option(new String(nommenu), options));
				// mettre à zéro la liste d'options
				options = new LinkedList<Option>();
			}
		}
		
		return (menu.isEmpty() ? null : new Menu(menu)) ;
	}
	
	public ConnectionDB getConn_db() {
		return conn_db;
	}

	public String getSGBDR()
	{
		return this.conn_db.getSGBDR();
	}
	
	public String getHost()
	{
		return this.conn_db.getHost();
	}
	
	public String getDbName()
	{
		return this.conn_db.getDbName();
	}
	
	public String getUserDB()
	{
		return this.conn_db.getUserDB();
	}
	
	public String getPasswordDB()
	{
		return this.conn_db.getPasswordDB();
	}
	
	public String getPort() 
	{
		return this.conn_db.getPort();
	}
}
