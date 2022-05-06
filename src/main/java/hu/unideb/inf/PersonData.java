package hu.unideb.inf;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class PersonData {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    @OneToMany
    private List<FinancialData> koltsegek;

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