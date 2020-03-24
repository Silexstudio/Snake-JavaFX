package utilities.JSON;

import frames.SettingsFrame;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Scoreboard extends JSONReader{
    private static Scoreboard instance;

    private Scoreboard(){
        file = "scoreboard";
        defval = "{ \"arr\" : [] }";
        read();
    }
    
    public ArrayList<String[]> getAll(){
        ArrayList<String[]> ret = new ArrayList<String[]>();
        JSONArray arr = (JSONArray) json.get("arr");
        for(Object i : arr){
            JSONArray act = (JSONArray) i;
            ret.add(new String[]{(String) act.get(0), (String) act.get(1), (String) act.get(2), (String) act.get(3)});
        }
        return ret;
    }
    
    public void put(String title, String score){
        JSONArray arr = (JSONArray) json.get("arr");
        JSONArray newArr = new JSONArray();
        newArr.add(title);
        newArr.add(score);
        arr.add(newArr);

        FileWriter f;
        try {
            f = new FileWriter(file + ".json");
            f.write(json.toJSONString());
            f.close();
        } catch (IOException ex) {
            Logger.getLogger(SettingsFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Scoreboard getInstance(){
        if(instance == null){
            instance = new Scoreboard();
        }
        return instance;
    }
}
