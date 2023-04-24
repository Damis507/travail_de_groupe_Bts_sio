package Model.Classes.Metiers;

import java.awt.Component;
import java.awt.Toolkit;

import javax.swing.JComboBox;

public class Trajet {
       
    private String id_trajet;
	private String id_employe;
	private String date_time_trajet;
	private String rue_adr_depart_trajet; 
	private String num_rue_adr_depart_trajet;
	private String cp_adr_depart_trajet;
	private String rue_adr_arrivee_trajet;
	private String num_rue_adr_arrivee_trajet;
	private String cp_adr_arrivee_trajet;
	private String nbr_max_places;
	private String pseudo;
	private String administrateur;
    
        public Trajet(String string, String string2, String string3, String string4, Component jtf_cp_adr_depart_trajet)
        {
            super();
            this.id_trajet = "";
            this.id_employe = "";
            this.date_time_trajet = "";
            this.rue_adr_depart_trajet = "";
            this.num_rue_adr_depart_trajet = "";
            this.cp_adr_depart_trajet = "";
            this.rue_adr_arrivee_trajet = "";
            this.num_rue_adr_arrivee_trajet = "";
            this.cp_adr_arrivee_trajet = "";
            this.nbr_max_places = "";
            this.pseudo = "";
            this.administrateur = "";
        }
        
        public Trajet(String id_trajet,
                           String id_employe, 
                           String date_time_trajet, 
                           String rue_adr_depart_trajet, 
                           String num_rue_adr_depart_trajet,
                           String cp_adr_depart_trajet,
                           String rue_adr_arrivee_trajet,
                           String num_rue_adr_arrivee_trajet,
                           String cp_adr_arrivee_trajet,
                           String nbr_max_places,
                           String pseudo,
                           String administrateur) {


                            super();
                            this.id_trajet = "id_trajet";
                            this.id_employe = "num_secu_employe";
                            this.date_time_trajet = "date/time_trajet";
                            this.rue_adr_depart_trajet = "rue_adr_depart_trajet";
                            this.num_rue_adr_depart_trajet = "num_rue_adr_depart_trajet";
                            this.cp_adr_depart_trajet = "cp_adr_depart_trajet";
                            this.rue_adr_arrivee_trajet = "rue_adr_arrivee_trajet";
                            this.num_rue_adr_arrivee_trajet = "num_rue_adr_arrivee_trajet";
                            this.cp_adr_arrivee_trajet = "cp_adr_arrivee_trajet";
                            this.nbr_max_places = "nbr_max_places";
                            this.pseudo = "pseudo";
                            this.administrateur = "administrateur";
        }
        
        public Trajet(String id_trajet, 
                              String id_employe, String string, String string2, String string3, String string4, String string5, String string6, Toolkit toolkit, String string7, Toolkit toolkit2) {
            super();
            this.id_trajet = "";
            this.id_employe = "";
            this.date_time_trajet = "";
            this.rue_adr_depart_trajet = "";
            this.num_rue_adr_depart_trajet = "";
            this.rue_adr_arrivee_trajet = "";
            this.num_rue_adr_arrivee_trajet = "";
            this.cp_adr_arrivee_trajet = "";
            this.nbr_max_places = "";
            this.pseudo = "";
            this.administrateur = "";
        }
    
        public Trajet(int i, String string, String string2, String string3, String string4, String string5, String string6, String string7, JComboBox jcb_nbr_max_places) {
        }

        public Trajet(int parseInt, String text, String text2, String text3, String text4, String text5, String text6,
                String text7, Toolkit toolkit, String text8, boolean selected) {
        }

        public Trajet() {
        }

        public String getid_employe() {
            return id_employe;
        }
    
        public void setid_employe(String id_employe) {
            this.id_employe = id_employe;
        }
    
        public String getid_trajet() {
            return id_trajet;
        }
    
        public void setid_trajet(String id_trajet) {
            this.id_trajet = id_trajet;
        }
    
        public String getPseudo() {
            return pseudo;
        }
    
        public void setPseudo(String pseudo) {
            this.pseudo = pseudo.toUpperCase();
        }
    
        public String getdate_time_trajet() {
            return date_time_trajet;
        }
    
        public void setdate_time_trajet(String integer) {
            this.date_time_trajet = integer;
        }
    
        public String getrue_adr_depart_trajet() {
            return rue_adr_depart_trajet;
        }
    
        public void setrue_adr_depart_trajet(String rue_adr_depart_trajet) {
            this.rue_adr_depart_trajet = rue_adr_depart_trajet;
        }
    
        public String getnum_rue_adr_depart_trajet() {
            return num_rue_adr_depart_trajet;
        }
    
        public void setnum_rue_adr_depart_trajet(String num_rue_adr_depart_trajet) {
            this.num_rue_adr_depart_trajet = num_rue_adr_depart_trajet;
        }
    
        public String getcp_adr_depart_trajet() {
            return cp_adr_depart_trajet;
        }
    
        public void setcp_adr_depart_trajet(String cp_adr_depart_trajet) {
            this.cp_adr_depart_trajet = cp_adr_depart_trajet;
        }

        public String getrue_adr_arrivee_trajet() {
            return rue_adr_arrivee_trajet;
        }
    
        public void setrue_adr_arrivee_trajet(String rue_adr_arrivee_trajet) {
            this.rue_adr_arrivee_trajet = rue_adr_arrivee_trajet;
        }
    
        public String getnum_rue_adr_arrivee_trajet() {
            return num_rue_adr_arrivee_trajet;
        }
    
        public void setnum_rue_adr_arrivee_trajet(String num_rue_adr_arrivee_trajet) {
            this.num_rue_adr_arrivee_trajet = num_rue_adr_arrivee_trajet;
        }
    
        public String getcp_adr_arrivee_trajet() {
            return cp_adr_arrivee_trajet;
        }
    
        public void setcp_adr_arrivee_trajet(String cp_adr_arrivee_trajet) {
            this.cp_adr_arrivee_trajet = cp_adr_arrivee_trajet;
        }

        public String getnbr_max_places() {
            return nbr_max_places;
        }
    
        public void setnbr_max_places(String nbr_max_places) {
            this.nbr_max_places = nbr_max_places;
        }

        public String getadministrateur() {
            return administrateur;
        }
    
        public void setadministrateur(String administrateur) {
            this.administrateur = administrateur;
        }

         @Override
        public String toString() {
            return "Utilisateur [id_trajet = " + id_trajet + ",  date_time_trajet = " +  date_time_trajet + ", rue_adr_depart_trajet = " + rue_adr_depart_trajet + ", num_rue_adr_depart_trajet = " + num_rue_adr_depart_trajet + ", cp_adr_depart_trajet = " + cp_adr_depart_trajet + ",  rue_adr_arrivee_trajet = " +  rue_adr_arrivee_trajet + ", num_rue_adr_arrivee_trajet = " +  num_rue_adr_arrivee_trajet + ", cp_adr_arrivee_trajet = " +  cp_adr_arrivee_trajet + ", nbr_max_places = " +  nbr_max_places + ", pseudo = " +  pseudo + ", administrateur = " +  administrateur + ",]";
        }

        public Object getText() {
            return null;
        }

        public Object getSelectedItem() {
            return null;
        }

    }
    