package sample;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;



public class EditMessage  extends PopUpMessage{

    private GridPane pane;
    protected TextField name;
    private Label lb;
    private Button delete;
    private Button clear;
    public EditMessage(ChoiceBox box) {
        setIcon(new Image("images/edit.png"));
        pane = new GridPane();
        pane.setHgap(20);
        delete = new Button("Delete");
        clear = new Button("Clear");
        name = new TextField();
        lb = new Label("New Name :");
        name.setPrefSize(220 , 20);
        setCenter(pane);
        setBottom(clear);
        setBottom(delete);
        pane.setPadding(new Insets(0,0,0,30));
        pane.add(lb, 0, 0);
        pane.add(name, 1, 0 , 2 ,1);
        pane.add(box, 3 ,1  , 2 , 1);
        box.getSelectionModel().select(0);
        box.setPrefSize(100, 20);


    }

    public Button getDelete() {
        return delete;
    }

    public Button getClear() {
        return clear;
    }
}







