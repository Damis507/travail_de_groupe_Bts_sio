package View;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controler.Controleur;
import Model.Classes.Autres.MonDefaultTableModel;
import Model.Classes.Metiers.Resultat;
import Model.Classes.Metiers.Trajet;
import View.Abstracts.AbstractVuePersonnalisable;

public class VueAffichageTrajet extends AbstractVuePersonnalisable
							    implements ItemListener,
							              ListSelectionListener {
	
	private JLabel jlb_id_trajet;
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
	private JTextField jtf_date_time_trajet;
	private JTextField jtf_rue_adr_depart_trajet;
	private JTextField jtf_num_rue_adr_depart_trajet;
	private JTextField jtf_cp_adr_depart_trajet;
	private JTextField jtf_rue_adr_arrivee_trajet;
	private JTextField jtf_num_rue_adr_arrivee_trajet;
	private JTextField jtf_cp_adr_arrivee_trajet;
	private JComboBox jcb_nbr_max_places;
	private JTextField jtf_pseudo;
	private JCheckBox jchb_administrateur;
	
	private JButton btnEnvoyer;
	private JButton btnCancel;
	
	private JTable jt_Trajet;
	
	private JPanel contenaire;
	
	private Trajet TrajetAncien;
	
	private ListSelectionModel listModel;
	
	private MonDefaultTableModel defaultTableModel;
	
	private Controleur controler;

	public VueAffichageTrajet(JFrame parent, 
						     String title, 
						     boolean modal, 
						     Controleur controler) {
		super(parent, title, modal);
		// TODO Auto-generated constructor stub
		init(controler);
	}

	public VueAffichageTrajet(JFrame parent, 
					         Dimension dimension, 
					         String title, 
					         boolean modal, 
					         Controleur controler) {
		super(parent, dimension, title, modal);
		// TODO Auto-generated constructor stub
		init(controler);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (
		!this.jtf_id_trajet.getText().equals("") &&
		!this.jtf_date_time_trajet.getText().equals("") &&
		!this.jtf_rue_adr_depart_trajet.getText().equals("") &&
		!this.jtf_num_rue_adr_depart_trajet.getText().equals("") &&
		!this.jtf_cp_adr_depart_trajet.getText().equals("") &&
		!this.jtf_rue_adr_arrivee_trajet.getText().equals("") &&
		!this.jtf_num_rue_adr_arrivee_trajet.getText().equals("") &&
		!this.jtf_cp_adr_arrivee_trajet.getText().equals("") &&
		!(this.jcb_nbr_max_places).getToolkit().equals("") &&
		!this.jtf_pseudo.getText().equals("") &&
		!this.jchb_administrateur.getVerifyInputWhenFocusTarget()
		);

		{
			Resultat resultatUpdate = this.controler.update(new Trajet(Integer.parseInt(this.jtf_id_trajet.getText()),
																		 this.jtf_date_time_trajet.getText(),
																		 this.jtf_rue_adr_depart_trajet.getText(),
																		 this.jtf_num_rue_adr_depart_trajet.getText(),
																		 this.jtf_cp_adr_depart_trajet.getText(),
																		 this.jtf_rue_adr_arrivee_trajet.getText(),
																		 this.jtf_num_rue_adr_arrivee_trajet.getText(),
																		 this.jtf_cp_adr_arrivee_trajet.getText(),
																		 this.jcb_nbr_max_places).getSelectedItem().toString(),
																		 this.jtf_pseudo.getText(),
																		 this.jchb_administrateur.isSelected(),
													         this.TrajetAncien);
			
			if (resultatUpdate.isSucces())
			{
				
				this.jtf_id_trajet.setText("");
				this.jtf_date_time_trajet.setText("");
				this.jtf_rue_adr_depart_trajet.setText("");
				this.jtf_cp_adr_depart_trajet.setText("");
				this.jtf_rue_adr_arrivee_trajet.setText("");
				this.jtf_num_rue_adr_arrivee_trajet.setText("");
				this.jtf_cp_adr_arrivee_trajet.setText("");
				this.jcb_nbr_max_places.setSelectedIndex(0);
				this.jtf_pseudo.setText("");
				this.jchb_administrateur.setSelected(false);




 				
 				this.btnEnvoyer.setEnabled(false);
 				
 				remplirTableau();
 						
 				JOptionPane.showMessageDialog(null, 
													  "Les informations ont été modifiées correctement", 
													  "Information", 
													  JOptionPane.INFORMATION_MESSAGE);
			}
			else
				JOptionPane.showMessageDialog(null, 
													  resultatUpdate.getMessage(), 
													  "ERREUR", 
													  JOptionPane.ERROR_MESSAGE);
		}
		/*else
			(new JOptionPane()).showMessageDialog(null, 
												  "Toutes les entrées doivent être renseignées", 
												  "Information", 
												  JOptionPane.INFORMATION_MESSAGE);*/
	}

	@Override
	public void initComponents() {
		// TODO Auto-generated method stub
		this.jlb_id_trajet = new JLabel("No. Trajet : ");
		this.jlb_date_time_trajet = new JLabel("date : ");
		this.jlb_rue_adr_depart_trajet= new JLabel("rue de depart : ");
		this.jlb_num_rue_adr_depart_trajet = new JLabel("num de depart : ");
		this.jlb_cp_adr_depart_trajet = new JLabel("Prénom : ");
		this.jlb_cp_adr_arrivee_trajet = new JLabel("Code Postal : ");
		this.jlb_rue_adr_arrivee_trajet = new JLabel("Rue : ");
		this.jlb_num_rue_adr_arrivee_trajet = new JLabel("Num rue : ");
		this.jlb_nbr_max_places = new JLabel("Nombre de places : ");
		this.jlb_pseudo = new JLabel("pseudo : ");
		this.jlb_administrateur = new JLabel("administrateur : ");
				
				
		this.jtf_id_trajet = new JTextField();
		this.jtf_id_trajet.setEnabled(false);
		this.jtf_id_trajet.setPreferredSize(new Dimension(150, 30));
		this.jtf_date_time_trajet = new JTextField();
		this.jtf_date_time_trajet.setPreferredSize(new Dimension(150, 30));
		this.jtf_rue_adr_depart_trajet = new JTextField();
		this.jtf_rue_adr_depart_trajet.setPreferredSize(new Dimension(150, 30));
		this.jtf_num_rue_adr_depart_trajet = new JTextField();
		this.jtf_num_rue_adr_depart_trajet.setPreferredSize(new Dimension(150, 30));
		this.jtf_cp_adr_depart_trajet = new JTextField();
		this.jtf_cp_adr_depart_trajet.setPreferredSize(new Dimension(150, 30));
		this.jtf_rue_adr_arrivee_trajet = new JTextField();
		this.jtf_rue_adr_arrivee_trajet.setPreferredSize(new Dimension(150, 30));
		this.jtf_num_rue_adr_arrivee_trajet = new JTextField();
		this.jtf_num_rue_adr_arrivee_trajet.setPreferredSize(new Dimension(150, 30));
		this.jtf_cp_adr_arrivee_trajet = new JTextField();
		this.jtf_cp_adr_arrivee_trajet.setPreferredSize(new Dimension(150, 30));
		this.jcb_nbr_max_places = new JComboBox();
		this.jcb_nbr_max_places.setPreferredSize(new Dimension(150, 30));
		this.jtf_pseudo = new JTextField();
		this.jtf_pseudo.setPreferredSize(new Dimension(150, 30));
		this.jchb_administrateur = new JCheckBox();
		this.jchb_administrateur.setPreferredSize(new Dimension(150, 30));
		
		this.btnEnvoyer = new JButton("Modifier");
		this.btnEnvoyer.setEnabled(false);
		this.btnEnvoyer.addActionListener(this);
		this.setButtonSubmit(this.btnEnvoyer);
		this.btnCancel = new JButton("Cancel");
		this.btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) 
			{
				dispose(); 
			}
		});
		
		this.contenaire = new JPanel();
		
		this.jt_Trajet = new JTable();
		
		this.TrajetAncien = new Trajet();
		
		JPanel contenaire_gauche;
		JPanel contenaire_droit;
		
		contenaire_gauche = new JPanel();
		contenaire_gauche.setPreferredSize(new Dimension(260, 30));
		
		contenaire_droit = new JPanel();
		contenaire_droit.setPreferredSize(new Dimension(520, 50));
		
		contenaire_gauche.add(this.jlb_id_trajet);
		contenaire_gauche.add(this.jtf_id_trajet);
		contenaire_gauche.add(this.jlb_date_time_trajet);
		contenaire_gauche.add(this.jtf_date_time_trajet);
		contenaire_gauche.add(this.jlb_rue_adr_depart_trajet);
		contenaire_gauche.add(this.jtf_rue_adr_depart_trajet);
		contenaire_gauche.add(this.jlb_num_rue_adr_depart_trajet);
		contenaire_gauche.add(this.jtf_num_rue_adr_depart_trajet);
		contenaire_gauche.add(this.jlb_cp_adr_depart_trajet);
		contenaire_gauche.add(this.jtf_cp_adr_depart_trajet);
		contenaire_gauche.add(this.jlb_rue_adr_arrivee_trajet);
		contenaire_gauche.add(this.jtf_rue_adr_arrivee_trajet);
		contenaire_gauche.add(this.jlb_num_rue_adr_arrivee_trajet);
		contenaire_gauche.add(this.jtf_num_rue_adr_arrivee_trajet);
		contenaire_gauche.add(this.jlb_cp_adr_arrivee_trajet);
		contenaire_gauche.add(this.jtf_cp_adr_arrivee_trajet);
		contenaire_gauche.add(this.jlb_nbr_max_places);
		contenaire_gauche.add(this.jcb_nbr_max_places);
		contenaire_gauche.add(this.jlb_pseudo);
		contenaire_gauche.add(this.jtf_pseudo);
		contenaire_gauche.add(this.jlb_administrateur);
		contenaire_gauche.add(this.jchb_administrateur);




		contenaire_gauche.add(this.btnEnvoyer);
		contenaire_gauche.add(this.btnCancel);
		
		this.listModel = this.jt_Trajet.getSelectionModel();
		this.listModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    this.listModel.addListSelectionListener(this);
	    
	    remplirTableau();
				
		JScrollPane monScroll = new JScrollPane();
		monScroll.setViewportView(this.jt_Trajet);
		contenaire_droit.add(monScroll, BorderLayout.NORTH);
		
		this.add(contenaire_gauche, BorderLayout.WEST);
		this.add(contenaire_droit, BorderLayout.EAST);
		
		this.getContentPane().add(this.contenaire, BorderLayout.CENTER);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		int[] rowSelected;
	    
	    if (!e.getValueIsAdjusting()) 
	    {
	    	rowSelected = this.jt_Trajet.getSelectedRows();
		    
	    	if (rowSelected.length > 0) 
		    {
	    		// récupérer les données de la table
	    		MonDefaultTableModel tm = (MonDefaultTableModel) this.jt_Trajet.getModel();
	    		
	    		this.TrajetAncien.setid_trajet( (String) tm.getValueAt(rowSelected[0], 0) ); 
	    		this.TrajetAncien.setdate_time_trajet( (String) tm.getValueAt(rowSelected[0], 1) );     
	    		this.TrajetAncien.setrue_adr_depart_trajet( (String) tm.getValueAt(rowSelected[0], 2) );
	    		this.TrajetAncien.setnum_rue_adr_depart_trajet( (String) tm.getValueAt(rowSelected[0], 3) );
	    		this.TrajetAncien.setcp_adr_depart_trajet( (String) tm.getValueAt(rowSelected[0], 4) );
				this.TrajetAncien.setrue_adr_arrivee_trajet( (String) tm.getValueAt(rowSelected[0], 0) ); 
	    		this.TrajetAncien.setnum_rue_adr_arrivee_trajet( (String) tm.getValueAt(rowSelected[0], 1) );     
	    		this.TrajetAncien.setcp_adr_arrivee_trajet( (String) tm.getValueAt(rowSelected[0], 2) );
	    		this.TrajetAncien.setnbr_max_places( (String) tm.getValueAt(rowSelected[0], 3) );
	    		this.TrajetAncien.setPseudo( (String) tm.getValueAt(rowSelected[0], 4) );
				this.TrajetAncien.setadministrateur( (String) tm.getValueAt(rowSelected[0], 4) );
	    		
	    		this.remplirFormulaire();
		    }
	    }
	}
	
	private void remplirFormulaire()
	{
		this.jtf_id_trajet.setText( this.TrajetAncien.getid_trajet()+"" );
		this.jtf_date_time_trajet.setText( this.TrajetAncien.getdate_time_trajet() );
		this.jtf_rue_adr_arrivee_trajet.setText( this.TrajetAncien.getrue_adr_depart_trajet() );
		this.jtf_num_rue_adr_arrivee_trajet.setText( this.TrajetAncien.getnum_rue_adr_depart_trajet() );
		this.jtf_cp_adr_depart_trajet.setText( this.TrajetAncien.getcp_adr_depart_trajet() );
		this.jtf_rue_adr_arrivee_trajet.setText( this.TrajetAncien.getrue_adr_arrivee_trajet()+"" );
		this.jtf_num_rue_adr_arrivee_trajet.setText( this.TrajetAncien.getnum_rue_adr_arrivee_trajet() );
		this.jtf_cp_adr_arrivee_trajet.setText( this.TrajetAncien.getcp_adr_arrivee_trajet() );
		this.jcb_nbr_max_places.setToolTipText( this.TrajetAncien.getnbr_max_places() );
		this.jtf_pseudo.setText( this.TrajetAncien.getPseudo() );
		this.jchb_administrateur.setText( this.TrajetAncien.getadministrateur() );
		
		this.btnEnvoyer.setEnabled(true);
	}
	
	private void init(Controleur controler)
	{
        this.controler = controler;
		
		this.initComponents();
		
		this.setVisible(true);
	}
	
	private void remplirTableau()
	{
		Resultat resultat = this.controler.selectAll(new Trajet());
		ResultSet rs = resultat.getReponse();
		
		this.defaultTableModel = new MonDefaultTableModel();
		
		String[] listLibelle = {"No. Trajet", "Nom", "Prénom", "Mail", "Téléphone"};
		String[] listChamp = {"id_Trajet", "nom_Trajet", "prenom_Trajet", "mail_Trajet", "tel_Trajet"};
		
		this.setValuesJT(rs, this.defaultTableModel, listLibelle, listChamp);
		
		this.jt_Trajet.setModel(this.defaultTableModel);
	}
	
}
    
