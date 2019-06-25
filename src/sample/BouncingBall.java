package sample;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class BouncingBall extends Parent {

    private Circle c;
    private TranslateTransition moveUp ;
    private TranslateTransition moveDown;
    private SequentialTransition transition;
    BouncingBall(Double radius , Duration duration , Double jumpTo , Color color){
        this(radius,duration,jumpTo);
        c.setFill(color);

    }
    BouncingBall(Double radius , Duration duration , Double jumpTo){
        c = new Circle(radius);
        moveUp = new TranslateTransition(duration);
        moveDown = new TranslateTransition(duration);
        getChildren().add(c);
        moveUp.setNode(c);
        moveDown.setNode(c);
        c.setFill(Color.BLACK);
        moveUp.setToY(-jumpTo);
        moveDown.setToY(0);
        transition = new SequentialTransition(moveUp , moveDown);

}

    public SequentialTransition getTransition() {
        return transition;
    }
    public void play(){
        transition.play();
    }
}


