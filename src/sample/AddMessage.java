package sample;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;

public class AddMessage extends PopUpMessage {

    private VBox layout;
    private Label url;
    private Label name;
    public TextField text1;
    public TextField text2;
    private Button save;
    public AddMessage(){
        url = new Label("URl:");
        name = new Label("Name:");
        text1 = new TextField();
        text2 = new TextField();
        save = new Button("Save");
        layout = new VBox();
        layout.getChildren().addAll(name , text1 , url , text2);
        setCenter(layout);


    }

    public void setUrl(String url) {
        text2.setText(url);
    }
}
