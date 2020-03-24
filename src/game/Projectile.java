package game;

public class Projectile extends Position{
    private int direction;
    private int speed;
    private int life;
    
    public Projectile(int x, int y, int direction){
        super(x, y);
        this.direction = direction;
        speed = 2;
        life = 2;
    }
    
    public void step(){
        for(int i = 0; i < speed; i++)
            moveInDirection(direction);
    }
    
    public void hit(){
        life--;
    }
    
    public void setDirection(int d){
        this.direction = d;
    }
    
    public int getLife(){
        return life;
    }
}
