package Views.TourLogs;

import DataAccess.Repositories.DAOs.ITourDAO;
import DataAccess.Repositories.DAOs.ITourLogDAO;
import DataAccess.Repositories.DAOs.TourDAO;
import DataAccess.Repositories.DAOs.TourLogDAO;
import Models.Tour;
import Models.TourLog;
import ViewModels.IViewModel;
import ViewModels.TourLogs.TourLogsViewModel;
import Views.IViewController;
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
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.ResourceBundle;


@NoArgsConstructor
public class TourLogsViewController implements IViewController {

    TourLogsViewModel viewModel;

    public Long selectedLogId=0L;

    public Tour selectedTour;
    public LongProperty selectedTourId= new SimpleLongProperty();

    public StringProperty searchString= new SimpleStringProperty();

    public TourLogCreateViewController logCreateViewController;

    public TourLogEditViewController tourLogEditViewController;

    public TourLogDetailsViewController tourLogDetailsViewController;

    public ObservableList<TourLog> logs= FXCollections.observableArrayList();


    @FXML
    public ListView<TourLog> logsListView = new ListView<>();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        viewModel= new TourLogsViewModel(this);

        loadLogs();
        register();

        selectedTourId.addListener(x->
                loadLogs());

        setListViewCellFormat();
        setSelectedLogListener();

    }

    private void register()
    {
       viewModel.register();
    }

    private void loadLogs()
    {
        viewModel.loadLogs();
    }

    private void setListViewCellFormat()
    {
        logsListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(TourLog item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getId() == null)
                    setText(null);
                else {
                    setText(item.getDate() + " | " + item.getReport() + " | " + item.getDistance());
                }
            }
        });
    }

    private void setSelectedLogListener()
    {
        viewModel.setSelectedLogListener();
    }

    public void addNewLog(ActionEvent actionEvent) {
       viewModel.addNewLog();

    }


    public void removeLog(ActionEvent actionEvent) {
        viewModel.removeLog();

    }

    public void editLog(ActionEvent actionEvent) {
      viewModel.editLog();
    }

    public void showLog(ActionEvent actionEvent) {
        viewModel.showLog();
    }

    @Override
    public IViewModel getViewModel() {
        return viewModel;
    }
}
