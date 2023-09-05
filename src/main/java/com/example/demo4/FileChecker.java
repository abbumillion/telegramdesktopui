package com.example.demo4;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.util.StringTokenizer;
public class FileChecker extends Application {
    @Override
    public void start(Stage primaryStage) {
        FileCherView fileCherView = new FileCherView();
        Scene scene = new Scene(fileCherView, Screen.getPrimary().getVisualBounds().getWidth(),Screen.getPrimary().getVisualBounds().getHeight() , Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setFullScreen(true);
        TextDemoService textDemoService = new TextDemoService();
        new Thread(textDemoService).start();
        fileCherView.prefWidthProperty().bind(scene.widthProperty());
        fileCherView.prefHeightProperty().bind(scene.heightProperty());
        textDemoService.messageProperty().addListener((observableValue, s, t1) -> {
            if (t1 != null)
            {

                StringTokenizer stringTokenizer = new StringTokenizer(t1,",");
                int total = Integer.parseInt(stringTokenizer.nextToken());
                int songs = Integer.parseInt(stringTokenizer.nextToken());
                int videos = Integer.parseInt(stringTokenizer.nextToken());
                int folders = Integer.parseInt(stringTokenizer.nextToken());
                int apps = Integer.parseInt(stringTokenizer.nextToken());
                int c = Integer.parseInt(stringTokenizer.nextToken());
                int cpp = Integer.parseInt(stringTokenizer.nextToken());
                int dll = Integer.parseInt(stringTokenizer.nextToken());
                int java = Integer.parseInt(stringTokenizer.nextToken());
                int pdf = Integer.parseInt(stringTokenizer.nextToken());
                int doc = Integer.parseInt(stringTokenizer.nextToken());
                int ppt = Integer.parseInt(stringTokenizer.nextToken());

                fileCherView.getCard1().getValueLabel().setText(String.valueOf(total + songs + videos + folders + apps + c + cpp + dll + java + pdf + doc + ppt));
                fileCherView.getCard2().getValueLabel().setText(String.valueOf(songs));
                fileCherView.getCard3().getValueLabel().setText(String.valueOf(videos));
                fileCherView.getCard4().getValueLabel().setText(String.valueOf(folders));
                fileCherView.getCard5().getValueLabel().setText(String.valueOf(apps));
                fileCherView.getCard6().getValueLabel().setText(String.valueOf(c));
                fileCherView.getCard7().getValueLabel().setText(String.valueOf(cpp));
                fileCherView.getCard8().getValueLabel().setText(String.valueOf(dll));
                fileCherView.getCard9().getValueLabel().setText(String.valueOf(java));
                fileCherView.getCard10().getValueLabel().setText(String.valueOf(pdf));
                fileCherView.getCard11().getValueLabel().setText(String.valueOf(doc));
                fileCherView.getCard12().getValueLabel().setText(String.valueOf(ppt));
                fileCherView.getCard13().getValueLabel().setText(String.valueOf(ppt));


            }
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
class TextDemoService extends Task {
    private File[] DRIVES = {new File("C://"),new File("D://")};
    int count = 0 , sng = 0 , vds = 0 , fldrs = 0 , imgs = 0 , apps = 0 , c = 0 , cpp = 0 , dll = 0 , java = 0 , pdf = 0 , doc = 0 , ppt = 0;

    private void startSlideShow(File dir) {
        if (dir.listFiles() != null)
        {
            File[] files = dir.listFiles();
            for (File file : files)
            {
                count++;
                if (file.isDirectory())
                {
                    fldrs++;
                    startSlideShow(file);
                } else if (file.isFile()) {
                    String path = file.getPath();;
                    // songs
                    if (path.contains(".mp3") || path.contains(".MP3") || path.contains(".m4a") || path.contains(".M4A"))
                    {
                        sng++;
                    }
                    // video
                    if (path.contains(".mp4") || path.contains(".mkv") || path.contains(".avi") || path.contains(".mpeg"))
                    {
                        vds++;
                    }
                    // images
                    if (path.contains(".png") || path.contains(".PNG") || path.contains(".jpg") || path.contains(".JPG"))
                    {
                        imgs++;
                    }
                    // apps
                    if (path.contains(".exe") || path.contains(".EXE"))
                    {
                        apps++;
                    }
                    // c
                    if (path.contains(".c") || path.contains(".mkv") || path.contains(".avi") || path.contains(".mpeg"))
                    {
                        c++;
                    }
                    // cpp
                    if (path.contains(".cxx") || path.contains(".cpp") || path.contains(".jpg") || path.contains(".JPG"))
                    {
                        cpp++;
                    }

                    // dll
                    if (path.contains(".mp3") || path.contains(".dll") || path.contains(".m4a") || path.contains(".M4A"))
                    {
                        dll++;
                    }
                    // java
                    if (path.contains(".mp4") || path.contains(".java") || path.contains(".avi") || path.contains(".mpeg"))
                    {
                        java++;
                    }
                    // pdf
                    if (path.contains(".pdf") || path.contains(".PDF") )
                    {
                        pdf++;
                    }
                    // doc
                    if (path.contains(".docx") || path.contains(".DOC") || path.contains(".m4a") || path.contains(".M4A"))
                    {
                        doc++;
                    }
                    // ppt
                    if (path.contains(".pdf") || path.contains(".PDF"))
                    {
                        pdf++;
                    }
                    String message = count + "," + sng + "," + vds + ","+ fldrs + "," + apps + "," + c + "," + cpp + "," + dll + "," + java + "," + pdf + "," + doc + "," + ppt;
                    updateMessage(message);
                }
            }
        }
    }
    @Override
    protected Object call() throws InterruptedException {
        for (File file : DRIVES)
        {
            startSlideShow(file);
        }
        return null;
    }
}
}

class FileCherView extends VBox {
    Card card1 , card2,card3,card4,card5,card6,card7,card8,card9,card10,card11,card12,card13;
    HBox hBox1,hBox2,hBox3,hBox4,hBox5;
    public FileCherView() {
        //

        card1 = new Card("Total");
        card2 = new Card("Song");
        card3 = new Card("Video");
        card4 = new Card("Folder");
        card5 = new Card("Apps");
        card6 = new Card("C");
        card7 = new Card("Cpp");
        card8 = new Card("Dll");
        card9 = new Card("Java");
        card10 = new Card("Pdf");
        card11 = new Card("Doc");
        card12 = new Card("Ppt");
        card13 = new Card("Total");
        //
        hBox1 = new HBox();
        hBox2 = new HBox();
        hBox3 = new HBox();
        hBox4 = new HBox();
        hBox5 = new HBox();
        //
        setAlignment(Pos.CENTER);
        hBox1.setAlignment(Pos.CENTER);
        hBox2.setAlignment(Pos.CENTER);
        hBox3.setAlignment(Pos.CENTER);
        hBox4.setAlignment(Pos.CENTER);
        hBox5.setAlignment(Pos.CENTER);
        //
        hBox1.getChildren().addAll(card2,card3,card4);
        hBox2.getChildren().addAll(card5,card6,card7);
        hBox3.getChildren().addAll(card8,card9,card10);
        hBox4.getChildren().addAll(card11,card12,card13);
        hBox5.getChildren().addAll(card1);
        //
        getChildren().addAll(hBox1,hBox2,hBox3,hBox4,hBox5);
        //
        hBox1.prefWidthProperty().bind(widthProperty());
        hBox2.prefWidthProperty().bind(widthProperty());
        hBox3.prefWidthProperty().bind(widthProperty());
        hBox4.prefWidthProperty().bind(widthProperty());
        hBox5.prefWidthProperty().bind(widthProperty());

        hBox1.prefHeightProperty().bind(heightProperty().multiply(.2));
        hBox2.prefHeightProperty().bind(heightProperty().multiply(.2));
        hBox3.prefHeightProperty().bind(heightProperty().multiply(.2));
        hBox4.prefHeightProperty().bind(heightProperty().multiply(.2));
        hBox5.prefHeightProperty().bind(heightProperty().multiply(.2));
        //
        card1.prefWidthProperty().bind(hBox5.widthProperty().multiply(2));
        card1.prefHeightProperty().bind(hBox5.heightProperty());
        //
        card2.prefWidthProperty().bind(hBox1.widthProperty().multiply(2));
        card2.prefHeightProperty().bind(hBox1.heightProperty());
        //
        card3.prefWidthProperty().bind(hBox1.widthProperty().multiply(2));
        card3.prefHeightProperty().bind(hBox1.heightProperty());
        //
        card4.prefWidthProperty().bind(hBox1.widthProperty().multiply(2));
        card4.prefHeightProperty().bind(hBox1.heightProperty());
        //
        card5.prefWidthProperty().bind(hBox2.widthProperty().multiply(2));
        card5.prefHeightProperty().bind(hBox2.heightProperty());
        //
        card6.prefWidthProperty().bind(hBox2.widthProperty().multiply(2));
        card6.prefHeightProperty().bind(hBox2.heightProperty());
        //
        card7.prefWidthProperty().bind(hBox2.widthProperty().multiply(2));
        card7.prefHeightProperty().bind(hBox2.heightProperty());
        //
        card8.prefWidthProperty().bind(hBox3.widthProperty().multiply(2));
        card8.prefHeightProperty().bind(hBox3.heightProperty());
        //
        card9.prefWidthProperty().bind(hBox3.widthProperty().multiply(2));
        card9.prefHeightProperty().bind(hBox3.heightProperty());
        //
        card10.prefWidthProperty().bind(hBox3.widthProperty().multiply(2));
        card10.prefHeightProperty().bind(hBox3.heightProperty());
        //
        card11.prefWidthProperty().bind(hBox4.widthProperty().multiply(2));
        card11.prefHeightProperty().bind(hBox4.heightProperty());
        //
        card12.prefWidthProperty().bind(hBox4.widthProperty().multiply(2));
        card12.prefHeightProperty().bind(hBox4.heightProperty());
        //
        card13.prefWidthProperty().bind(hBox4.widthProperty().multiply(2));
        card13.prefHeightProperty().bind(hBox4.heightProperty());
        //
    }

    public Card getCard1() {
        return card1;
    }

    public Card getCard2() {
        return card2;
    }

    public Card getCard3() {
        return card3;
    }

    public Card getCard4() {
        return card4;
    }

    public Card getCard5() {
        return card5;
    }

    public Card getCard6() {
        return card6;
    }

    public Card getCard7() {
        return card7;
    }

    public Card getCard8() {
        return card8;
    }

    public Card getCard9() {
        return card9;
    }

    public Card getCard10() {
        return card10;
    }

    public Card getCard11() {
        return card11;
    }

    public Card getCard12() {
        return card12;
    }

    public Card getCard13() {
        return card13;
    }
}
class Card extends VBox{
    Label valueLabel,nameLabel;
    public Card(String name){
        valueLabel = new Label();
        nameLabel = new Label(name);
        setAlignment(Pos.CENTER);
        valueLabel.setAlignment(Pos.CENTER);
        nameLabel.setAlignment(Pos.CENTER);
        valueLabel.prefWidthProperty().bind(widthProperty().multiply(.7));
        valueLabel.prefHeightProperty().bind(heightProperty().multiply(.3));
        nameLabel.prefWidthProperty().bind(widthProperty().multiply(.5));
        nameLabel.prefHeightProperty().bind(heightProperty().multiply(.6));
        getChildren().addAll(valueLabel,nameLabel);
        valueLabel.setFont(Font.font(40));
        nameLabel.setFont(Font.font(20));
        //
        setStyle("-fx-border-color: black;");
//        nameLabel.setStyle("-fx-border-color: black;");
    }

    public Label getNameLabel() {
        return nameLabel;
    }

    public Label getValueLabel() {
        return valueLabel;
    }
}