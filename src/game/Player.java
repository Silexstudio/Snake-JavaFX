package game;

import java.util.ArrayList;

public class Player {
    
    
    private String name;
    private int direction;
    private ArrayList<PositionBox> body;
    private ArrayList<Projectile> projectiles;
    private Boolean alive;
    private Boolean frozen;
    private int num, score, powerLevel, powerPercentage, shoot;
    private int[] tailPos;
    
    public Player(int num, String name, int direction, int position[]){
        this.num = num;
        this.name = name;
        this.direction = direction;
        
        projectiles = new ArrayList<Projectile>();
        body = new ArrayList<PositionBox>();
        body.add(new PositionBox(position[0], position[1], num));
        alive = true;
        frozen = false;
        shoot = 0;
        
        powerLevel = 1;
        powerPercentage = 0;
    }
    
    public void shoot(){
        if(!frozen){
            if(powerPercentage == 100){
                shoot += powerLevel * 2;
                powerPercentage = 0;
            }
        }
        else{
            increasePower(10);
            if(powerPercentage == 100){
                
            }
        }
    }
    
    public void freeze(){
        frozen = true;
    }
    
    public int getShoot(){
        return shoot;
    }
    
    public void shootProjectile(){
        PositionBox head = body.get(body.size() - 1);
        Projectile p = new Projectile(head.getX(), head.getY(), direction);
        p.step();
        
        if(!(p.getX() < 0 || p.getY() < 0 || p.getX() > Engine._n - 1 || p.getY() > Engine._m - 1)){
            projectiles.add(p);
        }
        shoot--;
    }
    
    public void kill(){
        alive = false;
    }
    
    public void resurrect(){
        alive = true;
    }
    
    public String getName(){
        return name;
    }
    
    public int getSize(){
        return body.size();
    }
    
    public int getDirection(){
        return direction;
    }
    
    public void setDirection(int d){
        if(direction % 2 != d % 2)direction = d;
    }
    
    public int getNum(){
        return num;
    }
    
    public void growTail(){
        body.add(0, new PositionBox(tailPos[0], tailPos[1], num));
    }
    
    public void increasePower(int p){
        if(powerPercentage < 100){
            powerPercentage += p;
            if(powerPercentage > 100) powerPercentage = 100;
        }
    }
    
    public void setPowerPercentage(int p){
        powerPercentage = p;
    }
    
    public int getPowerPercentage(){
        return powerPercentage;
    }

    public void move(){
        if(!frozen){
            //Body tmp = body.get(body.size() - 1);
            //Body head = new Body(tmp.getX(), tmp.getY(), num);
            //head.moveInDirection(direction);
            //body.add(head);
            PositionBox head = body.get(body.size() - 1);
            PositionBox tail = body.get(0);
            tailPos = new int[2];
            tailPos[0] = tail.getX();
            tailPos[1] = tail.getY();
            
            int oldPos[] = new int[]{head.getX() , head.getY()};
            for(int i = body.size() - 2; i >= 0; i--){
                PositionBox actBody = body.get(i);
                
                int tmpOldPos[] = new int[2];
                tmpOldPos[0] = actBody.getX();
                tmpOldPos[1] = actBody.getY();
                
                body.get(i).setX(oldPos[0]);
                body.get(i).setY(oldPos[1]);
                
                oldPos = tmpOldPos;
            }
            
            head.moveInDirection(direction);
        }
    }
    
    public void score(){
        score++;
    }
    
    public int getScore(){
        return score;
    }
    
    public PositionBox getHead(){
        return body.get(body.size() - 1);
    }
    
    public PositionBox getTail(){
        return body.get(0);
    }
    
    public ArrayList<PositionBox> getBody(){
        return body;
    }
    
    public ArrayList<Projectile> getProjectiles(){
        return projectiles;
    }
    
    public Boolean isAlive(){
        return alive;
    }
}
