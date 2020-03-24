package utilities;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import utilities.JSON.Settings;

public class SoundPlayer {
    private static SoundPlayer instance;
    private Media media;
    private MediaPlayer player, prePlayer;
    private Thread playerLooper;
    
    private SoundPlayer(){}
    
    public void play(int i, Boolean loop){
        stop();
        media = SoundCollector.getInstance().get(i);
        player = new MediaPlayer(media);
        if(!Settings.getInstance().getSetting(2)){
            mute();
        }
        player.play();
        
        if(loop){
            player.setOnPlaying(new Runnable(){
                @Override
                public void run(){
                    playerLooper.start();
                    if(!Settings.getInstance().getSetting(2)){
                        mute();
                    }
                }
            });
        }
    }
    
    private void stop(){
        if(prePlayer != null){
            prePlayer.dispose();
            prePlayer = null;
        }
        if(player != null){
            player.dispose();
            playerLooper.interrupt();
        }
        playerLooper = new Thread(new Runnable(){
            @Override
            public void run(){
                try{
                    while(!playerLooper.isInterrupted()){
                        prePlayer = new MediaPlayer(media);

                        Thread.sleep((long) media.getDuration().toMillis());

                        player.dispose();
                        player = prePlayer;
                        player.play();
                    }
                } catch (InterruptedException ex) {
                    if(prePlayer != null){
                        prePlayer.dispose();
                        prePlayer = null;
                    }
                }
            }
        });
    }
    
    public void mute(){
        player.setVolume(0);
    }
    
    public void unmute(){
        player.setVolume(1);
    }
    
    public static SoundPlayer getInstance(){
        if(instance == null){
            instance = new SoundPlayer();
        }
        return instance;
    }
}
