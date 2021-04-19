package Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import javax.swing.text.View;
import java.io.IOException;

public class ViewManager {
    private static final Logger log=LogManager.getLogger("standardLogger");

    public static IViewController createView(String groupName, Stage stage, String viewTitle) {
        Parent root=null;
        FXMLLoader loader = new FXMLLoader();

        log.info("creating window "+ groupName+"View.fxml");
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
