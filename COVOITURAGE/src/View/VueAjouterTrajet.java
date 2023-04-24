package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controler.Controleur;
import Model.Classes.Autres.DateChecker;
import Model.Classes.Metiers.Resultat;
import Model.Classes.Metiers.Trajet;
import View.Abstracts.AbstractVuePersonnalisable;

public class VueAjouterTrajet extends AbstractVuePersonnalisable {
	
	private JLabel jlb_id_trajet;
	private JLabel jlb_id_employe;
	private JLabel jlb_date_time_trajet;
	private JLabel jlb_rue_adr_depart_trajet;
	private JLabel jlb_num_rue_adr_depart_trajet;
	private JLabel jlb_cp_adr_depart_trajet;
	private JLabel jlb_rue_adr_arrivee_trajet;
	private JLabel jlb_num_rue_adr_arrivee_trajet;
	private JLabel jlb_cp_adr_arrivee_trajet;
	private JLabel jlb_nbr_max_places;
	private JLabel jlb_pseudo;
	private JLabel jlb_administrateur;

	private JTextField jtf_id_trajet;
	private JTextField jtf_id_employe;
	private JTextField jtf_date_time_trajet;
	private JTextField jtf_rue_adr_depart_trajet;
	private JTextField jtf_num_rue_adr_depart_trajet;
	private JTextField jtf_cp_adr_depart_trajet;
	private JTextField jtf_rue_adr_arrivee_trajet;
	private JTextField jtf_num_adr_arrivee_trajet;
	private JTextField jtf_cp_adr_arrivee_trajet;
	private JComboBox  jcb_nbr_max_places;
	private JTextField jtf_pseudo;
	private JCheckBox  jchb_administrateur;
	
	private JButton btn_entrer;
	private JButton btn_cancel;
	
	private JPanel jp;
	
	private Controleur controler;
	private JTextField jtf_num_adr_depart_trajet;
	private JLabel jlb_num_adr_depart_trajet;
	private JLabel jlb_num_adr_arrivee_trajet;

	public VueAjouterTrajet(JFrame parent, 
							  Dimension dimension, 
							  String title, 
							  boolean modal,
							  Controleur controler) {
		super(parent, dimension, title, modal);
		// TODO Auto-generated constructor stub
		
		this.controler = controler;

		this.initComponents();
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (this.jtf_id_trajet.getText().equals("")
				|| this.jtf_date_time_trajet.getText().equals(""))
		{
			(new JOptionPane()).showMessageDialog(null, 
					  "Toutes les entrées doivent être renseignées", 
					  "Information", 
					  JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			if (!DateChecker.isValid(this.jtf_date_time_trajet.getText()))
			{
				(new JOptionPane()).showMessageDialog(null, 
						  "Le format (dd/MM/yyyy) n'a pas été respecté "
						  + "ou la date n'est pas correcte (ex.: 31/11/2022).", 
						  "Information", 
						  JOptionPane.INFORMATION_MESSAGE);
				
				this.jtf_date_time_trajet.setText("");
			}
			else
			{
				Resultat r = this.controler.insertinto(new Trajet(this.jtf_id_trajet.getText(),
																	this.jtf_date_time_trajet.getText(),															
																	this.jtf_rue_adr_depart_trajet.getText(),
																	this.jtf_num_adr_depart_trajet.getText(),	
																	this.jtf_cp_adr_depart_trajet.getText(),
																	this.jtf_rue_adr_arrivee_trajet.getText(),
																	this.jtf_num_adr_arrivee_trajet.getText(),	
																	this.jtf_cp_adr_arrivee_trajet.getText(),
																	this.jcb_nbr_max_places.getToolkit(),
																	this.jtf_pseudo.getText(),
																	this.jchb_administrateur.getToolkit()));
				
				if (r.isSucces())
				{
					
					
					(new JOptionPane()).showMessageDialog(null, 
							  "Le Offre a été ajouté avec succès", 
							  "Information", 
							  JOptionPane.INFORMATION_MESSAGE);
					
					this.jtf_id_trajet.setText("");
					this.jtf_id_employe.setText("");
					this.jtf_date_time_trajet.setText("");
					this.jtf_rue_adr_depart_trajet.setText("");
					this.jtf_num_rue_adr_depart_trajet.setText("");
					this.jtf_cp_adr_depart_trajet.setText("");
					this.jtf_rue_adr_arrivee_trajet.setText("");
					this.jtf_num_adr_arrivee_trajet.setText("");
					this.jtf_cp_adr_arrivee_trajet.setText("");
					this.jcb_nbr_max_places.getSelectedItem().toString();
					this.jtf_pseudo.setText("");
					this.jchb_administrateur.setSelected(false);
				}

				else
					(new JOptionPane()).showMessageDialog(null, 
							  r.getMessage(), 
							  "Erreur", 
							  JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void initComponents() {
		// TODO Auto-generated method stub
		this.jp = new JPanel();
		
		this.jlb_id_trajet = new JLabel(" id trajet : ");
		this.jlb_id_employe = new JLabel(" id employe : ");
		this.jlb_date_time_trajet = new JLabel(" duré trajet : "); 
		this.jlb_rue_adr_depart_trajet = new JLabel(" rue depart : "); 
		this.jlb_num_adr_depart_trajet = new JLabel("num depart : "); 
		this.jlb_cp_adr_depart_trajet = new JLabel(" code postal depart: "); 
		this.jlb_rue_adr_arrivee_trajet = new JLabel(" rue arrivee : "); 
		this.jlb_num_adr_arrivee_trajet = new JLabel(" num arrivee : "); 
		this.jlb_cp_adr_arrivee_trajet = new JLabel(" code postal arrivee: "); 
		this.jlb_nbr_max_places = new JLabel(" nombre max de places: ");
		this.jlb_pseudo = new JLabel(" pseudo: ");
		this.jlb_administrateur = new JLabel(" administrateur : ");
		
		String nb_places[] = {"1", "2", "3", "4"};
		
		this.jtf_id_trajet = new JTextField();
		this.jtf_id_trajet.setPreferredSize(new Dimension(150, 30));
		this.jtf_id_employe = new JTextField();
		this.jtf_id_employe.setPreferredSize(new Dimension(150, 30));
		this.jtf_date_time_trajet= new JTextField();
		this.jtf_date_time_trajet.setPreferredSize(new Dimension(150, 30));
		this.jtf_rue_adr_depart_trajet = new JTextField();
		this.jtf_rue_adr_depart_trajet.setPreferredSize(new Dimension(150, 30));
		this.jtf_num_adr_depart_trajet = new JTextField();
		this.jtf_num_adr_depart_trajet.setPreferredSize(new Dimension(150, 30));
		this.jtf_cp_adr_depart_trajet = new JTextField();
		this.jtf_cp_adr_depart_trajet.setPreferredSize(new Dimension(150, 30));
		this.jtf_rue_adr_arrivee_trajet = new JTextField();
		this.jtf_rue_adr_arrivee_trajet.setPreferredSize(new Dimension(150, 30));
		this.jtf_num_adr_arrivee_trajet = new JTextField();
		this.jtf_num_adr_arrivee_trajet.setPreferredSize(new Dimension(150, 30));
		this.jtf_cp_adr_arrivee_trajet = new JTextField();
		this.jtf_cp_adr_arrivee_trajet.setPreferredSize(new Dimension(150, 30));
		this.jcb_nbr_max_places = new JComboBox(nb_places);
		this.jtf_cp_adr_arrivee_trajet = new JTextField();
		this.jtf_cp_adr_arrivee_trajet.setPreferredSize(new Dimension(150, 30));
		this.jchb_administrateur = new JCheckBox();
		
		this.btn_entrer.addActionListener(this);
		
		this.setButtonSubmit(btn_entrer);
		
		this.jp.add(this.jlb_id_trajet);
		this.jp.add(this.jtf_id_trajet);
		this.jp.add(this.jlb_id_employe);
		this.jp.add(this.jtf_id_employe);
		this.jp.add(this.jlb_date_time_trajet);
		this.jp.add(this.jtf_date_time_trajet);
		this.jp.add(this.jlb_rue_adr_depart_trajet);
		this.jp.add(this.jtf_rue_adr_depart_trajet);
		this.jp.add(this.jlb_num_adr_depart_trajet);
		this.jp.add(this.jtf_num_adr_depart_trajet);
		this.jp.add(this.jlb_cp_adr_depart_trajet);
		this.jp.add(this.jtf_cp_adr_depart_trajet);
		this.jp.add(this.jlb_rue_adr_arrivee_trajet);
		this.jp.add(this.jtf_rue_adr_arrivee_trajet);
		this.jp.add(this.jlb_num_adr_arrivee_trajet);
		this.jp.add(this.jtf_num_adr_arrivee_trajet);
		this.jp.add(this.jlb_cp_adr_arrivee_trajet);
		this.jp.add(this.jtf_cp_adr_arrivee_trajet);
		this.jp.add(this.jlb_nbr_max_places);
		this.jp.add(this.jcb_nbr_max_places);
		this.jp.add(this.jlb_pseudo);
		this.jp.add(this.jtf_pseudo);
		this.jp.add(this.jchb_administrateur);
		this.jp.add(this.jchb_administrateur);
		this.jp.add(this.btn_cancel);
		this.jp.add(this.btn_entrer);
		
		this.getContentPane().add(this.jp, BorderLayout.CENTER);
	}

}