package com.example.demo4;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        TelegramDesktopUI telegramDesktopUI = new TelegramDesktopUI();

        Scene scene = new Scene(telegramDesktopUI, Screen.getPrimary().getVisualBounds().getWidth() * .72, Screen.getPrimary().getVisualBounds().getHeight()
                * .576);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        scene.getStylesheets().addAll(new File("src/main/resources/stylesheets/d.css").toURI().toASCIIString());
        stage.show();
        telegramDesktopUI.prefWidthProperty().bind(scene.widthProperty());
        telegramDesktopUI.prefHeightProperty().bind(scene.heightProperty());
    }

    public static void main(String[] args) {
        launch();
    }
}

class TelegramDesktopUI extends HBox
{
    Button menuButton,sendFileButton,imojiButton,micButton , callButton,searchButton ,userInfoButton,threeDotButton;
    TextField searchTextField , writeMessageTextField;
    HBox hBox1,hBox2;
    VBox vBox1 , vBox2;
    ListView listView1 , listView2;
    Label userNameLabel;
    public TelegramDesktopUI()
    {
        //
        listView1 = new ListView<>();
        listView2 = new ListView<>();
        //
        searchTextField = new TextField();
        writeMessageTextField = new TextField();
        //
        hBox1 = new HBox();
        hBox2 = new HBox();
        vBox1 = new VBox();
        vBox2 = new VBox();
        //
        menuButton = new Button();
        sendFileButton = new Button();
        imojiButton = new Button();
        micButton = new Button();
        callButton = new Button("Call");
        searchButton = new Button("Search");
        userInfoButton = new Button("usinfo");
        threeDotButton = new Button(":");
        // adding controls to the root
        hBox1.getChildren().addAll(menuButton,searchTextField);
        vBox1.getChildren().addAll(hBox1 ,listView1);
        hBox2.getChildren().addAll(sendFileButton,writeMessageTextField,imojiButton,micButton);
        vBox2.getChildren().addAll(listView2 , hBox2);
        getChildren().addAll(vBox1,vBox2);
        //
        searchTextField.prefWidthProperty().bind(hBox1.widthProperty().multiply(.87));
        writeMessageTextField.prefWidthProperty().bind(hBox2.widthProperty().multiply(.8));
        //
        listView1.prefWidthProperty().bind(vBox1.widthProperty());
        listView1.prefHeightProperty().bind(vBox1.heightProperty().multiply(.995));
        //
        listView2.prefWidthProperty().bind(vBox2.widthProperty());
        listView2.prefHeightProperty().bind(vBox2.heightProperty().multiply(.995));
        // hbox1 to vbox1
        hBox1.prefWidthProperty().bind(vBox1.widthProperty());
        hBox1.prefHeightProperty().bind(vBox1.heightProperty().multiply(.05));
        //
        hBox2.prefWidthProperty().bind(vBox2.widthProperty());
        hBox2.prefHeightProperty().bind(vBox2.heightProperty().multiply(.05));
        //binding the vboxses to the root
        vBox1.prefWidthProperty().bind(widthProperty().multiply(.35));
        vBox1.prefHeightProperty().bind(heightProperty());
        vBox2.prefWidthProperty().bind(widthProperty().multiply(.65));
        vBox2.prefHeightProperty().bind(heightProperty());
        // set spacing and padding
        hBox1.setSpacing(3.5);
        hBox2.setSpacing(3.5);
        // set alignment
        hBox1.setAlignment(Pos.CENTER);
        hBox2.setAlignment(Pos.CENTER);
        vBox1.setAlignment(Pos.CENTER);
        vBox2.setAlignment(Pos.CENTER);
        setAlignment(Pos.CENTER);
        // add icons
        ImageView menuIcon = new ImageView(new File("src/main/resources/images/menu-icon-24.png").toURI().toASCIIString());
        ImageView userIcon = new ImageView(new File("src/main/resources/images/user-icon.png").toURI().toASCIIString());
        ImageView micIcon = new ImageView(new File("src/main/resources/images/mic-icon.png").toURI().toASCIIString());
        ImageView imojiIcon = new ImageView(new File("src/main/resources/images/imoji-icon.png").toURI().toASCIIString());
        menuButton.setGraphic(menuIcon);
        sendFileButton.setGraphic(userIcon);
        micButton.setGraphic(micIcon);
        imojiButton.setGraphic(imojiIcon);
        // sizing icons
        menuIcon.setFitWidth(28);
        menuIcon.setFitHeight(22);
        userIcon.setFitWidth(28);
        userIcon.setFitHeight(22);
        micIcon.setFitWidth(28);
        micIcon.setFitHeight(22);
        imojiIcon.setFitWidth(28);
        imojiIcon.setFitHeight(22);
        //
        for (int i =0 ; i < 100 ; i++)
        {
            listView1.getItems().addAll(new User("User",new Image(new File("src/main/resources/images/user-icon.png").toURI().toASCIIString())));

            listView2.getItems().addAll(new Message(Math.random() + "","this is a telegram message"));
        }
        listView1.setCellFactory(user -> new UserListCell());
        listView2.setCellFactory(message -> new MessageListCell());
    }

}
// create message class
class Message{
    String timestamp;
    String message;

    public Message(String timestamp , String message)
    {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
// create user class
class User
{
    private String name;
    private Image userImage;


    public User(String name , Image userImage)
    {
        this.name = name;
        this.userImage = userImage;
    }

    public String getName() {
        return name;
    }

    public Image getUserImage() {
        return userImage;
    }
}

// user repo class
class UserRepo{
    static ObservableList userList = FXCollections.observableArrayList();

    public static ObservableList getUserList() {
        return userList;
    }
}

// message repo
class MessageRepo{
    static ObservableList messageList = FXCollections.observableArrayList();

    public static ObservableList getMessageList() {
        return messageList;
    }
}
// user list cell
class UserListCell extends ListCell<User>{
    @Override
    protected void updateItem(User user, boolean b) {
        HBox hBox = new HBox(15);
        Label messageLabel = new Label();
        ImageView imageView = new ImageView(new File("src/main/resources/images/logo.png").toURI().toASCIIString());

        super.updateItem(user, b);
        if (user != null)
        {
            messageLabel.setText(user.getName());
            imageView = new ImageView(user.getUserImage());
        }
        imageView.setFitWidth(32);
        imageView.setFitHeight(24);
        //
        hBox.getChildren().addAll(imageView,messageLabel);
        setGraphic(hBox);
    }
}
class MessageListCell extends ListCell<Message>
{

    @Override
    protected void updateItem(Message message, boolean b) {

        HBox hBox = new HBox(15);
        Label messageLabel = new Label();
        Label timestampLabel = new Label();

        super.updateItem(message, b);
        if (message != null)
        {
            messageLabel.setText(message.getMessage());
            timestampLabel.setText(message.getTimestamp());
        }
        //
        hBox.getChildren().addAll(timestampLabel,messageLabel);
        setGraphic(hBox);
        //
    }
}