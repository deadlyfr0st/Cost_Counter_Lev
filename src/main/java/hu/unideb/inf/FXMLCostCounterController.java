package hu.unideb.inf;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLCostCounterController implements Initializable {

    @FXML
    private Button handleAverageButtonPushed;
    @FXML
    private Button handleDataUpLoadButtonPushed;
    @FXML
    private Button handleSearchButtonPushed;
    @FXML
    private Button handleRegisterButtonPushed;
    @FXML
    private TextField handleNameTypeing;
    @FXML
    private TextField handleDateFromTypeing;
    @FXML
    private TextField handleDateToTypeing;
    @FXML
    private TextField handleDateTypeing;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        CostTypeChoiceBox.getItems().addAll(costType);
        CostTypeChoiceBox1.getItems().addAll(costType);
        nameChoiceBox.getItems().addAll(costType);
        nameChoiceBox1.getItems().addAll(names);
    }




    //végleges adatokra kell cserélni ez csak a teszthez van
    private String[] costType = {"étel", "szórakozás", "utazás"};
    private String[] names = {"Piri", "Mari", "Béla"}; //


    @FXML
    private ChoiceBox<String> CostTypeChoiceBox; //költség típus lenyíló menü

    @FXML
    private ChoiceBox<String> CostTypeChoiceBox1;  //költség típus lenyíló menü

    @FXML
    private ChoiceBox<String> nameChoiceBox; // név lenyíló menü

    @FXML
    private ChoiceBox<String> nameChoiceBox1; // név lenyíló menü

    // A táblázatok vezérlése

    @FXML
    private TableView<PersonData> tableView;

    @FXML
    private TableColumn<FinancialData, LocalDate> DateFromColumn;

    @FXML
    private TableColumn<FinancialData, LocalDate> DateToColumn;

    @FXML
    private TableColumn<PersonData, String> NameColumn;

    @FXML
    private TableColumn<FinancialData, Integer> PriceColumn;

    @FXML
    private TableColumn<FinancialData, String> TypeColumn;

    // Gombok vezérlése
    @FXML
    void handleAverageButtonPushed(ActionEvent event) {

    }

    @FXML
    void handleDataUpLoadButtonPushed(ActionEvent event) {

    }

    @FXML
    void handleDateFromTypeing(ActionEvent event) {

    }

    @FXML
    void handleDateToTypeing(ActionEvent event) {

    }

    @FXML
    void handleDateTypeing(ActionEvent event) {

    }

    @FXML
    void handleSearchButtonPushed(ActionEvent event) {

    }

    @FXML
    void handleNameTypeing(ActionEvent event) {

    }

    @FXML
    void handleRegisterButtonPushed(ActionEvent event) {

        // A handleNameTypeing.getValu -t kell menteni az adatbázisba.
        // JPA ban megvan a save metódus

    }

    @FXML
    void handlePriceTypeing(ActionEvent event) {

    }


}
