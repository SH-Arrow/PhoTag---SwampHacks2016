package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
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

        //Scene 1 Set Up
        BorderPane firstPane = new BorderPane();
        firstPane = firstPaneSetUp(firstPane);

        scene1 = new Scene(firstPane, 760, 680);
        scene1.getStylesheets().add(Main.class.getResource("PhotagStyleSheet.css").toExternalForm());

        //Scene 2 Set Up
        BorderPane secondPane = new BorderPane();
        secondPane = secondPaneSetUp(secondPane);

        scene2 = new Scene(secondPane, 760, 680);
        scene2.getStylesheets().add(Main.class.getResource("PhotagStyleSheet.css").toExternalForm());

        window.setScene(scene1);
        window.setTitle("Photag - Intelligent Photo Organizational Tool");
        window.show();
    }

    public BorderPane firstPaneSetUp(BorderPane fP)
    {
        //Scene 1 Children

        //top pane : stack
        Image preImg = new Image(getClass().getResourceAsStream("ptIcon.png"), 120, 120, true, true);
        ImageView previewer = new ImageView();
        previewer.setImage(preImg);

        //left pane : hbox
        Label welcomeLabel = new Label("WELCOME TO");
        Label photagLabel = new Label("Photag");
        welcomeLabel.setStyle("-fx-text-fill: #dee8f9; -fx-font-weight: bold;");
        photagLabel.setStyle("-fx-text-fill: #558AAB");

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
        fSec2.getChildren().addAll(welcomeLabel, photagLabel);

        //First, section 3
        VBox fSec3 = new VBox(10);

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

        fP.setMargin(fSec1, new Insets(20,20,0,20));
        fP.setMargin(bottomPane, new Insets(20,20,0,20));

        bottomPane.setMargin(fSec2, new Insets(30,20,20,20));
        bottomPane.setMargin(fSec3, new Insets(0,20,20,20));

        fP.setCenter(fSec1);

        fP.setBottom(bottomPane);

        return fP;
    }

    public BorderPane secondPaneSetUp(BorderPane sP)
    {
        //Scene 2 Children
        //top pane  left: stack
        Label photagLabel = new Label("Photag");
        photagLabel.setStyle("-fx-text-fill: #558AAB;");

        //top pane right : stack
        backBtn1 = new Button("Back");
        backBtn1.setOnAction(e -> {System.out.println("Back to main page."); window.setScene(scene1);});

        //top
        Region reg = new Region();
        HBox top = new HBox();
        top.getChildren().addAll(photagLabel, reg, backBtn1);
        top.setHgrow(reg, Priority.ALWAYS);


        //left pane : stack
        StackPane left = new StackPane();
        Image preImg = new Image(getClass().getResourceAsStream("ptIcon.png"), 120, 120, true, true);
        ImageView previewer = new ImageView();
        previewer.setImage(preImg);
        left.setStyle("-fx-background-color: white");
        left.getChildren().add(previewer);

        //right pane : tableview
        ScrollPane right = new ScrollPane();

        //right pane subsection : table view

            //stack sub
            ObservableList<StackPane> list = getImagePreview();

        TilePane tile = new TilePane();

            for(StackPane stack: list)
            {
                tile.getChildren().add(stack);
            }

        tile.setPrefColumns(3);
        tile.setHgap(20);
        tile.setVgap(20);
        tile.setPrefTileHeight(220);

        right.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);    // Horizontal scroll bar
        right.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);    // Vertical scroll bar
        right.setContent(tile);
        right.setBackground(Background.EMPTY);

        //bottom pane : hbox
        Label poweredByLabel = new Label("POWERED BY");
        poweredByLabel.setStyle("-fx-text-fill: #dee8f9; -fx-font-weight: bold; -fx-font-size: 10pt; -fx-padding: 10,0,0,0");

        Image logo = new Image(getClass().getResourceAsStream("clarifai_logo.png"), 100, 50, true, true);
        ImageView img = new ImageView();
        img.setImage(logo);
        Region reg2 = new Region();
        Region reg3 = new Region();
        HBox bottom = new HBox();
        bottom.getChildren().addAll(reg2, reg3, poweredByLabel, img);
        bottom.setHgrow(reg2, Priority.ALWAYS);
        bottom.setHgrow(reg3, Priority.ALWAYS);


        //Second, section 2
        HBox center = new HBox();

        center.getChildren().addAll(left, right);
        center.setHgrow(left, Priority.ALWAYS);
        center.setHgrow(right, Priority.NEVER);
        center.setMargin(left, new Insets(0,20,0,0));

        sP.setMargin(top, new Insets(20,20,20,20));
        sP.setMargin(center, new Insets(20,20,20,20));
        sP.setMargin(bottom, new Insets(20,20,20,20));

        sP.setTop(top);
        sP.setCenter(center);
        sP.setBottom(bottom);
        return sP;
    }

    public ObservableList<StackPane> getImagePreview()
    {
        ObservableList<StackPane> stackList = FXCollections.observableArrayList();

        //for now we are doing 6 instances
        for(int i = 0; i < 16; i++)
        {
            StackPane subImgLabel = new StackPane();
            Label tag = new Label("Family•");

            tag.setStyle("-fx-background-color: #558AAB;\n" +
                    "    -fx-text-fill: #dee8f9;\n" +
                    "    -fx-font-size: 12pt;\n" +
                    "    -fx-font-family: \"Helvetica Neue\";" +
                    "    -fx-background-radius: 10% 75% 75% 10%;" +
                    "    -fx-background-insets: -5, -10, -10, -5;");

            ImageView image = new ImageView(new Image(getClass().getResourceAsStream("ptIcon.png"), 120, 120, true, true));
            image.setFitHeight(220);
            image.setFitWidth(120);
            image.setPreserveRatio(true);

            subImgLabel.getChildren().add(image);
            subImgLabel.getChildren().add(tag);
            subImgLabel.setStyle("-fx-background-color: #dee8f9;");
            subImgLabel.setMinHeight(220);
            subImgLabel.setMinWidth(120);
            subImgLabel.setMargin(image, new Insets(0,0,0,0));
            subImgLabel.setMargin(tag, new Insets(-180,0,0,-40));
            stackList.add(subImgLabel);
        }

        return stackList;
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
