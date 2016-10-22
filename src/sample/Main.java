package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.geometry.Orientation;
import javafx.stage.Stage;

import java.util.Collection;

public class Main extends Application //implements EventHandler<ActionEvent>
{
    //intialization
    Stage window;
    Scene scene1, scene2, scene3;

    Button assignBtn;
    Button contBtn;

    Button backBtn1;

    public static void main(String[] args)
    {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception
    {
        window = primaryStage;
        window.setResizable(false);

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        //Scene 1 Children

            //top pane : stack
        Image preImg = new Image(getClass().getResourceAsStream("ptIcon.png"), 120, 120, true, true);
        ImageView previewer = new ImageView();
        previewer.setImage(preImg);

        //left pane : hbox
        Label welcomeLabel = new Label("WELCOME TO");
        Label photagLabel1 = new Label("Photag");
        welcomeLabel.setStyle("-fx-text-fill: #dee8f9; -fx-font-weight: bold;");
        photagLabel1.setStyle("-fx-text-fill: #558AAB");

            //right pane : vbox
        assignBtn = new Button("Assign Main Folder");
        assignBtn.setOnAction(e -> System.out.println("Opening directory chooser."));

        contBtn = new Button("Continue");
        contBtn.setOnAction(e -> {System.out.println("Continuing to directory."); window.setScene(scene2);});

            //right pane subsection : hbox
        Label poweredByLabel = new Label("POWERED BY");
        poweredByLabel.setStyle("-fx-text-fill: #dee8f9; -fx-font-weight: bold; -fx-font-size: 10pt; -fx-padding: 10,0,0,0");

        Image logo = new Image(getClass().getResourceAsStream("clarifai_logo.png"), 100, 50, true, true);
        ImageView img = new ImageView();
        img.setImage(logo);

        //First, section 1
        StackPane fSec1 = new StackPane();
        fSec1.setStyle("-fx-background-color: white");
        fSec1.getChildren().addAll(previewer);

        //First, section 2
        HBox fSec2 = new HBox(20);
        fSec2.getChildren().addAll(welcomeLabel, photagLabel1);

        //First, section 3
        VBox fSec3 = new VBox(10);
//        Orientation.VERTICAL

        HBox subSection2 = new HBox();
        subSection2.getChildren().addAll(poweredByLabel, img);

        fSec3.getChildren().addAll(assignBtn, contBtn);
        fSec3.getChildren().forEach(button ->
        {
            ((Button)button).setMinWidth(Button.USE_PREF_SIZE);
            ((Button)button).setMaxWidth(Double.MAX_VALUE);
            ((Button)button).setMinHeight(Button.USE_PREF_SIZE);
            ((Button)button).setMaxHeight(Double.MAX_VALUE);
        });

        fSec3.getChildren().addAll(subSection2);

        HBox bottomPane = new HBox(10);
        bottomPane.getChildren().addAll(fSec2, fSec3);

        BorderPane firstPane = new BorderPane();

        firstPane.setMargin(fSec1, new Insets(20,20,0,20));
        firstPane.setMargin(bottomPane, new Insets(20,20,0,20));

        bottomPane.setMargin(fSec2, new Insets(30,20,20,20));
        bottomPane.setMargin(fSec3, new Insets(0,20,20,20));

        firstPane.setCenter(fSec1);

        firstPane.setBottom(bottomPane);



        scene1 = new Scene(firstPane, 760, 680);
        scene1.getStylesheets().add(Main.class.getResource("PhotagStyleSheet.css").toExternalForm());

        //Scene 2 Children
            //top pane : stack
        Label photagLabel2 = new Label("Photag");

            //center pane : stack
        backBtn1 = new Button("Back");
        backBtn1.setOnAction(e -> {System.out.println("Back to main page."); window.setScene(scene1);});


        //Second, section 1
        StackPane sSec1 = new StackPane();
        sSec1.getChildren().addAll(photagLabel2);

        //Second, section 2
        StackPane sSec2 = new StackPane();
        sSec2.getChildren().addAll(backBtn1);

        BorderPane secondPane = new BorderPane();
        secondPane.setTop(sSec1);
        secondPane.setCenter(sSec2);

        scene2 = new Scene(secondPane, 760, 680);

        window.setScene(scene1);
        window.setTitle("Photag - Intelligent Photo Organizational Tool");
        window.show();
    }

//    @Override
//    public void handle(ActionEvent event)
//    {
//        if(event.getSource() == assignBtn)
//        {
//            System.out.println("Opening directory chooser.");
//        }
//        else if(event.getSource() == contBtn)
//        {
//            System.out.println("Continuing to directory.");
//        }
//    }
}
