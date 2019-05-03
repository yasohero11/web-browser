package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.tools.Tool;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root, 1350, 600);

        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Candy Butcher");

        primaryStage.setOnCloseRequest(e->{
            NewTab.bookMarks.write();
            NewTab.history.write();
            NewTab.easyButtons.easyButtonMessage.write();
            primaryStage.close();
        });
        primaryStage.getIcons().add(new Image("sample/brower.png"));
        primaryStage.setResizable(false);
        primaryStage.show();

    }



    public static void main(String[] args) {
        launch(args);
    }
}
