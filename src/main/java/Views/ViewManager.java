package Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class ViewManager {


    public static IViewController createView(String groupName, Stage stage,String viewTitle) {
        Parent root=null;
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(ViewManager.class.getResource("/"+ groupName+"View.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        IViewController controller = loader.getController();
        stage.setTitle(viewTitle);
        stage.setScene(new Scene(root));
        stage.show();
        return controller;
    }


}
