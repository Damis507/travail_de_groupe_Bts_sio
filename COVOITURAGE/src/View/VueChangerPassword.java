package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import Controler.Controleur;
import Model.Classes.Metiers.Resultat;
import Model.Classes.Metiers.Employe;
import View.Abstracts.AbstractVuePersonnalisable;

public class VueChangerPassword extends AbstractVuePersonnalisable {
	
	private JLabel lbl_Employe;
	private JLabel lbl_old_password;
	private JLabel lbl_new_password;
	private JLabel lbl_conf_password;
	
	private JComboBox jcb_Employe;
	private JPasswordField jpf_old_password;
	private JPasswordField jpf_new_password;
	private JPasswordField jpf_conf_password;
	
	private JButton btn_enter;
	private JButton btn_cancel;
	
	private JPanel jp_contenaire;
	
	private Controleur controler;


	public VueChangerPassword(JFrame parent, 
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
		if (e.getSource().equals(btn_enter))
		{
			String old_mdp, new_mdp1, new_mdp2;
			
			old_mdp = new String(this.jpf_old_password.getPassword());
			new_mdp1 = new String(this.jpf_new_password.getPassword());
			new_mdp2 = new String(this.jpf_conf_password.getPassword());
			
			if (!old_mdp.equals("")
				&& !new_mdp1.equals("")
				&& !new_mdp2.equals(""))
			{
				Employe newUser = new Employe("", 
							                        this.jcb_Employe.getSelectedItem().toString(),
							                        "",
							                        "",
							                        "",
							                        new_mdp1.toString());
				
				Employe oldUser = new Employe("", 
							                        this.jcb_Employe.getSelectedItem().toString(),
							                        "",
							                        "",
							                        "",
							                        old_mdp.toString());
				
				if (this.controler.exist(oldUser))
				{
					if (new_mdp1.equals(new_mdp2))
					{
						this.controler.update(newUser, oldUser);
						this.dispose();
					}
					else
					{
						(new JOptionPane()).showMessageDialog(null, 
															  "Les deux nouveaux mot de passe ne sont égaux.", 
															  "Erreur", 
															  JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					(new JOptionPane()).showMessageDialog(null, 
														  "Le mot de passe ancien ne corresponds pas à cet Employe.", 
														  "Erreur", 
														  JOptionPane.ERROR_MESSAGE);
					
					this.jpf_old_password.setText("");
				}
			}
			else
			{
				(new JOptionPane()).showMessageDialog(null, 
													  "Vous devez remplir toutes les données.", 
													  "Erreur", 
													  JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else if (e.getSource().equals(this.btn_cancel))
		{
			this.dispose();
		}
	}

	@Override
	public void initComponents() {
		// TODO Auto-generated method stub
		this.lbl_Employe = new JLabel("Pseudo Employe : ");
		this.lbl_old_password = new JLabel("Ancien mot de passe : ");
		this.lbl_new_password = new JLabel("Nouveau mot de passe : ");
		this.lbl_conf_password = new JLabel("Confirmation : ");
		
		this.jcb_Employe = new JComboBox();
		this.jcb_Employe.setPreferredSize(new Dimension(150, 30));
		this.jpf_old_password = new JPasswordField();
		this.jpf_old_password.setPreferredSize(new Dimension(150, 30));
		this.jpf_new_password = new JPasswordField();
		this.jpf_new_password.setPreferredSize(new Dimension(150, 30));
		this.jpf_conf_password = new JPasswordField();
		this.jpf_conf_password.setPreferredSize(new Dimension(150, 30));
		
		Resultat resultat = this.controler.select(new Employe());
		
		ResultSet rs = resultat.getReponse();
		
		ArrayList<Object> lu = new ArrayList<Object>();
			
		try {
			while (rs.next())
				lu.add(new Employe(rs.getString("id_Employe"),
									   rs.getString("pseudo"),
									   rs.getString("nom"),
									   rs.getString("prenom"),
									   rs.getString("tel_cel"),
									   rs.getString("mot_de_passe")));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		int taille = lu.size();
		
		for (int i = 0; i < taille; i++)
			this.jcb_Employe.addItem(((Employe) lu.get(i)).getPseudo());
		
		this.btn_enter = new JButton("Confirmer");
		this.btn_cancel = new JButton("Cancel");
		
		this.btn_enter.addActionListener(this);
		this.btn_cancel.addActionListener(this);
		
		this.setButtonSubmit(btn_enter);
		
		this.jp_contenaire = new JPanel();
		
		this.jp_contenaire.add(this.lbl_Employe);
		this.jp_contenaire.add(this.jcb_Employe);
		this.jp_contenaire.add(this.lbl_old_password);
		this.jp_contenaire.add(this.jpf_old_password);
		this.jp_contenaire.add(this.lbl_new_password);
		this.jp_contenaire.add(this.jpf_new_password);
		this.jp_contenaire.add(this.lbl_conf_password);
		this.jp_contenaire.add(this.jpf_conf_password);
		this.jp_contenaire.add(this.btn_enter);
		this.jp_contenaire.add(this.btn_cancel);
		
		this.getContentPane().add(this.jp_contenaire, BorderLayout.CENTER);
	}

}
