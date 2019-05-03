package sample;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class BookMarkEdit extends EditMessage {

    Button clear;
    Button delete;
    Button save;
    BookMarkEdit(ChoiceBox box){
        super(box);

      clear = getClear();
      delete = getDelete();
      save =getSave();

        save.setOnAction(e-> {
            NewTab.bookMarks.edit(name.getText());
            close();
            name.setText("");
        });
        delete.setOnAction(e->{
            NewTab.bookMarks.delete();
        });
        clear.setOnAction(e->NewTab.bookMarks.clear());
    }
}
