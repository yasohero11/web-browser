package sample;

import javafx.animation.FadeTransition;
import javafx.event.EventHandler;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import com.jfoenix.controls.*;
import javafx.util.Duration;

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
    private WebView web ;
    private JFXButton historyButton;
    private JFXButton homeButton;
    private JFXButton settingsButton;
    private  FlowPane easyButtonsLayout =  new FlowPane();
    private Tab tab;
    private JFXButton edit;
    public StackPane background;
    public boolean colored;
    private WebEngine engine ;
    private TabHistory tabHistory ;


   private TabClass(){

       background = new StackPane();
       background.setPrefSize(1360, 466);
           text1 = new JFXTextField();
           tabHistory = new TabHistory();
           text2 = new TextField();
           toolBar = new ToolBar();
           layout = new BorderPane();
           historyButton = new JFXButton("H");
           historyButton.setId("history");
           text1.setMinHeight(30);
          // text1.setAlignment(Pos.CENTER);
           text1.setMinWidth(1050);
           text1.setPromptText("Search...");
           text2.setMinHeight(30);
           text2.setMinWidth(800);
           text2.setPromptText("Search...");

           searchButton = new JFXButton("", NewTab.setImage("images/search.png", 22, 22));
           backButton = new JFXButton("", NewTab.setImage("images/back.png", 22, 22));
           forwardButton = new JFXButton("", NewTab.setImage("images/forward.png", 22, 22));
           restartButton = new JFXButton("", NewTab.setImage("images/restart.png", 22, 22));
           homeButton = new JFXButton("", NewTab.setImage("images/brower.png", 50, 50));
           settingsButton = new JFXButton("", NewTab.setImage("images/settings4.png", 20, 20));
           settingsButton.setId("settingsButton");
           settingsButton.setLayoutX(1300);
           settingsButton.setLayoutY(20);

           homeButton.setId("homeButton");
           forwardButton.setDisable(true);
           backButton.setDisable(true);
           restartButton.setDisable(true);
           toolBar.getItems().addAll(homeButton, backButton, forwardButton, restartButton, text1, searchButton, historyButton);
           layout.setTop(toolBar);
           ImageView logo = NewTab.setImage("images/brower.png", 200, 200);
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
           edit.setLayoutY(375);

        edit.setPrefSize(200 , 40);
        centerLayout.getChildren().addAll(background,title , text2 , logo , edit , settingsButton);
        text1.setUnFocusColor(Paint.valueOf("#44DA26"));
        text1.setFocusColor(Paint.valueOf("#F2F07D"));

        layout.setCenter(centerLayout);

        searchButton.setOnMouseClicked(e->{
            if(text1.getText().length() != 0) {
                browse();


            }
        });
        backButton.setOnMouseClicked(e-> {

                engine.executeScript("history.back()");
                text1.setText(engine.getLocation());
            tabHistory.back();
                check();

        });

        forwardButton.setOnMouseClicked(e->{

            engine.executeScript("history.forward()");
            tabHistory.forward();
            check();


        });
        restartButton.setOnMouseClicked(e->{
            engine.reload();
        });

        historyButton.setOnAction(e->{
            NewTab.history.show();
            NewTab.history.play();
        });
        edit.setOnAction(e->NewTab.easyButtons.edit.show());
        settingsButton.setOnAction(e->NewTab.settings.show());


/*
       settingsButton.setOnMouseMoved(e->{
           settingsButton.setStyle("-fx-background-color:" + EasyButtonMessage.color());
       });
*/
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
                browse();


            }
        });
        text2.setOnAction(e -> {
            if(text2.getText().length() != 0) {
                text1.setText(text2.getText());
                browse();

            }
        });
        homeButton.setOnMouseClicked(e->
        {
                layout.setCenter(centerLayout);
                text1.setText("");
                text2.setText("");
                setEngine();
                tabHistory.reset();
                check();
            if(!tab.getText().equals("main tab")) {
                tab.setText("new tab");
            }
        });


        setEngine();

    }

    private void setEngine(){
        web = new WebView();
        engine = new WebEngine();
        engine = web.getEngine();
        engine.setOnStatusChanged(new EventHandler<WebEvent<String>>() {
            @Override
            public void handle(WebEvent<String> event) {
                if (!NewTab.history.isEmpty()) {
                    History.HistoryNode historyNode = NewTab.history.getLastHistoryNode();
                    if (!historyNode.getUrl().equals(engine.getLocation())) {
                        if (!tabHistory.contains(engine.getLocation())) {
                            tabHistory.addUrl(engine.getLocation());
                            check();
                        }
                        NewTab.history.add(engine.getLocation());
                        text1.setText(engine.getLocation());
                        setTabTiltle();
                    }
                }
            }
        });

    }
    public boolean isSelected(){
        return tab.isSelected();
    }

    public void check(){
         if(!tabHistory.isEmpty())
             restartButton.setDisable(false);
         else
             restartButton.setDisable(true);

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


    public void setBackgroundColor(String color) {
            background.getChildren().clear();
            background.setStyle(color);
            colored = true;
            if(NewTab.settings.imageSettings.ImageSelected()){
                NewTab.settings.imageSettings.reset();
            }
    }

    public void setBackgroundImage(Image image) {
        background.getChildren().clear();
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(466);
        imageView.setFitWidth(1360);
        colored = false;
        background.getChildren().add(imageView);
    }

    public void browse(){
        checkUrl();
        setTabTiltle();
        engine.load(text1.getText());
        tabHistory.addUrl(text1.getText());
        check();
        NewTab.history.add(engine.getLocation());
        layout.setCenter(web);
    }
    private void setSize(JFXButton button , int size){
        button.setPrefSize(20,20);
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
       if (!text1.getText().startsWith("https://") && !text1.getText().startsWith("http://")) {
           if(text1.getText().startsWith("www."))
           text1.setText("https://" + text1.getText());
           else
               text1.setText("https://www." + text1.getText());

       }
       if(!text1.getText().contains(".com/"))
           if(text1.getText().contains(".com")){
               if((text1.getText().indexOf(".com")+4) == text1.getLength())
               text1.setText( text1.getText()+"/");

           }
           else
              text1.setText( text1.getText()+".com/");
   }

   public void setTabTiltle(){
       if(!tab.getText().equals("main tab")) {
           String text = text1.getText();
           tab.setText(text.substring(text.indexOf(".") + 1, text.indexOf("." ,text.indexOf(".")+1)));
       }
   }

}