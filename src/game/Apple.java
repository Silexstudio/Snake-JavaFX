package game;

import javafx.scene.shape.Box;
import utilities.SequenceCollector;

public class Apple extends PositionElement{
    public Apple(int x, int y){
        super(x,y);
        
        element = new Box(Engine._size + 60, Engine._size + 60, 0);
        seq = SequenceCollector.getInstance().get(4);
        init();
    }
    
    public void move(){
        //element.setTranslateZ(-Engine._size/2);
        element.setTranslateX(Engine._mapX + super.getX() * Engine._size);
        element.setTranslateY(Engine._mapY + super.getY() * Engine._size);
    }
    
    @Override
    public void animate(){
        frame++;
        if(frame < maxFrame){
            mat.setDiffuseMap(seq.get(frame));
        }
        else{
            if(frame == maxFrame + 160){
                frame = 0;
            }
        }
    }
}
