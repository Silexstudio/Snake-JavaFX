package nodes;

import javafx.scene.control.Label;

public class ChoiceLabelBase extends Label {
    public ChoiceLabelBase(int i){
        setTranslateX(-500 + i * 368 * 2 + i * 120);
        setTranslateY(235);
        setTranslateZ(530 * 2 - 10);
        setScaleX(2);
        setScaleY(2);
    }
}
