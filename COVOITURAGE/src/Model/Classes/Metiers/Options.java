package Model.Classes.Metiers;

public class Options {
	
	private int id_option;
	private String libelle_option;
	
	public Options()
	{
		this.id_option = 0;
		this.libelle_option = "";
	}
	
	public Options(int id_option, String libelle_option) {
		super();
		this.id_option = id_option;
		this.libelle_option = libelle_option;
	}

	public int getId_option() {
		return id_option;
	}

	public void setId_option(int id_option) {
		this.id_option = id_option;
	}

	public String getLibelle_option() {
		return libelle_option;
	}

	public void setLibelle_option(String libelle_option) {
		this.libelle_option = libelle_option;
	}

	@Override
	public String toString() {
		return "Options [id_option=" + id_option + ", libelle_option=" + libelle_option + "]";
	}
}
