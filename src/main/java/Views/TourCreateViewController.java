package Views;

import DataAccess.Repositories.DAOs.ITourDAO;
import DataAccess.Repositories.DAOs.TourDAO;
import Models.Tour;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Double.parseDouble;


@Slf4j
@NoArgsConstructor
public class TourCreateViewController implements IViewController {

    ITourDAO tourDAO = TourDAO.getInstance();

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



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        distance.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                distance.setText(oldValue);
            }
        });
    }

    public void createTour(ActionEvent actionEvent) {
        double distanceDouble=0;
        if(!distance.getText().equals(""))
            distanceDouble = parseDouble(distance.getText());

        tourDAO.create(new Tour(1L, name.getText(), description.getText(), "MapReqTourPics/Download.jpeg",distanceDouble ));
        Stage stage = (Stage) name.getScene().getWindow();
        stage.close();

    }

    public void cancelCreation(ActionEvent actionEvent) {
        Stage stage = (Stage) name.getScene().getWindow();
        stage.close();
    }
}
