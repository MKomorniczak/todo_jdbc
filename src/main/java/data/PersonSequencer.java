package data;

public class PersonSequencer {
    private static int personId;

    public static int nextPersonId(){
        return ++personId;

    }

    public static int reset(){
        return personId=0;
    }



}
