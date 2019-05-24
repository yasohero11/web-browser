package sample;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ColorSettings extends  BackgroundRW{
    private Circle circle;
    private ColorPicker colorPicker;
    private FlowPane layout;
    private Node pane;
    ColorSettings(Node pane  , String fileName){
        super(fileName);
        layout = new FlowPane();
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setVgap(20);
        circle = new Circle(50);
        colorPicker = new ColorPicker();

        read();
        if(isColor()) {
            colorPicker.setValue(Color.web(getBackground()));
            circle.setFill(colorPicker.getValue());
        }
        else
            reset();

        if(pane instanceof TabPane) {
            if (isColor())
                pane.setStyle("-fx-background-color:" + getBackground());
        }
        else
            ((HistoryPane) pane).setColor(getBackground());


/*
        NewTab.tabList.addListener(new ListChangeListener<TabClass>() {
            @Override
            public void onChanged(Change<? extends TabClass> c) {
                System.out.println("en");
               NewTab.tabList.get(NewTab.tabList.size()-1).setBackhgroundColor("-fx-background-color:" + color);
            }
        });

*/
        //NewTab.pane.setStyle("-fx-background-color:" + format(colorPicker.getValue().toString()));

        colorPicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                circle.setFill(colorPicker.getValue());
                if(pane instanceof TabPane) {

                    for(int i = 0 ;  i < NewTab.tabList.size(); i++) {
                        // NewTab.tabList.get(i).setBackhgroundColor("-fx-background-color:" + format(colorPicker.getValue().toString()));
                        NewTab.tabList.get(i).setBackgroundColor("-fx-background-color:" + format(colorPicker.getValue().toString()));
                    }
                    setBackground(format(colorPicker.getValue().toString()));
                    pane.setStyle("-fx-background-color:" + getBackground());
                }
                else {
                    ((HistoryPane) pane).setColor(format(colorPicker.getValue().toString()));
                    setBackground(format(colorPicker.getValue().toString()));
                }
            }
        });
        layout.setPadding(new Insets(10));
        layout.setHgap(10);
        layout.getChildren().addAll(circle, colorPicker);
    }




     public void reset(){
         colorPicker.setValue(Color.WHITE);
         circle.setFill(colorPicker.getValue());
     }


    public FlowPane getColorLayout() {
        return layout;
    }
}
