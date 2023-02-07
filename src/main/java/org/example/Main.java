package org.example;
import org.example.Parser;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        String filePath = "data.xml";
        Document doc = createDoc(filePath);

        Node root = doc.getDocumentElement();
        Parser.read(root);

        Parser.employees.forEach(System.out::println);

        String jsonString = JsonConverter.listToJson(Parser.employees);
        System.out.println(jsonString);

        String jsonFile = "data2.json";
        JsonConverter.createJson(jsonString, jsonFile);
    }// main

    public static Document createDoc(String file) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(new File(file));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}