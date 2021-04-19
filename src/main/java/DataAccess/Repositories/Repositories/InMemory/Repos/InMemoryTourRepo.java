package DataAccess.Repositories.Repositories.InMemory.Repos;


import DataAccess.Repositories.Repositories.InMemory.IInMemoryDatabase;
import DataAccess.Repositories.Repositories.InMemory.InMemoryDatabase;
import DataAccess.Repositories.Repositories.Interfaces.ITourRepo;
import Models.Tour;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class InMemoryTourRepo implements ITourRepo {

    private final Logger log= LogManager.getLogger("standardLogger");

    private List<Runnable> subscribers = new ArrayList<>();

    IInMemoryDatabase inMemoryDatabase;

    public InMemoryTourRepo(IInMemoryDatabase inMemoryDatabase) {
        this.inMemoryDatabase=inMemoryDatabase;
        create(new Tour(1L,"Tour1","Desc1","MapReqTourPics/Download.jpeg",20d));
        create(new Tour(2L,"Tour2","Desc2","MapReqTourPics/up_road.jpg",30d));
        create(new Tour(3L,"Tour3","Desc3","MapReqTourPics/Download.jpeg",40d));
    }


    @Override
    public List<Tour> getAll() {
        log.info("get All Tours");
        return inMemoryDatabase.getTours();
    }

    @Override
    public Long create(Tour entity) {
        entity.setId(inMemoryDatabase.getCurrentTourID());
        inMemoryDatabase.getTours().add(entity);

        inMemoryDatabase.setCurrentTourID(inMemoryDatabase.getCurrentTourID()+1);

        inMemoryDatabase.triggerTourEvent();

        log.info("create Tour");

        return entity.getId();
    }


    @Override
    public Tour read(Long id) {
        log.info("read Tour");
        return inMemoryDatabase.getTours().stream().filter(x->x.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void update(Tour entity) {
        Tour tourToUpdate = inMemoryDatabase.getTours().stream().filter(x->x.getId().equals(entity.getId())).findFirst().orElse(null);
        if(tourToUpdate!=null) {
            tourToUpdate.setName(entity.getName());
            tourToUpdate.setTourDescription(entity.getTourDescription());
            tourToUpdate.setRouteInformation(entity.getRouteInformation());
            tourToUpdate.setTourDistance(entity.getTourDistance());
            if(!tourToUpdate.getLogs().equals(entity.getLogs()))
            {
                tourToUpdate.getLogs().addAll(
                //get every log that is contained in entity but not in tourToUpdate
                tourToUpdate.getLogs().stream()
                        .filter(x->
                                entity.getLogs().stream().map(y->y.getId()).collect(Collectors.toList())
                                        .contains(x.getId()))
                        .collect(Collectors.toList())
                );

                tourToUpdate.getLogs().removeAll(
                        //get every log that is not contained in entity but in tourToUpdate
                        entity.getLogs().stream()
                                .filter(x->
                                        !(tourToUpdate.getLogs().stream().map(y->y.getId()).collect(Collectors.toList())
                                                .contains(x.getId())))
                                .collect(Collectors.toList())
                );

            }
            inMemoryDatabase.triggerTourEvent();
            log.info("update Tour");

        }
        else
            log.error("TourLog null");
    }

    @Override
    public void delete(Long id) {
        Tour tourToRemove = inMemoryDatabase.getTours().stream().filter(x->x.getId().equals(id)).findFirst().orElse(null);
        if(tourToRemove!=null) {
            inMemoryDatabase.getTourLogs().removeAll(tourToRemove.getLogs());
            inMemoryDatabase.getTours().remove(tourToRemove);
            inMemoryDatabase.triggerTourEvent();
            log.info("delete Tour");

        }
        else
            log.error("TourLog null");
    }

    @Override
    public void registerForNotification(Runnable method) {
        log.info("registerForNotification");

        inMemoryDatabase.subscribeToursChangedEvent(method);
    }

}
