package hu.unideb.inf;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;

public class FinancialData {

    @Id
    @GeneratedValue
    private Integer financialID;

    private int food = 0;
    private int travel = 0;
    private int entertainment = 0;

    //int[0] = FOOD, int[1] = TRAVEL, int[2] = ENTERTAINMENT
    @OneToOne
    int[] typeOfCost = new int[]{food, travel, entertainment};

    private int cost;
    private LocalDate dateOfPurchase;

    public FinancialData() {
    }

    public FinancialData(int cost, LocalDate dateOfPurchase) {
        this.cost = cost;
        this.dateOfPurchase = dateOfPurchase;
    }

    public int[] getTypeOfCost() {
        return typeOfCost;
    }

    public void setTypeOfCost(int[] typeOfCost) {
        this.typeOfCost = typeOfCost;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

}