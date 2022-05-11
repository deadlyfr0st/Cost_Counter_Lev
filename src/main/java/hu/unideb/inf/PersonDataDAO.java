package hu.unideb.inf;

import java.util.List;

public interface PersonDataDAO extends AutoCloseable{

    public void savePersonData(PersonData a); //create
    public List<PersonData> getPersonData(); //read
    public List<String> getAllPersonName(); //read
    public void updatePersonData(PersonData a); //update
    public void deletePersonData(PersonData a); //delete
    public void searchPersonData(PersonData a); //search --String listát adjon vissza

}
