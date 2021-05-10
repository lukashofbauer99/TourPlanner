package DataAccess.Repositories.Repositories.Interfaces;

import javafx.collections.ObservableList;

import java.util.List;

public interface IRepository<T,id> {

    List<T> getAll();

    id create(T entity);
    T read(id id);
    boolean update(T entity);
    boolean delete(id id);

    void registerForNotification(Runnable method);
}
