package hu.domparse.a6nqw1;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DomModifyA6NQW1 {
    public static void main(String[] args) {

        //Importing and parsing XML file
        File xmlFile = new File("XMLA6NQW1.xml");
        Document doc = introduceFile(xmlFile);

        if (doc != null) { //if XML document parsing was successful
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
        } else {
            System.out.println("Document is null");
            System.exit(-1);
        }

        //Modifying part begins here
        NodeList modifyList = doc.getDocumentElement().getElementsByTagName("osszeg"); //Getting the "osszeg" nodes
        modifyData(modifyList); //Changing the values
        //Modifying part ends here

        //Printing the changed value begins here
        NodeList queryList = doc.getDocumentElement().getElementsByTagName("buntetes");
        for (int i = 0; i < queryList.getLength(); i++) {
            NodeList query = queryList.item(i).getChildNodes();
            for (int j = 0; j < query.getLength(); j++) {
                if (query.item(j).getNodeName().equals("osszeg")){
                    System.out.println("{buntetes}");
                    listData(queryList.item(i).getChildNodes(), "");
                }
            }
        }
        //Printing the changed value ends here
    }

    public static Document introduceFile(File xmlFile){ //Parsing the File which becomes an XML Document inside the code
        Document doc = null;

        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            doc = dbBuilder.parse(xmlFile);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    public static void listData(NodeList nodeList, String indent){ //Printing data
        indent += "\t";

        if(nodeList != null) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE && !node.getTextContent().trim().isEmpty()) { //if the node is an element and not empty
                    System.out.println(indent + "{" + node.getNodeName() + "}:");
                    NodeList nodeList_new = node.getChildNodes();
                    listData(nodeList_new, indent);
                } else if (node instanceof Text){
                    String value = node.getNodeValue().trim();
                    if (value.isEmpty()){
                        continue;
                    }
                    System.out.println(indent + node.getTextContent());
                }
            }
        }
    }

    public static void modifyData(NodeList nodeList){ //Modifying penalty values - method receives the "buntetes" nodes
        if (nodeList != null){
            for (int i = 0; i < nodeList.getLength(); i++) {
                int penalty_value = Integer.parseInt(nodeList.item(i).getTextContent());
                penalty_value -= 2500;
                nodeList.item(i).setTextContent(Integer.toString(penalty_value));
            }
        }
    }
}
