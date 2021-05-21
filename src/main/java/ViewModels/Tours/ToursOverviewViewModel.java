package ViewModels.Tours;

import DataAccess.Repositories.DAOs.ITourDAO;
import DataAccess.Repositories.DAOs.TourDAO;
import Models.Tour;
import ViewModels.IViewModel;
import Views.IViewController;
import Views.Tours.TourCreateViewController;
import Views.Tours.TourEditViewController;
import Views.Tours.ToursOverviewViewController;
import Views.ViewManager;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


@NoArgsConstructor
public class ToursOverviewViewModel implements IViewModel {

    ITourDAO tourDAO = TourDAO.getInstance();

    ToursOverviewViewController controller;

    public ToursOverviewViewModel(ToursOverviewViewController controller) {
        this.controller = controller;
    }

    public void initListView()
    {
        loadTours();
        setSelectedTourListener();
    }

    private void loadTours()
    {
        tourDAO.registerForNotification(()->
        {
            if(controller.searchString!=null&&controller.searchString.get()!=null)
                controller.tours.setAll(tourDAO.getAll().stream().filter(x->x.getName().contains(controller.searchString.get())).collect(Collectors.toList()));
            else
                controller.tours.setAll(tourDAO.getAll());
        });

        controller.searchString.addListener(x->{
            if(controller.searchString!=null&&controller.searchString.get()!=null)
                controller.tours.setAll(tourDAO.getAll().stream().filter(y->y.getName().contains(controller.searchString.get())).collect(Collectors.toList()));
            else
                controller.tours.setAll(tourDAO.getAll());
        });

        controller.tours.setAll(tourDAO.getAll());
        controller.toursListView.setItems(controller.tours);
    }

    private void setSelectedTourListener()
    {

        controller.toursListView.getSelectionModel().selectedItemProperty().addListener(((observableValue, old, newVal) ->
        {
            if(newVal!=null) {
                controller.selectedTourId.set(newVal.getId());
            }
        }));
    }

    public void addNewTour() {
        controller.tourCreateViewController=(TourCreateViewController) ViewManager.createView("createTour",new Stage(),"Create Tour");
    }

    public void removeTour() {
        long id = controller.selectedTourId.get();
        controller.selectedTourId.setValue(0);
        tourDAO.delete(id);
    }

    public void editTour() {
        if (controller.selectedTourId.get()!=0) {
            controller.tourEditViewController = (TourEditViewController) ViewManager.createView("editTour", new Stage(), "Edit Tour");
            controller.tourEditViewController.selectedTourId = controller.selectedTourId.get();
            controller.tourEditViewController.initSelectedTour();
        }
    }

    @Override
    public IViewController getController() {
        return controller;
    }
}
