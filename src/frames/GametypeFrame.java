package frames;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.stage.Stage;
import nodes.ChoiceLabel;
import nodes.ChoiceWrapper;

public class GametypeFrame extends Frame{
    private Thread ChoiceLabelController;
    
    public GametypeFrame(){
        super();
                
        startVideo(3, false);
        assignStylesheet(4);
        
        AnchorPane root = new AnchorPane();
        root.setBackground(Background.EMPTY);
        SubScene sub = new SubScene(root, 1920, 1080);
        PerspectiveCamera camera = new PerspectiveCamera(true);
        sub.setCamera(camera);
        camera.setFieldOfView(90);
        
        Group cameraGroup = new Group();
        cameraGroup.getChildren().add(camera);
        
        root.getChildren().add(cameraGroup);
        
        cameraGroup.setTranslateX(1920/2);
        cameraGroup.setTranslateY(1080/2);
        
        camera.setFarClip(6000);
        
        ChoiceLabel cl[] = new ChoiceLabel[4];
        ChoiceWrapper clwrapper[] = new ChoiceWrapper[4];
        for(int i = 0; i < 4; i++){
            cl[i] = new ChoiceLabel(i);
            clwrapper[i] = new ChoiceWrapper(i);
            root.getChildren().add(cl[i]);
            root.getChildren().add(clwrapper[i]);
        }
        
        ChoiceLabelController = new Thread(new Runnable(){
            @Override
            public void run(){
                while(!ChoiceLabelController.isInterrupted()){
                    for(int i = 0; i < 4; i++){
                        if(clwrapper[i].isHover()){
                            cl[i].rotateForward();
                        }
                        else{
                            cl[i].rotateBackward();
                        }
                    }
                    try {
                        Thread.sleep(1000/60);
                    } catch (InterruptedException ex) {}
                }
            }
        });
        
        ChoiceLabelController.start();

        Label btn1 = new Label();
        btn1.setId("btn1");
        btn1.setTranslateX(92);
        btn1.setTranslateY(810);
        
        btn1.setOnMouseClicked((MouseEvent mouseEvent) -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                leave();
                FrameController.getInstance().changeFrame(new MainFrame());
            }
        });
        
        getChildren().add(sub);
        getChildren().add(btn1);
    }
    
    public void leave(){
        ChoiceLabelController.interrupt();
    }
}
