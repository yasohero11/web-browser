package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class Main extends Application {
    public static Scene scene;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
         scene = new Scene(root, 1350, 600);

        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Candy Butcher");

        primaryStage.setOnCloseRequest(e->{
            NewTab.bookMarks.write();
            NewTab.history.write();
            NewTab.easyButtons.easyButtonMessage.write();

           if(NewTab.tabList.get(0).colored) {
               NewTab.settings.backgroundColor.write();
           }
           else
              NewTab.settings.imageSettings.write();

            NewTab.settings.HistoryColor.write();
            NewTab.settings.moodSettings.write();
            primaryStage.close();
        });
        primaryStage.getIcons().add(new Image("images/brower.png"));
       // primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);
        primaryStage.show();

    }



    public static void main(String[] args) {
        launch(args);
    }
}
