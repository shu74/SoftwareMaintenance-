/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neet.DiamondHunter.TileMapEditor;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Ashfaaq
 */
public class App_javafx extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
        Parent fuzz = FXMLLoader.load(getClass().getResource("TileMapEditor.fxml"));
        stage.setTitle("TileEditor");
        stage.setScene(new Scene(fuzz, 643, 800));
        stage.setResizable(true);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
