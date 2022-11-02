package doma6nqw1;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

public class DomReadA6NQW1 {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        File xmlFile = new File("usersA6NQW1.xml");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();

        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

        NodeList nList = doc.getElementsByTagName("user");

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            System.out.println("\nCurrent element: " + nNode.getNodeName());

            if(nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                System.out.println("User id: " + eElement.getAttribute("id"));
                System.out.println("First name: " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
                System.out.println("Last name: " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
                System.out.println("Profession: " + eElement.getElementsByTagName("profession").item(0).getTextContent());
            }
        }
    }
}