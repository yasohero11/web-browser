package sample;


import javafx.animation.SequentialTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class GoogleLoadingScreen extends Pane {

   private SequentialTransition transition;
    GoogleLoadingScreen(Double radius , Duration duration){

      BouncingBall b1  = new BouncingBall(radius, duration , 10.0, Color.web("#267EE7"));
      BouncingBall b2  = new BouncingBall(radius, duration , 10.0,Color.web("#EF322F"));
      BouncingBall b3  = new BouncingBall(radius, duration , 10.0,Color.web("#F1C40F"));
      BouncingBall b4  = new BouncingBall(radius, duration , 10.0,Color.web("#28B463"));
      b2.setLayoutX(radius*3);
      b3.setLayoutX(radius*6);
      b4.setLayoutX(radius*9);
      transition = new SequentialTransition(b1.getTransition(),b2.getTransition(),b3.getTransition(),b4.getTransition());
      transition.setCycleCount(1000000);
      getChildren().addAll(b1 ,b2 ,b3,b4);
      setStyle("-fx-background-color : transparent");
      transition.play();
    }
    GoogleLoadingScreen(Double radius){
        this(radius  ,Duration.seconds(0.07));
    }
}
