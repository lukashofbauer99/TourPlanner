package Views;

import ViewModels.Factory.ViewModelFactoryManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class ViewManager {

    ViewModelFactoryManager viewModelFactoryManager= new ViewModelFactoryManager();

    public void createView(String groupName, Stage stage,String viewTitle) throws IOException {
        Parent root;
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/"+ groupName+"View.fxml"));
        root = loader.load();
        IViewController controller = loader.getController();
        controller.init(viewModelFactoryManager.getFactory(groupName).createViewModel());
        stage.setTitle(viewTitle);
        stage.setScene(new Scene(root));
        stage.show();
    }

}
