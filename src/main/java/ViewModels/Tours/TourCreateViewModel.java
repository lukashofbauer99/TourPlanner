package ViewModels.Tours;

import BusinessLogic.Services.MapService.MapPictureServiceProvider;
import DataAccess.Repositories.DAOs.ITourDAO;
import DataAccess.Repositories.DAOs.TourDAO;
import Models.Tour;
import ViewModels.IViewModel;
import Views.IViewController;
import Views.Tours.TourCreateViewController;
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
public class TourCreateViewModel implements IViewModel {


    MapPictureServiceProvider mapPictureServiceProvider = MapPictureServiceProvider.getInstance();
    ITourDAO tourDAO = TourDAO.getInstance();

    TourCreateViewController controller;

    public TourCreateViewModel(TourCreateViewController controller) {
        this.controller = controller;
    }

    public void refreshPreview(){
        if(controller.end.textProperty().get().length()>2&&controller.start.textProperty().get().length()>2) {
            try {
                FileInputStream input = new FileInputStream(mapPictureServiceProvider.getPathOfCreatedPicture(controller.start.getText(), controller.end.getText()));
                Image image = new Image(input);
                controller. preview.setImage(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    public void createTour() {
        double distanceDouble=0;
        if(!controller.distance.getText().equals(""))
            distanceDouble = parseDouble(controller.distance.getText());

        String pathToFile= null;
            if(controller.start.getLength()>3&&controller.end.getLength()>3)
            {
            pathToFile=  mapPictureServiceProvider.getPathOfCreatedPicture(controller.start.getText(),controller.end.getText());
        }

        tourDAO.create(new Tour(1L, controller.name.getText(), controller.description.getText(),pathToFile,distanceDouble ));
        Stage stage = (Stage) controller.name.getScene().getWindow();
        stage.close();

    }

    @Override
    public IViewController getController() {
        return controller;
    }
}
