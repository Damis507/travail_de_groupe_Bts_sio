package Model.Classes.Autres;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateChecker {
	public static boolean isValid(String strdate) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		format.setLenient(false);
		try
		{
			/*
			 * parse recoit une chaine de caractere
			 * et retourne une Date - DateFormat
			 */
			format.parse(strdate); 
		}
		// Date invalide
		catch (ParseException e)
		{
			return false;
		}
		// Renvoie true si la date est valide
		return true;
    }
}
