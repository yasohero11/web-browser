package sample;


import javafx.scene.control.Button;

public class BookMarkMessage extends AddMessage {
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
        save.setOnAction(e->{

        if(text1.getText().length() != 0 && text2.getText().length() != 0)
            continoue();

        close();
    });

}

    public void continoue(){
        NewTab.bookMarks.add(text2.getText() , text1.getText());
        text1.setText("");
        text2.setText("");
    }

}


