package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EasyButtons {

    public LinkedList<BookMarkNode> list;
    public  FlowPane layout;
    private JFXButton add;
    EasyButtonMessage easyButtonMessage;
    private ChoiceBox box;
    EasyButtonEdit edit;
    private Label addLabel;
    EasyButtons(){
        list = new LinkedList<>();
        layout = new FlowPane();
        layout.setLayoutX(400);
        layout.setLayoutY(270);
        layout.setHgap(30);
        layout.setPrefSize(600 , 100);
        add = new JFXButton("+");
        addLabel  = new Label("     " ,  add);
        addLabel.setFont(Font.font(20));
        addLabel.setContentDisplay(ContentDisplay.TOP);
        add.setPrefSize(65 ,65);
        add.setFont(Font.font(30));
        box = new ChoiceBox();
        layout.getChildren().add(addLabel);
         easyButtonMessage = new
                EasyButtonMessage(list , layout , box );
          edit = new EasyButtonEdit(box , layout);
        add.setOnAction(e->{
            easyButtonMessage.show();

        });

    }

    public void clear(){
        list.clear();
        layout.getChildren().clear();
        box.getItems().clear();
        layout.getChildren().add(addLabel);
    }

    public void delete (){

        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getName().equals(box.getValue())){
                list.remove(i);
                box.getItems().remove(i);
                layout.getChildren().remove(i);
                i = list.size();
                box.getSelectionModel().select(0);
            }
        }
        if(list.size() == 5)
            layout.getChildren().add(addLabel);
    }
    public int edit(String text){
        int i;
        for ( i = 0 ; i < list.size(); i++){
            if(list.get(i).getName().equals(box.getValue())){
                list.get(i).setName(text);
                list.get(i).getButton().setText(text.substring(0,1));
                box.getItems().set(i , text);
                System.out.println(i);
                break;
            }
        }
        box.getSelectionModel().select(0);
        return i;
    }

    public FlowPane getLayout() {
        return layout;
    }


}
