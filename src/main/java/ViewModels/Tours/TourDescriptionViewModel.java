package ViewModels.Tours;

import DataAccess.Repositories.DAOs.ITourDAO;
import DataAccess.Repositories.DAOs.TourDAO;
import Models.Tour;
import ViewModels.IViewModel;
import Views.IViewController;
import Views.Tours.TourDescriptionViewController;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.NoArgsConstructor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;


@NoArgsConstructor
public class TourDescriptionViewModel implements IViewModel {

    ITourDAO tourDAO = TourDAO.getInstance();

    TourDescriptionViewController controller;

    public TourDescriptionViewModel(TourDescriptionViewController controller) {
        this.controller = controller;
    }

    public void setupSelectedTourListeners()
    {
        tourDAO.registerForNotification(()->
        {
            controller.selectedTour=tourDAO.read(controller.selectedTourId.longValue());
            if(controller.selectedTour!=null ) {
                controller.selectedTourName.setValue(controller.selectedTour.getName());
                controller.selectedTourDesc.setValue(controller.selectedTour.getTourDescription());

                if(controller.selectedTour.getRouteInformation()!=null&& !controller.selectedTour.getRouteInformation().equals("")) {
                    try {
                        FileInputStream input = new FileInputStream(controller.selectedTour.getRouteInformation());
                        Image image = new Image(input);
                        controller.selectedTourRouteInfoField.setImage(image);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
            else{
                controller.selectedTourName.setValue("");
                controller.selectedTourDesc.setValue("");
                controller.selectedTourRouteInfoField.setImage(null);
            }
        });

        controller.selectedTourId.addListener((observableValue, old, newVal) ->
        {
            controller.selectedTour=tourDAO.read(newVal.longValue());
            if(controller.selectedTour!=null ) {
                controller.selectedTourName.setValue(controller.selectedTour.getName());
                controller.selectedTourDesc.setValue(controller.selectedTour.getTourDescription());
                if (controller.selectedTour.getRouteInformation() != null && !controller.selectedTour.getRouteInformation().equals("")) {
                    try {
                        FileInputStream input = new FileInputStream(controller.selectedTour.getRouteInformation());
                        Image image = new Image(input);
                        controller.selectedTourRouteInfoField.setImage(image);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
            else{
                controller.selectedTourName.setValue("");
                controller.selectedTourDesc.setValue("");
                controller.selectedTourRouteInfoField.setImage(null);
                }
        });
    }

    @Override
    public IViewController getController() {
        return controller;
    }
}
