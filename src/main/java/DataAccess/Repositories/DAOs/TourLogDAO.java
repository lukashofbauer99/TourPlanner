package DataAccess.Repositories.DAOs;

import DataAccess.Repositories.Repositories.InMemory.InMemoryDatabase;
import DataAccess.Repositories.Repositories.InMemory.Repos.InMemoryTourLogRepo;
import DataAccess.Repositories.Repositories.InMemory.Repos.InMemoryTourRepo;
import DataAccess.Repositories.Repositories.Interfaces.ITourLogRepo;
import Models.TourLog;

import java.util.ArrayList;
import java.util.List;

public class TourLogDAO implements ITourLogDAO {

    private List<Runnable> subscribers = new ArrayList<>();
    private ITourLogRepo repo;


    public static TourLogDAO tourDAO;

    private TourLogDAO() {
        repo = new InMemoryTourLogRepo(InMemoryDatabase.getInstance());
    }


    public static TourLogDAO getInstance()
    {
        if(tourDAO==null)
            tourDAO=new TourLogDAO();

        return tourDAO;
    }

    @Override
    public List<TourLog> getAll() {
        return repo.getAll();
    }

    @Override
    public Long create(TourLog entity) {
        return repo.create(entity);
    }

    @Override
    public TourLog read(Long id) {
        return repo.read(id);
    }

    @Override
    public void update(TourLog entity) {
        repo.update(entity);
    }

    @Override
    public void delete(Long id) {
        repo.delete(id);
    }

    @Override
    public void registerForNotification(Runnable method) {
        repo.registerForNotification(method);
    }



}
