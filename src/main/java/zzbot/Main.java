package zzbot;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



/**
 * The main class for the ZzBot application, responsible for launching the GUI.
 * This class extends {@link javafx.application.Application} and sets up the
 * main window by loading the FXML layout and connecting it to the controller.
 */
public class Main extends Application {

    private ZzBot zzbot = new ZzBot();

    /**
     * Starts the JavaFX application by setting up the main window.
     *
     * <p>This method loads the FXML layout for the main window, initializes
     * the scene, and displays the window with the ZzBot title. The ZzBot
     * instance is passed to the controller.
     *
     * @param stage the primary stage for this application, onto which the
     *              application scene is set
     */
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("ZzBot");
            fxmlLoader.<MainWindow>getController().setZzBot(zzbot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

