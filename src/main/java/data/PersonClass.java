package data;

import Interfaces.People;
import model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class PersonClass implements People {
    @Override
    public Person create(Person person) {
        return null;
    }

    @Override
    public Collection<Person> findAll() {
        Collection<Person> personCollection = new ArrayList<>();
        String query = "select * from person";
        try {
            Statement statement = MySqlConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                personCollection.add(new Person(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personCollection;
    }

    @Override
    public Person findById(int person_id) {
        String query = "select * from person where person_id = ?";
        Person person = new Person();
        try (
                PreparedStatement preparedStatement = MySqlConnection.getConnection().prepareStatement(query)
        ) {
            preparedStatement.setInt(1, person_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                person.setPerson_id(resultSet.getInt(1));
                person.setFirstName(resultSet.getString(2));
                person.setLastName(resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return person;
    }

    @Override
    public Collection<Person> findByName(String first_name) {
        Collection<Person> personCollection = new ArrayList<>();
        String query = "select * from person where first_name = ?";
        try (
                PreparedStatement preparedStatement = MySqlConnection.getConnection().prepareStatement(query);
        ) {
            preparedStatement.setString(1, first_name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                personCollection.add(new Person(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)

                ));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return personCollection;
    }

    @Override
    public Person update(Person person) {
        String query = "update person set first_name = ?, last_name = ? where person_id = ?";
        Person personUpdated = new Person();
        try (
                PreparedStatement preparedStatement = MySqlConnection.getConnection().prepareStatement(query)
        ) {
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                personUpdated.setPerson_id(resultSet.getInt(1));
                personUpdated.setFirstName(resultSet.getString(2));
                personUpdated.setLastName(resultSet.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return personUpdated;
    }

    @Override
    public boolean deleteById(int person_id) {
        String query = "delete from person where person_id =?";
        try (
                PreparedStatement preparedStatement = MySqlConnection.getConnection().prepareStatement(query)

        ) {
            preparedStatement.setInt(1, person_id);
            int result = preparedStatement.executeUpdate();
            System.out.println("delete result" + result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


}
