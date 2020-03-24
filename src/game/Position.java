package game;

public class Position {
    private int x,y;
    
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public Position(Position p){
        this.x = p.x;
        this.y = p.y;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public void moveInDirection(int d){
        switch(d){
            case 1:
                y++;
                break;
            case 2:
                x++;
                break;
            case 3:
                y--;
                break;
            case 4:
                x--;
                break;
            case 5:
                y++;
                x--;
                break;
            case 6:
                y++;
                x++;
                break;
            case 7:
                y--;
                x++;
                break;
            case 8:
                y--;
                x--;
        }
    }
}
