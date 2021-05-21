package Views;

import BusinessLogic.Services.Import_ExportService.Import_ExportServiceProvider;
import BusinessLogic.Services.ReportingService.ReportingServiceProvider;
import DataAccess.Repositories.DAOs.TourDAO;
import ViewModels.IViewModel;
import ViewModels.MainViewModel;
import Views.TourLogs.TourLogsViewController;
import Views.Tours.TourDescriptionViewController;
import Views.Tours.ToursOverviewViewController;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;




public class MainViewController implements IViewController {

    MainViewModel viewModel;

    public LongProperty selectedTourId= new SimpleLongProperty();

    public StringProperty searchString= new SimpleStringProperty("");

    @FXML
    public TextField searchInput= new TextField("");

    @FXML
    private ToursOverviewViewController toursOverviewViewController;

    @FXML
    private TourDescriptionViewController tourDescriptionViewController;

    @FXML
    public TourLogsViewController tourLogsViewController;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        viewModel= new MainViewModel(this);
        selectedTourId.bindBidirectional(toursOverviewViewController.selectedTourId);
        selectedTourId.bindBidirectional(tourDescriptionViewController.selectedTourId);
        selectedTourId.bindBidirectional(tourLogsViewController.selectedTourId);

        searchInput.textProperty().bindBidirectional(searchString);

        searchString.bindBidirectional(toursOverviewViewController.searchString);
        searchString.bindBidirectional(tourLogsViewController.searchString);


    }

    public void generateReport(ActionEvent actionEvent) {
       viewModel.generateReport();
    }

    public void exportData(ActionEvent actionEvent) {
        viewModel.exportData();
    }

    public void importData(ActionEvent actionEvent) {
      viewModel.importData();
    }


    @Override
    public IViewModel getViewModel() {
        return viewModel;
    }
}
