
package ui.controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;

/**
 *  This class acts as a workaround for initializing the app GUI.
 * 
 */
public class MainUIController extends Application {
    /**
     * The path to the fxml file for the welcome window.
     */
    private final URL welcomefxml= getClass().getClassLoader().getResource("fxml/HOMEPAGE.fxml");
    
    /**
     * Starts the application
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(welcomefxml);
        Scene scene1 = new Scene(loader.load());
        scene1.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Welcome to CentriVaccinali");
        primaryStage.setResizable(false);
        primaryStage.show();
   }
    
}
