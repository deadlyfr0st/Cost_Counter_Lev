package hu.unideb.inf;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class FinancialData {

    @Id
    @GeneratedValue
    private Integer financialID;


    private int food = 0;
    private int travel = 0;
    private int entertainment = 0;


    @Enumerated(EnumType.STRING)
    private typeOfCost2 costType;

    public enum typeOfCost2{
        TRAVEL,FOOD,ENTERTAINMENT
    }

    //int[0] = FOOD, int[1] = TRAVEL, int[2] = ENTERTAINMENT

   // int[] typeOfCost = new int[]{food, travel, entertainment};

    private int cost;
    private LocalDate dateOfPurchase;

    public FinancialData() {
    }

    public FinancialData(int cost, LocalDate dateOfPurchase) {
        this.cost = cost;
        this.dateOfPurchase = dateOfPurchase;
    }

/*

    public int[] getTypeOfCost() {
        return typeOfCost;
    }

    public void setTypeOfCost(int[] typeOfCost) {
        this.typeOfCost = typeOfCost;
    }
*/


    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

}