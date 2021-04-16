package DataAccess.Repositories.DAOs;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;

public interface IDAO<T,id> {

    List<T> getAll();

    id create(T entity);
    T read(id id);
    void update(T entity);
    void delete(id id);

    void registerForNotification(Runnable method);
}
