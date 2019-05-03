package sample;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class BookMarkEdit extends EditMessage {

    private Button clear;
    private Button delete;
    private Button save;
    BookMarkEdit(ChoiceBox box){
        super(box);

      clear = getClear();
      delete = getDelete();
      save =getSave();
         setTittle("Bookmark update window");
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
