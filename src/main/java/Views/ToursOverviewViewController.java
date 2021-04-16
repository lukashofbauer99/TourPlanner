package Views;

import DataAccess.Repositories.DAOs.ITourDAO;
import DataAccess.Repositories.DAOs.TourDAO;
import Models.Tour;
import javafx.beans.property.*;
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
import java.util.stream.Collectors;


@Slf4j
@NoArgsConstructor
public class ToursOverviewViewController implements IViewController {

    ITourDAO tourDAO = TourDAO.getInstance();

    public ObservableList<Tour> tours= FXCollections.observableArrayList();

    public LongProperty selectedTourId= new SimpleLongProperty();

    public TourCreateViewController tourCreateViewController;

    public TourEditViewController tourEditViewController;

    public StringProperty searchString= new SimpleStringProperty();


    @FXML
    public ListView<Tour> toursListView = new ListView<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initListView();
    }

    public void initListView()
    {
        loadTours();

        setListViewCellFormat();
        setSelectedTourListener();
    }

    private void loadTours()
    {
        tourDAO.registerForNotification(()->
        {
            if(searchString!=null&&searchString.get()!=null)
                tours.setAll(tourDAO.getAll().stream().filter(x->x.getName().contains(searchString.get())).collect(Collectors.toList()));
            else
                tours.setAll(tourDAO.getAll());
        });

        searchString.addListener(x->{
            if(searchString!=null&&searchString.get()!=null)
                tours.setAll(tourDAO.getAll().stream().filter(y->y.getName().contains(searchString.get())).collect(Collectors.toList()));
            else
                tours.setAll(tourDAO.getAll());
        });

        tours.setAll(tourDAO.getAll());
        toursListView.setItems(tours);
    }

    private void setListViewCellFormat()
    {

        toursListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Tour item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getId() == null)
                    setText(null);
                else {
                    setText(item.getName());
                }
            }
        });
    }

    private void setSelectedTourListener()
    {

        toursListView.getSelectionModel().selectedItemProperty().addListener(((observableValue, old, newVal) ->
        {
            if(newVal!=null) {
                selectedTourId.set(newVal.getId());
            }
        }));
    }

    public void addNewTour(ActionEvent actionEvent) {
        tourCreateViewController=(TourCreateViewController) ViewManager.createView("createTour",new Stage(),"Create Tour");
    }

    public void removeTour(ActionEvent actionEvent) {
        tourDAO.delete(selectedTourId.get());
        selectedTourId.setValue(0);
    }

    public void editTour(ActionEvent actionEvent) {
        if (selectedTourId.get()!=0) {
            tourEditViewController = (TourEditViewController) ViewManager.createView("editTour", new Stage(), "Edit Tour");
            tourEditViewController.selectedTourId = selectedTourId.get();
            tourEditViewController.initSelectedTour();
        }
    }

}
