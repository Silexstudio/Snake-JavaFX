package game;

import frames.FrameController;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;


public class Engine {
    public static final int _size = 80;
    public static final int _mapX = -1320;
    public static final int _mapY = -240;
    public static final int _n = 58;
    public static final int _m = 27;
    
    public static Engine instance;
    private ArrayList<Player> players;
    private ArrayList<Apple> apples;
    private ArrayList<PowerApple> powerApples;
    private EventHandler<KeyEvent> keyBinds;
    private int map[][];
    private SubScene scene;
    private AnchorPane root;
    private AnimationTimer gameThread;
    private int framesPassed;
    
    public int getFramesPassed(){
        return framesPassed;
    }
    
    public void setFramesPassed(int f){
        framesPassed = f;
    }
    
    public static Engine getInstance(){
        if(instance == null){
            instance = new Engine();
        }
        return instance;
    }
    
    public void config(String arr[], SubScene scene, AnchorPane root){
        this.scene = scene;
        this.root = root;
        
        int positions[][] = {{0,0}, {_n - 1, _m - 1}, {0, _m - 1}, {0, _n - 1}};
        int directions[] = {2, 4, 1, 3};
        
        map = new int[_n][_m];
        
        players = new ArrayList<Player>();
        apples = new ArrayList<Apple>();
        powerApples = new ArrayList<PowerApple>();
        
        for(int i = 0; i < arr.length; i++){
            players.add(new Player(i, arr[i], directions[i],  positions[i]));
            addElement(players.get(i).getHead().getElement());
        }
        
        bindControls();
        
        PerspectiveCamera camera = new PerspectiveCamera(true);
        scene.setCamera(camera);
        camera.setFieldOfView(70);
        
        AmbientLight light = new AmbientLight(Color.WHITE);
         
        Group cameraGroup = new Group();
        cameraGroup.getChildren().add(camera);
        
        root.getChildren().add(cameraGroup);
        //root.getChildren().add(light);
        
        cameraGroup.setTranslateX(1920/2);
        cameraGroup.setTranslateY(1080/2);
        cameraGroup.setTranslateZ(-2000);
        
        camera.setFarClip(6000);
        
        framesPassed = 0;
        gameThread = new AnimationTimer(){
            @Override
            public void handle(long now){
                if(framesPassed == 3){
                    Engine.getInstance().game();
                    Engine.getInstance().setFramesPassed(0);
                }
                Engine.getInstance().render();
                Engine.getInstance().setFramesPassed(Engine.getInstance().getFramesPassed() + 1);
                try {
                    Thread.sleep(1000/60);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        
        startGame();
    }
    
    private Engine(){
        
    }
    
    public void addElement(Node e){
        root.getChildren().add(e);
    }
    
    public void removeElement(Node e){
        root.getChildren().remove(e);
    }
    
    public int[] searchFreePos(){
        int x,y;
        Boolean found = false;
        
        do{
            x = (int) (Math.random() * _n);
            y = (int) (Math.random() * _m);
            
            if(map[x][y] == 0){
                found = true;
            }
        }while(!found);
        
        return new int[]{x,y};
    }
    
    private Apple genApple(){
        int pos[] = searchFreePos();
        return new Apple(pos[0],pos[1]);
    }
    
    public void fillMap(){
        for(int i = 0; i < _n; i++){
            for(int j = 0; j < _m; j++){
                map[i][j] = 0;
            }
        }
        
        for(Player i : players){
            if(i.isAlive()){
                for(Projectile j : i.getProjectiles()){
                    map[j.getX()][j.getY()] = i.getNum();
                }
            }
        }
        
        for(Position i : apples){
            map[i.getX()][i.getY()] = 5;
        }
        
        for(PowerApple i : powerApples){
            map[i.getX()][i.getY()] = 6;
        }
        
        for(Player i : players){
            if(i.isAlive()){
                for(Position j : i.getBody()){
                    map[j.getX()][j.getY()] = i.getNum();
                }
            }
        }
    }
    
    private void removeApple(int x, int y){
        for(int i = 0; i < apples.size(); i++){
            if(apples.get(i).getX() == x && apples.get(i).getY() == y){
                removeElement(apples.get(i).getElement());
                apples.remove(i);
                break;
            }
        }

        for(int i = 0; i < powerApples.size(); i++){
            if(powerApples.get(i).getX() == x && powerApples.get(i).getY() == y){
                powerApples.remove(i);
                break;
            }
        }
    }
    
    private PowerApple getPowerApple(int x, int y){
        for(int i = 0; i < powerApples.size(); i++){
            PowerApple p = powerApples.get(i);
            if(p.getX() == x && p.getY() == y){
                return p;
            }
        }
        return null;
    }
    
    private void spawnPowerApple(int x, int y, int type, Player player){
        switch(type){
        }
    }
    
    public void removeProjectile(Player p, int x, int y){
        ArrayList<Projectile> arr = p.getProjectiles();
        
        for(int i = 0; i < arr.size(); i++){
            Projectile projectile = arr.get(i);
            if(projectile.getX() == x && projectile.getY() == y){
                arr.remove(i);
                break;
            }
        }
    }
        
    public void generateSingleApple(){
        Apple newApple = genApple();
        apples.add(newApple);
        addElement(newApple.getElement());
    }
    
    public void generateApples(){
        for(int i = apples.size(); i < players.size(); i++){
            generateSingleApple();
        }
    }
    
    public void generateSinglePowerApple(){
        //Position p = searchFreePos();
        //powerApples.add(new PowerApple(p.getX(), p.getY(), (int) (Math.random() * 2)));
    }
    
    public void generatePowerApples(){
        int score = 0;
        for(Player player : players){
            score += player.getScore();
        }
        if(score % 3 == 0) generateSinglePowerApple();
    }
    
    public void playerStep(){
        for(Player player : players){
            if(player.isAlive()){
                player.move();
                player.increasePower(25);
                
                if(player.getShoot() > 0){
                    player.shootProjectile();
                }
            }
        }
    }

    public void checkCollisionProjectiles(){
        for(Player player : players){
            if(player.isAlive()){
                ArrayList<Projectile> arr = player.getProjectiles();
                
                for(Projectile p : arr){
                    p.step();
                }
                
                int i = 0;
                while(i < arr.size()){
                    Projectile p = arr.get(i);
                    if(p.getX() < 0 || p.getY() < 0 || p.getX() > _n - 1 || p.getY() > _m - 1){
                        if(
                            p.getLife() == 0 ||
                            (p.getX() < 0 && p.getY() < 0) ||
                            (p.getX() < 0 && p.getY() > _m - 1) ||
                            (p.getY() < 0 && p.getX() > _n - 1) ||
                            (p.getY() > _m - 1 && p.getX() > _n - 1)
                        )
                        {
                            arr.remove(i);
                            i = -1;
                        }
                        else{
                            p.hit();
                            int possibleDirections[] = null;
                            if(p.getX() < 0){
                                p.setX(0);
                                possibleDirections = new int[]{2, 6, 7};
                            }
                            if(p.getY() < 0){
                                p.setY(0);
                                possibleDirections = new int[]{1, 5, 6};
                            }
                            if(p.getX() > _n - 1){
                                p.setX(_n - 1);
                                possibleDirections = new int[]{4, 8, 5};
                            }
                            if(p.getY() > _m - 1){
                                p.setY(_m - 1);
                                possibleDirections = new int[]{3, 7, 8};
                            }
                            int r = (int) (Math.random() * 3);
                            p.setDirection(possibleDirections[r]);
                            p.step();
                        }
                    }
                    i++;
                }
            }
        }
    }
    
    public void checkCollision(){
        Boolean scored = false;
        for(Player player : players){
            if(player.isAlive()){
                ArrayList<PositionBox> p = player.getBody();
                Position head = p.get(p.size() - 1);
                
                if(head.getX() < 0 || head.getX() > _n - 1 || head.getY() < 0 || head.getY() > _m - 1){
                    player.kill();
                    break;
                }
                
                int square = map[head.getX()][head.getY()];
                
                if((square > 0 && square < 5 && square != player.getNum()) || square == 8){
                    player.kill();
                }
                
                Boolean playerScored = false;
                
                if(square > 4 && square < 7){
                    if(square == 6){
                        spawnPowerApple(head.getX(), head.getY(), getPowerApple(head.getX(), head.getY()).getType(), player);
                    }
                    removeApple(head.getX(), head.getY());
                    scored = playerScored = true;
                }
                
                ArrayList<int[]> remove = new ArrayList<int[]>();
                
                for(Projectile projectile : player.getProjectiles()){
                    square = map[projectile.getX()][projectile.getY()];
                    if(square > 4 && square < 7){
                        if(square == 6){
                            spawnPowerApple(projectile.getX(), projectile.getY(), getPowerApple(projectile.getX(), projectile.getY()).getType(), player);
                        }
                        remove.add(new int[]{projectile.getX(), projectile.getY()});
                        scored = playerScored = true;
                    }
                }
                
                for(int i = 0; i < remove.size(); i++){
                    int r[] = remove.get(i);
                    removeApple(r[0], r[1]);
                    removeProjectile(player, r[0], r[1]);
                }
                
                if(playerScored){
                    player.score();
                    player.growTail();
                    addElement(player.getTail().getElement());
                }
                
            }
        }
        
        //An exception that isnt caught at the previous collision check
        //When both players reach the same square with their head it means that it's a TIE so both players die
        for(Player p1 : players){
            if(p1.isAlive()){
               for(Player p2 : players){
                    if(p2.isAlive()){
                        if(p1.getNum() != p2.getNum()){
                            Position p1Head = p1.getHead();
                            Position p2Head = p2.getHead();
                            
                            if(p1Head.getX() == p2Head.getX() && p1Head.getY() == p2Head.getY()){
                                p1.kill();
                                p2.kill();
                            }
                        }
                    }
                }
            }
        }
        
        if(scored){
            generatePowerApples();
        }
    }
    
    private void movePlayers(){
        for(Player p : players){
            for(PositionBox b : p.getBody()){
                b.move();
            }
        }
    }
    
    private void move(){
        movePlayers();
    }
    
    private void render(){
        for(Player p : players){
            for(PositionBox pb : p.getBody()){
                pb.animate();
            }
        }
        for(Apple a : apples){
            a.animate();
        }
    }
    
    private void game(){
        step();
        move();
    }
    
    public void step(){
        checkCollisionProjectiles();
        //componentStep();
        fillMap();
        playerStep();
        checkCollision();
        fillMap();
        generateApples();
    }
    
    public void startGame(){
        gameThread.start();
    }
    
    public void bindControls(){
        KeyCode binds[][] = new KeyCode[4][5];
        binds[0] = new KeyCode[]{KeyCode.W, KeyCode.A, KeyCode.S, KeyCode.D, KeyCode.SPACE};
        binds[1] = new KeyCode[]{KeyCode.UP, KeyCode.LEFT, KeyCode.DOWN, KeyCode.RIGHT, KeyCode.SHIFT};
        
        keyBinds  = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                KeyCode k = e.getCode();
                for(int i = 0; i < players.size(); i++){
                    Player act = players.get(i);
                    if(act.isAlive()){
                        if(k == binds[i][0]) act.setDirection(3);
                        if(k == binds[i][1]) act.setDirection(4);
                        if(k == binds[i][2]) act.setDirection(1);
                        if(k == binds[i][3]) act.setDirection(2);
                        if(k == binds[i][4]) act.shoot();
                    }
                }
            }
        };
        
        FrameController.getInstance().getScene().addEventFilter(KeyEvent.KEY_PRESSED, keyBinds);
    }
    
    public void unbindControls(){
        FrameController.getInstance().getScene().removeEventFilter(KeyEvent.KEY_PRESSED, keyBinds);
    }
    
    public void leave(){
        unbindControls();
    }
}
