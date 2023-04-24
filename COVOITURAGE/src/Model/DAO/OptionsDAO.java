package Model.DAO;

import java.util.ArrayList;

import Model.Classes.Abstracts.AbstractCRUD;
import Model.Classes.Metiers.Resultat;
import Model.Classes.Systeme.ConnectionDB;

public class OptionsDAO extends AbstractCRUD {

	public OptionsDAO(ConnectionDB conn_db, String requete, ArrayList<String> listValeurs) {
		super(conn_db, requete, listValeurs);
		// TODO Auto-generated constructor stub
	}

	public OptionsDAO(ConnectionDB conn_db) {
		super(conn_db);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Resultat insertinto(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deletefrom(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Object newObj, Object oldObj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Resultat select(Object obj) {
		// TODO Auto-generated method stub
		String requete = "select * "
				       + "from options "
			       	   + "where id_option in (select distinct id_option_o "
			       			   			   + "from comporte)";
		
		this.setRequete(requete);
		
		this.prepare();
		
		return this.execute();
	}

	@Override
	public Resultat selectAll(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object selectAllInfo(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
