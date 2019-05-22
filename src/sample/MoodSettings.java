package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MoodSettings {

        private VBox layout;
        private JFXButton mood1;
        private JFXButton mood2;
        private LinkedList<BookMarkNode> list;
        private FlowPane pane;
        private String color;
        public static String currentMood = "-fx-background-radius :30;";
        MoodSettings(){
            layout = new VBox();
            mood1 = new JFXButton("" ,NewTab.setImage("images/mood1.png" ,265 , 100));
            mood1.setPrefSize(100 , 70);
            mood2 = new JFXButton("" ,  NewTab.setImage("images/mood2.png" , 265 , 100));
            layout.setSpacing(100);
            layout.getChildren().addAll(mood1 , mood2);
            list =  NewTab.easyButtons.list;
            pane =  NewTab.easyButtons.layout;
            read();
            EventHandler<ActionEvent> event = e->{
                if(e.getSource() == mood1) {
                    currentMood = "-fx-background-radius :12;";
                     for(int i = 0 ; i < list.size(); i++){
                         color = EasyButtonMessage.color();
                      NewTab.easyButtons.list.get(i).getButton().setStyle("-fx-background-radius :12;" +
                              "-fx-background-color:" + color);
                         ((Label)pane.getChildren().get(i)).setTextFill(Color.web(color));
                     }
                    mood2.setStyle("-fx-border-color:white");
                    ((Label)pane.getChildren().get(list.size())).getGraphic().setStyle("-fx-background-radius :12;" +
                            " -fx-background-color:" + EasyButtonMessage.color());
                }
                else {
                    currentMood = "-fx-background-radius :30;";
                    for(int i = 0 ; i < list.size(); i++){
                        color = EasyButtonMessage.color();
                        NewTab.easyButtons.list.get(i).getButton().setStyle("-fx-background-radius :30; -fx-background-color:" + color);
                        ((Label)pane.getChildren().get(i)).setTextFill(Color.web(color));
                    }
                    mood1.setStyle("-fx-border-color:white");
                    ((Label)pane.getChildren().get(list.size())).getGraphic().setStyle("-fx-background-radius :30;" +
                            " -fx-background-color:" + EasyButtonMessage.color());
                }

                ((Button)e.getSource()).setStyle("-fx-border-color:red; -fx-border-width : 7px");
            };
            mood1.setOnAction(event);
            mood2.setOnAction(event);
        }


        public VBox getLayout() {
            return layout;
        }

    private void read() {

        BufferedReader bfr;
        try {
            File file = new File("mood.txt");
            if(file.exists()) {
                bfr = new BufferedReader(new FileReader("mood.txt"));
                String style = bfr.readLine();
                if(style != null) {
                 currentMood = style;
                }
                bfr.close();

            }
            if(NewTab.easyButtons.list.size() == 0)
                ((Label)NewTab.easyButtons.layout.getChildren().get(0)).getGraphic()
                        .setStyle(currentMood+"-fx-background-color:" +  EasyButtonMessage.color());

            if(currentMood.contains("12"))
                mood1.setStyle("-fx-border-color:red; -fx-border-width : 7px");
            else
                mood2.setStyle("-fx-border-color:red; -fx-border-width : 7px");


        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void write(){
        PrintWriter writer;
        try {
            writer = new PrintWriter("mood.txt");
            writer.println(currentMood);
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BookMarks.class.getName()).log(Level.SEVERE, null, ex);
        }

     }


}
