package sample;

import com.jfoenix.controls.JFXRadioButton;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.LinkedList;

public class ImageSettings extends BackgroundRW {

    private VBox layout;
    private ToggleGroup group;
    private ScrollPane scrollPane;
    LinkedList<ImageSettingsItem> list;
    ImageSettings(){
        super("background.txt");
        layout = new VBox();
        layout.setSpacing(10);
        scrollPane = new ScrollPane(layout);
        group = new ToggleGroup();
        list = new LinkedList<>();
        read();

        add("images/m.jpg");
        add("images/m2.jpg");
        add("images/m3.jpg");
        add("images/moon1.jpg");
        add("images/image.jpg");
        add("images/flag.png");
        add("images/maracco.png");
        add("images/milky-way.jpg");
        add("images/water.jpg");
        add("images/cool.jpeg");
        add("images/moon.jpg");

        if(!isColor()){
            for(int i = 0 ;  i  < list.size(); i++){
                if(list.get(i).imageUrl.equals(getBackground())){
                    list.get(i).radioButton.setSelected(true);
                    applyChanges(getSelectedImage());
                    i = list.size();
                }
            }
        }
        layout.setStyle("-fx-background-color: #B3B6B7");
        layout.setPrefSize(265 , 430);

    }



    public void add(String url){
        ImageSettingsItem item = new ImageSettingsItem(url);
        list.add(item);
    }
    public ImageSettingsItem getSelectedItem(){
        for(int i = 0 ; i  < list.size(); i++)
            if(list.get(i).radioButton.isSelected()){
                return list.get(i);
            }

        return  null;
    }
    public Image getSelectedImage(){
        return getSelectedItem().img;
    }
    public boolean ImageSelected(){
        return (getSelectedItem() != null);
    }
    public void reset(){
        getSelectedItem().radioButton.setSelected(false);
    }

    public ScrollPane getLayout() {
        return scrollPane;
    }
     private void applyChanges(Image image){
         for(int i = 0 ; i  < NewTab.tabList.size(); i++){
             NewTab.tabList.get(i).setBackgroundImage(image);
         }
     }
    class ImageSettingsItem{
       private HBox pane = new HBox();
       public JFXRadioButton radioButton;
       public Image img;
       public String imageUrl;
       private ImageView imageView;
       ImageSettingsItem(String url){
           pane = new HBox();
           radioButton = new JFXRadioButton();
           radioButton.setToggleGroup(group);
           radioButton.setPadding(new Insets(23,0,0,20));
           img = new Image(url);
           imageUrl = url;
           imageView = new ImageView(img);
           imageView.setFitWidth(100);
           imageView.setFitHeight(70);
           pane.setSpacing(100);
           pane.getChildren().addAll(radioButton ,  imageView);
           layout.getChildren().add(pane);
           radioButton.setOnAction(e->{
                  applyChanges(img);
                  NewTab.settings.backgroundColor.reset();
               setBackground(imageUrl);
           });
       }


    }

}
