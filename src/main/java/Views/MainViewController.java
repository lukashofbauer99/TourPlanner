package Views;

import BusinessLogic.Services.ReportingService.ReportingServiceProvider;
import DataAccess.Repositories.DAOs.TourDAO;
import Views.TourLogs.TourLogsViewController;
import Views.Tours.TourDescriptionViewController;
import Views.Tours.ToursOverviewViewController;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;




public class MainViewController implements IViewController {

    public TourDAO tourDAO = TourDAO.getInstance();

    public ReportingServiceProvider reportingServiceProvider= ReportingServiceProvider.getInstance();

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
        selectedTourId.bindBidirectional(toursOverviewViewController.selectedTourId);
        selectedTourId.bindBidirectional(tourDescriptionViewController.selectedTourId);
        selectedTourId.bindBidirectional(tourLogsViewController.selectedTourId);

        searchInput.textProperty().bindBidirectional(searchString);

        searchString.bindBidirectional(toursOverviewViewController.searchString);
        searchString.bindBidirectional(tourLogsViewController.searchString);


        initLoadTours();
    }

    private void initLoadTours()
    {
    }

    public void onKeyReleased(KeyEvent keyEvent) {
    }

    public void generateReport(ActionEvent actionEvent) {
        //Creating a File chooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF", "*.pdf*"));
        //Adding action on the menu item
        File file = fileChooser.showSaveDialog(this.searchInput.getScene().getWindow());

        if (file != null) {
            reportingServiceProvider.generateReport(tourDAO.getAll(),file.getPath());
        }

    }
}
