package sample;

import com.jfoenix.controls.JFXTextField;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class AddMessage extends PopUpMessage {


    private Label url;
    private Label name;
    public JFXTextField text1;
    public JFXTextField text2;
    private Button save;
    GridPane pane = new GridPane();
    public AddMessage(){
        url = new Label("URl:");
        name = new Label("Name:");
        text1 = new JFXTextField();
        text2 = new JFXTextField();
        text1.setUnFocusColor(Color.WHITE);
        text2.setUnFocusColor(Color.WHITE);
        save = new Button("Save");

        pane.setAlignment(Pos.CENTER);
        url.setStyle("-fx-font-size : 17; -fx-text-fill : white;");
        name.setStyle("-fx-font-size : 17; -fx-text-fill : white;");
        text1.setStyle("-fx-text-fill:" + EasyButtonMessage.color());
        text2.setStyle("-fx-text-fill:" + EasyButtonMessage.color());
        text1.setFont(Font.font(16));
        text2.setFont(Font.font(16));
        pane.add(name , 0 , 0 );
        pane.add(text1 , 1 , 0 , 1 , 1 );
        pane.add(url , 0 , 1 );
        pane.add(text2 , 1 , 1 ,2 , 1);
        pane.setVgap(20);
        pane.setHgap(5);
        text1.setPrefSize(250 , 20);
        text2.setPrefSize(250 , 20);
        setCenter(pane);

        EventHandler event = e->{
            ((JFXTextField)e.getSource()).setFocusColor(Color.web(EasyButtonMessage.color()));
            ((JFXTextField)e.getSource()).setStyle("-fx-text-fill:" + EasyButtonMessage.color());
        };
        text1.setOnMouseClicked(event);
        text2.setOnMouseClicked(event);
    }

    public void setUrl(String url) {
        text2.setText(url);
    }
}
