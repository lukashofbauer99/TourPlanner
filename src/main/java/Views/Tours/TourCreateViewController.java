package Views.Tours;

import ViewModels.IViewModel;
import ViewModels.Tours.TourCreateViewModel;
import Views.IViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.util.ResourceBundle;


@NoArgsConstructor
public class TourCreateViewController implements IViewController {
    TourCreateViewModel viewModel;

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

        viewModel= new TourCreateViewModel(this);

        distance.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                distance.setText(oldValue);
            }
        });

        // would be nice but is to slow
        //start.textProperty().addListener((observable, oldValue, newValue) -> refreshPreview());
        //end.textProperty().addListener((observable, oldValue, newValue) -> refreshPreview());
    }


    public void createTour(ActionEvent actionEvent) {
        viewModel.createTour();

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
