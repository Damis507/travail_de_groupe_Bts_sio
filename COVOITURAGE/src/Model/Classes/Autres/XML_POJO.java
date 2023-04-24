package Model.Classes.Autres;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XML_POJO {
	
	public static String[] getListValues(String pathFile, 
									     String nameChamp) 
					                           throws ParserConfigurationException, 
					                           SAXException, 
					                           IOException
	{
		/* XML Parser
		 * 
		 * Si nous voulons obtenir des données XML 
		 * dans un type compatible Java, 
		 * nous pouvons utiliser Java POJO 
		 * pour lire les données. Ici, nous utilisons 
		 * le type Employé de type ArrayList pour 
		 * ajouter chaque nœud à l’aide de la méthode add() 
		 * puis nous utilisons la boucle for pour 
		 * itérer chaque objet. 
		 */
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(pathFile));
        
        NodeList nodeList = document.getDocumentElement().getChildNodes();
        
        int qte = nodeList.getLength();
        
        ArrayList<String> arrayList = new ArrayList<String>();
        
        for (int i = 0; i < qte; i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) { 
                Element elem = (Element) node;
                arrayList.add(elem.getElementsByTagName(nameChamp)
                        		  .item(0)
                        		  .getChildNodes()
                        		  .item(0)
                        		  .getNodeValue());
            }
        }
		
        int longeur = arrayList.size();
        
        String[] listValues = new String[longeur];
        for (int i = 0; i < longeur; i++)
        	listValues[i] = arrayList.get(i);
		
        return listValues;
	}
	
}
