package Model.Classes.Metiers;

public class Vehicule {
	
	private String immatriculation_vehicule;
	private String date_mise_circulation; 
	private int capacite_transport; 
	private int nombre_places; 
	private boolean hayon; 
	private boolean couchette;
	private String id_agence; 
	private String id_permis;
	
	public Vehicule(String immatriculation_vehicule, 
			        String date_mise_circulation, 
			        int capacite_transport,
			        int nombre_places, 
			        boolean hayon, 
			        boolean couchette, 
			        String id_agence, 
			        String id_permis) {
		super();
		this.immatriculation_vehicule = immatriculation_vehicule;
		this.date_mise_circulation = date_mise_circulation;
		this.capacite_transport = capacite_transport;
		this.nombre_places = nombre_places;
		this.hayon = hayon;
		this.couchette = couchette;
		this.id_agence = id_agence;
		this.id_permis = id_permis;
	}
	
	public Vehicule() {
		super();
		this.immatriculation_vehicule = "";
		this.date_mise_circulation = "01/01/2022";
		this.capacite_transport = 0;
		this.nombre_places = 0;
		this.hayon = false;
		this.couchette = false;
		this.id_agence = "";
		this.id_permis = "";
	}

	public String getImmatriculation_vehicule() {
		return immatriculation_vehicule;
	}

	public void setImmatriculation_vehicule(String immatriculation_vehicule) {
		this.immatriculation_vehicule = immatriculation_vehicule;
	}

	public String getDate_mise_circulation() {
		return date_mise_circulation;
	}

	public void setDate_mise_circulation(String date_mise_circulation) {
		this.date_mise_circulation = date_mise_circulation;
	}

	public int getCapacite_transport() {
		return capacite_transport;
	}

	public void setCapacite_transport(int capacite_transport) {
		this.capacite_transport = capacite_transport;
	}

	public int getNombre_places() {
		return nombre_places;
	}

	public void setNombre_places(int nombre_places) {
		this.nombre_places = nombre_places;
	}

	public boolean isHayon() {
		return hayon;
	}

	public void setHayon(boolean hayon) {
		this.hayon = hayon;
	}

	public boolean isCouchette() {
		return couchette;
	}

	public void setCouchette(boolean couchette) {
		this.couchette = couchette;
	}

	public String getId_agence() {
		return id_agence;
	}

	public void setId_agence(String id_agence) {
		this.id_agence = id_agence;
	}

	public String getId_permis() {
		return id_permis;
	}

	public void setId_permis(String id_permis) {
		this.id_permis = id_permis;
	}

	@Override
	public String toString() {
		return "Vehicule [immatriculation_vehicule=" + immatriculation_vehicule + ", date_mise_circulation="
				+ date_mise_circulation + ", capacite_transport=" + capacite_transport + ", nombre_places="
				+ nombre_places + ", hayon=" + hayon + ", couchette=" + couchette + ", id_agence=" + id_agence
				+ ", id_permis=" + id_permis + "]";
	}
	
}
