package Views;

import DataAccess.Repositories.DAOs.ITourDAO;
import DataAccess.Repositories.DAOs.ITourLogDAO;
import DataAccess.Repositories.DAOs.TourDAO;
import DataAccess.Repositories.DAOs.TourLogDAO;
import Models.Tour;
import Models.TourLog;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;


@Slf4j
@NoArgsConstructor
public class TourLogCreateViewController implements IViewController {

    ITourLogDAO tourLogDAO = TourLogDAO.getInstance();
    ITourDAO tourDAO = TourDAO.getInstance();

    public long selectedTourId=0;

    private Tour selectedTour;

    @FXML
    public TextField report;

    @FXML
    public TextField totalTime;

    @FXML
    public TextField distance ;

    @FXML
    public TextField rating;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        distance.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                distance.setText(oldValue);
            }
        });

        totalTime.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                totalTime.setText(oldValue);
            }
        });

        rating.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[12345]?")) {
                rating.setText(oldValue);
            }
        });

    }

    public void initSelectedTour()
    {
        selectedTour= tourDAO.read(selectedTourId);

    }

    public void createLog(ActionEvent actionEvent) {
        double distanceDouble=0;
        if(!distance.getText().equals(""))
            distanceDouble = parseDouble(distance.getText());

        double totalTimeDouble=0;
        if(!totalTime.getText().equals(""))
            totalTimeDouble = parseDouble(totalTime.getText());

        int ratingInt=1;
        if(!rating.getText().equals(""))
            ratingInt= parseInt(rating.getText());

        TourLog tourLog = new TourLog(new Date(),report.getText(),distanceDouble,totalTimeDouble,ratingInt);
        tourLog.setId(tourLogDAO.create(tourLog));

        selectedTour.getLogs().add(tourLog);
        tourDAO.update(selectedTour);

        Stage stage = (Stage) report.getScene().getWindow();
        stage.close();

    }

    public void cancelCreation(ActionEvent actionEvent) {
        Stage stage = (Stage) report.getScene().getWindow();
        stage.close();
    }
}
