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
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.util.*;
import java.time.LocalDate;


public class FXMLCostCounterController implements Initializable {

    PersonData personData;

    FinancialData financialData;

    JpaPersonDataDAO jpaPersonDataDAO = new JpaPersonDataDAO();

    JpaFinancialDataDAO jpaFinancialDataDAO = new JpaFinancialDataDAO();

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
    private TextField handlePriceTyping;

    @FXML
    private DatePicker uploadDatePicker;

    @FXML
    private DatePicker dateFromDatePicker;

    @FXML
    private DatePicker dateTillDatePicker;

    private void personDataConverterMethod(ChoiceBox<PersonData> nameChoiceBoxSearch) {
        nameChoiceBoxSearch.setConverter(new StringConverter<>() {
            @Override
            public String toString(PersonData personData) {
                return personData.getName();
            }

            @Override
            public PersonData fromString(String s) {
                return jpaPersonDataDAO.getPersonData().stream().filter(personData
                        -> s.equals(personData.getName())).findAny().orElse(null);
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        costTypeChoiceBoxUpLoad.getItems().addAll(costType);
        costTypeChoiceBoxSearch.getItems().addAll(costType);
        nameChoiceBoxUpLoad.getItems().addAll(jpaPersonDataDAO.getPersonData());
        nameChoiceBoxSearch.getItems().addAll(jpaPersonDataDAO.getPersonData());
        personDataConverterMethod(nameChoiceBoxUpLoad);
        personDataConverterMethod(nameChoiceBoxSearch);

        /*
        NameColumn.setCellFactory(new PropertyValueFactory<PersonData, String>(name));
        TypeColumn.setCellFactory(new PropertyValueFactory<FinancialData, String>(type));
        PriceColumn.setCellFactory(new PropertyValueFactory<FinancialData, Integer>(price));
        DateFromColumn.setCellFactory(new PropertyValueFactory<FinancialData, LocalDate>(dateFrom));
        DateToColumn.setCellFactory(new PropertyValueFactory<FinancialData, LocalDate>(dateFrom));
        */
    }

    /////Költségtípusok véglegesítése hiányzik/////
    private final String[] costType = {"Élelmiszer", "TRAVEL", "Szórakozás"};

    private String nameInput;

    private LocalDate dateInput;

    private LocalDate dateInputFrom;

    private LocalDate dateInputTill;

    private String typeChoiceValue;

    @FXML
    private ChoiceBox<String> costTypeChoiceBoxUpLoad;

    @FXML
    private ChoiceBox<String> costTypeChoiceBoxSearch;

    @FXML
    private ChoiceBox<PersonData> nameChoiceBoxUpLoad;

    @FXML
    private ChoiceBox<PersonData> nameChoiceBoxSearch;

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

    private void resetChoiceBoxes() {
        nameChoiceBoxUpLoad.getItems().removeAll(jpaPersonDataDAO.getPersonData());
        nameChoiceBoxUpLoad.getItems().addAll(jpaPersonDataDAO.getPersonData());
        nameChoiceBoxSearch.getItems().removeAll(jpaPersonDataDAO.getPersonData());
        nameChoiceBoxSearch.getItems().addAll(jpaPersonDataDAO.getPersonData());
        costTypeChoiceBoxUpLoad.getItems().removeAll(costType);
        costTypeChoiceBoxUpLoad.getItems().addAll(costType);
        costTypeChoiceBoxSearch.getItems().removeAll(costType);
        costTypeChoiceBoxSearch.getItems().addAll(costType);
    }

    @FXML
    void handleRegisterButtonPushed(ActionEvent event) {
        if (!handleNameTyping.getText().isEmpty()) {
            nameInput = handleNameTyping.getText();
            if (!jpaPersonDataDAO.getAllPersonName().contains(nameInput)) {
                personData = new PersonData();
                personData.setName(nameInput);
                jpaPersonDataDAO.savePersonData(personData);
                alert.setAlertType(AlertType.INFORMATION);
                alert.setTitle("Sikeresen regisztráltál!");
                alert.setHeaderText("Elkezdheted a költségek feltöltését :)");
                alert.setContentText(null);
                alert.showAndWait();
                resetChoiceBoxes();
            } else {
                alert.setAlertType(AlertType.WARNING);
                alert.setTitle("Már regisztráltál!");
                alert.setHeaderText(null);
                alert.setContentText("Navigálj a feltöltés oldalra és válaszd ki a neved a lenyíló menüből.");
                alert.showAndWait();
            }
        } else {
            alert.setAlertType(AlertType.ERROR);
            alert.setTitle("Hiányzó adat!");
            alert.setHeaderText(null);
            alert.setContentText("Név megadása szükséges!");
            alert.showAndWait();
        }
    }

    @FXML
    void getUploadDate(ActionEvent event) {
        dateInput = uploadDatePicker.getValue();
    }

    @FXML
    void getFromDate(ActionEvent event) {
        dateInputFrom = dateFromDatePicker.getValue();
    }

    @FXML
    void getTillDate(ActionEvent event) {
        dateInputTill = dateTillDatePicker.getValue();
    }

    @FXML
    void handleDataUpLoadButtonPushed(ActionEvent event) {
        int priceInput;
        if (!nameChoiceBoxUpLoad.getSelectionModel().isEmpty() && (uploadDatePicker.getValue() != null)
                && !costTypeChoiceBoxUpLoad.getSelectionModel().isEmpty() && !handlePriceTyping.getText().isEmpty()) {
            priceInput = Integer.parseInt(handlePriceTyping.getText());
            typeChoiceValue = costTypeChoiceBoxUpLoad.getValue();

            financialData = new FinancialData();
            financialData.setCost(priceInput);
            financialData.setDateOfPurchase(dateInput);
            financialData.setCostType(FinancialData.typeOfCost.valueOf(typeChoiceValue));
            jpaFinancialDataDAO.saveFinancialData(financialData);

            this.personData = nameChoiceBoxUpLoad.getValue();
            this.personData.getFinancialDataList().add(financialData);
            jpaPersonDataDAO.savePersonData(personData);

            resetChoiceBoxes();
            uploadDatePicker.setValue(null);
            handlePriceTyping.clear();
        } else {
            if (nameChoiceBoxUpLoad.getSelectionModel().isEmpty()) {
                alert.setTitle("Hiányzó adat!");
                alert.setHeaderText("Név megadása szükséges!");
                alert.setContentText("Kérjük a lenyíló listából válasza ki a megfelő nevet!");
                alert.showAndWait();
            }
            if (costTypeChoiceBoxUpLoad.getSelectionModel().isEmpty()) {
                alert.setTitle("Hiányzó adat!");
                alert.setHeaderText("Költség kategória megadása szükséges!");
                alert.setContentText("Kérjük a lenyíló listából válaszon kategóriát!");
                alert.showAndWait();
            }
            if (uploadDatePicker.getValue() == null) {
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

    @FXML
    void handleSearchButtonPushed(ActionEvent event) {
        if (!nameChoiceBoxSearch.getSelectionModel().isEmpty() && !costTypeChoiceBoxSearch.getSelectionModel().isEmpty()
                && (dateFromDatePicker.getValue() != null) && (dateTillDatePicker.getValue() != null)) {
            //NameColumn.setText(jpaPersonDataDAO.);
            System.out.println("műxik");



        } else {
            if (nameChoiceBoxSearch.getSelectionModel().isEmpty()) {
                alert.setTitle("Hiányzó adat!");
                alert.setHeaderText("Név megadása szükséges!");
                alert.setContentText("Kérjük a lenyíló listából válasza ki a megfelő nevet!");
                alert.showAndWait();
            }
            if (costTypeChoiceBoxSearch.getSelectionModel().isEmpty()) {
                alert.setTitle("Hiányzó adat!");
                alert.setHeaderText("Költség kategória megadása szükséges!");
                alert.setContentText("Kérjük a lenyíló listából válaszon kategóriát!");
                alert.showAndWait();
            }
            if (dateFromDatePicker.getValue() == null) {
                alert.setTitle("Hiányzó adat!");
                alert.setHeaderText(null);
                alert.setContentText("Kezdő dátum megadása szükséges!");
                alert.showAndWait();
            }
            if (dateTillDatePicker.getValue() == null) {
                alert.setTitle("Hiányzó adat!");
                alert.setHeaderText(null);
                alert.setContentText("Befejező dátum megadása szükséges!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    void handleAverageButtonPushed(ActionEvent event) {
        if (!costTypeChoiceBoxSearch.getSelectionModel().isEmpty()
                && !nameChoiceBoxSearch.getSelectionModel().isEmpty()) {



        } else {
            if (nameChoiceBoxSearch.getSelectionModel().isEmpty()) {
                alert.setTitle("Hiányzó adat!");
                alert.setHeaderText("Név megadása szükséges!");
                alert.setContentText("Kérjük a lenyíló listából válasza ki a megfelő nevet!");
                alert.showAndWait();
            }
            if (costTypeChoiceBoxSearch.getSelectionModel().isEmpty()) {
                alert.setTitle("Hiányzó adat!");
                alert.setHeaderText("Költség kategória megadása szükséges!");
                alert.setContentText("Kérjük a lenyíló listából válaszon kategóriát!");
                alert.showAndWait();
            }
            if (dateFromDatePicker.getValue() == null) {
                alert.setTitle("Hiányzó adat!");
                alert.setHeaderText(null);
                alert.setContentText("Kezdő dátum megadása szükséges!");
                alert.showAndWait();
            }
            if (dateTillDatePicker.getValue() == null) {
                alert.setTitle("Hiányzó adat!");
                alert.setHeaderText(null);
                alert.setContentText("Befejező dátum megadása szükséges!");
                alert.showAndWait();
            }
        }
    }
}
