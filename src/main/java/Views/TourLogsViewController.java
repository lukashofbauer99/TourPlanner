package Views;

import DataAccess.Repositories.Repositories.InMemoryStringRepo;
import Models.Tour;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.ResourceBundle;


@Slf4j
@NoArgsConstructor
public class TourLogsViewController implements IViewController {

    InMemoryStringRepo inMemoryStringRepo= InMemoryStringRepo.getInstance();


    public ObjectProperty<Tour> selectedTour= new SimpleObjectProperty<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void addNewLog(ActionEvent actionEvent) {
    }

    public void removeLog(ActionEvent actionEvent) {
        inMemoryStringRepo.inputText.setValue("");
        inMemoryStringRepo.outputText.setValue("");
    }

}
