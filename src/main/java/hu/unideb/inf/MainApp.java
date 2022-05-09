package hu.unideb.inf;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.h2.tools.Server;

import java.sql.SQLException;
import java.time.LocalDate;


public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        startDatabase();

       /* PersonDataDAO aDAO= new JpaPersonDataDAO();
        PersonData a = new PersonData();
        a.setName("hoyomany");
        //a.setIncome(211222);
        aDAO.savePersonData(a);
        aDAO.close();

        FinancialData fd = new FinancialData(200, LocalDate.of(2022,04,26));
*/
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/FXMLCostCounterScene.fxml"));
        Scene scene = new Scene(loader.load());
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
