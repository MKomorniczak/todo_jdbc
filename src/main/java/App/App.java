package App;


import Interfaces.People;
import data.PersonClass;
import model.Person;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class App
{
    public static void main( String[] args ){
        People test = new PersonClass();
        Person person = test.findById(2);
        System.out.println("person" + person);

        Collection<Person> findByName = test.findByName("Marek");
        System.out.println(findByName);




/*
     Collection<Person> findAll = test.findAll();
        System.out.println(findAll);*/





    }
}
