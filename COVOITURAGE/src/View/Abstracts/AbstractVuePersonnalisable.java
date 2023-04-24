package View.Abstracts;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import Model.Classes.Autres.XML_POJO;


/*
 * Classe qui offre un super type abstract pour une fenetre d'interface
 */

public abstract class AbstractVuePersonnalisable extends JDialog  
												 implements ActionListener {
	
	// Dimension de la fenetre
	protected Dimension dimension = new Dimension();
	
	public AbstractVuePersonnalisable(JFrame parent, 
			                          String title, 
			                          boolean modal)
	{ 
		// Appele au constructeur de JDialog
		super(parent, title, modal);
		// Centrer la fenetre
		this.setLocationRelativeTo(null);
		// La fenetre ne pourra pas changer de taille
		this.setResizable(false); 
		/*
		 *  Desactiver le bouton qui sert a fermer la fenetre
		 *  Dans notre cas, on le programme avec le bouton Fermer de la
		 *  classe fille
		 */
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); 
	}
	
	public AbstractVuePersonnalisable(JFrame parent, 
			                          Dimension dimension, 
			                          String title, 
			                          boolean modal)
	{ 
		super(parent, title, modal);
		this.setSize(dimension);
		this.setLocationRelativeTo(null);
		this.setResizable(false); 
		/*
		 *  Desactiver le bouton qui sert a fermer la fenetre.
		 *  Dans notre cas, on le programme avec le bouton Fermer.
		 */
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); 
	}
	
	// Determine qu'elle bouton sera activé avec la touche ENTER
	public void setButtonSubmit(JButton bouton)
	{
		this.getRootPane().setDefaultButton(bouton);
	}
	
	public void setDimension(Dimension dimension)
	{
		this.setSize(dimension);
	}
	
	protected boolean setValuesJT(ResultSet source,
								 DefaultTableModel defaultTableModel,
							     String[] listLibelle,
							     String[] listChamp)
	{
		if (listLibelle.length != listChamp.length ||
				source == null || 
				defaultTableModel == null)
			return false;
		else
		{
			// Ajouter tous les libelles
			for (int i = 0; i < listLibelle.length; i++)
				defaultTableModel.addColumn(listLibelle[i]);
			
			/*
			 *  Un tableau d'Objet qui a la taille
			 *  de la liste des libelles recus
			 */
			Object[] registre = new Object[listLibelle.length];
			
			try {
				while(source.next())
				{
					for (int i = 0; i < listLibelle.length; i++)
					{
						if (source.getObject(listChamp[i]) instanceof Integer)
							registre[i] = source.getInt(listChamp[i]);
						else if (source.getObject(listChamp[i]) instanceof String)
							registre[i] = source.getString(listChamp[i]);
					}
					
					defaultTableModel.addRow(registre);
				}
				
				return true;
			} 
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
	}
	
	protected void setValuesJCB(ResultSet source, 
			                JComboBox destination, 
			                Object classeMetier)
	{
		/*
		 * Cette méthode prend en charge le remplissage d'un
		 * JComboBox avec les informations de la vue stockées
		 * dans un ResultSet. 
		 * La classe métier sert à identifier quel object 
		 * doit être utilisé pour accueillir ces informations.
		 */
		ArrayList<Object> list = new ArrayList<Object>();
		
		try 
		{
			while (source.next())
			{
				/*if (classeMetier instanceof Instrument)
					list.add(new Instrument(source.getString("libelle_instrument")));
				else if (classeMetier instanceof Utilisateur)
					list.add(new Utilisateur(source.getString("nom"),
							                 source.getString("prenom")));*/
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int taille = list.size();
		
		for (int i = 0; i < taille; i++)
		{
			/*if (classeMetier instanceof Instrument)
				destination.addItem( ((Instrument) list.get(i)).getLibelle_instrument() );
			else if (classeMetier instanceof Utilisateur)
				destination.addItem( ((Utilisateur) list.get(i)).getNom() + ", " + ((Utilisateur) list.get(i)).getPrenom() );*/
		}
	}
	
	/*
	 *  Cette méthode sera implementée dans les classes 
	 *  qui font heriter de cette classe.
	 *  Ces classes auront ses propres composantes 
	 *  et seront initialisées dans l'implementation
	 *  de cette méthode
	 */
	public abstract void initComponents();
}
