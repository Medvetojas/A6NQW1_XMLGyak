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


        //A buntetesek osszeget 2500-al csokkenti
        NodeList buntetesModifyList = doc.getDocumentElement().getElementsByTagName("osszeg");
        for (int i = 0; i < buntetesModifyList.getLength(); i++) {
            int penalty_value = Integer.parseInt(buntetesModifyList.item(i).getTextContent());
            penalty_value -= 2500;
            buntetesModifyList.item(i).setTextContent(Integer.toString(penalty_value));
        }

        NodeList buntetesQueryList = doc.getDocumentElement().getElementsByTagName("buntetes");
        for (int i = 0; i < buntetesQueryList.getLength(); i++) {
            NodeList query = buntetesQueryList.item(i).getChildNodes();
            for (int j = 0; j < query.getLength(); j++) {
                if (query.item(j).getNodeName().equals("osszeg")) {
                    System.out.println("{buntetes}:");
                    listData(buntetesQueryList.item(i).getChildNodes(), "");
                }
            }
        }

        System.out.println("-------------------------------------------------------------");

        //A ledolgozott evek erteket noveli meg eggyel
        NodeList alkalmazottModifyList = doc.getDocumentElement().getElementsByTagName("ledolgozott_evek");
        for (int i = 0; i < alkalmazottModifyList.getLength(); i++) {
            int worked_value = Integer.parseInt(alkalmazottModifyList.item(i).getTextContent());
            worked_value++;
            alkalmazottModifyList.item(i).setTextContent(Integer.toString(worked_value));
        }

        NodeList alkalmazottQueryList = doc.getDocumentElement().getElementsByTagName("alkalmazott");
        for (int i = 0; i < alkalmazottQueryList.getLength(); i++) {
            NodeList query = alkalmazottQueryList.item(i).getChildNodes();
            for (int j = 0; j < query.getLength(); j++) {
                if (query.item(j).getNodeName().equals("ledolgozott_evek")) {
                    System.out.println("{alkalmazott}:");
                    listData(alkalmazottQueryList.item(i).getChildNodes(), "");
                }
            }
        }

        System.out.println("-------------------------------------------------------------");

        //A traffipaxok sebesseghatarjat noveli 20-szal
        NodeList traffipaxModifyList = doc.getDocumentElement().getElementsByTagName("sebesseghatar"); //Getting the "osszeg" nodes
        for (int i = 0; i < traffipaxModifyList.getLength(); i++) {
            int speedlimit_value = Integer.parseInt(traffipaxModifyList.item(i).getTextContent());
            speedlimit_value += 20;
            traffipaxModifyList.item(i).setTextContent(Integer.toString(speedlimit_value));
        }

        NodeList traffipaxQueryList = doc.getDocumentElement().getElementsByTagName("traffipax");
        for (int i = 0; i < traffipaxQueryList.getLength(); i++) {
            NodeList query = traffipaxQueryList.item(i).getChildNodes();
            for (int j = 0; j < query.getLength(); j++) {
                if (query.item(j).getNodeName().equals("sebesseghatar")) {
                    System.out.println("{traffipax}:");
                    listData(traffipaxQueryList.item(i).getChildNodes(), "");
                }
            }
        }

        System.out.println("-------------------------------------------------------------");

        //Az alkalmazottak fizeteset csokkenti 50000 Ft-tal
        NodeList alkalmazott2ModifyList = doc.getDocumentElement().getElementsByTagName("fizetes"); //Getting the "osszeg" nodes
        for (int i = 0; i < alkalmazott2ModifyList.getLength(); i++) {
            int payment_value = Integer.parseInt(alkalmazott2ModifyList.item(i).getTextContent());
            payment_value -= 50000;
            alkalmazott2ModifyList.item(i).setTextContent(Integer.toString(payment_value));
        }

        NodeList alkalmazott2QueryList = doc.getDocumentElement().getElementsByTagName("alkalmazott");
        for (int i = 0; i < alkalmazott2QueryList.getLength(); i++) {
            NodeList query = alkalmazott2QueryList.item(i).getChildNodes();
            for (int j = 0; j < query.getLength(); j++) {
                if (query.item(j).getNodeName().equals("fizetes")) {
                    System.out.println("{alkalmazott}:");
                    listData(alkalmazott2QueryList.item(i).getChildNodes(), "");
                }
            }
        }

        System.out.println("-------------------------------------------------------------");

        //Budapestre költözteti az autópályafelügyeleteket az ország pontjairól
        NodeList autopalyafelugyeletModifyList = doc.getDocumentElement().getElementsByTagName("telepules"); //Getting the "osszeg" nodes
        for (int i = 0; i < autopalyafelugyeletModifyList.getLength(); i++) {
            autopalyafelugyeletModifyList.item(i).setTextContent("Budapest");
        }

        NodeList autopalyafelugyeletQueryList = doc.getDocumentElement().getElementsByTagName("autopalya_felugyelet");
        for (int i = 0; i < autopalyafelugyeletQueryList.getLength(); i++) {
            NodeList query = autopalyafelugyeletQueryList.item(i).getChildNodes();
            for (int j = 0; j < query.getLength(); j++) {
                if (query.item(j).getNodeName().equals("telepules")) {
                    System.out.println("{autopalya_felugyelet}:");
                    listData(autopalyafelugyeletQueryList.item(i).getChildNodes(), "");
                }
            }
        }

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
}
