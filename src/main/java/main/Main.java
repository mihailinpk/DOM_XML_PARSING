package main;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;

@SpringBootApplication
public class Main implements CommandLineRunner {

    private final String XML_FILE_PATH = "src\\main\\resources\\example.xml";
    private final String TAG_NAME = "employee";
    private final String FIELD1_NAME = "name";
    private final String FIELD2_NAME = "job";

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(XML_FILE_PATH);

        ArrayList<Employee> employeesList = new ArrayList<>();

        NodeList employeesNodeList = document.getDocumentElement().getElementsByTagName(TAG_NAME);

        // парсим и выводим на экран всех сотрудников
        for(int i = 0; i < employeesNodeList.getLength(); i++)  {
            Node employee = employeesNodeList.item(i);
            NodeList emloyeeNodeList = employee.getChildNodes();
            String name = "";
            String job = "";
            for (int j = 0; j < emloyeeNodeList.getLength(); j++)   {
                Node node = emloyeeNodeList.item(j);
                if (node.getNodeName().equals(FIELD1_NAME))  {
                    name = node.getTextContent();
                }
                if (node.getNodeName().equals(FIELD2_NAME))   {
                    job = node.getTextContent();
                }
            }
            employeesList.add(new Employee(name, job));
        }

        employeesList.forEach(System.out::println);

    }

}