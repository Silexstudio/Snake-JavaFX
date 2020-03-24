package utilities;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import utilities.*;
        
public class SequencePlayer{
        private Thread t;
        private SequenceLoader sl1,sl2;
        private Label bg;
        private Boolean loop;
    
        public SequencePlayer(Label background, int it, Boolean looped){
            this.bg = background;
            this.loop = looped;
            sl1 = SequenceCollector.getInstance().get(it);
            if(this.loop) sl2 = SequenceCollector.getInstance().get(it + 1); 
            
            t = new Thread(new Task<Void>(){
                @Override
                public Void call(){
                    int i = 0;
                    Boolean switched = false;
                    while(!t.isInterrupted()){
                        if(switched || loop == false){
                            if(i == sl1.getLength()){
                                i = 0;
                            }
                            else{
                                i++;
                            }
                            bg.setGraphic(new ImageView(sl1.get(i)));
                        }
                        else{
                            bg.setGraphic(new ImageView(sl1.get(i)));
                            if(i == sl1.getLength() - 1){
                                switched = true;
                                sl1 = sl2;
                                sl2 = null;
                                i = 0;
                            }
                            else{
                                i++;
                            }
                        }
                        
                        try {
                            Thread.sleep(1000/60);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(SequencePlayer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                    return null;
                }
            });
        }
        
        public void start(){
            t.start();
        }
        
        public void stop(){
            t.interrupt();
            sl1 = null;
            sl2 = null;
            t = null;
        }
}
