package dev.tomdotbat.jet.documents;

public class Document {
    public Document() { //Constructs an empty document
        super();
    }

    public Document(String body, String location) { //Constructs a document with existing text and location
        this.body = body;
        this.location = location;
    }

    public String getBody() { //Document body getter/setter
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getLocation() { //Document location getter/setter
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private String body = "";
    private String location = "Untitled";
}
