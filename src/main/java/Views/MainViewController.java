package Views;

import DataAccess.Repositories.DAOs.TourDAO;
import DataAccess.Repositories.Repositories.InMemoryStringRepo;
import Models.Tour;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.ResourceBundle;


@Slf4j
public class MainViewController implements IViewController {



    InMemoryStringRepo inMemoryStringRepo= InMemoryStringRepo.getInstance();
    TourDAO tourDAO = TourDAO.getInstance();

    public ObservableList<Tour> tours= FXCollections.observableArrayList();

    public ObjectProperty<Tour> selectedTour= new SimpleObjectProperty<>();

    @FXML
    public TextField selectedTourName= new TextField();

    @FXML
    public Label searchOutput = new Label();

    @FXML
    private ToursOverviewViewController toursOverviewViewController;

    @FXML
    private TourDescriptionViewController tourDescriptionViewController;

    @FXML
    public TourLogsViewController tourLogsViewController;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        selectedTourName.textProperty().bindBidirectional(inMemoryStringRepo.inputText);
        searchOutput.textProperty().bindBidirectional(inMemoryStringRepo.outputText);

        initLoadTours();
    }

    private void initLoadTours()
    {
        selectedTour.bindBidirectional(toursOverviewViewController.selectedTour);
        selectedTour.bindBidirectional(tourDescriptionViewController.selectedTour);
        selectedTour.bindBidirectional(tourLogsViewController.selectedTour);

        tours.addAll(tourDAO.getAll());

        toursOverviewViewController.tours=tours;

        toursOverviewViewController.initListView();

    }

    public void onKeyReleased(KeyEvent keyEvent) {
        inMemoryStringRepo.outputText.setValue( "you searched for "+ inMemoryStringRepo.inputText.getValue());
    }


    public void addNewTour(ActionEvent actionEvent) {
    }

    public void removeTour(ActionEvent actionEvent) {
    }

}
