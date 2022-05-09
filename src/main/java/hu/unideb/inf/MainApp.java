package hu.unideb.inf;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.h2.tools.Server;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        startDatabase();
        FinancialDataDAO aDAOF= new JpaFinancialDataDAO();
        PersonDataDAO aDAOP = new JpaPersonDataDAO();
/*

//////////////////////// koltseg keszites /////////////////////////////

        FinancialData fd = new FinancialData();
        fd.setCost(22322);
        fd.setCostType(FinancialData.typeOfCost.ENTERTAINMENT);
        fd.setDateOfPurchase(LocalDate.ofEpochDay(1992-1-1));
        aDAOF.saveFinancialData(fd);

//////////////////////////Person keszites/////////////////////////

        PersonData person = new PersonData();
        person.setName("proba1");

        person.getFinancialDataList().add(fd);
        person.getFinancialDataList().add(fd);
        person.getFinancialDataList().add(fd);
        aDAOP.savePersonData(person);
*/
     /*   for (FinancialData financialData: person.getFinancialDataList()){
            System.out.println(financialData.getCost());
            System.out.println(financialData.getCostType());

        }*/






        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/FXMLCostCounterScene.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Cost Counter");
        stage.setScene(scene);
        stage.show();

        Connection conn = DriverManager.
        getConnection("jdbc:h2:~/Prod_db", "sa", "");

        ////////////////// DB lekerdezes hogy megkapjuk az embert////////////////
        String query = "SELECT * FROM PERSONDATA WHERE NAME= 'proba1'";
        try (Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                int financialID = rs.getInt("ID");
                System.out.println(financialID);

            }
        }
    }
    private static void startDatabase() throws SQLException {
        new Server().runTool("-tcp", "-web", "-ifNotExists");
    }
    public static void main(String[] args) {
        launch(args);
    }


}
