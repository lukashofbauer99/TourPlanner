package Views.Tours;

import BusinessLogic.Services.IMapPictureService;
import BusinessLogic.Services.MockMapPictureService;
import DataAccess.Repositories.DAOs.ITourDAO;
import DataAccess.Repositories.DAOs.TourDAO;
import Models.Tour;
import Views.IViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Double.parseDouble;


@NoArgsConstructor
public class TourCreateViewController implements IViewController {


    IMapPictureService mapPictureService = new MockMapPictureService();
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

    @FXML
    public ImageView preview;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        distance.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                distance.setText(oldValue);
            }
        });

        start.textProperty().addListener((observable, oldValue, newValue) -> refreshPreview());
        end.textProperty().addListener((observable, oldValue, newValue) -> refreshPreview());
    }

    private void refreshPreview(){
        if(end.textProperty().get().length()>2&&start.textProperty().get().length()>2) {
            try {
                FileInputStream input = new FileInputStream(mapPictureService.getPathOfCreatedPicture(start.getText(), end.getText()));
                Image image = new Image(input);
                preview.setImage(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void createTour(ActionEvent actionEvent) {
        double distanceDouble=0;
        if(!distance.getText().equals(""))
            distanceDouble = parseDouble(distance.getText());

        tourDAO.create(new Tour(1L, name.getText(), description.getText(), mapPictureService.getPathOfCreatedPicture(start.getText(), end.getText()),distanceDouble ));
        Stage stage = (Stage) name.getScene().getWindow();
        stage.close();

    }

    public void cancelCreation(ActionEvent actionEvent) {
        Stage stage = (Stage) name.getScene().getWindow();
        stage.close();
    }
}