package edu.ib;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class TVDemo extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage startStage) throws IOException {

        Parent root= FXMLLoader.load(getClass().getResource("/fxml/start.fxml"));
        Scene scene= new Scene(root,400,300);
        /*Image img = new Image("C:\\Users\\User\\Desktop\\SEMESTR 5\\Metody numeryczne\\Lab 12\\5529077.jpg");
        BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);*/
        startStage.setTitle("Differential Equation Calculator");
        startStage.setScene(scene);
        startStage.show();
    }

    public void onStartClicked(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/tvdemo.fxml"));
        Scene scene= new Scene(root,800,600);
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Differential Equation Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
