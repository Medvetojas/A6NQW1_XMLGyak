package hu.domparse.a6nqw1;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DomQueryA6NQW1 {
    public static void main(String[] args) {

        //Importing and parsing XML file
        File xmlFile = new File("XMLA6NQW1.xml");
        Document doc = introduceFile(xmlFile);

        if (doc != null) { //if XML document parsing was successful
            doc.getDocumentElement().normalize();
        } else {
            System.out.println("Document is null");
            System.exit(-1);
        }

        //Specifying queried nodes, then running queries
        NodeList buntetesList = doc.getDocumentElement().getElementsByTagName("buntetes");

        for (int i = 0; i < buntetesList.getLength(); i++) {
            NodeList query = buntetesList.item(i).getChildNodes();
            for (int j = 0; j < query.getLength(); j++) {
                if (query.item(j).getNodeName().equals("osszeg") && Integer.parseInt(query.item(j).getTextContent()) > 30000){
                    System.out.println("{buntetes}:");
                    listData(buntetesList.item(i).getChildNodes(), "");
                }
            }
        }

        System.out.println("-------------------------------------------------------------");

        NodeList traffipaxList = doc.getDocumentElement().getElementsByTagName("traffipax");
        //Azon traffipaxokat írja ki, melyeknek a beállított sebességmérése 110 km/h vagy afeletti

        for (int i = 0; i < traffipaxList.getLength(); i++) {
            NodeList query = traffipaxList.item(i).getChildNodes();
            for (int j = 0; j < query.getLength(); j++) {
                if (query.item(j).getNodeName().equals("sebesseghatar") && Integer.parseInt(query.item(j).getTextContent()) >= 110){
                    System.out.println("{traffipax}:");
                    listData(traffipaxList.item(i).getChildNodes(), "");
                }
            }
        }

        System.out.println("-------------------------------------------------------------");

        NodeList alkalmazottList = doc.getDocumentElement().getElementsByTagName("alkalmazott");
        //Azon alkalmazottak kilistázása, akiknek a fizetésük több, mint 500.000 Ft

        for (int i = 0; i < alkalmazottList.getLength(); i++) {
            NodeList query = alkalmazottList.item(i).getChildNodes();
            for (int j = 0; j < query.getLength(); j++) {
                if (query.item(j).getNodeName().equals("fizetes") && Integer.parseInt(query.item(j).getTextContent()) >= 500000){
                    System.out.println("{alkalmazott}:");
                    listData(alkalmazottList.item(i).getChildNodes(), "");
                }
            }
        }

        System.out.println("-------------------------------------------------------------");

        NodeList autopalyafelugyeletList = doc.getDocumentElement().getElementsByTagName("autopalya_felugyelet");
        //A miskolci autópályafelügyeletek kilistázása

        for (int i = 0; i < autopalyafelugyeletList.getLength(); i++) {
            NodeList query = autopalyafelugyeletList.item(i).getChildNodes();
            for (int j = 0; j < query.getLength(); j++) {
                if (query.item(j).getNodeName().equals("telepules") && query.item(j).getTextContent().equals("Miskolc")){
                    System.out.println("{autopalyafelugyelet}:");
                    listData(autopalyafelugyeletList.item(i).getChildNodes(), "");
                }
            }
        }

        System.out.println("-------------------------------------------------------------");

        NodeList alkalmazott2List = doc.getDocumentElement().getElementsByTagName("alkalmazott");
        //Azon alkalmazottak kilistázása, akik még nem dolgoztak 6 évet

        for (int i = 0; i < alkalmazott2List.getLength(); i++) {
            NodeList query = alkalmazott2List.item(i).getChildNodes();
            for (int j = 0; j < query.getLength(); j++) {
                if (query.item(j).getNodeName().equals("ledolgozott_evek") && Integer.parseInt(query.item(j).getTextContent()) <= 6){
                    System.out.println("{alkalmazott}:");
                    listData(alkalmazott2List.item(i).getChildNodes(), "");
                }
            }
        }
    }

    public static Document introduceFile(File xmlFile){ //Parsing the File which becomes an XML Document inside the code
        Document doc = null;

        try {
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

        if (nodeList != null) {
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
}
