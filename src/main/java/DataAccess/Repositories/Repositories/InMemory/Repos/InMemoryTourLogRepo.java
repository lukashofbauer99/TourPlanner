package DataAccess.Repositories.Repositories.InMemory.Repos;


import DataAccess.Repositories.Repositories.InMemory.IInMemoryDatabase;
import DataAccess.Repositories.Repositories.Interfaces.ITourLogRepo;
import Models.TourLog;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTourLogRepo implements ITourLogRepo {

    private final Logger log= LogManager.getLogger("standardLogger");

    private List<Runnable> subscribers = new ArrayList<>();

    IInMemoryDatabase inMemoryDatabase;

    public InMemoryTourLogRepo(IInMemoryDatabase inMemoryDatabase) {
        this.inMemoryDatabase= inMemoryDatabase;
    }


    @Override
    public List<TourLog> getAll() {
        log.info("get All TourLogs");
        return inMemoryDatabase.getTourLogs();
    }

    @Override
    public Long create(TourLog entity) {
        entity.setId(inMemoryDatabase.getCurrentTourLogID());
        inMemoryDatabase.getTourLogs().add(entity);

        inMemoryDatabase.setCurrentTourLogID(inMemoryDatabase.getCurrentTourLogID()+1);

        inMemoryDatabase.triggerTourLogEvent();

        log.info("create TourLog");

        return entity.getId();
    }


    @Override
    public TourLog read(Long id) {
        log.info("read TourLog");
        return inMemoryDatabase.getTourLogs().stream().filter(x->x.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public boolean update(TourLog entity) {
        TourLog logToUpdate = inMemoryDatabase.getTourLogs().stream().filter(x->x.getId().equals(entity.getId())).findFirst().orElse(null);
        if(logToUpdate!=null) {
            logToUpdate.setRating(entity.getRating());
            logToUpdate.setDate(entity.getDate());
            logToUpdate.setAverageSpeed(entity.getAverageSpeed());
            logToUpdate.setDifficulty(entity.getDifficulty());
            logToUpdate.setDistance(entity.getDistance());
            logToUpdate.setRating(entity.getRating());
            logToUpdate.setRecommendedPeopleCount(entity.getRecommendedPeopleCount());
            logToUpdate.setToiletOnThePath(entity.getToiletOnThePath());
            logToUpdate.setTypeOfTransport(entity.getTypeOfTransport());
            logToUpdate.setTotalTime(entity.getTotalTime());

            inMemoryDatabase.triggerTourLogEvent();
            inMemoryDatabase.triggerTourEvent();
            log.info("update TourLog");
            return true;
        }
        else {
            log.error("TourLog null");
            return false;
        }

    }

    @Override
    public boolean delete(Long id) {
        TourLog logToRemove = inMemoryDatabase.getTourLogs().stream().filter(x->x.getId().equals(id)).findFirst().orElse(null);
        if(logToRemove!=null) {
            inMemoryDatabase.getTourLogs().remove(logToRemove);
            inMemoryDatabase.getTours().forEach(x->x.getLogs().remove(logToRemove));
            inMemoryDatabase.triggerTourLogEvent();
            inMemoryDatabase.triggerTourEvent();
            log.info("delete TourLog");
            return true;
        }
        else {
            log.error("TourLog null");
            return false;
        }
    }

    @Override
    public void registerForNotification(Runnable method) {
        log.info("registerForNotification");
        inMemoryDatabase.subscribeToursLogsChangedEvent(method);
    }


}
