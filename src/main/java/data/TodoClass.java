package data;

import Interfaces.TodoItems;
import model.Person;
import model.Todo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class TodoClass implements TodoItems {

    @Override
    public Todo create(Todo todo) {
        return null;
    }

    @Override
    public Collection<Todo> findAll() {
        Collection<Todo> todoCollection = new ArrayList<>();
        String query = "select * from todo_item";
        try {
            Statement statement = MySqlConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                todoCollection.add(new Todo(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDate(4).toLocalDate(),
                        resultSet.getBoolean(5),
                        resultSet.getInt(6)
                ));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoCollection;
    }

    @Override
    public Todo findById(int todo_id) {
        String query = "select * from todo_item where todo_id = ?";
        Todo todo = new Todo();
        try (
                PreparedStatement preparedStatement = MySqlConnection.getConnection().prepareStatement(query)
        ) {
            preparedStatement.setInt(1, todo_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                todo.setTodo_id(resultSet.getInt(1));
                todo.setTitle(resultSet.getString(2));
                todo.setDescription(resultSet.getString(3));
                todo.setDeadline(resultSet.getDate(4).toLocalDate());
                todo.setDone(resultSet.getBoolean(5));
                todo.setAssignee_id(resultSet.getInt(6));


            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todo;
    }

    @Override
    public Collection<Todo> findByDoneStatus(boolean done) {
        Collection<Todo> todoCollection = new ArrayList<>();
        String query = "select * from todo_item where done = ?";
        try (
                PreparedStatement preparedStatement = MySqlConnection.getConnection().prepareStatement(query)
        ) {
            preparedStatement.setBoolean(1, done);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                todoCollection.add(new Todo(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDate(4).toLocalDate(),
                        resultSet.getBoolean(5),
                        resultSet.getInt(6)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return todoCollection;
    }

    @Override
    public Collection<Todo> findByAssignee(int assignee_id) {
        Collection<Todo> todoCollection = new ArrayList<>();
        String query = "select * from todo_item where assignee_id =?";
        try (
                PreparedStatement preparedStatement = MySqlConnection.getConnection().prepareStatement(query)

        ) {
            preparedStatement.setInt(1, assignee_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                todoCollection.add(new Todo(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDate(4).toLocalDate(),
                        resultSet.getBoolean(5),
                        resultSet.getInt(6)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoCollection;
    }

    @Override
    public Collection<Todo> findByAssignee(Person person) {
        Collection<Todo> todoCollection = new ArrayList<>();
        String query = "select * from todo_item where person_id =?";
        try (
                PreparedStatement preparedStatement = MySqlConnection.getConnection().prepareStatement(query)

        ) {
            preparedStatement.setInt(1, person.getPersonId());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                todoCollection.add(new Todo(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDate(4).toLocalDate(),
                        resultSet.getBoolean(5),
                        resultSet.getInt(6)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoCollection;
    }

    @Override
    public Collection<Todo> findByUnassignedTodoItems() {
        return null;
    }

    @Override
    public Todo update(Todo todo) {
        Todo todoUpdated = new Todo();
        String query = "update todo_item set title =?, description =?, deadline =?, done =?, assignee_id =? where todo_id = ?";
        try(
                PreparedStatement preparedStatement = MySqlConnection.getConnection().prepareStatement(query)
                ) {
            preparedStatement.setString(1, todo.getTitle());
            preparedStatement.setString(2, todo.getDescription());
            //preparedStatement.setDate(3, todo.getDeadline());
            preparedStatement.setBoolean(4, todo.isDone());
            preparedStatement.setInt(5, todo.getAssignee_id());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                todoUpdated.setTodo_id(resultSet.getInt(1));
                todoUpdated.setTitle(resultSet.getString(2));
                todoUpdated.setDescription(resultSet.getString(3));
                todoUpdated.setDeadline(resultSet.getDate(4).toLocalDate());
                todoUpdated.setDone(resultSet.getBoolean(5));
                todoUpdated.setAssignee_id(resultSet.getInt(6));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoUpdated;
    }

    @Override
    public boolean deleteById(int todo_id) {
        String query = "delete from todo_item where todo_id = ?";
        try (
                PreparedStatement preparedStatement = MySqlConnection.getConnection().prepareStatement(query)
        ) {
            preparedStatement.setInt(1, todo_id);
            int result = preparedStatement.executeUpdate();
            System.out.println("delete result" + result);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
}
