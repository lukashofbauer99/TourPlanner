package DataAccess.Repositories.DAOs;

import BusinessLogic.Services.ConfigService.ConfigService;
import DataAccess.Repositories.Repositories.InMemory.InMemoryDatabase;
import DataAccess.Repositories.Repositories.InMemory.Repos.InMemoryTourRepo;
import DataAccess.Repositories.Repositories.Interfaces.ITourRepo;
import DataAccess.Repositories.Repositories.PostgresDB.PostgresTourRepo;
import Models.Tour;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TourDAO implements ITourDAO {

    private final Logger log = LogManager.getLogger("standardLogger");

    private ITourRepo repo;

    public static TourDAO tourDAO;

    private TourDAO() {
        //load from config ?

        if(ConfigService.RepoType.equals("InMemory")) {
            log.info("using in memoryRepo");
            repo = new InMemoryTourRepo(InMemoryDatabase.getInstance());
        }
        else if(ConfigService.RepoType.equals("Database"))
        {
            repo = new PostgresTourRepo(ConfigService.ConnectionString,ConfigService.DBUser,ConfigService.DBPassword);
        }
        else
        {
            log.fatal("Wrong Config");
        }
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
