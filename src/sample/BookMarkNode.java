package sample;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class BookMarkNode {
    private String name;
    private String url;
    private JFXButton bt ;



    BookMarkNode() {
        bt =new JFXButton ("any");
        bt.setPrefSize(100, 20);

    }


    public BookMarkNode(String url, String name) {
        this();
        this.url = url;
        this.name = name;
        bt.setText(name);
        bt.setOnAction(e->{
        NewTab.getSelectedTab().setURL(url);
        NewTab.getSelectedTab().tabHistory.addUrl(NewTab.getSelectedTab().getURL());
        NewTab.getSelectedTab().browse();
        NewTab.getSelectedTab().check();
        });

    }
    public BookMarkNode(String url , String name , int size){
           this(url , name);
           bt.setPrefSize(size,size);
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        bt.setText(name);
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public JFXButton getButton (){
        return bt;
    }


}
