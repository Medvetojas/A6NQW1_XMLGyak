package doma6nqw1;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class DomWriteA6NQW1 {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        Document doc = dBuilder.newDocument();

        Element root = doc.createElementNS("DOMA6NQW1", "users");
        doc.appendChild(root);

        root.appendChild(createUser(doc, "1", "John", "Doe1", "Web Developer"));
        root.appendChild(createUser(doc, "2", "Jane", "Doe2", "SW Developer"));
        root.appendChild(createUser(doc, "3", "Jinn", "Doe3", "Manager"));

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transf = transformerFactory.newTransformer();

        transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transf.setOutputProperty(OutputKeys.INDENT, "yes");
        transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(doc);

        File myFile = new File("users1A6NQW1.xml");

        StreamResult console = new StreamResult(System.out);
        StreamResult file = new StreamResult(myFile);

        transf.transform(source, console);
        transf.transform(source, file);
    }

    private static Node createUser(Document doc, String id, String firstName, String lastName, String profession){
        Element user = doc.createElement("user");

        user.setAttribute("id", id);

        user.appendChild(createUserElement(doc, user, "firstname", firstName));
        user.appendChild(createUserElement(doc, user, "lastname", lastName));
        user.appendChild(createUserElement(doc, user, "profession", profession));

        return user;
    }

    private static Node createUserElement(Document doc, Element element, String name, String value){
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
}
