package sample;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public abstract class PopUpMessage {
    private BorderPane layout ;
    private Stage frame;
    private Button save ;
    private Button close ;
    private boolean contiue;
    private HBox bottomLayout ;
    private Text title;
    private HBox topLayout;
    private double x = 0;
    private double y = 0;
    public PopUpMessage() {
        bottomLayout = new HBox();
        title = new Text("title");
        title.setFont(Font.font(18));
        topLayout = new HBox(title);
        frame = new Stage();
        frame.initModality(Modality.APPLICATION_MODAL);
        frame.setTitle("Pop Up Message");
        frame.initStyle(StageStyle.TRANSPARENT);
        frame.setResizable(false);
        save = new Button("Save");
        close = new Button("Close");
        bottomLayout.getChildren().addAll(save, close);
        bottomLayout.setAlignment(Pos.CENTER);
        bottomLayout.setSpacing(20);
        layout = new BorderPane();
        layout.setBottom(bottomLayout);
        layout.setStyle("-fx-background-color: #4B4955");
        topLayout.setStyle("-fx-background-color:#4B4955 ");
        contiue =  false;
        BorderPane pane = new BorderPane();
        pane.setCenter(layout);
        pane.setTop(topLayout);
        frame.setScene(new Scene(pane , 500, 300));
        pane.setOnMousePressed(e->{
            x = e.getSceneX();
            y = e.getSceneY();
        });
        pane.setOnMouseDragged(e->{

                frame.setX(e.getScreenX()-x);
                frame.setY(e.getScreenY()-y);

        });

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

    protected void setTitle(String title){
        this.title.setText(title);
    }

     protected void setIcon(ImageView imageView){
         topLayout.getChildren().add(0 , imageView);
     }
    protected BorderPane getLayout() {
        return layout;
    }


}