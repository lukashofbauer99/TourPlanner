package Views.TourLogs;

import DataAccess.Repositories.DAOs.ITourDAO;
import DataAccess.Repositories.DAOs.ITourLogDAO;
import DataAccess.Repositories.DAOs.TourDAO;
import DataAccess.Repositories.DAOs.TourLogDAO;
import Models.Tour;
import Models.TourLog;
import Views.IViewController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
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

    @FXML
    public TextField averageSpeed;

    @FXML
    public TextField typeOfTransport;

    @FXML
    public TextField difficulty;

    @FXML
    public TextField recommendedPeopleCount;

    @FXML
    public CheckBox toiletOnThePath;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        distance.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                distance.setText(oldValue);
            }
        });

        averageSpeed.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                averageSpeed.setText(oldValue);
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


        difficulty.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[12345]?")) {
                difficulty.setText(oldValue);
            }
        });

        recommendedPeopleCount.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,3}?")) {
                recommendedPeopleCount.setText(oldValue);
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
        double averageSpeedDouble=0;
        if(!averageSpeed.getText().equals(""))
            averageSpeedDouble = parseDouble(averageSpeed.getText());

        int ratingInt=1;
        if(!rating.getText().equals(""))
            ratingInt= parseInt(rating.getText());

        int difficultyInt=1;
        if(!difficulty.getText().equals(""))
            difficultyInt= parseInt(difficulty.getText());

        int recommendedPeopleCountInt=1;
        if(!recommendedPeopleCount.getText().equals(""))
            recommendedPeopleCountInt= parseInt(recommendedPeopleCount.getText());

        TourLog tourLog = new TourLog(new Date(),report.getText(),distanceDouble,totalTimeDouble,ratingInt, averageSpeedDouble
                ,typeOfTransport.getText(),difficultyInt, recommendedPeopleCountInt, toiletOnThePath.isSelected());
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
