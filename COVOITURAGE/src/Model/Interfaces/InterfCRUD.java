package Model.Interfaces;

import java.util.ArrayList;

import Model.Classes.Metiers.Resultat;

public interface InterfCRUD {
	
	/*
	 * M E T H O D E S   A B S T R A C T S
	 * 
	 * Create : insert into
	 * Read : select 
	 * Update : update
	 * Delete : delete
	 */
	
	public abstract Resultat insertinto(Object obj);
	public abstract boolean deletefrom(Object obj);
	public abstract boolean update(Object newObj, Object oldObj);
	public abstract Resultat select(Object obj);
	public abstract Resultat selectAll(Object obj);
	public abstract Object selectAllInfo(Object obj);

}
