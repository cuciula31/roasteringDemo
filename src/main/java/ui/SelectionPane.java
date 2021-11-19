package ui;

import Motion.FadeTransition;
import javafx.scene.layout.Pane;

public class SelectionPane {

    private final FadeTransition fadeTransition = new FadeTransition();

    public void initialize(Pane selectionPane){
        fadeTransition.fade(selectionPane,0,1,350,150);
    }

    public void electricianSelected(Pane selectionPane){
        fadeTransition.fade(selectionPane,1,0,350,150);

    }

    public void mechanicSelected(Pane selectionPane){
        fadeTransition.fade(selectionPane,1,0,350,150);

    }


}
