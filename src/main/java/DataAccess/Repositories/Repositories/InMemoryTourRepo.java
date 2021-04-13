package DataAccess.Repositories.Repositories;


import Models.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTourRepo implements IRepository<Tour,Long> {

    List<Tour> tours= new ArrayList<>();

    long currentId=0;

    public InMemoryTourRepo() {
        create(new Tour(1l,"Tour1","Desc1","MapReqTourPics/Download.jpeg",20d));
        create(new Tour(2l,"Tour2","Desc2","MapReqTourPics/up_road.jpg",30d));
        create(new Tour(3l,"Tour3","Desc3","MapReqTourPics/Download.jpeg",40d));
    }


    @Override
    public List<Tour> getAll() {
        return tours;
    }

    @Override
    public Long create(Tour entity) {
        entity.setId(currentId);
        tours.add(entity);

        currentId++;

        return entity.getId();
    }


    @Override
    public Tour read(Long id) {
        return tours.stream().filter(x->x.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void update(Tour entity) {
        Tour tourToUpdate = tours.stream().filter(x->x.getId().equals(entity.getId())).findFirst().orElse(null);
        if(tourToUpdate!=null) {
            tourToUpdate.setName(entity.getName());
            tourToUpdate.setTourDescription(entity.getTourDescription());
            tourToUpdate.setRouteInformation(entity.getRouteInformation());
            tourToUpdate.setTourDistance(entity.getTourDistance());
            tourToUpdate.setLogs(entity.getLogs());
        }
    }

    @Override
    public void delete(Long id) {
        Tour tourToRemove = tours.stream().filter(x->x.getId().equals(id)).findFirst().orElse(null);
        if(tourToRemove!=null)
            tours.remove(tourToRemove);
    }
}
