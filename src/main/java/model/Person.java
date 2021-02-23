package model;

public class Person {
    private int person_id;
    private String first_name;
    private String last_name;

    public Person() {
    }

    public Person(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Person(int personId, String firstName, String lastName) {
        this.person_id = personId;
        this.first_name = firstName;
        this.last_name = lastName;
    }

    public int getPersonId() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String firstName) {
        this.first_name = firstName;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String lastName) {
        this.last_name = lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "person_id=" + person_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                '}';
    }
}


