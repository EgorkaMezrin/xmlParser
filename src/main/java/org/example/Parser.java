package org.example;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static List<Employee> employees = new ArrayList<>();
    public static void read(Node node) {
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node_ = nodeList.item(i);
            if (Node.ELEMENT_NODE == node_.getNodeType()) {
                if (node_.getNodeName().equals("employee")) {
                    Employee empl = createEmployee(node_);
                    employees.add(empl);
                } else {
                    Element element = (Element) node_;
                    NamedNodeMap map = element.getAttributes();
                    for (int j = 0; j < map.getLength(); j++) {
                        String attrName = map.item(j).getNodeName();
                        String attrValue = map.item(j).getNodeValue();
                    }
                    read(node_);
                }
            }
        }
    }// read

    private static Employee createEmployee(Node node) {
        Element element = (Element) node;
        String id = element.getElementsByTagName("id").item(0).getTextContent();
        String firstName = element.getElementsByTagName("firstName").item(0).getTextContent();
        String lastName = element.getElementsByTagName("lastName").item(0).getTextContent();
        String country = element.getElementsByTagName("country").item(0).getTextContent();
        String age = element.getElementsByTagName("age").item(0).getTextContent();
        Employee emp = new Employee(Long.parseLong(id), firstName, lastName, country, Integer.parseInt(age));
        return emp;
    }
}