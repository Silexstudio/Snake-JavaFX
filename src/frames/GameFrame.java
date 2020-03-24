package frames;

import game.Engine;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import utilities.SequenceCollector;

public class GameFrame extends Frame {
    private Engine engine;
    
    public GameFrame(String arr[]){
        
        startVideo(7, true);
        assignStylesheet(6);
        
        /*Box b = new Box(100, 100, 100);
        PhongMaterial mat = new PhongMaterial();
        mat.setDiffuseMap(new Image(SequenceCollector.getInstance().getClass().getResource("assets/images/test.jpg").toExternalForm()));
        b.setMaterial(mat);
        b.setTranslateX(600);
        b.setTranslateY(600);
        b.setTranslateZ(500);*/
        
        /*Box box = new Box(60, 60, 60);
        box.setTranslateX(-900);
        box.setTranslateY(800);
        PhongMaterial mat = new PhongMaterial();
        mat.setDiffuseMap(SequenceCollector.getInstance().get(0).get(0));
        box.setMaterial(mat);
        
        Box box1 = new Box(60, 60, 60);
        box1.setTranslateX(-900);
        box1.setMaterial(mat);
        
        new Thread(new Runnable(){
            @Override
            public void run(){
                int i = 1;
                while(i != 61){
                    box1.setTranslateY(800-i);
                    box1.setHeight(i);
                    try {
                        Thread.sleep(1000/60);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    i++;
                }
            }
        }).start();
        
        Box b = new Box(120, 120, 0);
        b.setTranslateX(-900);
        b.setTranslateY(800);
        b.setTranslateZ(-30);
        PhongMaterial mat2 = new PhongMaterial();
        mat2.setDiffuseMap(new Image(SequenceCollector.getInstance().getClass().getResource("assets/images/test.png").toExternalForm()));
        //mat2.setDiffuseColor(Color.BLUE);
        b.setMaterial(mat2);*/
        
        AnchorPane root = new AnchorPane();
        root.setBackground(Background.EMPTY);
        
        SubScene sub = new SubScene(root, 1920, 1080, true, SceneAntialiasing.BALANCED);
        engine = Engine.getInstance();
        engine.config(arr, sub, root);
        
        //root.getChildren().add(box);
        //root.getChildren().add(box1);
        //root.getChildren().add(b);
        getChildren().add(sub);
        
        
        /*new Thread(new Runnable(){
            @Override
            public void run(){
                while(true){
                    Platform.runLater(new Runnable(){
                        @Override
                        public void run(){
                            b.getTransforms().add(new Rotate(3,0,0,0,Rotate.Y_AXIS));
                            b.getTransforms().add(new Rotate(1.5,0,0,0,Rotate.X_AXIS));
                            b.getTransforms().add(new Rotate(0.35,0,0,0,Rotate.Z_AXIS));
                        }
                    });
                    try {
                        Thread.sleep(1000/60);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();*/
        
    }
}
