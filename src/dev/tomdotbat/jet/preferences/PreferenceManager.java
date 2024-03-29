package dev.tomdotbat.jet.preferences;

import java.io.*;

public class PreferenceManager {
    public static PreferenceManager getInstance() { //Singleton class getter
        if (instance == null) instance = new PreferenceManager();
        return instance;
    }

    public String getFontName() {
        return fontName;
    } //Getters and setters for preferences
    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public int getFontSize() {
        return fontSize;
    }
    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public boolean getWordWrap() {
        return wordWrap;
    }
    public void setWordWrap(boolean wordWrap) {
        this.wordWrap = wordWrap;
    }

    public float getZoomLevel() {
        return zoomLevel;
    }
    public void setZoomLevel(float zoomLevel) {
        this.zoomLevel = zoomLevel;
    }

    public boolean getShowStatusBar() {
        return showStatusBar;
    }
    public void setShowStatusBar(boolean showStatusBar) {
        this.showStatusBar = showStatusBar;
    }

    public boolean getShowIconBar() {
        return showIconBar;
    }
    public void setShowIconBar(boolean showIconBar) {
        this.showIconBar = showIconBar;
    }

    public void savePreferences() { //Saves the current set of preferences to a file
        try {
            FileOutputStream fileOutput = new FileOutputStream("preferences.dat");
            DataOutputStream dataOutput = new DataOutputStream(fileOutput);

            dataOutput.writeInt(fontSize); //Write all of our preferences in the expected order
            dataOutput.writeBoolean(wordWrap);
            dataOutput.writeFloat(zoomLevel);
            dataOutput.writeBoolean(showStatusBar);
            dataOutput.writeBoolean(showIconBar);

            dataOutput.writeInt(fontName.length()); //Indicate the string length of the font name so we know how many chars to read
            dataOutput.writeChars(fontName);

            dataOutput.close();
            fileOutput.close();
        }
        catch (Exception ignored) {}
    }

    private static PreferenceManager instance;

    private PreferenceManager() { //Preference manager constructor, loads preferences from the file if it exists
        try {
            FileInputStream fileInput = new FileInputStream("preferences.dat");
            DataInputStream dataInput = new DataInputStream(fileInput);

            fontSize = dataInput.readInt(); //Read the preferences in the same order they were written
            wordWrap = dataInput.readBoolean();
            zoomLevel = dataInput.readFloat();
            showStatusBar = dataInput.readBoolean();
            showIconBar = dataInput.readBoolean();

            int fontNameLength = dataInput.readInt(); //Builds the fontName string by reading the specified amount of chars from the file
            StringBuilder fontNameBuilder = new StringBuilder();

            for (int i = 0; i < fontNameLength; i++) fontNameBuilder.append(dataInput.readChar());

            fontName = fontNameBuilder.toString();

            fileInput.close();
            dataInput.close();
        }
        catch (Exception ignored) {}
    }

    private int fontSize = 14; //Declare preferences with the defaults
    private String fontName = "Arial";
    private boolean wordWrap = true;
    private float zoomLevel = 1;
    private boolean showStatusBar = true;
    private boolean showIconBar = true;
}
