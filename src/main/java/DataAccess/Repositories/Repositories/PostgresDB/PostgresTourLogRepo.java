package DataAccess.Repositories.Repositories.PostgresDB;

import DataAccess.Repositories.Repositories.Interfaces.ITourLogRepo;
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

public class PostgresTourLogRepo implements ITourLogRepo {
    List<Runnable> tourLogsSubscribers = new ArrayList<>();

    Connection connection;

    public PostgresTourLogRepo(String connectionString, String username, String password) {
        try {
            connection = DriverManager.getConnection(connectionString,username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<TourLog> getAll() {
        List<TourLog> toursLogs = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("""
                    SELECT 
                    id,                        
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
                    FROM tourlog     
                    """);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                TourLog tourLog= new TourLog();
                tourLog.setId(resultSet.getLong(1));
                DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
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

                toursLogs.add(tourLog);

            }

        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
        }
        return toursLogs;
    }

    @Override
    public Long create(TourLog entity) {
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

    @Override
    public TourLog read(Long id) {
        TourLog tourLog = new TourLog();
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
                    where id=?
                    """);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tourLog.setId(resultSet.getLong(1));
                DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
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



            }
        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
        }
        return tourLog;
    }

    @Override
    public boolean update(TourLog entity) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("""
                     Update 
                        tourLogs
                        SET
                        date=?,                  
                        report=?,                
                        distance=?,              
                        totalTime=?,             
                        rating=?,                
                        averageSpeed=?,          
                        typeOfTransport=?,       
                        difficulty=?,       
                        recommendedPeopleCount=?,
                        toiletOnPath=?     
                        WHERE id=?
                        RETURNING id
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

            statement.setLong(11, entity.getId());

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
                    FROM tourLogs
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
        tourLogsSubscribers.add(method);
    }

    private void notifySubs()
    {
        tourLogsSubscribers.forEach(Runnable::run);
    }
}
