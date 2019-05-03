package sample;

import java.io.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
public class BookMarks{
    private LinkedList<BookMarkNode> list = new LinkedList<>();

    private JFXButton add;
    private JFXButton edit;
    private ToolBar toolBar;
    public ChoiceBox box;
    private BookMarkMessage bookMarkMessage;
    private BookMarkEdit editMessage;
    BookMarks() {
        toolBar = new ToolBar();
        edit = new JFXButton("Edit");
        add = new JFXButton("Add");

        box =  new ChoiceBox();
        bookMarkMessage = new BookMarkMessage();
        editMessage = new BookMarkEdit(box);
        toolBar.getItems().addAll(edit,add);



        add.setOnAction(e-> {
            bookMarkMessage.show();
            bookMarkMessage.setUrl(NewTab.getSelectedTab().getURL());
    });
        edit.setOnAction(e->{
            box.getSelectionModel().select(0);
            editMessage.show();
        });
    }

    public void add(String url , String name){
        if(list.size()<12) {
            list.add(new BookMarkNode(url, name));
            toolBar.getItems().add(toolBar.getItems().size()-2 , list.get(list.size()-1).getButton());
            box.getItems().add(list.get(list.size()-1).getName());
        }
       if(list.size()==12)
           add.setDisable(true);
    }
    public BookMarkNode getBookMarkAt(int index){
        return list.get(index);
    }

    public void clear(){
        list.clear();
        toolBar.getItems().clear();
        box.getItems().clear();
        add.setDisable(false);
        toolBar.getItems().addAll(edit , add);
        editMessage.close();
    }

    public void edit(String text){
        for (int i = 0 ; i < list.size(); i++){
            if(list.get(i).getName().equals(box.getValue())){
                list.get(i).setName(text);
                box.getItems().set(i , text);
                i = list.size();
            }
        }
    }

    public ToolBar getToolBar() {
        return toolBar;
    }

    public int getSize(){
        return list.size();
    }



    public void delete (){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getName().equals(box.getValue())){
                list.remove(i);
                box.getItems().remove(i);
                toolBar.getItems().remove(i);
                i = list.size();
                box.getSelectionModel().select(0);
            }
        }
    }




    public void read() {

        BufferedReader bfr;
        try {
            File file = new File("BookMark.txt");
                if(file.exists()) {
                bfr = new BufferedReader(new FileReader("BookMark.txt"));
                String line = "";
                String name;
                String url;
                while (line != null) {
                    line = bfr.readLine();
                    if (line != null) {
                        url = line.substring(0, line.indexOf('$'));
                        name = line.substring(line.indexOf('$') + 1, line.length());
                        add(url, name);
                    }
                }
                bfr.close();
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void write(){
        PrintWriter writer;
        try {
            writer = new PrintWriter("BookMark.txt");
            for(int i = 0; i  < list.size(); i++){
                writer.println(list.get(i).getUrl() + "$" + list.get(i).getName());
            }
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BookMarks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}