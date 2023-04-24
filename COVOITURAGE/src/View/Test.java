package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import View.Abstracts.AbstractVuePersonnalisable;

public class Test extends AbstractVuePersonnalisable {

	private JLabel lbl_nom;
	private JTextField jtf_nom;
	
	private JButton btn_entrer;
	private JButton btn_cancel;
	
	private JPanel jp;
	
	public Test(JFrame parent, 
			    Dimension dimension, 
			    String title, 
			    boolean modal) {
		super(parent, dimension, title, modal);
		// TODO Auto-generated constructor stub
		
		this.initComponents();
		
		this.setVisible(true);
	}
	
	public Test(JFrame parent, 
			    String title, 
			    boolean modal) {
		super(parent, title, modal);
		// TODO Auto-generated constructor stub
		
		this.initComponents();
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(this.btn_entrer))
		{
			if (!this.jtf_nom.getText().equals(""))
				new VueMessage(null, 
						       new Dimension(300, 300), 
						       "Information", 
						       this.jtf_nom.getText().toUpperCase().trim(), 
						       true);
			else
				JOptionPane.showMessageDialog(this, "Ce n'est qu'un simple message",
						"ERROR",
						JOptionPane.ERROR_MESSAGE);
		}
		else
			dispose();
	}

	@Override
	public void initComponents() {
		// TODO Auto-generated method stub
		this.lbl_nom = new JLabel("Nom : ");
		this.jtf_nom = new JTextField();
		this.jtf_nom.setPreferredSize(new Dimension(200, 30));
		this.btn_entrer = new JButton("Entrer");
		this.btn_cancel = new JButton("Fermer");
		
		this.jp = new JPanel();
		
		this.btn_entrer.addActionListener(this);
		this.btn_cancel.addActionListener(this);
		
		this.jp.add(this.lbl_nom); 
		this.jp.add(this.jtf_nom);
		
		this.jp.add(this.btn_entrer);
		this.jp.add(this.btn_cancel);
		
		this.setButtonSubmit(btn_entrer);
		
		this.getContentPane().add(this.jp, BorderLayout.CENTER);
	}

}
