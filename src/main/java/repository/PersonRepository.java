package repository;

import model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonRepository implements Crud<Person>{
    private Connection connection ;

    public PersonRepository() {
        try{
            this.connection = SingleConnection.getInstance().getConnection();
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean add(Person person) {
        String add = """
                insert into person values (default , ? , ? )
                """;
        int returnValue = 0 ;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(add);
            preparedStatement.setString(1,person.getUsername());
            preparedStatement.setString(2,person.getPassword());
            returnValue = preparedStatement.executeUpdate() ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnValue > 0 ;
    }

    @Override
    public Person find(int id) {
        String find = """
                select * from person where id = ? 
                """;
         Person person = null ;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(find);
            preparedStatement.setInt(1,id);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            person = new Person( person.getId() , person.getUsername() , person.getPassword() );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person ;
    }

    @Override
    public List<Person> findAll() {
        return new ArrayList<Person>();
    }

    @Override
    public boolean delete(int id) {
        String delete = """
                delete from person where id = ? 
                """;
        int returnValue = 0;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1, id);
            returnValue = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnValue > 0 ;
    }

    @Override
    public boolean update(Person person) {
        String update = """
                update person set username = ? , password = ?
                where id = ?
                """;
        int returnValue = 0 ;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1,person.getUsername());
            preparedStatement.setString(2,person.getPassword());
            preparedStatement.setInt(3,person.getId());
            returnValue = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnValue > 0 ;
    }
}
