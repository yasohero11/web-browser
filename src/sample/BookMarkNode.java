package sample;

import com.jfoenix.controls.JFXButton;
public class BookMarkNode {
    private String name;
    private String url;
    private JFXButton button ;

    public BookMarkNode(String url, String name) {
        this.url = url;
        this.name = name;
        button =new JFXButton (name);
        button.setPrefSize(100, 20);
        button.setText(name);
        button.setOnAction(e->{
        NewTab.getSelectedTab().setURL(url);
       // NewTab.getSelectedTab().tabHistory.addUrl(url);
        NewTab.getSelectedTab().browse();
       // NewTab.getSelectedTab().check();
        });

    }

    public BookMarkNode(String url , String name , int size){
           this(url , name);
           button.setPrefSize(size,size);
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        button.setText(name);
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public JFXButton getButton (){
        return button;
    }


}
