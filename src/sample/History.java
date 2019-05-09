package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class History {
    LinkedList<HistoryNode> list = new LinkedList<>();

    VBox layout;
    Stage window;
    Scene frame;
    int count = 0;
    BorderPane borderPane;
    HistoryPane pane2;

    History() {
        borderPane = new BorderPane();
        window = new Stage();
        window.setTitle("History");
        window.initModality(Modality.APPLICATION_MODAL);
        pane2 = new HistoryPane(800, 600, 50);
        frame = new Scene(pane2, 800, 600);
        window.setResizable(false);
        window.getIcons().add(new Image("images/history.png"));
        window.setScene(frame);
    }

    public void add(String url) {
        list.add(new HistoryNode(url));
        setLayout(list.get(list.size() - 1));
    }

    public void add(String url, int index) {
        list.add(index, new HistoryNode(url));
    }

    public HistoryNode getHistoryNodeAt(int index) {
        return list.get(index);
    }

    public void printData() {
        System.out.print("[");
        for (int i = 0; i < list.size() - 1; i++)
            System.out.print(list.get(i).getUrl() + ",");

        System.out.print(list.get(list.size() - 1).getUrl() + "]");
    }


    public LinkedList<HistoryNode> getHistory() {
        return list;
    }

    public void setHistory(History history) {
        this.list = history.getHistory();
    }

    public void remove(int index) {
        list.remove(index);
    }

    public void clear() {
        list.clear();
    }

    public int getSize() {
        return list.size();
    }

    public void deepCopy(LinkedList<HistoryNode> history, int size) {
        for (int i = 0; i < size; i++) {
            list.add(history.get(i));
        }
    }

    private void syce() {
        pane2 = new HistoryPane(800, 600, 50);
        pane2.setSpacing(20);
        frame = new Scene(pane2, 800, 600);
        window.setScene(frame);
        for (int i = 0; i < list.size(); i++)
            setLayout(list.get(i));
    }

    public void setLayout(HistoryNode historyNode) {
        Text text1 = new Text(historyNode.getUrl());
        Text text2 = new Text(historyNode.getDate() +" -- " + historyNode.getTime());
        setFont(text1);
        setFont(text2);
        pane2.setSpacing(20);
        historyNode.closeButton.setAlignment(Pos.CENTER);
        pane2.add(text1, text2, historyNode.closeButton);


    }

    public void read() {

        BufferedReader bfr;
        try {
            File file = new File("History.txt");
            if(file.exists()) {
                bfr = new BufferedReader(new FileReader("History.txt"));
                String line = "";
                String url;
                String date;
                String time;
                while (line != null) {
                    line = bfr.readLine();
                    if (line != null) {
                        url = line.substring(0, line.indexOf('$'));
                        date = line.substring(line.indexOf('$') + 1,
                                line.indexOf("$", line.indexOf("$") + 1));
                        time = line.substring(line.indexOf("$", line.indexOf("$") + 1) + 1, line.length());
                        add(url);
                        list.getLast().setDate(date);
                        list.getLast().setTime(time);
                    }
                }
                bfr.close();
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void show() {
        window.show();
    }

    private void setFont(Text text) {
        text.setFont(Font.font(20));
    }

    public void write(){
        PrintWriter writer;
        try {
            writer = new PrintWriter("History.txt");
            for(int i = 0; i  < list.size(); i++){
                writer.println(list.get(i).getUrl()+ "$" + list.get(i).getDate() + "$" + list.get(i).getTime());
            }
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BookMarks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // inner class
    public class HistoryNode {

        int numberOfTheObject;
        private String date;
        private String time;
        private String url;
        private Button closeButton;

        HistoryNode(String url) {
            closeButton = new Button("X");
            numberOfTheObject = count;
            count++;
            this.url = url;
            Date d = new Date();
            SimpleDateFormat date = new SimpleDateFormat("MM/dd/y");
            this.date = date.format(d);
            SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");
            this.time = time.format(d);
            closeButton.setOnAction(e -> {
                for (int i = 0; i < list.size(); i++) {
                    if (numberOfTheObject == list.get(i).numberOfTheObject) {
                        list.remove(i);
                        syce();
                    }

                }
            });

        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTime() {
            return time;
        }

        public int getNumberOfTheObject() {
            return numberOfTheObject;
        }

        public String getDate() {
            return date;
        }

        public String getUrl() {
            return url;
        }

        @Override
        public String toString() {
            return url + " " + date + " " + time;
        }
    }
}