package sample;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import com.jfoenix.controls.*;
public class TabClass {
    private String URL;
    private JFXTextField text1;
    private TextField text2;
    private ToolBar toolBar;
    public  BorderPane layout;
    private Pane centerLayout;
    private Text title;
    private JFXButton searchButton;
    private JFXButton backButton;
    private JFXButton forwardButton;
    private JFXButton restartButton;
    private WebView web;
    private JFXButton historyButton;
    private JFXButton homeButton;
    private JFXButton settingsButton;
    public TabHistory tabHistory;
    private  FlowPane easyButtonsLayout =  new FlowPane();
    private Tab tab;
    private JFXButton edit;
    public StackPane backhground;
    public boolean colored;




   private TabClass(){

       backhground = new StackPane();
       backhground.setPrefSize(1360, 466);
           text1 = new JFXTextField();
           text2 = new TextField();
           toolBar = new ToolBar();
           layout = new BorderPane();
           historyButton = new JFXButton("H");
           historyButton.setId("history");
           text1.setMinHeight(30);
           text1.setAlignment(Pos.CENTER);
           text1.setMinWidth(1050);
           text1.setPromptText("Search...");
           text2.setMinHeight(30);
           text2.setMinWidth(800);
           text2.setPromptText("Search...");

           searchButton = new JFXButton("", setImage("images/search.png", 22, 22));
           backButton = new JFXButton("", setImage("images/back.png", 22, 22));
           forwardButton = new JFXButton("", setImage("images/forward.png", 22, 22));
           restartButton = new JFXButton("", setImage("images/restart.png", 22, 22));
           homeButton = new JFXButton("", setImage("images/brower.png", 50, 50));
           settingsButton = new JFXButton("", setImage("images/settings4.png", 20, 20));
           settingsButton.setId("settingsButton");
           settingsButton.setLayoutX(1300);
           settingsButton.setLayoutY(20);

           homeButton.setId("homeButton");
           forwardButton.setDisable(true);
           backButton.setDisable(true);
           restartButton.setDisable(true);
           toolBar.getItems().addAll(homeButton, backButton, forwardButton, restartButton, text1, searchButton, historyButton);
           layout.setTop(toolBar);
           ImageView logo = setImage("images/brower.png", 200, 200);
           logo.setX(570);
           centerLayout = new Pane();
           title = new Text("Candy Butcher");
           title.setFont(Font.font("Verdana", 30));
           title.setX(560);
           title.setY(170);
           text2.setLayoutX(280);
           text2.setLayoutY(220);
           edit = new JFXButton("Edit Easy Button");
           edit.setLayoutX(570);
           edit.setLayoutY(370);

        edit.setPrefSize(200 , 40);
        centerLayout.getChildren().addAll(backhground,title , text2 , logo , edit , settingsButton);
        text1.setAlignment(Pos.CENTER);
        text1.setUnFocusColor(Paint.valueOf("#44DA26"));
        text1.setFocusColor(Paint.valueOf("#F2F07D"));

        layout.setCenter(centerLayout);
        tabHistory = new TabHistory(text1);
        searchButton.setOnMouseClicked(e->{
            if(text1.getText().length() != 0) {
                tabHistory.addUrl(text1.getText());

                browse();
                check();
            }
        });
        backButton.setOnMouseClicked(e-> {
            if(!tabHistory.atFirst()) {
                tabHistory.back();
                browse();
                check();
            }
        });

        forwardButton.setOnMouseClicked(e->{
            if(!tabHistory.atLast()) {
                tabHistory.forward();
                browse();
                check();
            }
        });
        restartButton.setOnMouseClicked(e->{
            browse();
        });

        historyButton.setOnAction(e->NewTab.history.show());
        edit.setOnAction(e->NewTab.easyButtons.edit.show());
        settingsButton.setOnAction(e->NewTab.settings.show());

    }

    TabClass(Tab tab) {
        this();
        this.tab = tab;
        layout.setBottom(NewTab.bookMarks.getToolBar());
        easyButtonsLayout = new FlowPane();
        centerLayout.getChildren().addAll(NewTab.easyButtons.getLayout() );
        tab.setOnSelectionChanged(e->{
            layout.setBottom(NewTab.bookMarks.getToolBar());
            easyButtonsLayout.getChildren().add(NewTab.easyButtons.getLayout());
            centerLayout.getChildren().addAll(NewTab.easyButtons.getLayout());
        });

        text1.setOnAction(e -> {
            if(text1.getText().length() != 0) {
                text2.setText(text1.getText());
                tabHistory.addUrl(text1.getText());
                browse();
                check();
            }
        });
        text2.setOnAction(e -> {
            if(text2.getText().length() != 0) {
                text1.setText(text2.getText());
                tabHistory.addUrl(text1.getText());
                browse();
                check();
            }
        });
        homeButton.setOnMouseClicked(e->
        {
                layout.setCenter(centerLayout);
                text1.setText("");
                text2.setText("");
                tabHistory.reset();
                restartButton.setDisable(true);
                check();
            if(!tab.getText().equals("main tab")) {
                tab.setText("new tab");
            }
        });
    }
    public boolean isSelected(){
        return tab.isSelected();
    }
    public void check(){
         if(!tabHistory.isEmpty())
             restartButton.setDisable(false);

        if(!tabHistory.atFirst() && !tabHistory.isEmpty()) {
            backButton.setDisable(false);
        }
        else
            backButton.setDisable(true);

        if(!tabHistory.atLast() && !tabHistory.isEmpty())
            forwardButton.setDisable(false);
        else
            forwardButton.setDisable(true);

    }


    public void setBackhgroundColor(String color) {
            backhground.getChildren().clear();
            backhground.setStyle(color);
            colored = true;
            if(NewTab.settings.imageSettings.ImageSelected()){
                NewTab.settings.imageSettings.reset();
            }
    }

    public void setBackhgroundImage(Image image) {
        backhground.getChildren().clear();
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(466);
        imageView.setFitWidth(1360);
        colored = false;
        backhground.getChildren().add(imageView);
    }

    public void browse(){
        web = new WebView();
        WebEngine engine = web.getEngine();
        checkUrl();
        setTabTiltle();
        engine.load(text1.getText());

        System.out.println(engine.getLocation());
       // System.out.println(engine.getHistory().getEntries().get(engine.getHistory().getEntries().size()));
        NewTab.history.add(engine.getLocation());
        layout.setCenter(web);
    }
    private void setSize(JFXButton button , int size){
        button.setPrefSize(20,20);
    }
    private ImageView setImage(String url , int width , int  height){
        ImageView img  = new ImageView(url);
        img.setFitWidth(width);
        img.setFitHeight(height);
        return img;
    }

    public Pane getLayout() {
        return layout;
    }

    public String getURL() {
        URL = text1.getText();
        return URL;
    }
    public void setURL(String url){
        text1.setText(url);
    }

   private void checkUrl() {
       if (!text1.getText().startsWith("https://www.") || !text1.getText().startsWith("http://www.")) {
           if(!text1.getText().startsWith("https://"))
           text1.setText("https://www." + text1.getText());
       }
       if(!text1.getText().endsWith(".com"))
           text1.setText( text1.getText()+".com");
   }

   public void setTabTiltle(){
       if(!tab.getText().equals("main tab")) {
           String text = text1.getText();
           tab.setText(text.substring(text.indexOf(".") + 1, text.lastIndexOf(".")));
       }
   }

}