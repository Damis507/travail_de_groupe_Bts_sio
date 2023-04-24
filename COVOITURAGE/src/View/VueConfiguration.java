package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controler.Controleur;
import Controler.Abstracts.AbstractControler;
import Model.Classes.Systeme.ConfigurationDB;
import Model.Classes.Systeme.Session;
import Model.Interfaces.Observer.InterfObservateur;
import View.Abstracts.AbstractVuePersonnalisable;

/*
 * Vue de l'interface Configuration qui herite d'AbstractVuePersonnalisable
 * et implemente aussi Observateur. Elle doit observer le modele pour recuperer
 * d'"une autre facon" les informations du modele. Puisque VueConfiguration 
 * comporte un controler on aurait pu aussi acceder au modele a partir du controleur.
 */

public class VueConfiguration extends AbstractVuePersonnalisable implements InterfObservateur {
	
	private JLabel lbl_sgbdr;
	private JLabel lbl_host;
	private JLabel lbl_port;
	private JLabel lbl_dbname;
	private JLabel lbl_user;
	private JLabel lbl_password;
	
	private JComboBox jcb_sgbdr; // Liste deroulante
	private JTextField jtf_host;
	private JTextField jtf_port;
	private JTextField jtf_dbname;
	private JTextField jtf_user;
	private JPasswordField jpf_passord;
	
	private JButton btn_entrer;
	private JButton btn_cancel;
	
	private JPanel jp_config;
	
	private Controleur controler;
	private Session session; 
	
	public VueConfiguration(JFrame parent, Dimension dimension, String title, boolean modal, Controleur controler) {
		super(parent, dimension, title, modal);
		// TODO Auto-generated constructor stub

		this.controler = controler;
		
		this.session = null;
		
		this.initComponents();
		
		// C'est l'objet qui l'appelle qui la rendra visible.
	}

	@Override
	public void initComponents() {
		// TODO Auto-generated method stub
		this.jp_config = new JPanel();
		
		this.lbl_sgbdr = new JLabel("Système de BD : ");
		this.lbl_host = new JLabel("Serveur : ");
		this.lbl_port = new JLabel("Port connexion : ");
		this.lbl_dbname = new JLabel("Base de données : ");
		this.lbl_user = new JLabel("Utilisateur BD : ");
		this.lbl_password = new JLabel("Mot de passe : ");
		
		this.jcb_sgbdr = new JComboBox();
		this.jtf_host= new JTextField();
		this.jtf_port= new JTextField();
		this.jtf_dbname= new JTextField();
		this.jtf_user= new JTextField();
		this.jpf_passord = new JPasswordField();
		this.btn_entrer = new JButton("Configurer");
		
		// Definir l'ecouteur du boouton
		this.btn_entrer.addActionListener(this);
		this.btn_cancel = new JButton("Fermer");
		this.btn_cancel.addActionListener(this);
		
		this.setButtonSubmit(btn_entrer); // C'est bouton qui va reagir a une ENTER
		
		this.jp_config.add(lbl_sgbdr);
		
		this.jp_config.add(jcb_sgbdr);
		this.jp_config.add(lbl_host);
		this.jp_config.add(jtf_host);
		this.jp_config.add(lbl_port);
		this.jp_config.add(jtf_port);
		this.jp_config.add(lbl_dbname);
		this.jp_config.add(jtf_dbname);
		this.jp_config.add(lbl_user);
		this.jp_config.add(jtf_user);
		this.jp_config.add(lbl_password);
		this.jp_config.add(jpf_passord);
		this.jp_config.add(btn_entrer);
		this.jp_config.add(btn_cancel);
			
		this.getContentPane().add(this.jp_config, BorderLayout.CENTER);
	}

	@Override
	public void update(Session session) {
		// TODO Auto-generated method stub
		/*
		 * Comme c'est un observateur au moment de l'affichage de l'interface
		 * les entrées recevrant les valeurs de la configuration de la session
		 * actuelle.
		 */
		
		String sgbdr[] = {"mysql", "postgresql"};
		
		if (this.session == null)
		{
			this.jcb_sgbdr.addItem(sgbdr[0]);
			this.jcb_sgbdr.addItem(sgbdr[1]);
		}
		
		this.session = session;
		
		// selectionner le SGBDR correct.
		if (this.session.getSGBDR().equals(sgbdr[0]))
			this.jcb_sgbdr.setSelectedIndex(0);
		else
			this.jcb_sgbdr.setSelectedIndex(1);
		this.jcb_sgbdr.setPreferredSize(new Dimension(150, 30));
		this.jtf_host.setText(this.session.getHost());
		this.jtf_host.setPreferredSize(new Dimension(150, 30));
		this.jtf_port.setText(this.session.getPort());
		this.jtf_port.setPreferredSize(new Dimension(150, 30));
		this.jtf_dbname.setText(this.session.getDbName());
		this.jtf_dbname.setPreferredSize(new Dimension(150, 30));
		this.jtf_user.setText(this.session.getUserDB());
		this.jtf_user.setPreferredSize(new Dimension(150, 30));
		this.jpf_passord.setText(this.session.getPasswordDB());
		this.jpf_passord.setPreferredSize(new Dimension(150, 30));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		/* 
		 * Si la source de l'evenement est le 
		 * bouton btn_enter
		 */
		if (e.getSource().equals(this.btn_entrer))
		{
			/*
			 * Reinitialiser la configuration de la session
			 * avec les valeurs saissis, s'ils aboutissent
			 * a une connexion avec succès.
			 */
			if (this.controler.connect(new ConfigurationDB((String) this.jcb_sgbdr.getSelectedItem(),
																	  this.jtf_host.getText(),
																	  this.jtf_port.getText(),
																	  this.jtf_dbname.getText(),
																	  this.jtf_user.getText(),
																	  new String(this.jpf_passord.getPassword()))))
				this.dispose(); // Fermer la fenetre
			else
			{
				// Affiche une erreur si la connexion n'abouti pas
				new VueMessage(null, new Dimension(350, 80), "Erreur", "Erreur de connection. Vérifiez vos informations.", true);
				
				// Vider la session equivaut a reprendre les valeurs de connexion de session par défaut.
				this.controler.setSessionDefaut();
				
				// M.a.j tous les observateurs
				this.controler.notifiyObserverModel();
			}
		}
		/*
		 * Si la source de l'evenemment est 
		 * le bouton btn_cancel
		 */
		else if (e.getSource().equals(this.btn_cancel))
		{
			// Sort cette interface de la liste d'observateurs.
			this.controler.removeObserverModel(1);
			this.dispose();
		}
	}

}
