package UnitTests.DataAccess.Repositories.InMemory;

import DataAccess.Repositories.Repositories.InMemory.InMemoryDatabase;
import DataAccess.Repositories.Repositories.InMemory.Repos.InMemoryTourLogRepo;
import DataAccess.Repositories.Repositories.InMemory.Repos.InMemoryTourRepo;
import Models.Tour;
import Models.TourLog;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestInMemoryTourLogRepo {

    InMemoryTourLogRepo inMemoryTourRepo= new InMemoryTourLogRepo(InMemoryDatabase.getInstance());

    @AfterEach
    void clearDB()
    {
        InMemoryDatabase.getInstance().setTours(new ArrayList<>());
    }

    @Test
    void createTour()
    {
        //arrange
        TourLog tourlog = new TourLog();

        //act
        tourlog.setId(inMemoryTourRepo.create(tourlog));


        //assert
        assertNotNull(tourlog.getId());
    }

    @Test
    void update()
    {
        //arrange
        TourLog tourlog = new TourLog();
        tourlog.setReport("name");
        tourlog.setId(inMemoryTourRepo.create(tourlog));
        //act
        tourlog.setReport("name updated");
        inMemoryTourRepo.update(tourlog);

        //assert
        assertEquals("name updated", tourlog.getReport());
    }

    @Test
    void read()
    {
        //arrange
        TourLog tourlog = new TourLog();
        tourlog.setReport("name");
        tourlog.setId(inMemoryTourRepo.create(tourlog));

        //act
        TourLog searchedTour= inMemoryTourRepo.read(tourlog.getId());

        //assert
        assertEquals("name",searchedTour.getReport());
    }

    @Test
    void delete()
    {
        //arrange
        TourLog tourlog = new TourLog();
        tourlog.setReport("name");
        tourlog.setId(inMemoryTourRepo.create(tourlog));

        //act
        inMemoryTourRepo.delete(tourlog.getId());

        TourLog searchedTourLog= inMemoryTourRepo.read(tourlog.getId());

        //assert
        assertNull(searchedTourLog);
    }

    @Test
    void getAll()
    {
        //arrange
        TourLog tourlog = new TourLog();
        tourlog.setReport("name");
        tourlog.setId(inMemoryTourRepo.create(tourlog));
        TourLog tourlog2 = new TourLog();
        tourlog2.setReport("name2");
        tourlog2.setId(inMemoryTourRepo.create(tourlog2));
        TourLog tourlog3 = new TourLog();
        tourlog3.setReport("name3");
        tourlog3.setId(inMemoryTourRepo.create(tourlog3));

        //act
        List<TourLog> tourlogs= inMemoryTourRepo.getAll();


        //assert
        assertEquals(3,tourlogs.size());
    }
}
