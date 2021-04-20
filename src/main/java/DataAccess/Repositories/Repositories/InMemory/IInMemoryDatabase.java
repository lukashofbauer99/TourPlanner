package DataAccess.Repositories.Repositories.InMemory;

import Models.Tour;
import Models.TourLog;

import java.util.List;

public interface IInMemoryDatabase {
    Long getCurrentTourID();
    void setCurrentTourID(Long id);
    List<Tour> getTours();
    void subscribeToursChangedEvent(Runnable method);
    void triggerTourEvent();

    Long getCurrentTourLogID();
    void setCurrentTourLogID(Long id);
    List<TourLog> getTourLogs();
    void subscribeToursLogsChangedEvent(Runnable method);
    void triggerTourLogEvent();
}
