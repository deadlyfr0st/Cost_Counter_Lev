package hu.unideb.inf;

public class TableModel {
    String dateColumn, nameColumn, priceColumn, typeColumn;

    public TableModel(String dateColumn, String nameColumn, String priceColumn, String typeColumn) {
        this.dateColumn = dateColumn;
        this.nameColumn = nameColumn;
        this.priceColumn = priceColumn;
        this.typeColumn = typeColumn;
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
