package utilities;

import java.util.ArrayList;
import javafx.scene.image.Image;

public class SequenceLoader {
    public ArrayList<Image> arr;
    private int length;
    private int framerate;
    
    public SequenceLoader(String s, String format, int length, int framerate){
        arr = new ArrayList<Image>();
        this.length = length;
        this.framerate = framerate;
        for(int i = 0; i < length; i++){
            arr.add(new Image(getClass().getResource("assets/images/" + s + "/" + i + "." + format).toExternalForm()));
        }
    }
    
    public Image get(int i){
        return arr.get(i);
    }
    
    public int getLength(){
        return length;
    }
    
    public int getFramerate(){
        return framerate;
    }
}