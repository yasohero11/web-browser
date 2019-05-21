package sample;

import com.jfoenix.controls.JFXTabPane;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    Tab mainTab;
    @FXML
    Tab newTab;
    @FXML
    JFXTabPane tabPane;
    @FXML
    Tab other;




      @FXML
      public void onClose(){

      }
      @FXML
      public void  onMin(){

      }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
            NewTab tab  = new NewTab(tabPane , mainTab , newTab);
    }
}
