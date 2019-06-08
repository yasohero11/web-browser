package sample;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.KeyCode;

public class BookMarkEdit extends EditMessage {

    private Button clear;
    private Button delete;
    private Button save;
    BookMarkEdit(ChoiceBox box){
        super(box);

      clear = getClear();
      delete = getDelete();
      save =getSave();
         setTitle("Bookmark update window");
        save.setOnAction(e-> edit());
        name.setOnKeyPressed(e->{
            if(e.getCode() == KeyCode.ENTER)
                edit();
        });
        delete.setOnAction(e-> NewTab.bookMarks.delete());
        clear.setOnAction(e->NewTab.bookMarks.clear());
    }
    private void edit(){
        if(name.getLength() != 0) {
            NewTab.bookMarks.edit(name.getText());
            close();
            name.setText("");
        }
    }
}
