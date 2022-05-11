package hu.unideb.inf;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class FinancialData {

    @Id
    @GeneratedValue
    private Integer financialID;
    @Enumerated(EnumType.STRING)
    private typeOfCost costType;
    public enum typeOfCost{
        TRAVEL,FOOD,ENTERTAINMENT
    }
    private int cost;
    private LocalDate dateOfPurchase;

    //////////////////////Setters Getters////////////////////////
    public FinancialData() {
    }
    public Integer getFinancialID() {
        return financialID;
    }

    public void setFinancialID(Integer financialID) {
        this.financialID = financialID;
    }

    public typeOfCost getCostType() {
        return costType;
    }

    public void setCostType(typeOfCost costType) {
        this.costType = costType;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public FinancialData(int cost, LocalDate dateOfPurchase) {
        this.cost = cost;
        this.dateOfPurchase = dateOfPurchase;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }



}