import BusinessLogic.Services.Config.Config;
import Views.ViewManager;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.logging.log4j.core.config.Configurator;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Config.load();
        Configurator.initialize(null, "log4j.conf.xml");
        ViewManager.createView("main", primaryStage, "Main Window");
    }


    public static void main(String[] args){
        launch(args);
    }
}
