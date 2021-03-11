package Views;

import ViewModels.IViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@NoArgsConstructor
public class SideViewController implements IViewController {

    @Getter
    @Setter
    private IViewModel viewModel;

    @FXML //inits Testfield
    public TextField text;


}
