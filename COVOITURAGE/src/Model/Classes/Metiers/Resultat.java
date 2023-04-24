package Model.Classes.Metiers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Resultat {
	
	private String message;
	private boolean succes;
	private ResultSet reponse;
	
	public Resultat(String message, boolean succes, ResultSet reponse) {
		super();
		this.message = message;
		this.succes = succes;
		this.reponse = reponse;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSucces() {
		return succes;
	}

	public void setSucces(boolean succes) {
		this.succes = succes;
	}

	public ResultSet getReponse() {
		return reponse;
	}
	
}
