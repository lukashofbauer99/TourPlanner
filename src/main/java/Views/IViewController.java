package Views;

import ViewModels.IViewModel;
import javafx.fxml.Initializable;

public interface IViewController extends Initializable {

    IViewModel getViewModel();

}
