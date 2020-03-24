package main;

import utilities.SequenceCollector;
import utilities.StylesheetCollector;
import utilities.VideoCollector;
import utilities.SoundCollector;
import frames.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.paint.Color;

public class main extends Application {
    private Boolean b;
    private FrameController fc;
    
    @Override
    public void start(Stage primaryStage) {
        
        new Thread(new Runnable(){
            @Override
            public void run(){
                while(true){
                    System.gc();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
        
        
        b = false;
        Scene scene = new Scene(new StackPane(), 1280, 720, Color.BLACK);
        
        primaryStage.setScene(scene);
        
        //primaryStage.setFullScreen(true);
        primaryStage.setTitle("Snake");
        primaryStage.show();
        
        SequenceCollector sec = SequenceCollector.getInstance();
        VideoCollector vc = VideoCollector.getInstance();
        StylesheetCollector stc = StylesheetCollector.getInstance();
        SoundCollector sc = SoundCollector.getInstance();
        
        fc = FrameController.getInstance();
        fc.init(scene, primaryStage);
        
        bindListeners(primaryStage);
        
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
    }

    private void bindListeners(Stage primaryStage){
        ChangeListener<Object> listener1 = new ChangeListener<Object>(){
            @Override
            public void changed(ObservableValue<? extends Object> observable, Object o1, Object o2){
                fc.translate(false);
            }
        };
        
        ChangeListener<Object> listener2 = new ChangeListener<Object>(){
            @Override
            public void changed(ObservableValue<? extends Object> observable, Object o1, Object o2){
                fc.translate(true);
            }
        };
        
        primaryStage.widthProperty().addListener(listener1);
        primaryStage.heightProperty().addListener(listener1);
        primaryStage.maximizedProperty().addListener(listener2);
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
