package com.example.assignment_1gc200474224;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class SceneChanger {

    public static void changeScenes(ActionEvent event, String fxmlFileName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }
}
