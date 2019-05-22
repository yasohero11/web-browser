package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Settings {

    private Stage window;
    private Scene frame;
    public ColorSettings backgroundColor;
    public ColorSettings HistoryColor;
    public ImageSettings imageSettings;
    public MoodSettings moodSettings;
    private  BorderPane layout;
    private ListView listView;
   Settings(){
       backgroundColor = new ColorSettings(NewTab.pane , "background.txt");
       HistoryColor = new ColorSettings(NewTab.history.pane2 , "historycolor.txt");
       imageSettings = new ImageSettings();
       moodSettings = new MoodSettings();
       layout = new BorderPane();
       listView = new ListView();
       listView.setPrefSize(155 , 430);
       frame = new Scene(layout , 440 , 430);
       window = new Stage();
       window.initModality(Modality.APPLICATION_MODAL);
       window.setResizable(false);
       listView.getItems().addAll("Background Color" ,"Background History Color", "Background Image" , "Mood Settings");
       layout.setLeft(listView);
       listView.getSelectionModel().selectedItemProperty().addListener(e->{
           if(listView.getSelectionModel().isSelected(0))
               layout.setCenter(backgroundColor.getColorLayout());
           else if(listView.getSelectionModel().isSelected(1)){
               layout.setCenter(HistoryColor.getColorLayout());
           }
           else if(listView.getSelectionModel().isSelected(2)) {
               layout.setCenter(imageSettings.getLayout());
           }
           else
               layout.setCenter(moodSettings.getLayout());
           /*
           else {
               layout.setCenter(new StackPane());
               BackgroundImage backgroundimage = new BackgroundImage(new Image("images/moon.jpg" ,1360,600,false ,false),
                       BackgroundRepeat.NO_REPEAT,
                       BackgroundRepeat.NO_REPEAT,
                       BackgroundPosition.DEFAULT,
                       null);

               NewTab.pane.setBackground(new Background(backgroundimage));
           }
           */
       });




       // BackgroundFill backgroundFill = new BackgroundFill(Color.RED);
       // NewTab.pane.setBackground(new Background(backgroundimage));
       window.getIcons().add(new Image("images/settings.png"));
       window.setTitle("Settings");
       window.setScene(frame);
   }
    public void show(){
        window.show();
    }
}
