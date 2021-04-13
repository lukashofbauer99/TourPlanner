package Views;

import DataAccess.Repositories.Repositories.InMemoryStringRepo;
import Models.Tour;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.ResourceBundle;


@Slf4j
@NoArgsConstructor
public class ToursOverviewViewController implements IViewController {

    InMemoryStringRepo inMemoryStringRepo= InMemoryStringRepo.getInstance();


    @FXML //inits Testfield
    public TextField text;

    @FXML
    public ListView<Tour> toursListView = new ListView<>();

    public ObservableList<Tour> tours;

    public ObjectProperty<Tour> selectedTour= new SimpleObjectProperty<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initListView()
    {

        toursListView.setItems(tours);

        toursListView.setCellFactory(param -> new ListCell<Tour>()
        {
            @Override
            protected void updateItem(Tour item,boolean empty)
            {
                super.updateItem(item,empty);

                if(empty|| item==null||item.getId()==null)
                    setText(null);
                else{
                    setText(item.getName());
                }
            }
        });

        toursListView.getSelectionModel().selectedItemProperty().addListener(((observableValue, old, newVal) ->
        {
            if(newVal!=null)
                selectedTour.setValue(newVal);
        }));
    }

    public void addNewLog(ActionEvent actionEvent) {
    }

    public void removeLog(ActionEvent actionEvent) {
        inMemoryStringRepo.inputText.setValue("");
        inMemoryStringRepo.outputText.setValue("");
    }

    public void addNewTour(ActionEvent actionEvent) {
    }

    public void removeTour(ActionEvent actionEvent) {
    }

}
