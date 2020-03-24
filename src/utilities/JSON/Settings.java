package utilities.JSON;
import frames.SettingsFrame;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import utilities.SoundPlayer;

public class Settings extends JSONReader{
    private static Settings instance;
    
    private Settings(){
        file = "settings";
        defval = "{\"0\" : true, \"1\" : true, \"2\" : true}";
        read();
    }
    
    public void change(int i){
        Boolean newVal = !getSetting(i);
        json.put(Integer.toString(i), newVal);

        if(i == 2){
            if(newVal) SoundPlayer.getInstance().unmute();
            else SoundPlayer.getInstance().mute();
        }
        
        FileWriter f;
        try {
            f = new FileWriter(file + ".json");
            f.write(json.toJSONString());
            f.close();
        } catch (IOException ex) {
            Logger.getLogger(SettingsFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Boolean getSetting(int i){
        return (Boolean) json.get(Integer.toString(i));
    }
    
    public static Settings getInstance(){
        if(instance == null){
            instance = new Settings();
        }
        return instance;
    }
}
