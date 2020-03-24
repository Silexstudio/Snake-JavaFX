package game;

import javafx.scene.shape.Box;
import utilities.SequenceCollector;

public class PositionBox extends PositionElement{
    public PositionBox(int x, int y, int i){
        super(x,y);
        
        element = new Box(Engine._size, Engine._size, 0);
        seq = SequenceCollector.getInstance().get(i);
        init();
    }
}
