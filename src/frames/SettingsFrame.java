package frames;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import utilities.SoundCollector;
import utilities.JSON.Settings;
import utilities.SoundEffectPlayer;
import utilities.VideoCollector;

public class SettingsFrame extends Frame{
      public SettingsFrame(){
        super();
        
        startVideo(3, false);

        assignStylesheet(2);
        
        Label btn1 = new Label();
        btn1.setId("btn1");
        btn1.setTranslateX(915);
        btn1.setTranslateY(670);
        
        btn1.setOnMouseClicked((MouseEvent mouseEvent) -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                FrameController.getInstance().changeFrame(new MainFrame());
            }
        });

        ObservableList<Node> c = getChildren();
                    
        Label panel = new Label();
        panel.setId("panel");
        panel.setTranslateX(700);
        panel.setTranslateY(340);
        c.add(panel);

        Settings settings = Settings.getInstance();
        
        Label s[] = new Label[3];
        for(int i = 0; i < 3; i++){
            s[i] = new Label();
            
            if(settings.getSetting(i)) s[i].setId("setting-active");
            else s[i].setId("setting");
            
            s[i].setTranslateX(725);
            s[i].setTranslateY(480 + i * 45 + i * 25);
            
            final int finalI = i;
            s[i].setOnMouseClicked((MouseEvent mouseEvent) -> {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    settings.change(finalI);
                    SoundEffectPlayer.getInstance().play(2);
                    if(settings.getSetting(finalI)) s[finalI].setId("setting-active");
                    else s[finalI].setId("setting");
                }
            });
            c.add(s[i]);
        }
        
        c.add(btn1);
      }
}
