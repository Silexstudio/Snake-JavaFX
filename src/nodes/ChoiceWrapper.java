package nodes;

import frames.FrameController;
import frames.GametypeFrame;
import frames.TutorialFrame;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class ChoiceWrapper extends ChoiceLabelBase {
    public ChoiceWrapper(int i){
        super(i);
        setId("cl-wrapper");

        setOnMouseClicked((MouseEvent mouseEvent) -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                GametypeFrame f = (GametypeFrame) FrameController.getInstance().getFrame();
                f.leave();
                FrameController.getInstance().changeFrame(new TutorialFrame(i+1));
            }
        });
    }
}
