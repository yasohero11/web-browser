package sample;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;

public abstract class PopUpMessage {
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
        frame.initModality(Modality.APPLICATION_MODAL);
        frame.setResizable(false);
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

    protected Button getSave() {
        return save;
    }

    protected Button getExit() {
        return close;
    }


    protected void setBottom(Node item){
      bottomLayout.getChildren().add(1 , item);
    }
    protected void setCenter(Pane centerLayout){
        layout.setCenter(centerLayout);
    }
    protected void show(){
        frame.show();
    }
    protected void close(){
        frame.close();
    }

    protected void setContiue(boolean contiue) {
        this.contiue = contiue;
    }

    protected boolean getContinue(){
        return contiue;
    }

    protected void setTittle(String tittle){
        frame.setTitle(tittle);
    }

     protected void setIcon(Image img){
         frame.getIcons().add(img);
     }
    protected BorderPane getLayout() {
        return layout;
    }


}