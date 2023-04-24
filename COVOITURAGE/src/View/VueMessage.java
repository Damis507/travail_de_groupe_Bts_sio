package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import View.Abstracts.AbstractVuePersonnalisable;

/*
 * Classe utiilisée pour personnaliser COMPLETEMENT la fenetre avec le message 
 * qu'on veut afficher.
 */

public class VueMessage extends AbstractVuePersonnalisable {
	
	private JLabel lbl_message;
	private JButton btn_ok;
	private JPanel container;
	private String message; 

	public VueMessage(JFrame parent, 
			          Dimension dimension, 
			          String title, 
			          String message, 
			          boolean modal) {
		super(parent, dimension, title, modal);
		// TODO Auto-generated constructor stub
		this.message = message;
		
		this.initComponents();
	}

	@Override
	public void initComponents() {
		// TODO Auto-generated method stub
		this.lbl_message = new JLabel(this.message);
		this.lbl_message.setForeground(Color.RED);
		
		this.btn_ok = new JButton("Fermer");
		
		/*
		 * Autre version :
		 * 
		 * this.btn_ok.addActionListener(this);
		 * 
		 * et ajouter dispose; dans la méhotde actionPerformed de la classe 
		 */
		this.btn_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		this.setButtonSubmit(btn_ok);
		
		this.container = new JPanel();
		
		this.container.add(this.lbl_message);
		this.container.add(this.btn_ok);
		/*
		 * Une autre facons d'ajouter au content pane 
		 * le JPanel qui contient les composantes
		 */
		this.setContentPane(this.container);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
