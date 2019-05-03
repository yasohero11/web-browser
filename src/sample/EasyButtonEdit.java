package sample;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class EasyButtonEdit extends EditMessage {
    private Button save;
    private Button delete;
    private Button clear;
    EasyButtonEdit(ChoiceBox box){
        super(box);

        save = getSave();
        clear = getClear();
        delete = getDelete();
          setTittle("Easy button update window");
        save.setOnAction(e-> {
            NewTab.easyButtons.edit(name.getText());
            close();
            name.setText("");
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
