package sample;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PopUpMessage {
    private BorderPane layout ;
    private Stage frame;
    private Button save ;
    private Button close ;
    private boolean contiue;
    private HBox bottomLayout ;

    public PopUpMessage() {
        bottomLayout = new HBox();
        frame = new Stage();
        frame.setTitle("Pop Up Message");
        save = new Button("Save");
        close = new Button("Close");
        bottomLayout.getChildren().addAll(save, close);
        bottomLayout.setAlignment(Pos.CENTER);
        bottomLayout.setSpacing(20);
        layout = new BorderPane();
        layout.setBottom(bottomLayout);
        contiue =  false;
        frame.setScene(new Scene(layout , 500, 300));

        close.setOnAction(e->close());
    }

    public Button getSave() {
        return save;
    }

    public Button getExit() {
        return close;
    }


    public void setBottom(Node item){
      bottomLayout.getChildren().add(1 , item);
    }
    public void setCenter(Pane centerLayout){
        layout.setCenter(centerLayout);
    }
    public void show(){
        frame.show();
    }
    public void close(){
        frame.close();
    }

    public void setContiue(boolean contiue) {
        this.contiue = contiue;
    }

    public boolean getContinue(){
        return contiue;
    }

    public void setTittle(String tittle){
        frame.setTitle(tittle);
    }


    public BorderPane getLayout() {
        return layout;
    }


}