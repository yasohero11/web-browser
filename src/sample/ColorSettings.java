package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ColorSettings {
    private Circle circle;
    private ColorPicker colorPicker;
    private FlowPane layout;
    ColorSettings(){
        layout = new FlowPane();
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setVgap(20);
        circle = new Circle(50);
        colorPicker = new ColorPicker();
        read();
        circle.setFill(colorPicker.getValue());
        //NewTab.pane.setStyle("-fx-background-color:" + format(colorPicker.getValue().toString()));
        colorPicker.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                circle.setFill(colorPicker.getValue());
                NewTab.pane.setStyle("-fx-background-color:" + format(colorPicker.getValue().toString()));

            }
        });
        layout.setPadding(new Insets(10));
        layout.setHgap(10);
        layout.getChildren().addAll(circle, colorPicker);
    }


    private String format(String color){

        return "#" + color.substring(2 , color.length()-2);
    }
    private void read() {

        BufferedReader bfr;
        try {
            File file = new File("background.txt");
            if(file.exists()) {
                bfr = new BufferedReader(new FileReader("background.txt"));
                String style = format(bfr.readLine());
               if(style != null) {
                   NewTab.pane.setStyle("-fx-background-color:" + style);
                   colorPicker.setValue(Color.web(style));
               }

                bfr.close();
            }
            else
                colorPicker.setValue(Color.web("#5dade2"));


        }catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void write(){
        PrintWriter writer;
        try {
            writer = new PrintWriter("background.txt");
            writer.println(colorPicker.getValue().toString());
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BookMarks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public FlowPane getColorLayout() {
        return layout;
    }
}
