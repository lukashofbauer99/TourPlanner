package Views;

import DataAccess.Repositories.DAOs.ITourDAO;
import DataAccess.Repositories.DAOs.ITourLogDAO;
import DataAccess.Repositories.DAOs.TourDAO;
import DataAccess.Repositories.DAOs.TourLogDAO;
import DataAccess.Repositories.Repositories.InMemory.Repos.InMemoryStringRepo;
import Models.Tour;
import Models.TourLog;
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


@Slf4j
@NoArgsConstructor
public class TourLogsViewController implements IViewController {

    ITourDAO tourDAO = TourDAO.getInstance();
    ITourLogDAO tourLogDAO = TourLogDAO.getInstance();

    private Long selectedLogId;

    private Tour selectedTour;
    public LongProperty selectedTourId= new SimpleLongProperty();

    public StringProperty searchString= new SimpleStringProperty();

    public TourLogCreateViewController logCreateViewController;

    public TourLogEditViewController tourLogEditViewController;

    public ObservableList<TourLog> logs= FXCollections.observableArrayList();


    @FXML
    public ListView<TourLog> logsListView = new ListView<>();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadLogs();

        tourDAO.registerForNotification(()->
        {
            loadLogs();
        });

        selectedTourId.addListener(x->
        {
            loadLogs();
        });

        setListViewCellFormat();
        setSelectedLogListener();

    }

    private void loadLogs()
    {
        if (selectedTourId.get()!=0) {
            selectedTour = tourDAO.read(selectedTourId.get());
            logs.setAll(selectedTour.getLogs());
            logsListView.setItems(logs);
            selectedLogId =null;
        }
    }

    private void setListViewCellFormat()
    {
        logsListView.setCellFactory(param -> new ListCell<TourLog>()
        {
            @Override
            protected void updateItem(TourLog item,boolean empty)
            {
                super.updateItem(item,empty);

                if(empty|| item==null||item.getId()==null)
                    setText(null);
                else{
                    setText(item.getDate()+" | "+ item.getReport()+" | "+ item.getDistance());
                }
            }
        });
    }

    private void setSelectedLogListener()
    {
        logsListView.getSelectionModel().selectedItemProperty().addListener(((observableValue, old, newVal) ->
        {
            if(newVal!=null) {
                selectedLogId =newVal.getId();
            }
        }));
    }

    public void addNewLog(ActionEvent actionEvent) {
        if (selectedTourId.get()!=0) {
            logCreateViewController = (TourLogCreateViewController) ViewManager.createView("createTourLog", new Stage(), "Create Log");
            logCreateViewController.selectedTourId = selectedTourId.get();
            logCreateViewController.initSelectedTour();
        }

    }


    public void removeLog(ActionEvent actionEvent) {
        if(selectedLogId !=null) {
            tourLogDAO.delete(selectedLogId);
        }

    }

    public void editLog(ActionEvent actionEvent) {
        if (selectedTourId.get()!=0&& selectedLogId!=0) {
            tourLogEditViewController = (TourLogEditViewController) ViewManager.createView("editTourLog", new Stage(), "Edit Log");
            tourLogEditViewController.selectedTourLogId = selectedLogId;
            tourLogEditViewController.initSelectedTourLog();
        }
    }
}
