package UnitTests.DataAccess.Repositories.InMemory;

import DataAccess.Repositories.Repositories.InMemory.InMemoryDatabase;
import DataAccess.Repositories.Repositories.InMemory.Repos.InMemoryTourRepo;
import Models.Tour;
import Models.TourLog;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestInMemoryTourRepo {

    InMemoryTourRepo inMemoryTourRepo= new InMemoryTourRepo(InMemoryDatabase.getInstance());

    @AfterEach
    void clearDB()
    {
        InMemoryDatabase.getInstance().setTours(new ArrayList<>());
    }

    @Test
    void createTour()
    {
        //arrange
        Tour tour = new Tour();

        //act
        tour.setId(inMemoryTourRepo.create(tour));


        //assert
        assertNotNull(tour.getId());
    }

    @Test
    void update()
    {
        //arrange
        Tour tour = new Tour();
        tour.setName("name");
        tour.setId(inMemoryTourRepo.create(tour));
        //act
        tour.setName("name updated");
        inMemoryTourRepo.update(tour);

        //assert
        assertEquals("name updated", tour.getName());
    }

    @Test
    void read()
    {
        //arrange
        Tour tour = new Tour();
        tour.setName("name");
        tour.setId(inMemoryTourRepo.create(tour));

        //act
        Tour searchedTour= inMemoryTourRepo.read(tour.getId());

        //assert
        assertEquals("name",searchedTour.getName());
    }

    @Test
    void delete()
    {
        //arrange
        Tour tour = new Tour();
        tour.setName("name");
        tour.setId(inMemoryTourRepo.create(tour));

        //act
        inMemoryTourRepo.delete(tour.getId());

        Tour searchedTour= inMemoryTourRepo.read(tour.getId());

        //assert
        assertNull(searchedTour);
    }

    @Test
    void getAll()
    {
        //arrange
        Tour tour = new Tour();
        tour.setName("name");
        tour.setId(inMemoryTourRepo.create(tour));
        Tour tour2 = new Tour();
        tour2.setName("name2");
        tour2.setId(inMemoryTourRepo.create(tour2));
        Tour tour3 = new Tour();
        tour3.setName("name3");
        tour3.setId(inMemoryTourRepo.create(tour3));

        //act
        List<Tour> tours= inMemoryTourRepo.getAll();


        //assert
        assertEquals(6,tours.size());
    }

    @Test
    void addLogWithUpdate()
    {
        //arrange
        Tour tour = new Tour();
        tour.setName("name");
        tour.setId(inMemoryTourRepo.create(tour));

        //act
        tour.getLogs().add(new TourLog());
        tour.getLogs().add(new TourLog());
        inMemoryTourRepo.update(tour);


        //assert
        assertEquals(2,tour.getLogs().size());
    }

    @Test
    void removeLogWithUpdate()
    {
        //arrange
        Tour tour = new Tour();
        tour.setName("name");
        tour.getLogs().add(new TourLog());
        tour.getLogs().add(new TourLog());
        tour.setId(inMemoryTourRepo.create(tour));

        //act
        tour.getLogs().remove(0);
        inMemoryTourRepo.update(tour);


        //assert
        assertEquals(1,tour.getLogs().size());
    }

}
