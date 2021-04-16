package DataAccess.Repositories.DAOs;

import DataAccess.Repositories.Repositories.InMemory.InMemoryDatabase;
import DataAccess.Repositories.Repositories.InMemory.Repos.InMemoryTourRepo;
import DataAccess.Repositories.Repositories.Interfaces.ITourRepo;
import Models.Tour;

import java.util.List;

public class TourDAO implements ITourDAO {

    private ITourRepo repo;


    public static TourDAO tourDAO;

    private TourDAO() {
        //load from config ?
        repo = new InMemoryTourRepo(InMemoryDatabase.getInstance());
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

    @Override
    public void registerForNotification(Runnable method) {
        repo.registerForNotification(method);
    }



}
