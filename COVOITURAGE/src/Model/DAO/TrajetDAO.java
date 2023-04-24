package Model.DAO;

import java.util.ArrayList;

import Model.Classes.Abstracts.AbstractCRUD;
import Model.Classes.Metiers.Resultat;
import Model.Classes.Metiers.Trajet;
import Model.Classes.Metiers.Employe;
import Model.Classes.Systeme.ConnectionDB;

public class TrajetDAO extends AbstractCRUD {

	public TrajetDAO(ConnectionDB conn_db, String requete, ArrayList<String> listValeurs) {
		super(conn_db, requete, listValeurs);
		// TODO Auto-generated constructor stub
	}
	
	public TrajetDAO(ConnectionDB conn_db) {
		super(conn_db);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Resultat insertinto(Object obj) {
		// TODO Auto-generated method stub
		String requete = "insert into Trajet (id_trajet,date_time_trajet,rue_adr_depart_trajet,num_rue_adr_depart_trajet,cp_adr_depart_trajet,rue_adr_arrivee_trajet,num_rue_adr_arrivee_trajet,cp_adr_arrivee_trajet,nbr_max_places,Pseudo,administrateur) "
				                    + "values ('?', '?', '?', '?','?', '?', '?', '?','?','?')";
		
		this.setRequete(requete);
		ArrayList<String> listValeurs = new ArrayList<String>();
		listValeurs.add(((Trajet) obj).getid_trajet());
		listValeurs.add(((Trajet) obj).getdate_time_trajet());
		listValeurs.add(((Trajet) obj).getrue_adr_depart_trajet());
		listValeurs.add(((Trajet) obj).getnum_rue_adr_depart_trajet());
		listValeurs.add(((Trajet) obj).getcp_adr_depart_trajet());
		listValeurs.add(((Trajet) obj).getrue_adr_arrivee_trajet());
		listValeurs.add(((Trajet) obj).getnum_rue_adr_arrivee_trajet());
		listValeurs.add(((Trajet) obj).getcp_adr_arrivee_trajet());
		listValeurs.add(((Trajet) obj).getnbr_max_places());
		listValeurs.add(((Trajet) obj).getPseudo());
		listValeurs.add(((Trajet) obj).getadministrateur());
		
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
		Trajet newTrajet = (Trajet) newObj;
		Trajet oldTrajet = (Trajet) oldObj;
		
		String requete = "update Trajet "
				       + "set date_time_trajet = '?', "
				       + "rue_adr_depart_trajet = '?', "
				       + "num_rue_adr_depart_trajet = '?', "
				       + "cp_adr_depart_trajet = '?' "
					   + "rue_adr_arrivee_trajet = '?', "
					   + "num_rue_adr_arrivee_trajet = '?', "
					   + "cp_adr_arrivee_trajet = '?', "
					   + "nbr_max_places = '?', "
					   + "Pseudo = '?', "
					   + "Administrateur = '?', "
				       + "where id_Trajet = ?;";
		
		this.setRequete(requete);
		
		ArrayList<String> listValeurs = new ArrayList<String>();
		
		listValeurs.add(newTrajet.getdate_time_trajet().toUpperCase());
		listValeurs.add(newTrajet.getrue_adr_depart_trajet().toUpperCase());
		listValeurs.add(newTrajet.getnum_rue_adr_depart_trajet().toUpperCase());
		listValeurs.add(newTrajet.getcp_adr_depart_trajet().toUpperCase());
		listValeurs.add(newTrajet.getrue_adr_arrivee_trajet().toUpperCase());
		listValeurs.add(newTrajet.getnum_rue_adr_arrivee_trajet().toUpperCase());
		listValeurs.add(newTrajet.getcp_adr_arrivee_trajet().toUpperCase());
		listValeurs.add(newTrajet.getnbr_max_places().toUpperCase());
		listValeurs.add(newTrajet.getPseudo().toUpperCase());
		listValeurs.add(newTrajet.getadministrateur().toUpperCase());
		listValeurs.add(oldTrajet.getid_trajet()+"".toUpperCase());


		
		this.setListValeurs(listValeurs);
		
		this.prepare();
		
		return this.execute() != null;
	}

	@Override
	public Resultat select(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resultat selectAll(Object obj) {
		// TODO Auto-generated method stub
		String requete = "select * from Trajet;";

		this.setRequete(requete);
		
		this.prepare();
		
		return this.execute();
	}

	@Override
	public Object selectAllInfo(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

}