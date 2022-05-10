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

import static hu.unideb.inf.FinancialData.typeOfCost.TRAVEL;


public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        startDatabase();
        FinancialDataDAO aDAOF = new JpaFinancialDataDAO();
        PersonDataDAO aDAOP = new JpaPersonDataDAO();


//////////////////////// koltseg keszites /////////////////////////////

        FinancialData fd = new FinancialData();
        fd.setCost(22322);
        fd.setCostType(FinancialData.typeOfCost.ENTERTAINMENT);
        fd.setDateOfPurchase(LocalDate.ofEpochDay(1992-1-1));
        aDAOF.saveFinancialData(fd);


        FinancialData fd2 = new FinancialData();
        fd2.setCost(22322);
        fd2.setCostType(TRAVEL);
        fd2.setDateOfPurchase(LocalDate.of(1882,12,13));
        aDAOF.saveFinancialData(fd2);

//////////////////////////Person keszites/////////////////////////

/*        PersonData person = new PersonData();
        person.setName("proba1");

        person.getFinancialDataList().add(fd);
        person.getFinancialDataList().add(fd);
        person.getFinancialDataList().add(fd2);
        aDAOP.savePersonData(person);

       for (FinancialData financialData: person.getFinancialDataList()){
            System.out.println(financialData.getCost());
            System.out.println(financialData.getCostType());

        }*/


        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/FXMLCostCounterScene.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Cost Counter");
        stage.setScene(scene);
        stage.show();

        Connection conn = DriverManager.getConnection("jdbc:h2:~/Prod_db", "sa", "");

        ////////////////// DB lekerdezes hogy megkapjuk az embert////////////////
        List<Integer> idls = new ArrayList<>();
        String query = "SELECT * FROM PERSONDATA WHERE NAME= 'proba1'";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int financialID = rs.getInt("ID");
                System.out.println(financialID);
                idls.add(financialID);
            }
        }
///////////////////////////////DB lekerdezes hogy megkapjuk a koltseget//////////////////////////////

     /*   for (Integer owner_iddd: idls) {

            String query2 = "SELECT * FROM FINANCIALDATA  WHERE COSTTYPE = 'ENTERTAINMENT' AND OWNER_PERSONDATA ='' ";
            try (Statement stmt2 = conn.createStatement()) {
                ResultSet rs = stmt2.executeQuery(query2);
                System.out.println(rs.next());
            }
        }*/



    }

    private static void startDatabase() throws SQLException {
        new Server().runTool("-tcp", "-web", "-ifNotExists");
    }

    public static void main(String[] args) {
        launch(args);
    }


}
