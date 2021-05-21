package ViewModels;

import BusinessLogic.Services.Import_ExportService.Import_ExportServiceProvider;
import BusinessLogic.Services.ReportingService.ReportingServiceProvider;
import DataAccess.Repositories.DAOs.TourDAO;
import Views.IViewController;
import Views.MainViewController;
import Views.TourLogs.TourLogsViewController;
import Views.Tours.TourDescriptionViewController;
import Views.Tours.ToursOverviewViewController;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class MainViewModel implements IViewModel {

    public TourDAO tourDAO = TourDAO.getInstance();

    public ReportingServiceProvider reportingServiceProvider= ReportingServiceProvider.getInstance();
    public Import_ExportServiceProvider import_exportServiceProvider= Import_ExportServiceProvider.getInstance();

    MainViewController controller;

    public MainViewModel(MainViewController controller) {
        this.controller = controller;
    }

    public void generateReport() {
        //Creating a File chooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF", "*.pdf*"));
        //Adding action on the menu item
        File file = fileChooser.showSaveDialog(controller.searchInput.getScene().getWindow());

        if(file!=null) {
            if (!reportingServiceProvider.generateReport(tourDAO.getAll(), file.getPath())) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error creating Report", ButtonType.OK);
                alert.showAndWait();
            }
        }

    }

    public void exportData() {
        //Creating a File chooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON", "*.json*"));
        //Adding action on the menu item
        File file = fileChooser.showSaveDialog(controller.searchInput.getScene().getWindow());


        if(file!=null) {
            if (!import_exportServiceProvider.exportData(tourDAO.getAll(), file.getPath())) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error creating Export", ButtonType.OK);
                alert.showAndWait();
            }
        }

    }

    public void importData() {
        //Creating a File chooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON", "*.json*"));
        //Adding action on the menu item
        File file = fileChooser.showOpenDialog(controller.searchInput.getScene().getWindow());

        if(file!=null)
            import_exportServiceProvider.importData(file.getPath()).forEach(x->tourDAO.create(x));


    }



    @Override
    public IViewController getController() {
        return controller;
    }
}
