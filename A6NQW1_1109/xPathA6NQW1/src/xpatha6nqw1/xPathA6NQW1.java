package xpatha6nqw1;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;

public class xPathA6NQW1 {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.parse("studentA6NQW1.xml");
        document.getDocumentElement().normalize();

        XPath xPath = XPathFactory.newInstance().newXPath();

        String expression = "/class/student";
        // String expression = "//student[@id=02]";
        // String expression = "//student";
        // String expression = "/class/student[2]";
        // String expression = "/class/student[last()]";
        // String expression = "/class/student[last()-1]";
        // String expression = "/class/";
        // String expression = "/class/[@>=1]";
        // String expression = "/descendant-or-self::";
        // String expression = "/class/student[20<kor]";
        //String expression = "/class/student/keresztnev | /class/student/vezeteknev";

        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            System.out.println("Aktuális elem: " + node.getNodeName());

            if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {
                Element element = (Element) node;

                System.out.println("Hallgató ID: " + element.getAttribute("id"));
                System.out.println("Hallgató keresztneve: " + element.getElementsByTagName("keresztnev").item(0).getTextContent());
                System.out.println("Hallgató vezetekneve: " + element.getElementsByTagName("vezeteknev").item(0).getTextContent());
                System.out.println("Hallgató beceneve: " + element.getElementsByTagName("becenev").item(0).getTextContent());
                System.out.println("Hallgató kora: " + element.getElementsByTagName("kor").item(0).getTextContent());
            }
        }
    }
}