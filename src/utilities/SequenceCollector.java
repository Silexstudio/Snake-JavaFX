package utilities;

import java.util.ArrayList;
import utilities.SequenceLoader;

public class SequenceCollector {
    static private SequenceCollector instance = null;
    public ArrayList<SequenceLoader> arr;
    
    private SequenceCollector(){
        arr = new ArrayList<SequenceLoader>();
        
        //add("p1", "jpg", 58, 60);
        add("p1", "gif", 1, 60);
        add("p2", "gif", 1, 60);
        add("p2", "gif", 1, 60);
        add("p1", "gif", 1, 60);
        add("apple", "png", 94, 60);
    }
    
    private void add(String s, String format, int length, int framerate){
        arr.add(new SequenceLoader(s,format,length,framerate));
    }
    
    public SequenceLoader get(int seq){
        return arr.get(seq);
    }
    
    static public SequenceCollector getInstance(){
        if(instance == null){
            instance = new SequenceCollector();
        }
        return instance;
    }   
}
