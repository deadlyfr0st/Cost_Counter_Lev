package hu.unideb.inf;

public class TableModel {
    String nameColumn, typeColumn, dateColumn, priceColumn;

    public TableModel(){
    }

    public TableModel(String nameColumn, String typeColumn, String dateColumn, String priceColumn) {
        this.nameColumn = nameColumn;
        this.typeColumn = typeColumn;
        this.dateColumn = dateColumn;
        this.priceColumn = priceColumn;
    }

    public String getDateColumn() {
        return dateColumn;
    }

    public void setDateColumn(String dateColumn) {
        this.dateColumn = dateColumn;
    }

    public String getNameColumn() {
        return nameColumn;
    }

    public void setNameColumn(String nameColumn) {
        this.nameColumn = nameColumn;
    }

    public String getPriceColumn() {
        return priceColumn;
    }

    public void setPriceColumn(String priceColumn) {
        this.priceColumn = priceColumn;
    }

    public String getTypeColumn() {
        return typeColumn;
    }

    public void setTypeColumn(String typeColumn) {
        this.typeColumn = typeColumn;
    }
}
