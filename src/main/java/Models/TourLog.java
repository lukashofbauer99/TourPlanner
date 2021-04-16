package Models;

import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
public class TourLog {
    Long id;
    Date date;

    public TourLog(Date date, String report, double distance, double totalTime, int rating) {
        this.date = date;
        this.report = report;
        this.distance = distance;
        this.totalTime = totalTime;
        this.rating = rating;
    }

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

    public boolean getToiletOnThePath() {
        return toiletOnThePath;
    }
}