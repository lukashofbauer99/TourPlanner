package DataAccess.Repositories.Repositories.InMemory;

import Models.Tour;
import Models.TourLog;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class InMemoryDatabase implements IInMemoryDatabase {

    //Repos
    List<Runnable> tourSubscribers = new ArrayList<>();
    List<Runnable> tourLogSubscribers = new ArrayList<>();

    private static InMemoryDatabase instance=null;

    private InMemoryDatabase() {
    }

    Long currentTourID= 1L;
    List<Tour> tours = new ArrayList<>();

    Long currentTourLogID= 1L;
    List<TourLog> tourLogs = new ArrayList<>();

    Long currentTour_Log_RelID= 1L;
    List<Tour_Logs_Rel> tour_logs_rels = new ArrayList<>();

    public static InMemoryDatabase getInstance()
    {
        if(instance==null)
            instance= new InMemoryDatabase();

        return instance;
    }

    @Override
    public void subscribeToursChangedEvent(Runnable method) {
        tourSubscribers.add(method);
    }

    @Override
    public void triggerTourEvent() {
        tourSubscribers.forEach(x->x.run());
    }

    @Override
    public void subscribeToursLogsChangedEvent(Runnable method) {
        tourLogSubscribers.add(method);
    }

    @Override
    public void triggerTourLogEvent() {
        tourLogSubscribers.forEach(x->x.run());

    }
}
