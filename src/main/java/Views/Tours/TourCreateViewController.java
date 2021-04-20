package Views.Tours;

import BusinessLogic.Services.MapService.IMapPictureService;
import BusinessLogic.Services.MapService.MapPictureServiceProvider;
import BusinessLogic.Services.MapService.MapQuestPictureService;
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Double.parseDouble;


@NoArgsConstructor
public class TourCreateViewController implements IViewController {


    MapPictureServiceProvider mapPictureServiceProvider = MapPictureServiceProvider.getInstance();
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

        // would be nice but is to slow
        //start.textProperty().addListener((observable, oldValue, newValue) -> refreshPreview());
        //end.textProperty().addListener((observable, oldValue, newValue) -> refreshPreview());
    }

    private void refreshPreview(){
        if(end.textProperty().get().length()>2&&start.textProperty().get().length()>2) {
            try {
                FileInputStream input = new FileInputStream(mapPictureServiceProvider.getPathOfCreatedPicture(start.getText(), end.getText()));
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

        String pathToFile= null;
        if(start.getLength()>3&&end.getLength()>3)
        {
            pathToFile=  mapPictureServiceProvider.getPathOfCreatedPicture(start.getText(),end.getText());
        }

        tourDAO.create(new Tour(1L, name.getText(), description.getText(),pathToFile,distanceDouble ));
        Stage stage = (Stage) name.getScene().getWindow();
        stage.close();

    }

    public void cancelCreation(ActionEvent actionEvent) {
        Stage stage = (Stage) name.getScene().getWindow();
        stage.close();
    }

    public void updatePreview(ActionEvent actionEvent) {
        refreshPreview();
    }
}
