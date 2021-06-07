package DataAccess.Repositories.Repositories.PostgresDB;

import DataAccess.Repositories.Repositories.Interfaces.ITourRepo;
import Models.Tour;
import Models.TourLog;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PostgresTourRepo implements ITourRepo {
    List<Runnable> tourSubscribers = new ArrayList<>();

    Connection connection;

    public PostgresTourRepo(String connectionString, String username, String password) {
        try {
            connection = DriverManager.getConnection(connectionString,username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Tour> getAll() {
        List<Tour> tours = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("""
                    SELECT 
                    id, 
                    name,
                    tourDescription,
                    routeInformation,
                    tourDistance
                    FROM tours
                    ORDER BY name
                    """);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Tour tour= new Tour();
                tour.setId(resultSet.getLong(1));
                tour.setName(resultSet.getString(2));
                tour.setTourDescription(resultSet.getString(3));
                tour.setRouteInformation(resultSet.getString(4));
                tour.setTourDistance(resultSet.getDouble(5));

                tour.setLogs(findTourLogToTourId(resultSet.getLong(1)));


                tours.add(tour);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tours;
    }

    @Override
    public Long create(Tour entity) {
        Long id;
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("""
                    INSERT 
                    INTO
                    tours
                    (name, tourDescription, routeInformation, tourDistance)
                    VALUES 
                    (
                    ?,?,?,?
                    )
                    returning id
                    """);

            statement.setString(1, entity.getName());
            statement.setString(2, entity.getTourDescription());
            statement.setString(3, entity.getRouteInformation());
            statement.setDouble(4, entity.getTourDistance());

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            id = resultSet.getLong(1);
            entity.setId(id);
            entity.getLogs().forEach(x->
                    {
                        x.setTourId(id);
                        createTourLog(x);
                    });

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

        notifySubs();
        return id;
    }

    @Override
    public Tour read(Long id) {
        Tour tour = new Tour();
        try {
            PreparedStatement statement = connection.prepareStatement("""
                    SELECT 
                    id, 
                    name,
                    tourDescription,
                    routeInformation,
                    tourDistance
                    FROM tours 
                    where id=?
                    """);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tour.setId(resultSet.getLong(1));
                tour.setName(resultSet.getString(2));
                tour.setTourDescription(resultSet.getString(3));
                tour.setRouteInformation(resultSet.getString(4));
                tour.setTourDistance(resultSet.getDouble(5));


                tour.setLogs(findTourLogToTourId(resultSet.getLong(1)));
                return tour;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Tour entity) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("""
                     Update 
                        tours
                        SET
                        name=?,
                        tourDescription=?,
                        routeInformation=?,
                        tourDistance=?
                        WHERE id=?
                        RETURNING id
                    """);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getTourDescription());
            statement.setString(3, entity.getRouteInformation());
            statement.setDouble(4, entity.getTourDistance());

            statement.setLong(5, entity.getId());

            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next())
                return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        notifySubs();

        return true;
    }

    @Override
    public boolean delete(Long id) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("""
                    DELETE
                    FROM tours
                    WHERE id=?
                    returning id
                    """);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next())
                return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        notifySubs();
        return true;
    }

    @Override
    public void registerForNotification(Runnable method) {
        tourSubscribers.add(method);
    }

    private void notifySubs()
    {
        tourSubscribers.forEach(Runnable::run);
    }

    public List<TourLog> findTourLogToTourId(Long id) {
        List<TourLog> tourLogs = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("""
                    SELECT 
                    ID,                        
                    date,                  
                    report,                
                    distance,              
                    totalTime,             
                    rating,                
                    averageSpeed,          
                    typeOfTransport,       
                    difficulty,       
                    recommendedPeopleCount,
                    toiletOnPath    
                    FROM tourLogs
                    where tourId=?
                    """);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TourLog tourLog = new TourLog();
                tourLog.setId(resultSet.getLong(1));
                DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",Locale.US);
                tourLog.setDate(formatter.parse(resultSet.getString(2)));
                tourLog.setReport(resultSet.getString(3));
                tourLog.setDistance(resultSet.getDouble(4));
                tourLog.setTotalTime(resultSet.getDouble(5));
                tourLog.setRating(resultSet.getInt(6));
                tourLog.setAverageSpeed(resultSet.getDouble(7));
                tourLog.setTypeOfTransport(resultSet.getString(8));
                tourLog.setDifficulty(resultSet.getInt(9));
                tourLog.setRecommendedPeopleCount(resultSet.getInt(10));
                tourLog.setToiletOnThePath(resultSet.getBoolean(11));

                tourLogs.add(tourLog);

            }
        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
        }
        return tourLogs;
    }

    public Long createTourLog(TourLog entity) {
        Long id;
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("""
                    INSERT 
                    INTO
                    tourLogs
                    (date,                  
                    report,                
                    distance,              
                    totalTime,             
                    rating,                
                    averageSpeed,          
                    typeOfTransport,       
                    difficulty,       
                    recommendedPeopleCount,
                    toiletOnPath,
                    tourId)
                    VALUES 
                    (
                    ?,?,?,?,?,?,?,?,?,?,?
                    )
                    returning id
                    """);

            statement.setString(1, entity.getDate().toString());
            statement.setString(2, entity.getReport());
            statement.setDouble(3, entity.getDistance());
            statement.setDouble(4, entity.getTotalTime());
            statement.setInt(5, entity.getRating());
            statement.setDouble(6, entity.getAverageSpeed());
            statement.setString(7, entity.getTypeOfTransport());
            statement.setInt(8, entity.getDifficulty());
            statement.setInt(9, entity.getRecommendedPeopleCount());
            statement.setBoolean(10, entity.getToiletOnThePath());
            statement.setLong(11, entity.getTourId());

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            id = resultSet.getLong(1);
            entity.setId(id);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

        notifySubs();
        return id;
    }
}
