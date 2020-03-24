package utilities.JSON;

import frames.SettingsFrame;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReader {
    protected JSONObject json;
    protected String file, defval;
        
    public JSONReader(){}
    
    public void read(){
        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            FileReader f = new FileReader(file + ".json");
            obj = parser.parse(f);
            f.close();
        } catch (FileNotFoundException ex) {
            try {
                obj = parser.parse(defval);
            } catch (ParseException ex1) {
            }
        } catch (IOException ex) {
        } catch (ParseException ex) {
        }

        json = (JSONObject) obj;
    }
}
