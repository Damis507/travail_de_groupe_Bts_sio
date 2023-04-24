package Model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Classes.Abstracts.AbstractCRUD;
import Model.Classes.Metiers.Resultat;
import Model.Classes.Metiers.Employe;
import Model.Classes.Systeme.ConnectionDB;
import Model.Interfaces.InterfOperationsAdmin;

public class EmployeDAO extends AbstractCRUD implements InterfOperationsAdmin {

	public EmployeDAO(ConnectionDB conn_db, String requete, ArrayList<String> listValeurs) {
		super(conn_db, requete, listValeurs);
		// TODO Auto-generated constructor stub
	}

	public EmployeDAO(ConnectionDB conn_db) {
		super(conn_db);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Resultat insertinto(Object obj) {
		// TODO Auto-generated method stub
		String requete = "insert into employe "
				       + "values ('?', '?', '?', '?', '?', '?')";
		
		this.setRequete(requete);
		
		ArrayList<String> listValeurs = new ArrayList<String>();
		listValeurs.add(((Employe) obj).getnum_secu_employe());
		listValeurs.add(((Employe) obj).getNom());
		listValeurs.add(((Employe) obj).getPrenom());
		listValeurs.add(((Employe) obj).getTel_cel());
		listValeurs.add(((Employe) obj).getPseudo());
		listValeurs.add(((Employe) obj).getMdp());
		
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
		Employe newemploye = (Employe) newObj;
		Employe oldemploye = (Employe) oldObj;
		
		String requete = "update employe set ";
		
		if (!newemploye.getnum_secu_employe().equals(""))
			requete += "id_employe = '" + newemploye.getnum_secu_employe() + "', ";
		
		if (!newemploye.getNom().equals(""))
			requete += "nom = '" + newemploye.getNom() + "', ";
		
		if (!newemploye.getPrenom().equals(""))
			requete += "prenom = '" + newemploye.getPrenom() + "', ";
		
		if (!newemploye.getTel_cel().equals(""))
			requete += "tel_cel = '" + newemploye.getTel_cel() + "', ";
		
		if (!newemploye.getPseudo().equals(""))
			requete += "pseudo = '" + newemploye.getPseudo() + "', ";
		
		if (!newemploye.getMdp().equals(""))
			requete += "mot_de_passe = '" + newemploye.getMdp() + "', ";
		
		if (!newemploye.getnum_secu_employe().equals(""))
			requete += "where id_employe = '" + newemploye.getnum_secu_employe() + "'";
		else
			requete += "where pseudo = '" + newemploye.getPseudo() + "'";
		
		requete = requete.replaceFirst(", w", " w");
		
		this.setRequete(requete);
		
		this.prepare();
		
		this.execute();
		
		return true;
	}

	@Override
	public Resultat select(Object obj) {
		// TODO Auto-generated method stub
		String requete = "select * from employe";
		
		this.setRequete(requete);
		
		this.prepare();
		
		return this.execute();
	}

	@Override
	public Resultat setDroits(List LDroit, Employe employe) {
		// TODO Auto-generated method stub
		/*
		 * Recuperer les droits actuels de l'employe
		 */
		List<String> ldroits_anciens_employe = this.getDroits(employe);
		int index;
		String requete;
		
		if (ldroits_anciens_employe != null)
		{
			for (int i = 0; i < ldroits_anciens_employe.size(); i++)
			{
				/*
				 * Si l'i-eme droit ancien n'est pas dans la nouvelle
				 * selection de droits, alors il faut le supprimer de la BD.
				 */
				index = LDroit.indexOf(ldroits_anciens_employe.get(i));
				if (index == -1)
				{
					requete = "delete from autorisation "
							+ "where id_option = "
							+ "(select id_option "
							 + "from options "
							 + "where libelle_option = '" + ldroits_anciens_employe.get(i).toString() + "') "
							+ "and "
							+ "id_employe = "
							+ "(select id_employe "
											  + "from employe "
											  + "where pseudo = '" +  employe.getPseudo() + "')";
					
					this.setRequete(requete);
					
					this.prepare();
					
					this.execute();
				}
				/*
				 * S'il est dans la liste nouvelle selection de droits
				 * alors on le supprime
				 */
				else
				{
					LDroit.remove(index);
				}
			}
		}
			
		/*
		 * 
		 * Ajouter que les nouveaux droits
		 * 
		 * Nouveaux		Ancien(BD)
		 * 2x			7x
		 * 1x			2
		 * 3s			1
		 * 4s			5x
		 * 
		 * A la fin les seuls droits qui resterons seront :
		 * 2, 1 (les anciens qui sont deja dans la BD) et 3 et 4 (les nouveaux)
		 * 
		 * 
		 */
		for (int i = 0; i < LDroit.size(); i++)
		{
			requete = "insert into autorisation values "
					+ "((select id_employe "
					  + "from employe "
					  + "where pseudo = '" + employe.getPseudo() + "'), "
					 + "(select id_option "
					  + "from options "
					  + "where libelle_option = '" + LDroit.get(i) + "'))";
			
			this.setRequete(requete);
			
			this.prepare();
			
			this.execute();
		}
		
		return new Resultat("", true, null);
	}

	@Override
	public List<String> getDroits(Employe employe) {
		// TODO Auto-generated method stub
		Resultat reponse = this.getDroits(employe.getPseudo());
		
		List<String> ldroits = new ArrayList<String>();
		
		ResultSet droits = reponse.getReponse();
		
		try {
			while (droits.next())
			{
				ldroits.add(droits.getString("libelle_option"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ldroits;
	}
	
	private Resultat getDroits(String pseudo) {
		// TODO Auto-generated method stub
		String requete = "select libelle_option "
				       + "from employe u "
				       + "inner join autorisation a on a.id_employe = u.id_employe "
				       + "inner join options o on o.id_option = a.id_option "
				       + "where pseudo = '" + pseudo + "'";
		
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
