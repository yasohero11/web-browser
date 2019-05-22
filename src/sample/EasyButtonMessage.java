 package sample;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.*;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

 public class EasyButtonMessage extends BookMarkMessage {
     private LinkedList<BookMarkNode> list;
     private FlowPane layout;

     private static Random random = new Random();
     private ChoiceBox box;

     EasyButtonMessage(LinkedList<BookMarkNode> list , FlowPane layout , ChoiceBox box){
         setIcon(new Image("images/add.png"));
         this.layout = layout;
         this.list= list;
         this.box = box;
         setTittle("Easy Buttons");
     }
    @Override
    public void continueOperation() {

        BookMarkNode node = new BookMarkNode(text2.getText(), text1.getText(), 65);
        node.getButton().setText(text1.getText().substring(0,1).toUpperCase());
        node.getButton().setFont(Font.font(20));
        String  color =  color();
        node.getButton().setStyle(MoodSettings.currentMood+";-fx-background-color:" +  color);

        ((Label)layout.getChildren().get(layout.getChildren().size()-1)).getGraphic().setStyle(MoodSettings.currentMood+
                "-fx-background-color:" +  color());
        node.getButton().setTooltip(new Tooltip(text1.getText()));
        Label l = new Label(node.getName() , node.getButton());
        l.setContentDisplay(ContentDisplay.TOP);
        l.setFont(Font.font(22));
        l.setTextFill(Color.web(color));
        box.getItems().add(node.getName());
        box.getSelectionModel().select(0);
        list.add(node);
        text1.setText("");
        text2.setText("");
        if (list.size() < 6)
            layout.getChildren().add(layout.getChildren().size() - 1, l);
        else
        {
            layout.getChildren().remove(layout.getChildren().size()-1);
            layout.getChildren().add(layout.getChildren().size() , l);
        }

    }

    public static String color(){
         int num = random.nextInt(11);
         String color = "";
         switch (num){
             case 0: color = "#E74C3C";
               break;
             case 1:  color ="#A569BD";
               break;
             case 2:  color = "#F4D03F";
               break;
             case 3:  color ="#45CD62";
               break;
             case 4:  color ="#F39C12";
               break;
             case 5:  color ="#C6EC4C";
                break;
             case 6: color = "#EC92C3";
                break;
             case 7: color = "#DD314B";
                break;
             case 8: color = "#22F1B2";
                 break;
             case 9: color = "#2CD8E7";
                 break;

             case 10: color = "#D90F46";
                 break;
         }

      return  color;
    }
     public void read(){
         BufferedReader bfr;

         try {

             File file = new File("EasyButtons.txt");
             if(file.exists()) {
                 bfr = new BufferedReader( new FileReader("EasyButtons.txt"));
                 String line = "";
                 String name;
                 String url;
                 while (line != null) {
                     line = bfr.readLine();
                     if (line != null) {
                         url = line.substring(0, line.indexOf('$'));
                         name = line.substring(line.indexOf('$') + 1, line.length());
                         text2.setText(url);
                         text1.setText(name);
                         continueOperation();
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
             writer = new PrintWriter("EasyButtons.txt");
             for(int i = 0; i  < list.size(); i++){
                 writer.println(list.get(i).getUrl() + "$" + list.get(i).getName());
             }
             writer.close();
         } catch (FileNotFoundException ex) {
             Logger.getLogger(BookMarks.class.getName()).log(Level.SEVERE, null, ex);
         }
     }




 }
