package frames;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class TutorialFrame extends Frame{
    private TextField[] t;
    private Label btn1;
    private int playernum;
    
    public TutorialFrame(int pnum){
        super();
        
        playernum = pnum;
        
        startVideo(3, false);
        assignStylesheet(5);
        
        Label bg = new Label();
        bg.setId("bg-" + playernum);
        bg.setTranslateX(0);
        bg.setTranslateY(0);
        
        getChildren().add(bg);
        
        if(playernum != 1){
            t = new TextField[playernum];
            for(int i = 0; i < playernum; i++){
                t[i] = new TextField();
                t[i].setText("Player" + (i+1));

                if(i % 2 == 0){
                    t[i].setTranslateX(1366);
                }
                else{
                    t[i].setTranslateX(1625);
                }

                if(i < 2){
                    t[i].setTranslateY(120);
                }
                else{
                    t[i].setTranslateY(320);
                }
                t[i].setOnKeyTyped(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        checkFields();
                    }
                });

                getChildren().add(t[i]);
            }
        }
        
        btn1 = new Label();
        btn1.setId("btn1");
        btn1.setTranslateX(1508);
        btn1.setTranslateY(680);
        
        btn1.setOnMouseClicked((MouseEvent mouseEvent) -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY) && checkFields()){
                String arr[] = new String[playernum];
                if(playernum != 1){
                    for(int i = 0; i < playernum; i++){
                        arr[i] = t[i].getText().trim();
                    }
                }
                else{
                    arr[0] = "singleplayer";
                }
                FrameController.getInstance().changeFrame(new GameFrame(arr));
            }
        });
        
        Label btn2 = new Label();
        btn2.setId("btn2");
        btn2.setTranslateX(42);
        btn2.setTranslateY(680);
        
        btn2.setOnMouseClicked((MouseEvent mouseEvent) -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                FrameController.getInstance().changeFrame(new GametypeFrame());
            }
        });
        
        getChildren().add(btn1);
        getChildren().add(btn2);
    }
    
    private Boolean checkFields(){
        if(playernum == 1) return true;
        
        Boolean all = true;
        for(int i = 0; i < playernum; i++){
            if(t[i].getText().trim().isEmpty()){
                all = false;
            }
        }
        if(all){
            btn1.setId("btn1");
        }
        else{
            btn1.setId("btn1-disabled");
        }
        return all;
    }
}
