package game;

import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Shape3D;
import utilities.SequenceCollector;
import utilities.SequenceLoader;

public class PositionElement extends Position{
    protected Shape3D element;
    protected SequenceLoader seq;
    protected PhongMaterial mat;
    protected int frame, maxFrame;
    
    public PositionElement(int x, int y){
        super(x,y);
        frame = -1;
        mat = new PhongMaterial();
    }
    
    public void init(){
        element.setMaterial(mat);
        maxFrame = seq.getLength();
        move();
        animate();
    }
    
    public Shape3D getElement(){
        return element;
    }
    
    public void move(){
        element.setTranslateX(Engine._mapX + super.getX() * Engine._size);
        element.setTranslateY(Engine._mapY + super.getY() * Engine._size);
    }
    
    public void animate(){
        frame++;
        if(frame == maxFrame){
            frame = 0;
        }
        mat.setDiffuseMap(seq.get(frame));
    }
}
