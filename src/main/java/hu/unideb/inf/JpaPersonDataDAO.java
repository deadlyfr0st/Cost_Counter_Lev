package hu.unideb.inf;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class JpaPersonDataDAO implements PersonDataDAO {
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void savePersonData(PersonData a) {
        entityManager.getTransaction().begin();
        entityManager.persist(a);
        entityManager.getTransaction().commit();
    }


    @Override
    public List<PersonData> getPersonData() {
        TypedQuery<PersonData> query= entityManager.createQuery(
                "SELECT a FROM PersonData a", PersonData.class);
        List<PersonData> personDataList = query.getResultList();
        return personDataList;
    }

    @Override
    public List<String> getAllPersonName() {
        List<String> personNames = new ArrayList<>();
        TypedQuery<PersonData> query= entityManager.createQuery(
                "SELECT a FROM PersonData a", PersonData.class);
        List<PersonData> getAllPersonDataList = query.getResultList();
        for (PersonData personName : getAllPersonDataList) {
            personNames.add(personName.getName());
        }
        return personNames;
    }

    @Override
    public void updatePersonData(PersonData a) {
        entityManager.getTransaction().begin();
        entityManager.persist(a);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deletePersonData(PersonData a) {
        entityManager.getTransaction().begin();
        entityManager.remove(a);
        entityManager.getTransaction().commit();
    }

    @Override
    //// adja vissza a persondata, minden attributumma oszt lehet vele tovabb

    public void searchPersonData(PersonData a) {

       /* public String {
            TypedQuery<PersonData> query= entityManager.createQuery(
                    "SELECT a FROM PersonData a WHERE name= a;
            return PersonData;*/

    }

    @Override
    public void close() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }
}
