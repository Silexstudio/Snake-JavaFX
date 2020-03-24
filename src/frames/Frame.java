package frames;

import utilities.VideoCollector;
import utilities.StylesheetCollector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import utilities.*;
import utilities.JSON.Settings;

public class Frame extends AnchorPane{
    private SequencePlayer seqPlayer;
    protected Thread translateFirst = new Thread(new Runnable(){
        @Override
        public void run(){  
            double w = stage.getWidth();
            while(w == 0){
                w = stage.getWidth();
            }
            translate(false);
        }
    });
    protected Stage stage;
    protected Scene scene;
    protected MediaPlayer sound;
    private int videoIndex;
    private Media media;
    private MediaPlayer player, prePlayer;
    private MediaView view, preView;
    private Thread playerLooper = new Thread(new Runnable(){
        @Override
        public void run(){
            try{
                while(!playerLooper.isInterrupted() && player != null){
                    prePlayer = new MediaPlayer(media);
                    preView = new MediaView(prePlayer);

                    Thread.sleep((long) media.getDuration().toMillis());

                    Platform.runLater(new Runnable(){
                        @Override
                        public void run(){
                            getChildren().remove(view);
                            getChildren().add(preView);
                            preView.toBack();
                        }
                    });

                    view = preView;
                    player.dispose();
                    player = prePlayer;
                    player.play();
                }
            } catch (InterruptedException ex) {
                prePlayer.dispose();
                prePlayer = null;
                preView = null;
            }
        }
    });
    
    private Thread playerDelayer = new Thread(new Runnable(){
        @Override
        public void run(){
            Media tmpMedia;
            MediaPlayer tmpPlayer = null;
            MediaView tmpView;
            try {
                if(!playerDelayer.isInterrupted() && player != null){
                    tmpMedia = media;
                    tmpPlayer = player;
                    tmpView = view;

                    media = VideoCollector.getInstance().get(videoIndex + 1);
                    player = new MediaPlayer(media);
                    view = new MediaView(player);

                    Thread.sleep((long) tmpMedia.getDuration().toMillis());

                    final MediaView finalTmpView = tmpView;
                    Platform.runLater(new Runnable(){
                        @Override
                        public void run(){
                            getChildren().remove(finalTmpView);
                            if(view != null){
                                getChildren().add(view);
                                view.toBack();
                            }
                        }
                    });

                    tmpPlayer.dispose();
                    player.setOnPlaying(new Runnable(){
                        @Override
                        public void run(){
                            playerLooper.start();
                        }
                    });
                    player.play();
                }
            } catch (InterruptedException ex) {
                tmpPlayer.dispose();
                tmpMedia = null;
                tmpView = null;
            }
        }
    });
    
    protected Frame(){
        super();
        setBackground(Background.EMPTY);
        translateFirst.start();
        FrameController fc = FrameController.getInstance();
        this.scene = fc.getScene();
        this.stage = fc.getStage();
    }
    
    public void stop(){
        if(player != null){
            player.dispose();
        }
        if(sound != null){
            sound.dispose();
        }
        if(playerLooper != null){
            playerLooper.interrupt();
        }
        if(playerDelayer != null){
            playerDelayer.interrupt();
        }
        if(seqPlayer != null){
            seqPlayer.stop();
        }
        System.gc();
    }
    
    public void startSeq(int i, Boolean loop){
        Label bg = new Label();
        getChildren().add(bg);
        
        seqPlayer = new SequencePlayer(bg, i, loop);
        seqPlayer.start();
    }
    
    public void startVideo(int i, Boolean loop){
        if(Settings.getInstance().getSetting(0)){
            videoIndex = i;

            media = VideoCollector.getInstance().get(i);
            if(media == null) System.out.println("SOMETHING WENT REALLY FCKING WRONG.");
            player = new MediaPlayer(media);
            view = new MediaView(player);

            player.play();
            getChildren().add(view);
            view.toBack();

            player.setOnPlaying(new Runnable(){
                @Override
                public void run(){
                    if(player != null){
                        if(loop){
                            playerLooper.start();
                        }
                        else{
                            playerDelayer.start();
                        }
                    }
                }
            });
        }
        else{
            Label bg = new Label();
            bg.setId("low-bg");
            getChildren().add(bg);
            bg.toBack();
        }
    }
    
    protected void translate(Boolean maximized){
        if(scene != null){
            if(maximized){
                stage.setFullScreen(true);
            }
            double size = Math.max(scene.getWidth(), scene.getHeight() * 16d/9d)/1920d;
            Scale s = new Scale(size, size);
            s.setPivotX(0);
            s.setPivotY(0);
            getTransforms().setAll(s);
        }
    }
    
    protected void assignStylesheet(int i){
        scene.getStylesheets().clear();
        scene.getStylesheets().add(StylesheetCollector.getInstance().get(i));
    }
}
