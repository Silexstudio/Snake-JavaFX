package utilities;

import java.util.ArrayList;
import javafx.scene.media.Media;

public class VideoCollector {
    private ArrayList<Media> arr;
    private static VideoCollector instance = null;
    
    private VideoCollector(){
        arr = new ArrayList<Media>();
        
        add("bg/untitled");
        add("bg/credits");
        add("bg/credits_loop");
        add("bg/basic");
        add("bg/basic_loop");
        add("bg/scoreboard");
        add("bg/scoreboard_loop");
        add("bg/game");
    }
    
    public Media get(int i){
        return arr.get(i);
    }
    
    private void add(String s){
        arr.add(new Media(getClass().getResource("assets/videos/" + s + ".mp4").toExternalForm()));
    }
    
    public static VideoCollector getInstance(){
        if(instance == null) {
            instance = new VideoCollector();
        }
        return instance;
    }
}
