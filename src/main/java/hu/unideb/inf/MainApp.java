package hu.unideb.inf;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.h2.tools.Server;

import java.sql.*;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        startDatabase();

        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/FXMLCostCounterScene.fxml"));
        Scene scene = new Scene(loader.load());
        //String css = this.getClass().getResource("Styles.css").toExternalForm(); // runtime error
        //scene.getStylesheets().add(css); // runtime error
        stage.setTitle("Cost Counter");
        stage.setScene(scene);
        stage.show();
    }

    private static void startDatabase() throws SQLException {
        new Server().runTool("-tcp", "-web", "-ifNotExists");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
