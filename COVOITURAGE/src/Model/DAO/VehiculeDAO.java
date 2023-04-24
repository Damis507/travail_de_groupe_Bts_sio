package Model.DAO;

import java.util.ArrayList;

import Model.Classes.Abstracts.AbstractCRUD;
import Model.Classes.Metiers.Resultat;
import Model.Classes.Metiers.Vehicule;
import Model.Classes.Systeme.ConnectionDB;

public class VehiculeDAO extends AbstractCRUD {


	public VehiculeDAO(ConnectionDB conn_db) {
		super(conn_db);
		// TODO Auto-generated constructor stub
	}
	
	public VehiculeDAO(ConnectionDB conn_db, 
					   String requete, 
					   ArrayList<String> listValeurs) {
		super(conn_db, requete, listValeurs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Resultat insertinto(Object obj) {
		// TODO Auto-generated method stub
		
		String requete = "insert into vehicule values ('?', '?', ?, ?, ?, ?, "
						+ "(select id_agence from agence where nom_agence = '?'), "
						+ "(select id_permis from permis where libelle_permis = '?'))";
		
		this.setRequete(requete);
		ArrayList<String> listValeurs = new ArrayList<String>();
		listValeurs.add(((Vehicule) obj).getImmatriculation_vehicule());
		listValeurs.add(((Vehicule) obj).getDate_mise_circulation());
		listValeurs.add(((Vehicule) obj).getCapacite_transport()+"");
		listValeurs.add(((Vehicule) obj).getNombre_places()+"");
		listValeurs.add(((Vehicule) obj).isHayon()+"");
		listValeurs.add(((Vehicule) obj).isCouchette()+"");
		listValeurs.add(((Vehicule) obj).getId_agence());
		listValeurs.add(((Vehicule) obj).getId_permis());
		
		this.setListValeurs(listValeurs);
		
		this.prepare();
		
		return this.execute();
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
		return null;
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
