package hu.unideb.inf;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.temporal.ChronoUnit;
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

    @FXML
    private TextField avarageField;


    @FXML
    private TextField név;


    @FXML
    private TextField típus;


    @FXML
    private TextField összeg;


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

    ObservableList<TableModel> listview = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        costTypeChoiceBoxUpLoad.getItems().addAll(costTypeUpload);
        costTypeChoiceBoxSearch.getItems().addAll(costTypeSearch);
        nameChoiceBoxUpLoad.getItems().addAll(jpaPersonDataDAO.getPersonData());
        nameChoiceBoxSearch.getItems().addAll(jpaPersonDataDAO.getPersonData());
        personDataConverterMethod(nameChoiceBoxUpLoad);
        personDataConverterMethod(nameChoiceBoxSearch);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nameColumn"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateColumn"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("priceColumn"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("typeColumn"));

        listview.add(new TableModel());
        tableView.setItems(listview);
    }

    /////Költségtípusok véglegesítése hiányzik/////
    private final String[] costTypeUpload = {"Élelmiszer", "Utazás", "Szórakozás"};
    private final String[] costTypeSearch = {"Élelmiszer", "Utazás", "Szórakozás", "Összes"};

    private String nameInput;

    private LocalDate dateInput;

    private LocalDate dateInputFrom;

    private LocalDate dateInputTill;

    private String typeChoiceValue;

    private Double avarage = 0.0;

    private Double allCost = 0.0;

    @FXML
    private ChoiceBox<String> costTypeChoiceBoxUpLoad;

    @FXML
    private ChoiceBox<String> costTypeChoiceBoxSearch;

    @FXML
    private ChoiceBox<PersonData> nameChoiceBoxUpLoad;

    @FXML
    private ChoiceBox<PersonData> nameChoiceBoxSearch;

    @FXML
    private TableView<TableModel> tableView;
    @FXML
    private TableColumn<TableModel, String> dateColumn;
    @FXML
    private TableColumn<TableModel, String> nameColumn;
    @FXML
    private TableColumn<TableModel, String> priceColumn;
    @FXML
    private TableColumn<TableModel, String> typeColumn;

    Alert alert = new Alert(AlertType.INFORMATION);

    private void resetChoiceBoxes() {
        nameChoiceBoxUpLoad.getItems().removeAll(jpaPersonDataDAO.getPersonData());
        nameChoiceBoxUpLoad.getItems().addAll(jpaPersonDataDAO.getPersonData());
        nameChoiceBoxSearch.getItems().removeAll(jpaPersonDataDAO.getPersonData());
        nameChoiceBoxSearch.getItems().addAll(jpaPersonDataDAO.getPersonData());
        costTypeChoiceBoxUpLoad.getItems().removeAll(costTypeUpload);
        costTypeChoiceBoxUpLoad.getItems().addAll(costTypeUpload);
        costTypeChoiceBoxSearch.getItems().removeAll(costTypeSearch);
        costTypeChoiceBoxSearch.getItems().addAll(costTypeSearch);
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

            //Enum elemzes
            if (typeChoiceValue.equals("Szórakozás")) {
                financialData.setCostType(FinancialData.typeOfCost.ENTERTAINMENT);
            }
            if (typeChoiceValue.equals("Élelmiszer")) {
                financialData.setCostType(FinancialData.typeOfCost.FOOD);
            }
            if (typeChoiceValue.equals("Utazás")) {
                financialData.setCostType(FinancialData.typeOfCost.TRAVEL);
            }

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
        int counter = 0;
        avarage = 0.0;
        allCost = 0.0;
        if (!nameChoiceBoxSearch.getSelectionModel().isEmpty() && !costTypeChoiceBoxSearch.getSelectionModel().isEmpty()
                && (dateFromDatePicker.getValue() != null) && (dateTillDatePicker.getValue() != null)) {
            List<FinancialData> financialDataListRet = new ArrayList<>();

            this.dateInputFrom = dateFromDatePicker.getValue();
            this.dateInputTill = dateTillDatePicker.getValue();
            this.personData = nameChoiceBoxSearch.getValue();

            for (FinancialData fc : this.personData.getFinancialDataList()) {
                if (costTypeChoiceBoxSearch.getValue().equals("Utazás")) {
                    if (fc.getDateOfPurchase().isAfter(this.dateInputFrom) && fc.getDateOfPurchase().isBefore(this.dateInputTill) || fc.getDateOfPurchase().isEqual(this.dateInputFrom) || fc.getDateOfPurchase().isEqual(this.dateInputTill)) {
                        if (fc.getCostType() == FinancialData.typeOfCost.TRAVEL)
                            financialDataListRet.add(fc);
                    }
                }
                if (costTypeChoiceBoxSearch.getValue().equals("Élelmiszer")) {
                    if (fc.getDateOfPurchase().isAfter(this.dateInputFrom) && fc.getDateOfPurchase().isBefore(this.dateInputTill) || fc.getDateOfPurchase().isEqual(this.dateInputFrom) || fc.getDateOfPurchase().isEqual(this.dateInputTill)) {
                        if (fc.getCostType() == FinancialData.typeOfCost.FOOD)
                            financialDataListRet.add(fc);
                    }
                }
                if (costTypeChoiceBoxSearch.getValue().equals("Szórakozás")) {
                    if (fc.getDateOfPurchase().isAfter(this.dateInputFrom) && fc.getDateOfPurchase().isBefore(this.dateInputTill) || fc.getDateOfPurchase().isEqual(this.dateInputFrom) || fc.getDateOfPurchase().isEqual(this.dateInputTill)) {
                        if (fc.getCostType() == FinancialData.typeOfCost.ENTERTAINMENT)
                            financialDataListRet.add(fc);
                    }
                }
                if (costTypeChoiceBoxSearch.getValue().equals("Összes")) {
                    if (fc.getDateOfPurchase().isAfter(this.dateInputFrom) && fc.getDateOfPurchase().isBefore(this.dateInputTill) || fc.getDateOfPurchase().isEqual(this.dateInputFrom) || fc.getDateOfPurchase().isEqual(this.dateInputTill)) {
                            financialDataListRet.add(fc);
                    }
                }
            }
            if (financialDataListRet.isEmpty()) {
                alert.setTitle("");
                alert.setHeaderText("Ebben az időszakban nem volt költés");
                alert.setContentText("");
                alert.showAndWait();
            }
            tableView.getItems().clear();

            for (FinancialData fc2 : financialDataListRet) {
                listview.add(new TableModel(nameChoiceBoxSearch.getValue().toString(),
                        fc2.getCostType().toString(),
                        fc2.getDateOfPurchase().toString(),
                        String.valueOf(fc2.getCost())));
                tableView.setItems(listview);
                avarage += fc2.getCost();
                counter++;
            }
            final DecimalFormat df = new DecimalFormat();
            allCost = avarage;
            avarage = avarage / counter;

            long nOfDaysBetween = ChronoUnit.DAYS.between(dateInputFrom, dateInputTill);
            típus.setText(Long.toString(nOfDaysBetween));
            összeg.setText(df.format(allCost));
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
        if (!nameChoiceBoxSearch.getSelectionModel().isEmpty() && !costTypeChoiceBoxSearch.getSelectionModel().isEmpty()
                && (dateFromDatePicker.getValue() != null) && (dateTillDatePicker.getValue() != null)) {
        final DecimalFormat df = new DecimalFormat("0 .00");
            avarageField.setText(df.format(avarage));
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
