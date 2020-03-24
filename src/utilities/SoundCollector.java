package utilities;

import java.util.ArrayList;
import javafx.scene.media.Media;

public class SoundCollector {
    private ArrayList<Media> arr;
    private static SoundCollector instance = null;
    
    private SoundCollector(){
        arr = new ArrayList<Media>();
        
        add("menu");
        add("credits");
        add("button");
        add("score");
    }
    
    private void add(String s){
        arr.add(new Media(getClass().getResource("assets/sound/" + s + ".mp3").toExternalForm()));
    }
    
    public Media get(int i){
        return arr.get(i);
    }
    
    public static SoundCollector getInstance(){
        if(instance == null) {
            instance = new SoundCollector();
        }
        return instance;
    }
}