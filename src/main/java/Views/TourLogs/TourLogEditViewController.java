package Views.TourLogs;

import DataAccess.Repositories.DAOs.ITourLogDAO;
import DataAccess.Repositories.DAOs.TourLogDAO;
import Models.TourLog;
import ViewModels.IViewModel;
import ViewModels.TourLogs.TourLogEditViewModel;
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


@NoArgsConstructor
public class TourLogEditViewController implements IViewController {

    TourLogEditViewModel viewModel;

    public long selectedTourLogId=0;

    public TourLog selectedTourLog;

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

        viewModel= new TourLogEditViewModel(this);


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
        viewModel.initSelectedTourLog();
    }

    public void saveLog(ActionEvent actionEvent) {
        viewModel.saveLog();
    }

    public void cancelCreation(ActionEvent actionEvent) {
        Stage stage = (Stage) report.getScene().getWindow();
        stage.close();
    }

    @Override
    public IViewModel getViewModel() {
        return viewModel;
    }
}
