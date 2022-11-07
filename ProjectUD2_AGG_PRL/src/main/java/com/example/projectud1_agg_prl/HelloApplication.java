package com.example.projectud1_agg_prl;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;

public class    HelloApplication extends Application {
    /**
     *
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage.setTitle("MHW-API ARMOR VISUALIZER!");
        stage.setScene(new Scene(root,640,380));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}