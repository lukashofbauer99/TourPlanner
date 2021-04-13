package Models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Tour {
    Long id;
    String name;

    public Tour(Long id, String name, String tourDescription, String routeInformation, double tourDistance) {
        this.id = id;
        this.name = name;
        this.tourDescription = tourDescription;
        this.routeInformation = routeInformation;
        this.tourDistance = tourDistance;
    }

    String tourDescription;
    //Path to Picture
    String routeInformation;
    //tour distance in km
    double tourDistance;

    List<TourLog> logs = new ArrayList<>();
}
