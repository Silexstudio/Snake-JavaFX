package nodes;

import javafx.application.Platform;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

public class ChoiceLabel extends ChoiceLabelBase{
    private int r, prevR, i, speed;
    
    public ChoiceLabel(int i){
        super(i);
        speed = 6;
        prevR = 0;
        r = 0;
        this.i = i;
        setId("cl-" + (i + 1));
        setEffect(new DropShadow(10, Color.WHITE));
    }
    
    public void checkId(){
        if(r > 59 + i * 21){
            setId("cl-go");
        }
        else{
            setId("cl-" + (i + 1));
        }
    }
    
    public void rotateBackward(){
        if(r != 0){
            r -= speed;
            if(r < 0){
                Platform.runLater(new Runnable(){
                    @Override
                    public void run(){
                        getTransforms().add(new Rotate(-speed - r,184,264,0,Rotate.Y_AXIS));
                    }
                });
                r = 0;
             }
            else{
                Platform.runLater(new Runnable(){
                    @Override
                    public void run(){
                        getTransforms().add(new Rotate(-speed,184,264,0,Rotate.Y_AXIS));
                    }
                });
            }
            checkId();
        }
    }
    
    public void rotateForward(){
        if(r != 180){
            r += speed;
            if(r > 180){
                Platform.runLater(new Runnable(){
                    @Override
                    public void run(){
                        getTransforms().add(new Rotate(180 - r,184,264,0,Rotate.Y_AXIS));
                    }
                });
                r = 180;
             }
            else{
                Platform.runLater(new Runnable(){
                    @Override
                    public void run(){
                        getTransforms().add(new Rotate(6,184,264,0,Rotate.Y_AXIS));
                    }
                });
            }
            checkId();
        }
    }
}
