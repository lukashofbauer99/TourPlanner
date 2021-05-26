package ViewModels.TourLogs;

import DataAccess.Repositories.DAOs.ITourDAO;
import DataAccess.Repositories.DAOs.ITourLogDAO;
import DataAccess.Repositories.DAOs.TourDAO;
import DataAccess.Repositories.DAOs.TourLogDAO;
import Models.Tour;
import Models.TourLog;
import ViewModels.IViewModel;
import Views.IViewController;
import Views.TourLogs.TourLogCreateViewController;
import Views.TourLogs.TourLogDetailsViewController;
import Views.TourLogs.TourLogEditViewController;
import Views.TourLogs.TourLogsViewController;
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
public class TourLogsViewModel implements IViewModel {

    ITourDAO tourDAO = TourDAO.getInstance();
    ITourLogDAO tourLogDAO = TourLogDAO.getInstance();

    TourLogsViewController controller;

    public TourLogsViewModel(TourLogsViewController controller) {
        this.controller = controller;
    }

    public void loadLogs()
    {
        if (controller.selectedTourId!=null&&controller.selectedTourId.get()!=0) {
            controller.selectedTour = tourDAO.read(controller.selectedTourId.get());
            if(controller.searchString!=null&&controller.searchString.get()!=null)
                controller.logs.setAll(controller.selectedTour.getLogs().stream().filter(x->x.getReport().contains(controller.searchString.get())).collect(Collectors.toList()));
            else
                controller.logs.setAll(controller.selectedTour.getLogs());
            controller.logsListView.setItems(controller.logs);
            controller.selectedLogId = null;
        }
        else
            controller.logs.clear();
    }

    public void register()
    {
        tourDAO.registerForNotification(this::loadLogs);
        tourLogDAO.registerForNotification(this::loadLogs);
        controller.searchString.addListener((x->loadLogs()));
    }

    public void setSelectedLogListener()
    {
        controller.logsListView.getSelectionModel().selectedItemProperty().addListener(((observableValue, old, newVal) ->
        {
            if(newVal!=null) {
                controller.selectedLogId =newVal.getId();
            }
        }));
    }

    public void addNewLog() {
        if (controller.selectedTourId.get()!=0) {
            controller.logCreateViewController = (TourLogCreateViewController) ViewManager.createView("createTourLog", new Stage(), "Create Log");
            controller.logCreateViewController.selectedTourId = controller.selectedTourId.get();
            controller.logCreateViewController.initSelectedTour();
        }

    }


    public void removeLog() {
        if(controller.selectedLogId !=null) {
            tourLogDAO.delete(controller.selectedLogId);
        }

    }

    public void editLog() {
        if (controller.selectedTourId.get()!=0&&controller.selectedLogId!=null&& controller.selectedLogId!=0) {
            controller.tourLogEditViewController = (TourLogEditViewController) ViewManager.createView("editTourLog", new Stage(), "Edit Log");
            controller.tourLogEditViewController.selectedTourLogId = controller.selectedLogId;
            controller.tourLogEditViewController.initSelectedTourLog();
        }
    }

    public void showLog() {
        if (controller.selectedTourId.get() != 0 && controller.selectedLogId != null && controller.selectedLogId != 0) {
            controller.tourLogDetailsViewController = (TourLogDetailsViewController) ViewManager.createView("detailsTourLog", new Stage(), "Show Log");
            controller.tourLogDetailsViewController.selectedTourLogId = controller.selectedLogId;
            controller.tourLogDetailsViewController.initSelectedTourLog();
        }
    }

    @Override
    public IViewController getController() {
        return controller;
    }
}
