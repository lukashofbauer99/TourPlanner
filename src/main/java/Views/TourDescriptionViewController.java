package Views;

import Models.Tour;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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

    public ObjectProperty<Tour> selectedTour = new SimpleObjectProperty<>();

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

        setupSelectedTourListeners();

        selectedTourNameField.textProperty().bindBidirectional(selectedTourName);
        selectedTourDescField.textProperty().bindBidirectional(selectedTourDesc);
    }

    public void setupSelectedTourListeners()
    {
        //Name
        selectedTour.addListener((observableValue, old, newVal) ->
        {
            if(selectedTour.get()!=null) {
                selectedTourName.setValue(selectedTour.get().getName());
            }
        });

        //Desc
        selectedTour.addListener((observableValue, old, newVal) ->
        {
            if(selectedTour.get()!=null) {
                selectedTourDesc.setValue(selectedTour.get().getTourDescription());
            }
        });

        //RoutInfo
        selectedTour.addListener((observableValue, old, newVal) ->
        {
            if(selectedTour.get()!=null) {
                try {
                    FileInputStream input = new FileInputStream(newVal.getRouteInformation());
                    Image image= new Image(input);
                    selectedTourRouteInfoField.setImage(image);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

        });

    }
}
