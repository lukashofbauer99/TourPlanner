package Views.TourLogs;

import DataAccess.Repositories.DAOs.ITourLogDAO;
import DataAccess.Repositories.DAOs.TourLogDAO;
import Models.TourLog;
import ViewModels.IViewModel;
import ViewModels.TourLogs.TourLogDetailsViewModel;
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
public class TourLogDetailsViewController implements IViewController {

    TourLogDetailsViewModel viewModel;

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

        viewModel = new TourLogDetailsViewModel(this);
    }

    public void initSelectedTourLog()
    {
        viewModel.initSelectedTourLog();
    }

    public void close(ActionEvent actionEvent) {
        Stage stage = (Stage) report.getScene().getWindow();
        stage.close();
    }

    @Override
    public IViewModel getViewModel() {
        return viewModel;
    }
}
