package sample;

import com.jfoenix.controls.JFXTabPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;

import javafx.scene.control.Tab;

import javafx.scene.image.ImageView;

public class NewTab implements EventHandler<Event> {
    public static JFXTabPane pane;
    public static History history = new History();
    public static  EasyButtons easyButtons = new EasyButtons();
    public static Settings settings;
    private Tab mainTab;
    private Tab newTab;
    public static BookMarks bookMarks;
   // public static LinkedList<TabClass> tabList;
    public static ObservableList<TabClass> tabList = FXCollections.observableArrayList();

    NewTab(JFXTabPane pane , Tab mainTab, Tab newTab){
        this.pane = pane;
        this.newTab = newTab;
        this.mainTab = mainTab;
        bookMarks = new BookMarks();
//        tabList = new LinkedList<>();
        TabClass tabClass = new TabClass(mainTab);
        tabList.add(tabClass);
        settings = new Settings();
        mainTab.setContent(tabClass.getLayout());
        newTab.setOnSelectionChanged(this);
        bookMarks.read();
        history.read();
        easyButtons.easyButtonMessage.read();
       // pane.getSelectionModel().selectLast();
    }

    @Override
    public void handle(Event event) {

         if(newTab.isSelected()){
             Tab tab = new Tab("new tab");
             TabClass tabClass = new TabClass(tab);
             tabList.add(tabClass);
             if(settings.imageSettings.ImageSelected()){
                 tabList.get(tabList.size()-1).setBackgroundImage(settings.imageSettings.getSelectedImage());
             }
             else
             tabList.get(tabList.size()-1).setBackgroundColor("-fx-background-color:" +settings.backgroundColor.getBackground());

             tab.setContent(tabClass.getLayout());
             pane.getTabs().add(pane.getTabs().size()- 1  ,  tab);
             pane.getSelectionModel().select(pane.getTabs().size()-2);
        }


    }

    public static TabClass getSelectedTab(){
        for(int i = 0 ; i < tabList.size(); i++){
            if(tabList.get(i).isSelected())
                return tabList.get(i);
        }
            return tabList.get(0);
    }
    public static ImageView setImage(String url , int width , int  height){
        //Image image = new Image(url ,width,height,true ,true);
        ImageView img  = new ImageView(url);
        img.setFitWidth(width);
        img.setFitHeight(height);
        return img;
    }


}

