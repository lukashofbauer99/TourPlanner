package Views.TourLogs;

import DataAccess.Repositories.DAOs.ITourLogDAO;
import DataAccess.Repositories.DAOs.TourLogDAO;
import Models.TourLog;
import Views.IViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;


@Slf4j
@NoArgsConstructor
public class TourLogEditViewController implements IViewController {

    ITourLogDAO tourLogDAO = TourLogDAO.getInstance();

    public long selectedTourLogId=0;

    private TourLog selectedTourLog;

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

    public void initSelectedTourLog()
    {
        if (selectedTourLogId!=0) {
            selectedTourLog = tourLogDAO.read(selectedTourLogId);
            report.setText(selectedTourLog.getReport());
            totalTime.setText(String.valueOf(selectedTourLog.getTotalTime()));
            distance.setText(String.valueOf(selectedTourLog.getDistance()));
            rating.setText(String.valueOf(selectedTourLog.getRating()));
            averageSpeed.setText(String.valueOf(selectedTourLog.getAverageSpeed()));
            typeOfTransport.setText(String.valueOf(selectedTourLog.getTypeOfTransport()));
            difficulty.setText(String.valueOf(selectedTourLog.getDifficulty()));
            recommendedPeopleCount.setText(String.valueOf(selectedTourLog.getRecommendedPeopleCount()));
            toiletOnThePath.setSelected(selectedTourLog.getToiletOnThePath());
        }


    }

    public void saveLog(ActionEvent actionEvent) {
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

        selectedTourLog.setReport(report.getText());
        selectedTourLog.setDistance(distanceDouble);
        selectedTourLog.setTotalTime(totalTimeDouble);
        selectedTourLog.setRating(ratingInt);
        selectedTourLog.setAverageSpeed(averageSpeedDouble);
        selectedTourLog.setTypeOfTransport(typeOfTransport.getText());
        selectedTourLog.setDifficulty(difficultyInt);
        selectedTourLog.setRecommendedPeopleCount(recommendedPeopleCountInt);
        selectedTourLog.setToiletOnThePath(toiletOnThePath.isSelected());

        tourLogDAO.update(selectedTourLog);

        Stage stage = (Stage) report.getScene().getWindow();
        stage.close();

    }

    public void cancelCreation(ActionEvent actionEvent) {
        Stage stage = (Stage) report.getScene().getWindow();
        stage.close();
    }

}
