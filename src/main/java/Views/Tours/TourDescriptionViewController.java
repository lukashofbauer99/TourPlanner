package Views.Tours;

import DataAccess.Repositories.DAOs.ITourDAO;
import DataAccess.Repositories.DAOs.TourDAO;
import Models.Tour;
import ViewModels.IViewModel;
import ViewModels.Tours.TourDescriptionViewModel;
import Views.IViewController;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;


@NoArgsConstructor
public class TourDescriptionViewController implements IViewController {


    TourDescriptionViewModel viewModel;

    public Tour selectedTour= null;
    public LongProperty selectedTourId= new SimpleLongProperty();


    @FXML
    public Label selectedTourNameField = new Label();
    public SimpleStringProperty selectedTourName = new SimpleStringProperty();

    @FXML
    public TextArea selectedTourDescField= new TextArea();
    public SimpleStringProperty selectedTourDesc = new SimpleStringProperty();

    @FXML
    public ImageView selectedTourRouteInfoField = new ImageView();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewModel= new TourDescriptionViewModel(this);

        selectedTourNameField.textProperty().bindBidirectional(selectedTourName);
        selectedTourDescField.textProperty().bindBidirectional(selectedTourDesc);

        setupSelectedTourListeners();
    }

    public void setupSelectedTourListeners()
    {
       viewModel.setupSelectedTourListeners();
    }

    @Override
    public IViewModel getViewModel() {
        return viewModel;
    }
}
