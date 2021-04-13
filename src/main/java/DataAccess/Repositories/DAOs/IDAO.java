package DataAccess.Repositories.DAOs;

import java.util.List;

public interface IDAO<T,id> {

    List<T> getAll();

    id create(T entity);
    T read(id id);
    void update(T entity);
    void delete(id id);
}
