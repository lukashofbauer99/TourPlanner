package Views.Tours;

import DataAccess.Repositories.DAOs.ITourDAO;
import DataAccess.Repositories.DAOs.TourDAO;
import Models.Tour;
import ViewModels.IViewModel;
import ViewModels.Tours.ToursOverviewViewModel;
import Views.IViewController;
import Views.ViewManager;
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


@NoArgsConstructor
public class ToursOverviewViewController implements IViewController {


    ToursOverviewViewModel viewModel;

    public ObservableList<Tour> tours= FXCollections.observableArrayList();

    public LongProperty selectedTourId= new SimpleLongProperty();

    public TourCreateViewController tourCreateViewController;

    public TourEditViewController tourEditViewController;

    public StringProperty searchString= new SimpleStringProperty();


    @FXML
    public ListView<Tour> toursListView = new ListView<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        viewModel= new ToursOverviewViewModel(this);

        setListViewCellFormat();
        initListView();
    }

    public void initListView()
    {
        viewModel.initListView();
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

    public void addNewTour(ActionEvent actionEvent) {
        viewModel.addNewTour();
    }

    public void removeTour(ActionEvent actionEvent) {
        viewModel.removeTour();
    }

    public void editTour(ActionEvent actionEvent) {
       viewModel.editTour();
    }

    @Override
    public IViewModel getViewModel() {
        return viewModel;
    }
}
