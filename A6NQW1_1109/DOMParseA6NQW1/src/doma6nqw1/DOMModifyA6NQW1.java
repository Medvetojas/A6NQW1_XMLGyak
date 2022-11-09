package doma6nqw1;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class DOMModifyA6NQW1 {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        File inputFile = new File("carsA6NQW1.xml");

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document doc = documentBuilder.parse(inputFile);

        Node cars = doc.getFirstChild();
        Node supercar = doc.getElementsByTagName("supercars").item(0);

        NamedNodeMap attr = supercar.getAttributes();
        Node nodeAttr = attr.getNamedItem("company");
        nodeAttr.setTextContent("Lamborghini");

        NodeList list = supercar.getChildNodes();

        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;

                if("carname".equals(eElement.getNodeName())) {
                    if("Ferrari 01".equals(eElement.getTextContent())) {
                        eElement.setTextContent("Lamborghini 01");
                    }
                }
            }
        }

        NodeList childNodes = cars.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);

            if("luxurycars".equals(node.getNodeName())) {
                node.removeChild(node);
            }
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        DOMSource source = new DOMSource(doc);

        System.out.println("----Módosított XML fájl----");
        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);
    }
}