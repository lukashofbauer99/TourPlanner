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
public class TourLogDetailsViewController implements IViewController {

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

    public void close(ActionEvent actionEvent) {
        Stage stage = (Stage) report.getScene().getWindow();
        stage.close();
    }
}
