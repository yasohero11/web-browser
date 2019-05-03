package sample;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.io.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EasyButtons {

    private LinkedList<BookMarkNode> list;
    private static FlowPane layout;
    private JFXButton add;
    EasyButtonMessage easyButtonMessage;
    private ChoiceBox box;
    EasyButtonEdit edit;
    EasyButtons(){
        list = new LinkedList<>();
        layout = new FlowPane();
        layout.setLayoutX(400);
        layout.setLayoutY(270);
        layout.setHgap(30);
        layout.setPrefSize(600 , 100);
        add = new JFXButton("+");
        add.setStyle("-fx-background-color:" +  EasyButtonMessage.color());
        add.setPrefSize(65 ,65);
        add.setFont(Font.font(30));
        box = new ChoiceBox();
        layout.getChildren().add(add);
         easyButtonMessage = new
                EasyButtonMessage(list , layout , box);
          edit = new EasyButtonEdit(box);
        add.setOnAction(e->{
            easyButtonMessage.show();

        });

    }

    public void clear(){
        list.clear();
        layout.getChildren().clear();
        box.getItems().clear();
        add.setStyle("-fx-background-color:" +  EasyButtonMessage.color());
        layout.getChildren().add(add);
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
            layout.getChildren().add(add);
    }
    public void edit(String text){
        for (int i = 0 ; i < list.size(); i++){
            if(list.get(i).getName().equals(box.getValue())){
                list.get(i).setName(text);
                list.get(i).getButton().setText(text.substring(0,1));
                box.getItems().set(i , text);
                i = list.size();
            }
        }
        box.getSelectionModel().select(0);
    }

    public FlowPane getLayout() {
        return layout;
    }
}
