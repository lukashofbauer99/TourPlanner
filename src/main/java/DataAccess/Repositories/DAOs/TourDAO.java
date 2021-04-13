package DataAccess.Repositories.DAOs;

import DataAccess.Repositories.Repositories.IRepository;
import DataAccess.Repositories.Repositories.InMemoryTourRepo;
import Models.Tour;
import javafx.collections.ObservableList;

import java.util.List;

public class TourDAO implements IDAO<Tour,Long> {
    private IRepository<Tour,Long> repo;


    public static TourDAO tourDAO;

    private TourDAO() {
        repo = new InMemoryTourRepo();
    }


    public static TourDAO getInstance()
    {
        if(tourDAO==null)
            tourDAO=new TourDAO();

        return tourDAO;
    }

    @Override
    public List<Tour> getAll() {
        return repo.getAll();
    }

    @Override
    public Long create(Tour entity) {
        return repo.create(entity);
    }

    @Override
    public Tour read(Long id) {
        return repo.read(id);
    }

    @Override
    public void update(Tour entity) {
        repo.update(entity);
    }

    @Override
    public void delete(Long id) {
        repo.delete(id);
    }
}
