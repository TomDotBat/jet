package dev.tomdotbat.jet.documents;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class DocumentStorage {
    public static Document readDocument(String location) { //Creates a document object from a file at a specified location
        Document document = null;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(location)); //Create a file reader

            String body = "";
            String line = reader.readLine();

            while(line != null) { //Read until there's no lines left to read
                body += line + "\n";
                line = reader.readLine();
            }

            reader.close();

            body = body.substring(0, body.length() - 1); //Trim off the last newline char

            document = new Document(body, location); //Build the document object
        }
        catch (Exception ignored) {}

        return document;
    }

    public static void writeDocument(Document document) { //Writes the body of a document object to the location it is set to
        try {
            FileWriter writer = new FileWriter(document.getLocation()); //Create a file writer
            writer.write(document.getBody()); //Write the body of the document to the file
            writer.flush();
            writer.close();
        }
        catch (Exception ignored) {}
    }
}
