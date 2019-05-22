package sample;

import javafx.scene.paint.Color;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class BackgroundRW {


    private String background;
    private String fileName;
    BackgroundRW(String fileName){
       this.fileName =  fileName;
    }

    protected String format(String color){

        return "#" + color.substring(2 , color.length()-2);
    }
    public  boolean isColor(){
        return  background.startsWith("#");
    }
    protected void read() {

        BufferedReader bfr;
        try {
            File file = new File(fileName);
            if(file.exists()) {
                bfr = new BufferedReader(new FileReader(fileName));
                String style = bfr.readLine();
                if(style != null) {
                    background =  style;
                }
                bfr.close();
            }
            else
            if(fileName.equals("background.txt"))
                background = "images/m2.jpg";
                else
                background = "#994d66";

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getBackground() {
        return background;
    }

    public void write(){
        PrintWriter writer;
        try {
            writer = new PrintWriter(fileName);
            writer.println(background);
            //writer.println(colorPicker.getValue().toString());
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BookMarks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
