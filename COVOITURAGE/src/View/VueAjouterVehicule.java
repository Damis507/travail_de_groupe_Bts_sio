package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import Controler.Controleur;
import Model.Classes.Autres.DateChecker;
import Model.Classes.Autres.XML_POJO;
import Model.Classes.Metiers.Resultat;
import Model.Classes.Metiers.Vehicule;
import View.Abstracts.AbstractVuePersonnalisable;

public class VueAjouterVehicule extends AbstractVuePersonnalisable {
	
	private JLabel jlb_immatriculation_vehicule;
	private JLabel jlb_date_mise_circulation;
	private JLabel jlb_capacite_transport;
	private JLabel jlb_nombre_places;
	private JLabel jlb_hayon;
	private JLabel jlb_couchette;
	private JLabel jlb_agence;
	private JLabel jlb_permis;
	
	private JTextField jtf_immatriculation_vehicule;
	private JTextField jtf_date_mise_circulation;
	private JTextField jtf_capacite_transport;
	private JComboBox jcb_nombre_places;
	private JCheckBox jchb_hayon;
	private JCheckBox jchb_couchette;
	private JComboBox jcb_agence;
	private JComboBox jcb_permis;
	
	private JButton btn_entrer;
	private JButton btn_cancel;
	
	private JPanel jp;
	
	private Controleur controler;

	public VueAjouterVehicule(JFrame parent, 
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
		if (this.jtf_immatriculation_vehicule.getText().equals("")
				|| this.jtf_date_mise_circulation.getText().equals("")
				|| this.jtf_capacite_transport.getText().equals(""))
		{
			(new JOptionPane()).showMessageDialog(null, 
					  "Toutes les entrées doivent être renseignées", 
					  "Information", 
					  JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			if (!DateChecker.isValid(this.jtf_date_mise_circulation.getText()))
			{
				(new JOptionPane()).showMessageDialog(null, 
						  "Le format (dd/MM/yyyy) n'a pas été respecté "
						  + "ou la date n'est pas correcte (ex.: 31/11/2022).", 
						  "Information", 
						  JOptionPane.INFORMATION_MESSAGE);
				
				this.jtf_date_mise_circulation.setText("");
			}
			else
			{
				Resultat r = this.controler.insertinto(new Vehicule(this.jtf_immatriculation_vehicule.getText(),
																	this.jtf_date_mise_circulation.getText(),
																	Integer.parseInt(this.jtf_capacite_transport.getText()),
																	Integer.parseInt(this.jcb_nombre_places.getSelectedItem().toString()),
																	this.jchb_hayon.isSelected(),
																	this.jchb_couchette.isSelected(),
																	this.jcb_agence.getSelectedItem().toString(),
																	this.jcb_permis.getSelectedItem().toString()));
				
				if (r.isSucces())
				{
					
					
					(new JOptionPane()).showMessageDialog(null, 
							  "Le vehicule a été ajouté avec succès", 
							  "Information", 
							  JOptionPane.INFORMATION_MESSAGE);
					
					this.jtf_immatriculation_vehicule.setText("");
					this.jtf_date_mise_circulation.setText("");
					this.jtf_capacite_transport.setText("");
					this.jcb_nombre_places.setSelectedIndex(0);
					this.jchb_hayon.setSelected(false);
					this.jchb_couchette.setSelected(false);
					this.jcb_agence.setSelectedIndex(0);
					this.jcb_permis.setSelectedIndex(0);
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
		
		this.jlb_immatriculation_vehicule = new JLabel("No. immat : ");
		this.jlb_date_mise_circulation = new JLabel("Mise en circ : ");
		this.jlb_capacite_transport = new JLabel("Capacité de transp : ");
		this.jlb_nombre_places = new JLabel("Nombre de places : ");
		this.jlb_hayon = new JLabel("Hayon : ");
		this.jlb_couchette = new JLabel("Couchette : ");
		this.jlb_agence = new JLabel("Agence : ");
		this.jlb_permis = new JLabel("Permis : ");
		
		String nb_places[] = {"1", "2", "3", "4","5"};
		
		this.jtf_immatriculation_vehicule = new JTextField();
		this.jtf_immatriculation_vehicule.setPreferredSize(new Dimension(150, 30));
		this.jtf_date_mise_circulation = new JTextField();
		this.jtf_date_mise_circulation.setPreferredSize(new Dimension(150, 30));
		this.jtf_capacite_transport = new JTextField();
		this.jtf_capacite_transport.setPreferredSize(new Dimension(150, 30));
		this.jcb_nombre_places = new JComboBox(nb_places);
		this.jchb_hayon = new JCheckBox();
		this.jchb_couchette = new JCheckBox();
		
		try {
			this.jcb_agence = new JComboBox(XML_POJO.getListValues("src/Model/XML/agence.xml", 
																   "nom_agence"));
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			this.jcb_permis = new JComboBox(XML_POJO.getListValues("src/Model/XML/permis.xml", 
																   "libelle_permis"));
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.btn_entrer = new JButton("Enregistrer");
		this.btn_cancel = new JButton("Annuler");
		
		this.btn_cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) 
			{
				dispose(); 
			}
		});
		
		this.btn_entrer.addActionListener(this);
		
		this.setButtonSubmit(btn_entrer);
		
		this.jp.add(this.jlb_immatriculation_vehicule);
		this.jp.add(this.jtf_immatriculation_vehicule);
		this.jp.add(this.jlb_date_mise_circulation);
		this.jp.add(this.jtf_date_mise_circulation);
		this.jp.add(this.jlb_nombre_places);
		this.jp.add(this.jcb_nombre_places);
		this.jp.add(this.jlb_capacite_transport);
		this.jp.add(this.jtf_capacite_transport);
		this.jp.add(this.jlb_agence);
		this.jp.add(this.jcb_agence);
		this.jp.add(this.jlb_permis);
		this.jp.add(this.jcb_permis);
		this.jp.add(this.jlb_hayon);
		this.jp.add(this.jchb_hayon);
		this.jp.add(this.jlb_couchette);
		this.jp.add(this.jchb_couchette);
		this.jp.add(this.btn_cancel);
		this.jp.add(this.btn_entrer);
		
		this.getContentPane().add(this.jp, BorderLayout.CENTER);
	}

}
