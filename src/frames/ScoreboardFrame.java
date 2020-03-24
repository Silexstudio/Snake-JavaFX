package frames;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import utilities.JSON.Scoreboard;
import utilities.JSON.Settings;
import utilities.SoundPlayer;

public class ScoreboardFrame extends Frame{
    public ScoreboardFrame(){
        super();
        
        startVideo(5, false);
        SoundPlayer.getInstance().play(3, true);
        
        assignStylesheet(3);
        
        Scoreboard scoreboard = Scoreboard.getInstance();
        ArrayList<String[]> arr = scoreboard.getAll();
        
        ObservableList<Node> c = getChildren();

        Label btn1 = new Label();
        btn1.setId("btn1");
        btn1.setTranslateX(1462);
        btn1.setTranslateY(160);
        
        btn1.setOnMouseClicked((MouseEvent mouseEvent) -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                FrameController.getInstance().changeFrame(new MainFrame());
            }
        });
        
        AnchorPane txts = new AnchorPane();
        
        int i = 0;
        int pos[] = {136,640,1120,962};
        for(String[] s : arr){
            Text txt = null;
            for(int j = 0; j < 4; j++){
                txt = new Text();
                txt.setTranslateX(pos[j]);
                txt.setTranslateY(428 + i * 62.75);
                txt.setText(s[j]);
                txt.setFill(new Color(1,1,1,.75));
                txts.getChildren().add(txt);
            }
            txt.setTextAlignment(TextAlignment.RIGHT);
            txt.setWrappingWidth(800);
            i++;
            if(i == 10)break;
        }
        
        if(Settings.getInstance().getSetting(0)){
            txts.setId("table");
            txts.setOpacity(0);
            btn1.setOpacity(0);
            
            new Thread(new Runnable(){
                @Override
                public void run(){
                    try {
                        Thread.sleep(1700);
                        double i = 0;
                        while(i != 1){
                            btn1.setOpacity(i);
                            i += 0.05;
                            Thread.sleep(1000/60);
                        }

                    } catch (InterruptedException ex) {
                        Logger.getLogger(ScoreboardFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }).start();
            
            new Thread(new Runnable(){
                @Override
                public void run(){
                    try {
                        Thread.sleep(1500);
                        double i = 0;
                        while(i != 1){
                            txts.setOpacity(i);
                            i += 0.025;
                            Thread.sleep(1000/60);
                        }

                    } catch (InterruptedException ex) {
                        Logger.getLogger(ScoreboardFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }).start();
        }
        
        c.add(txts);
        getChildren().add(btn1);
    }
}
