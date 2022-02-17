package repository;

import java.util.ArrayList;
import java.util.List;

public interface Crud<T> {

    boolean add(T t) ;

    T find(int id);

    List<T> findAll();

    boolean delete(int id);

    boolean update(T t);

}
