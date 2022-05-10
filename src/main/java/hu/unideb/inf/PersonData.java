package hu.unideb.inf;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PersonData {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @OneToMany()
    @JoinColumn(name = "owner_personData")
    private List<FinancialData> financialDataList = new ArrayList<>();

    public void setId(Integer id) {
        this.id = id;
    }

    public List<FinancialData> getFinancialDataList() {
        return financialDataList;
    }

    public void setFinancialDataList(List<FinancialData> financialDataList) {
        this.financialDataList = financialDataList;
    }

    public PersonData() {
    }

    public PersonData(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}