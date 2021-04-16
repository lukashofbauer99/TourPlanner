package Views;

import DataAccess.Repositories.DAOs.ITourDAO;
import DataAccess.Repositories.DAOs.TourDAO;
import Models.Tour;
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


@Slf4j
@NoArgsConstructor
public class TourDescriptionViewController implements IViewController {

    ITourDAO tourDAO = TourDAO.getInstance();

    private Tour selectedTour= null;
    public LongProperty selectedTourId= new SimpleLongProperty();


    @FXML
    public Label selectedTourNameField = new Label();
    SimpleStringProperty selectedTourName = new SimpleStringProperty();

    @FXML
    public TextArea selectedTourDescField= new TextArea();
    SimpleStringProperty selectedTourDesc = new SimpleStringProperty();

    @FXML
    public ImageView selectedTourRouteInfoField = new ImageView();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedTourNameField.textProperty().bindBidirectional(selectedTourName);
        selectedTourDescField.textProperty().bindBidirectional(selectedTourDesc);

        setupSelectedTourListeners();
    }

    public void setupSelectedTourListeners()
    {
        tourDAO.registerForNotification(()->
        {
            selectedTour=tourDAO.read(selectedTourId.longValue());
            if(selectedTour!=null ) {
                selectedTourName.setValue(selectedTour.getName());
                selectedTourDesc.setValue(selectedTour.getTourDescription());
                try {
                    FileInputStream input = new FileInputStream(selectedTour.getRouteInformation());
                    Image image= new Image(input);
                    selectedTourRouteInfoField.setImage(image);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else{
                selectedTourName.setValue("");
                selectedTourDesc.setValue("");
                selectedTourRouteInfoField.setImage(null);
            }
        });

        selectedTourId.addListener((observableValue, old, newVal) ->
        {
            selectedTour=tourDAO.read(newVal.longValue());
            if(selectedTour!=null ) {
                selectedTourName.setValue(selectedTour.getName());
                selectedTourDesc.setValue(selectedTour.getTourDescription());
                try {
                    FileInputStream input = new FileInputStream(selectedTour.getRouteInformation());
                    Image image= new Image(input);
                    selectedTourRouteInfoField.setImage(image);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else{
                selectedTourName.setValue("");
                selectedTourDesc.setValue("");
                selectedTourRouteInfoField.setImage(null);
                }
        });
    }
}
