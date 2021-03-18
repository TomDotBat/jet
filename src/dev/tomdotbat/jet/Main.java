package dev.tomdotbat.jet;

import dev.tomdotbat.jet.windows.EditorWindow;

public class Main {
    public static void main(String[] args) { //Program entry point
        EditorWindow editor = new EditorWindow(); //Create our editor window and display it
        editor.setVisible(true);
    }
}
