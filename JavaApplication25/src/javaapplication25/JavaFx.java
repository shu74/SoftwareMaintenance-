/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication25;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
/**
 *
 * @author Ashfaaq
 */
public class JavaFx extends Application{
    
      
    @Override
    public void start (Stage stage) {
       
        final SwingNode swingNode = new SwingNode();

        createSwingContent(swingNode);
       
        StackPane pane = new StackPane();
        pane.getChildren().add(swingNode);
        
        stage.setTitle("Swing in JavaFX");
        stage.setScene(new Scene(pane, 250, 150));
        stage.show();
    }

    

    private void createSwingContent(final SwingNode swingNode){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                swingNode.setContent(new JButton("Click me!"));
            }
        });

    }   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

