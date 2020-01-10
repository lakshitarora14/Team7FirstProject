
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XMLFileIHandler implements IHandler {
    MyCollectionClass MyCollection = MyCollectionClass.getInstance();
    String path = "/Users/kevalmehta/IdeaProjects/coviamproject/src/main/java/inputfiles/employee.xml";
    public  void read() {
        List<Employee>list=new ArrayList<Employee>();
        try {

            File file = new File(path);//constructor of file class having file path as argument
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("employee");//nodelist give ordered list of nodes
            for (int itr = 0; itr < nodeList.getLength(); itr++) {//getlength gives no of nodes
                Node node = nodeList.item(itr);//item returns the node at specified index
                System.out.println("\nNode Name :" + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;//element represent element or properties in xml document

                    Employee e=new Employee();
                    Date date1=new SimpleDateFormat("dd/MM/yyyy").parse((eElement.getElementsByTagName("dateOfBirth").item(0).getTextContent()));
                    e.setDateOfBirth(date1);
                    e.setExperience(Double.parseDouble(eElement.getElementsByTagName("experience").item(0).getTextContent()));
                    e.setFirstName(eElement.getElementsByTagName("firstName").item(0).getTextContent());
                    e.setLastName(eElement.getElementsByTagName("lastName").item(0).getTextContent());

                    MyCollection.add(e);

                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    public void write() {
        try {
            String newFilePath = "/Users/kevalmehta/IdeaProjects/coviamproject/src/main/java/outputfiles/newCreate.xml";
            File file = new File(newFilePath);

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element root = document.createElement("employees");
            TransformerFactory transformerFactory = null;
            Transformer transformer = null;
            DOMSource domSource = null;
            StreamResult streamResult = null;
            document.appendChild(root);
            for (int i = 0; i < 100; i++) {
                Employee obj = MyCollection.get();//my collection is your collection of 300 employee elements
                Element employee = document.createElement("employee");
                root.appendChild(employee);
                Element firstName = document.createElement("firstname");
                firstName.appendChild(document.createTextNode(obj.getFirstName()));
                employee.appendChild(firstName);
                Element lastname = document.createElement("lastname");
                lastname.appendChild(document.createTextNode(obj.getLastName()));
                employee.appendChild(lastname);
                Element dateOfBirth = document.createElement("dob");
                dateOfBirth.appendChild(document.createTextNode((obj.getDateOfBirth().toString())));
                employee.appendChild(dateOfBirth);
                Element experience = document.createElement("experience");
                experience.appendChild(document.createTextNode(Double.toString((Double)obj.getExperience())));
                employee.appendChild(experience);
                transformerFactory = TransformerFactory.newInstance();
                transformer = transformerFactory.newTransformer();
                domSource = new DOMSource(document);
                streamResult = new StreamResult(file);
                transformer.transform(domSource, streamResult);
            }

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}