package sample;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;

public class EasyButtonEdit extends EditMessage {
    private Button save;
    private Button delete;
    private Button clear;
    EasyButtonEdit(ChoiceBox box , FlowPane layout){
        super(box);
        save = getSave();
        clear = getClear();
        delete = getDelete();
          setTittle("Easy button update window");
        save.setOnAction(e-> {
            if(name.getText().length() !=0) {
                ((Label)layout.getChildren().get(NewTab.easyButtons.edit(name.getText()))).setText(name.getText());
                close();
                name.setText("");
            }
        });
        delete.setOnAction(e->{
           NewTab.easyButtons.delete();
        });
        clear.setOnAction(e->{
            NewTab.easyButtons.clear();
            close();
        });
    }
}
