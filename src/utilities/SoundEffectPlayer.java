package utilities;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import utilities.JSON.Settings;

public class SoundEffectPlayer {
    private static SoundEffectPlayer instance;
    
    private SoundEffectPlayer(){}
    
    public void play(int i){
        if(Settings.getInstance().getSetting(1)){
            Media m = SoundCollector.getInstance().get(i);
            MediaPlayer p = new MediaPlayer(m);

            p.setOnReady(new Runnable(){
                @Override
                public void run(){
                    p.play();
                    Thread t = new Thread(new Runnable(){
                        @Override
                        public void run(){
                            try {
                                Thread.sleep((long) m.getDuration().toMillis());
                            } catch (InterruptedException ex) {
                                Logger.getLogger(SoundEffectPlayer.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            p.dispose();
                        }
                    });
                    t.start();
                }
            });
        }
    }
    
    public static SoundEffectPlayer getInstance(){
        if(instance == null){
            instance = new SoundEffectPlayer();
        }
        return instance;
    }
}
