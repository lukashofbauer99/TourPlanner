package DataAccess.Repositories.DAOs;

import DataAccess.Repositories.Repositories.InMemory.InMemoryDatabase;
import DataAccess.Repositories.Repositories.InMemory.Repos.InMemoryTourRepo;
import DataAccess.Repositories.Repositories.Interfaces.ITourRepo;
import Models.Tour;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TourDAO implements ITourDAO {

    private final Logger log = LogManager.getLogger("standardLogger");

    private ITourRepo repo;

    public static TourDAO tourDAO;

    private TourDAO() {
        //load from config ?

        log.info("using in memoryRepo");
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
