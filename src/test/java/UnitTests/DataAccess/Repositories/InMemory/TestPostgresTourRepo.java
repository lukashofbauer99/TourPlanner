package UnitTests.DataAccess.Repositories.InMemory;

import DataAccess.Repositories.Repositories.InMemory.InMemoryDatabase;
import DataAccess.Repositories.Repositories.PostgresDB.PostgresTourRepo;
import Models.Tour;
import Models.TourLog;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestPostgresTourRepo {


    PostgresTourRepo postgresTourRepo = new PostgresTourRepo("jdbc:postgresql://172.17.0.2:5432/tourplannerTest","postgres","postgres");

    @Test
    void createTour()
    {
        //arrange
        Tour tour = new Tour();

        //act
        tour.setId(postgresTourRepo.create(tour));


        //assert
        assertNotNull(tour.getId());
    }

    @Test
    void update()
    {
        //arrange
        Tour tour = new Tour();
        tour.setName("name");
        tour.setId(postgresTourRepo.create(tour));
        //act
        tour.setName("name updated");
        postgresTourRepo.update(tour);

        //assert
        assertEquals("name updated", tour.getName());
    }

    @Test
    void read()
    {
        //arrange
        Tour tour = new Tour();
        tour.setName("name");
        tour.setId(postgresTourRepo.create(tour));

        //act
        Tour searchedTour= postgresTourRepo.read(tour.getId());

        //assert
        assertEquals("name",searchedTour.getName());
    }

    @Test
    void delete()
    {
        //arrange
        Tour tour = new Tour();
        tour.setName("name");
        tour.setId(postgresTourRepo.create(tour));

        //act
        postgresTourRepo.delete(tour.getId());

        Tour searchedTour= postgresTourRepo.read(tour.getId());

        //assert
        assertNull(searchedTour);
    }

    @Test
    void getAll()
    {
        //arrange
        Tour tour = new Tour();
        tour.setName("name");
        tour.setId(postgresTourRepo.create(tour));
        Tour tour2 = new Tour();
        tour2.setName("name2");
        tour2.setId(postgresTourRepo.create(tour2));
        Tour tour3 = new Tour();
        tour3.setName("name3");
        tour3.setId(postgresTourRepo.create(tour3));

        //act
        List<Tour> tours= postgresTourRepo.getAll();


        //assert
        assertEquals(3,tours.size());
    }

    @Test
    void addLogWithUpdate()
    {
        //arrange
        Tour tour = new Tour();
        tour.setName("name");
        tour.setId(postgresTourRepo.create(tour));

        //act
        tour.getLogs().add(new TourLog());
        tour.getLogs().add(new TourLog());
        postgresTourRepo.update(tour);


        //assert
        assertEquals(2,tour.getLogs().size());
    }

}
