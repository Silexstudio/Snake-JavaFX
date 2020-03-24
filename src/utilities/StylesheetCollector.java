package utilities;

import java.util.ArrayList;
import javafx.scene.text.Font;

public class StylesheetCollector {
    private ArrayList<String> arr;
    private static StylesheetCollector instance = null;
    
    private StylesheetCollector(){
        arr = new ArrayList<String>();
        
        Font.loadFont(getClass().getResource("assets/font/bebas.ttf").toExternalForm(), 42);
        
        add("main");
        add("credits");
        add("settings");
        add("scoreboard");
        add("gametype");
        add("tutorial");
        add("game");
    }
    
    public String get(int i){
        return arr.get(i);
    }
    
    private void add(String s){
        arr.add(getClass().getResource("assets/css/" + s + ".css").toExternalForm());
    }
    
    public static StylesheetCollector getInstance(){
        if(instance == null) {
            instance = new StylesheetCollector();
        }
        return instance;
    }
}
