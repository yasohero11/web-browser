package sample;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class HistoryPane extends Pane {
   private ScrollPane mainPane;
   private BorderPane layout;
   private FlowPane centerLayout;
   private VBox rightLayout;
   private VBox linkLayout;
   private VBox dateLayout;
   JFXButton close;
   FlowPane flowPane;
    HistoryPane(int width , int height , int rightSize){
        mainPane = new ScrollPane();
        mainPane.setPrefSize(width+10 , height+10);
        layout = new BorderPane();
        rightLayout = new VBox();
        linkLayout = new VBox();
        dateLayout = new VBox();
        centerLayout = new FlowPane();
        layout.setPrefSize(width , height);
        linkLayout.setPrefHeight(height);
        dateLayout.setPrefHeight(height);
        linkLayout.setPrefWidth((width-rightSize)/2);
        dateLayout.setPrefWidth((width-rightSize)/2);
        rightLayout.setPrefWidth(rightSize);
        centerLayout.getChildren().addAll(linkLayout,dateLayout);
        layout.setCenter(centerLayout);
        layout.setRight(rightLayout);
        mainPane.setContent(layout);
        close = new JFXButton("Close");
        JFXButton clear = new JFXButton("Clear");
        close.setId("close1");
        clear.setId("clear");
        ToolBar toolBar = new ToolBar(clear,close);
        toolBar.setStyle("-fx-background-color: #2C2F30");
        clear.setStyle("-fx-text-fill: white; -fx-font-size:16; -fx-border-width: 0px 0px 2px 0px;" + "-fx-border-color: #F39C12");
        close.setStyle("-fx-text-fill: white; -fx-font-size:16; -fx-border-width: 0px 0px 2px 0px;" + "-fx-border-color: #D83542");
        Text text = new Text("History");
        text.setFill(Color.WHITE);
        text.setFont(Font.font(16));
        flowPane = new FlowPane(NewTab.setImage("images/history1.png" ,25 , 25) ,  text);
        flowPane.setPadding(new Insets(0,0,0,5));
        flowPane.setStyle("-fx-background-color: #2C2F30");
        BorderPane pane = new BorderPane();
        pane.setCenter(mainPane);
        pane.setBottom(toolBar);
        pane.setTop(flowPane);
        getChildren().add(pane);
        clear.setOnAction(e->{
            NewTab.history.clear();
            linkLayout.getChildren().clear();
            dateLayout.getChildren().clear();
            rightLayout.getChildren().clear();

        });


    }

    public void setSpacing(int size){
        linkLayout.setSpacing(size);
        dateLayout.setSpacing(size);
        rightLayout.setSpacing(size+2);

    }
    public void remove(int index){
        linkLayout.getChildren().remove(index);
        dateLayout.getChildren().remove(index);
        rightLayout.getChildren().remove(index);
    }
    public void setColor(String color){
        linkLayout.setStyle("-fx-background-color :" + color);
        dateLayout.setStyle("-fx-background-color :" + color);
        rightLayout.setStyle("-fx-background-color :" + color);
    }
    public void setDatePadding(int size) {
        dateLayout.setPadding(new Insets(0,0,0,size));

    }
    public void add(Text link , Text date , Button button){
        linkLayout.getChildren().add(0,link);
        dateLayout.getChildren().add(0,date);
        rightLayout.getChildren().add(0,button);
    }
}