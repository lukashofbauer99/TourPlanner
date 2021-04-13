import Views.ViewManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    ViewManager viewManager = new ViewManager();

    @Override
    public void start(Stage primaryStage) throws Exception{
        ViewManager.createView("main",primaryStage,"Main Window");
    }


    public static void main(String[] args){
        launch(args);
    }
}
