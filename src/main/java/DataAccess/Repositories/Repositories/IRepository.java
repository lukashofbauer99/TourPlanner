package DataAccess.Repositories.Repositories;

import javafx.collections.ObservableList;

import java.util.List;

public interface IRepository<T,id> {

    List<T> getAll();

    id create(T entity);
    T read(id id);
    void update(T entity);
    void delete(id id);
}
