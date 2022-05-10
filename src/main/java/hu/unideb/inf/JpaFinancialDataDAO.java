package hu.unideb.inf;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaFinancialDataDAO implements  FinancialDataDAO{
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void saveFinancialData(FinancialData a) {
        entityManager.getTransaction().begin();
        entityManager.persist(a);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<FinancialData> getFinancialDataDAO() {
        TypedQuery<FinancialData> query= entityManager.createQuery(
                "SELECT a FROM FinancialData a  ", FinancialData.class);
        return query.getResultList();
    }

    @Override
    public void updateFinancialDataDAO(FinancialData a) {
        entityManager.getTransaction().begin();
        entityManager.persist(a);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteFinancialData(FinancialData a) {
        entityManager.getTransaction().begin();
        entityManager.remove(a);
        entityManager.getTransaction().commit();
    }

    @Override
    public void savePersonData(PersonData personData) {
        entityManager.getTransaction().begin();
        entityManager.persist(personData);
        entityManager.getTransaction().commit();
    }


    @Override
    public void close() throws Exception {
        entityManager.close();
        entityManagerFactory.close();

    }
}
