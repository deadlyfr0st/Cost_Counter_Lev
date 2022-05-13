package hu.unideb.inf;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public interface FinancialDataDAO extends AutoCloseable {

    public void saveFinancialData(FinancialData a); //create

    public List<FinancialData> getFinancialDataDAO(); //read

    public void updateFinancialDataDAO(FinancialData a); //update

    public void deleteFinancialData(FinancialData a); //delete

    public void savePersonData(PersonData personData); //Person
}
