package frames;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utilities.SoundPlayer;

public class MainFrame extends Frame{
    public MainFrame(){
        super();
                
        startVideo(0, true);
        SoundPlayer.getInstance().play(0, true);
       
        assignStylesheet(0);
        
        Label btn1 = new Label();
        btn1.setId("btn1");
        btn1.setTranslateX(727);
        btn1.setTranslateY(440);
        
        Label btn2 = new Label();
        btn2.setId("btn2");
        btn2.setTranslateX(727);
        btn2.setTranslateY(525);
        
        Label btn3 = new Label();
        btn3.setId("btn3");
        btn3.setTranslateX(727);
        btn3.setTranslateY(610);
        
        Label btn4 = new Label();
        btn4.setId("btn4");
        btn4.setTranslateX(727);
        btn4.setTranslateY(695);
        
        Label btn5 = new Label();
        btn5.setId("btn5");
        btn5.setTranslateX(727);
        btn5.setTranslateY(780);
        
        btn1.setOnMouseClicked((MouseEvent mouseEvent) -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                FrameController.getInstance().changeFrame(new GametypeFrame());
            }
        });
        
        btn2.setOnMouseClicked((MouseEvent mouseEvent) -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                FrameController.getInstance().changeFrame(new ScoreboardFrame());
            }
        });
        
        btn3.setOnMouseClicked((MouseEvent mouseEvent) -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                FrameController.getInstance().changeFrame(new SettingsFrame());
            }
        });
        
        btn4.setOnMouseClicked((MouseEvent mouseEvent) -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                FrameController.getInstance().changeFrame(new CreditsFrame());
            }
        });
        
        btn5.setOnMouseClicked((MouseEvent mouseEvent) -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                Platform.exit();
                System.exit(0);
            }
        });
        
        ObservableList<Node> c = getChildren();
        c.add(btn1);
        c.add(btn2);
        c.add(btn3);
        c.add(btn4);
        c.add(btn5);
    }
}
