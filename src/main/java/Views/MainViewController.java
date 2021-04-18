package Views;

import Views.TourLogs.TourLogsViewController;
import Views.Tours.TourDescriptionViewController;
import Views.Tours.ToursOverviewViewController;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.ResourceBundle;




@Slf4j
public class MainViewController implements IViewController {



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

}
