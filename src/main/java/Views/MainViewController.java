package Views;

import ViewModels.IViewModel;
import ViewModels.MainViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class MainViewController implements IViewController {


    @Getter
    @Setter
    private IViewModel viewModel;

    @FXML
    public TextField searchInput= new TextField();

    @FXML
    public Label searchOutput = new Label();


    public void init(IViewModel viewModel)
    {
        this.viewModel=viewModel;
        searchInput.textProperty().bindBidirectional(((MainViewModel)viewModel).getInputText());
        searchOutput.textProperty().bindBidirectional(((MainViewModel)viewModel).getOutputText());
    }

    public void onKeyReleased(KeyEvent keyEvent) {
        ((MainViewModel)viewModel).updateOutputText();
    }

    public void ClearSearchField(ActionEvent actionEvent) {
        ((MainViewModel)viewModel).clearFields();
    }
}
