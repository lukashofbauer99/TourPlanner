package Models;

import lombok.Data;

import java.util.Date;

@Data
public class TourLog {
    Date date;
    String report;
    //tour distance in km
    double distance;
    //time spent in hours
    double totalTime;
    //1-5
    int rating;

    //in km/h
    double averageSpeed;
    String typeOfTransport;
    int difficulty;
    int recommendedPeopleCount;
    boolean toiletOnThePath;

}