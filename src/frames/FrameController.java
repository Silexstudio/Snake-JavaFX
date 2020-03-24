package frames;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class FrameController {
    private Scene scene;
    private Stage stage;
    private Frame frame;
    static private FrameController instance = null;
    
    public FrameController(){}
    
    public void init(Scene scene, Stage stage){
        this.scene = scene;
        this.stage = stage;
        changeFrame(new MainFrame());
    }
    
    public Scene getScene(){
        return scene;
    }
    
    public Stage getStage(){
        return stage;
    }
    
    public Frame getFrame(){
        return frame;
    }
    
    public void changeFrame(Frame f){
        if(frame != null) frame.stop();
        frame = null;
        frame = f;
        scene.setRoot(f);
    }
    
    public void translate(Boolean maximized){
        frame.translate(maximized);
    }
    
    public static FrameController getInstance(){
        if(instance == null){
            instance = new FrameController();
        }
        return instance;
    }
}
