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
import java.util.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;

public class FXMLCostCounterController implements Initializable {

    PersonData personData = new PersonData();

    FinancialData financialData = new FinancialData();
    JpaPersonDataDAO jpaPersonDataDAO = new JpaPersonDataDAO();

    @FXML
    private Button handleAverageButtonPushed;
    @FXML
    private Button handleDataUpLoadButtonPushed;
    @FXML
    private Button handleSearchButtonPushed;
    @FXML
    private Button handleRegisterButtonPushed;
    @FXML
    private TextField handleNameTyping;
    @FXML
    private TextField handleDateFromTyping;
    @FXML
    private TextField handleDateToTyping;
    @FXML
    private TextField handleDateTyping;
    @FXML
    private TextField handlePriceTyping;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // CostTypeChoiceBox.getItems().addAll(Arrays.toString(financialData.getTypeOfCost()));
        CostTypeChoiceBox.getItems().addAll(costType);
        // CostTypeChoiceBox1.getItems().addAll(Arrays.toString(financialData.getTypeOfCost()));
        CostTypeChoiceBox1.getItems().addAll(costType);
        nameChoiceBox.getItems().addAll(personData.getName());
        nameChoiceBox1.getItems().addAll(personData.getName());

        /*
        NameColumn.setCellFactory(new PropertyValueFactory<PersonData, String>(name));
        TypeColumn.setCellFactory(new PropertyValueFactory<FinancialData, String>(type));
        PriceColumn.setCellFactory(new PropertyValueFactory<FinancialData, Integer>(price));
        DateFromColumn.setCellFactory(new PropertyValueFactory<FinancialData, LocalDate>(dateFrom));
        DateToColumn.setCellFactory(new PropertyValueFactory<FinancialData, LocalDate>(dateFrom));
*/
    }


    //végleges adatokra kell cserélni ez csak a teszthez van
    private String[] costType = {"étel", "szórakozás", "utazás"};
    private String[] names = {"Piri", "Mari", "Béla"}; //
    private String nameInput;
    private LocalDate dataInput;
    private int priceInput;
    private String tyepchoice;
    private String namechoice;


    // választható opciók (lenyíló fülek)

    @FXML
    private ChoiceBox<String> CostTypeChoiceBox; //költség típus lenyíló menü a költség felvétel fülön

    @FXML
    private ChoiceBox<String> CostTypeChoiceBox1;  //költség típus lenyíló menü a keresés fülön

    @FXML
    private ChoiceBox<String> nameChoiceBox; // név lenyíló menü a költség felvétel fülön

    @FXML
    private ChoiceBox<String> nameChoiceBox1; // név lenyíló menü a keresés fülön

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

    Alert alert = new Alert(AlertType.INFORMATION);

    // Gombok vezérlése

    @FXML
    void handleRegisterButtonPushed(ActionEvent event) throws Exception {
        if (!handleNameTyping.getText().isEmpty()) {
            nameInput = handleNameTyping.getText();
            personData = new PersonData();
            personData.setName(nameInput);
            jpaPersonDataDAO.savePersonData(personData);
        } else {
            Alert nevAlert = new Alert(AlertType.INFORMATION);
            nevAlert.setTitle("Hiányzó adat!");
            nevAlert.setHeaderText(null);
            nevAlert.setContentText("Név megadása szükséges!");
            nevAlert.showAndWait();
        }
    }

    @FXML
    void handleAverageButtonPushed(ActionEvent event) {
        if (!handleDateFromTyping.getText().isEmpty() && !CostTypeChoiceBox1.getSelectionModel().isEmpty()
                && !handleDateToTyping.getText().isEmpty() && !nameChoiceBox1.getSelectionModel().isEmpty()) {


            // olyan metódus kell ami a táblázatba  !!!


        } else {
            if (nameChoiceBox1.getSelectionModel().isEmpty()) {
                alert.setTitle("Hiányzó adat!");
                alert.setHeaderText("Név megadása szükséges!");
                alert.setContentText("Kérjük a lenyíló listából válasza ki a megfelő nevet!");
                alert.showAndWait();
            }
            if (CostTypeChoiceBox1.getSelectionModel().isEmpty()) {
                alert.setTitle("Hiányzó adat!");
                alert.setHeaderText("Költség kategória megadása szükséges!");
                alert.setContentText("Kérjük a lenyíló listából válaszon kategóriát!");
                alert.showAndWait();
            }
            if (handleDateFromTyping.getText().isEmpty()) {
                alert.setTitle("Hiányzó adat!");
                alert.setHeaderText("Kezdő dátum megadása szükséges!");
                alert.setContentText("Dátum megadásánál figyeljen a helyes formátumra!\n Példa: 2022.01.01");
                alert.showAndWait();
            }
            if (handleDateToTyping.getText().isEmpty()) {
                alert.setTitle("Hiányzó adat!");
                alert.setHeaderText("Befejező dátum megadása szükséges!");
                alert.setContentText("Dátum megadásánál figyeljen a helyes formátumra!\n Példa: 2022.01.01");
                alert.showAndWait();
            }
        }
    }

    @FXML
    void handleDataUpLoadButtonPushed(ActionEvent event) {
        if (!handleDateTyping.getText().isEmpty() && !CostTypeChoiceBox.getSelectionModel().isEmpty()
                && !handlePriceTyping.getText().isEmpty() && !nameChoiceBox.getSelectionModel().isEmpty()) {
            dataInput = LocalDate.parse(handleDateTyping.getText());
            priceInput = Integer.parseInt(handlePriceTyping.getText());
            tyepchoice = CostTypeChoiceBox.getValue();
            namechoice = nameChoiceBox.getValue();

            // olyan metódus kell ami a táblázatba rögzíti a kapott értékeket !!!
        } else {
            if (nameChoiceBox1.getSelectionModel().isEmpty()) {
                alert.setTitle("Hiányzó adat!");
                alert.setHeaderText("Név megadása szükséges!");
                alert.setContentText("Kérjük a lenyíló listából válasza ki a megfelő nevet!");
                alert.showAndWait();
            }
            if (CostTypeChoiceBox1.getSelectionModel().isEmpty()) {
                alert.setTitle("Hiányzó adat!");
                alert.setHeaderText("Költség kategória megadása szükséges!");
                alert.setContentText("Kérjük a lenyíló listából válaszon kategóriát!");
                alert.showAndWait();
            }
            if (handleDateTyping.getText().isEmpty()) {
                alert.setTitle("Hiányzó adat!");
                alert.setHeaderText("Dátum megadása szükséges!");
                alert.setContentText("Dátum megadásánál figyeljen a helyes formátumra!\n Példa: 2022.01.01");
                alert.showAndWait();
            }
            if (handlePriceTyping.getText().isEmpty()) {
                alert.setTitle("Hiányzó adat!");
                alert.setHeaderText("Összeg megadása szükséges!");
                alert.setContentText("Összeg megadásánál figyeljen a helyes formátumra!\n Példa: 12500");
                alert.showAndWait();
            }
        }

    }

    /*
    @FXML
    void handleDateFromTyping(ActionEvent event) {
    }
    @FXML
    void handlePriceTyping(ActionEvent event) {
    }
    @FXML
    void handleDateToTyping(ActionEvent event) {
    }
    @FXML
    void handleDateTyping(ActionEvent event) {
    }
    @FXML
    void handleNameTyping(ActionEvent event) {
    }
    */

    @FXML
    void handleSearchButtonPushed(ActionEvent event) {
        if (!handleDateFromTyping.getText().isEmpty() && !CostTypeChoiceBox1.getSelectionModel().isEmpty()
                && !handleDateToTyping.getText().isEmpty() && !nameChoiceBox1.getSelectionModel().isEmpty()) {
            //NameColumn.setText(jpaPersonDataDAO.);
            System.out.println("műxik");


            // olyan metódus kell ami a táblázatba  !!!


        } else {
            if (nameChoiceBox1.getSelectionModel().isEmpty()) {
                alert.setTitle("Hiányzó adat!");
                alert.setHeaderText("Név megadása szükséges!");
                alert.setContentText("Kérjük a lenyíló listából válasza ki a megfelő nevet!");
                alert.showAndWait();
            }
            if (CostTypeChoiceBox1.getSelectionModel().isEmpty()) {
                alert.setTitle("Hiányzó adat!");
                alert.setHeaderText("Költség kategória megadása szükséges!");
                alert.setContentText("Kérjük a lenyíló listából válaszon kategóriát!");
                alert.showAndWait();
            }
            if (handleDateFromTyping.getText().isEmpty()) {
                alert.setTitle("Hiányzó adat!");
                alert.setHeaderText("Kezdő dátum megadása szükséges!");
                alert.setContentText("Dátum megadásánál figyeljen a helyes formátumra!\n Példa: 2022.01.01");
                alert.showAndWait();
            }
            if (handleDateToTyping.getText().isEmpty()) {
                alert.setTitle("Hiányzó adat!");
                alert.setHeaderText("Befejező dátum megadása szükséges!");
                alert.setContentText("Dátum megadásánál figyeljen a helyes formátumra!\n Példa: 2022.01.01");
                alert.showAndWait();
            }
        }
    }
}
