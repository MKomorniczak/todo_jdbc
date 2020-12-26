package data;

import model.Person;

import java.util.Arrays;

public class People {
    private static Person[] Person=new Person[0];
    public void addPerson(Person person){
        model.Person[] newPersonArray= Arrays.copyOf(Person, Person.length +1);
        newPersonArray[newPersonArray.length -1]=person;
        Person=newPersonArray;
    }

    public int size(){
        return Person.length;
    }

    public model.Person[] findAll(){
        return Person;
    }

    public model.Person[] findById(int personId){
        return Person;
    }

    public void clear(){
        Person = null;
    }

}
