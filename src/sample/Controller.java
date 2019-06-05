package sample;

import com.jfoenix.controls.JFXTabPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Tab;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    Tab mainTab;
    @FXML
    Tab newTab;
    @FXML
    JFXTabPane tabPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            NewTab tab  = new NewTab(tabPane , mainTab , newTab);
    }
}
