package repository;

import model.Person;
import model.Twitter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TwitterRepository implements Crud<Twitter> {
    private Connection connection;

    public TwitterRepository() {
        try {
            this.connection = SingleConnection.getInstance().getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean add(Twitter twitter) {
        return false ;
    }

    public boolean add(int id , Twitter twitter){
        String add = """
                insert into twitter values (default , ? , ? )
                """;
        int returnValue = 0 ;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(add);
            preparedStatement.setInt(1,id );
            preparedStatement.setString(2,twitter.getContent());
            returnValue = preparedStatement.executeUpdate() ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnValue > 0 ;
    }

    @Override
    public Twitter find(int id) {
        String find = """
                select * from twitter where id = ? 
                """;
        Twitter twitter = null ;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(find);
            preparedStatement.setInt(1,id);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            twitter = new Twitter( twitter.getId() , twitter.getContent() , twitter.getComments() );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return twitter ;
    }

    @Override
    public List<Twitter> findAll() {
        return new ArrayList<>();
    }

    @Override
    public boolean delete(int id) {
        String delete = """
                delete from twitter where id = ? 
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
    public boolean update(Twitter twitter) {
        String update = """
                update twitter set content = ? 
                where id = ?
                """;
        int returnValue = 0 ;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1,twitter.getContent());
            preparedStatement.setInt(2,twitter.getId());
            returnValue = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnValue > 0 ;
    }
}
