package Motion;

import javafx.scene.Node;
import javafx.util.Duration;

public class FadeTransition {

    public void fade(Node node, double fromOpacity, double toOpacity, int millis) {
        javafx.animation.FadeTransition fadeTransition = new javafx.animation.FadeTransition();
        fadeTransition.setFromValue(fromOpacity);
        fadeTransition.setToValue(toOpacity);
        fadeTransition.setNode(node);
        fadeTransition.setOnFinished(actionEvent -> {
            if(toOpacity == 0){
                node.setDisable(true);
                node.setVisible(false);
            }else{
                node.setDisable(false);
                node.setVisible(true);
            }
                }
        );
        fadeTransition.setDuration(Duration.millis(millis));
        fadeTransition.play();
    }

    public void fade(Node node, double fromOpacity, double toOpacity, int millis, int delay) {
        javafx.animation.FadeTransition fadeTransition = new javafx.animation.FadeTransition();
        fadeTransition.setFromValue(fromOpacity);
        fadeTransition.setToValue(toOpacity);
        fadeTransition.setOnFinished(actionEvent -> {
                    if(toOpacity == 0){
                        node.setDisable(true);
                        node.setVisible(false);
                    }else{
                        node.setDisable(false);
                        node.setVisible(true);
                    }
                }
        );
        fadeTransition.setNode(node);
        fadeTransition.setDuration(Duration.millis(millis));
        fadeTransition.setDelay(Duration.millis(delay));
        fadeTransition.play();
    }


}
