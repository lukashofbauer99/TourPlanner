package Models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class TourLog {
    Long id;
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

    long tourId;

    public TourLog(Date date, String report, double distance, double totalTime, int rating) {
        this.date = date;
        this.report = report;
        this.distance = distance;
        this.totalTime = totalTime;
        this.rating = rating;
    }

    public TourLog(Date date, String report, double distance, double totalTime, int rating, double averageSpeed, String typeOfTransport, int difficulty, int recommendedPeopleCount, boolean toiletOnThePath) {
        this.date = date;
        this.report = report;
        this.distance = distance;
        this.totalTime = totalTime;
        this.rating = rating;
        this.averageSpeed = averageSpeed;
        this.typeOfTransport = typeOfTransport;
        this.difficulty = difficulty;
        this.recommendedPeopleCount = recommendedPeopleCount;
        this.toiletOnThePath = toiletOnThePath;
    }

    public boolean getToiletOnThePath() {
        return toiletOnThePath;
    }
}