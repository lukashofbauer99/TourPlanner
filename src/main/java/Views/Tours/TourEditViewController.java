package Views.Tours;

import BusinessLogic.Services.MapService.IMapPictureService;
import BusinessLogic.Services.MapService.MapPictureServiceProvider;
import BusinessLogic.Services.MapService.MapQuestPictureService;
import DataAccess.Repositories.DAOs.ITourDAO;
import DataAccess.Repositories.DAOs.TourDAO;
import Models.Tour;
import ViewModels.IViewModel;
import ViewModels.Tours.TourEditViewModel;
import Views.IViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lombok.NoArgsConstructor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Double.parseDouble;


@NoArgsConstructor
public class TourEditViewController implements IViewController {

    MapPictureServiceProvider mapPictureServiceProvider = MapPictureServiceProvider.getInstance();

    TourEditViewModel viewModel;

    ITourDAO tourDAO = TourDAO.getInstance();

    public long selectedTourId=0;

    public Tour selectedTour;

    @FXML
    public TextField name;

    @FXML
    public TextField description;

    @FXML
    public TextField distance ;

    @FXML
    public TextField start;

    @FXML
    public TextField end;

    @FXML
    public ImageView preview;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        viewModel = new TourEditViewModel(this);

        distance.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                distance.setText(oldValue);
            }
        });

        // would be nice but is to slow
        //start.textProperty().addListener((observable, oldValue, newValue) -> refreshPreview());
        //end.textProperty().addListener((observable, oldValue, newValue) -> refreshPreview());
    }

    public void initSelectedTour()
    {
        viewModel.initSelectedTour();

    }

    public void saveTour(ActionEvent actionEvent) {
        viewModel.saveTour();

    }

    public void cancelCreation(ActionEvent actionEvent) {
        Stage stage = (Stage) name.getScene().getWindow();
        stage.close();
    }

    public void updatePreview(ActionEvent actionEvent) {
        viewModel.refreshPreview();
    }

    @Override
    public IViewModel getViewModel() {
        return viewModel;
    }
}
