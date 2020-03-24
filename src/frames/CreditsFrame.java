package frames;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utilities.SoundPlayer;

public class CreditsFrame extends Frame{
      public CreditsFrame(){
        super();
                
        startVideo(1, false);
        SoundPlayer.getInstance().play(1, true);
        
        assignStylesheet(1);
        
        Label btn1 = new Label();
        btn1.setId("btn5");
        btn1.setTranslateX(785);
        btn1.setTranslateY(644);
        
        btn1.setOnMouseClicked((MouseEvent mouseEvent) -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                FrameController.getInstance().changeFrame(new MainFrame());
            }
        });
        
        ObservableList<Node> c = getChildren();
        c.add(btn1);
      }
}
