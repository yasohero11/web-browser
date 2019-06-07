package sample;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;

public class EasyButtonEdit extends EditMessage {
    private Button save;
    private Button delete;
    private Button clear;
    private FlowPane layout;
    EasyButtonEdit(ChoiceBox box , FlowPane layout){
        super(box);
        this.layout = layout;
        save = getSave();
        clear = getClear();
        delete = getDelete();
          setTittle("Easy button update window");
        save.setOnAction(e-> edit());
        delete.setOnAction(e->{
           NewTab.easyButtons.delete();
        });
        name.setOnKeyPressed(e->{
            if(e.getCode() == KeyCode.ENTER)
                edit();
        });
        clear.setOnAction(e->{
            NewTab.easyButtons.clear();
            close();
        });
    }
    private void edit(){
        if(name.getText().length() !=0) {
            ((Label) layout.getChildren().get(NewTab.easyButtons.edit(name.getText()))).setText(name.getText());
            close();
            name.setText("");
        }
    }

}
