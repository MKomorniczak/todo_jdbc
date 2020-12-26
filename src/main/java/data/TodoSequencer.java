package data;

public class TodoSequencer {
    private static int todoId;

    public static int nextTodoId(){
        return ++todoId;
    }

    public static int reset(){
        return todoId=0;
    }


}
