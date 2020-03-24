package game;

public class PowerApple extends Position {
    private int type;
    
    public PowerApple(int x, int y, int type){
        super(x,y);
        this.type = type;
    }
    
    public int getType(){
        return type;
    }
}
