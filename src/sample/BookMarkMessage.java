package sample;


import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class BookMarkMessage extends AddMessage{
    private Button save;
    private Button close;
    BookMarkMessage(){
    save = getSave();
    close = getExit();
    setTittle("BookMarks");
        close.setOnAction(e->{
            close();
            text1.setText("");
            text2.setText("");
        });

        save.setOnAction(e-> add());
        /*
        save.setOnKeyPressed(e->{
            if(text1.getText().length() != 0 && text2.getText().length() != 0)
    if(e.getCode() == KeyCode.ENTER){
        continueOperation();
        close();
    }});
    */
        EventHandler<KeyEvent> eventHandler = e->{
            if(e.getCode() == KeyCode.ENTER)
                add();
        };

        text1.setOnKeyPressed(eventHandler);
        text2.setOnKeyPressed(eventHandler);

}

    private void add(){
        if(text1.getText().length() != 0 && text2.getText().length() != 0) {
            continueOperation();
            close();
        }
    }

    public void icon(Image img){
        setIcon(img);
    }

    public void continueOperation(){
        NewTab.bookMarks.add(text2.getText() , text1.getText());
        text1.setText("");
        text2.setText("");
    }

}


