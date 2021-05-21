package ViewModels.TourLogs;

import DataAccess.Repositories.DAOs.ITourLogDAO;
import DataAccess.Repositories.DAOs.TourLogDAO;
import Models.TourLog;
import ViewModels.IViewModel;
import Views.IViewController;
import Views.TourLogs.TourLogDetailsViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.util.ResourceBundle;


@NoArgsConstructor
public class TourLogDetailsViewModel implements IViewModel {

    ITourLogDAO tourLogDAO = TourLogDAO.getInstance();

    TourLogDetailsViewController controller;

    public TourLogDetailsViewModel(TourLogDetailsViewController controller) {
        this.controller = controller;
    }

    public void initSelectedTourLog()
    {
        if (controller.selectedTourLogId!=0) {
            controller.selectedTourLog = tourLogDAO.read(controller.selectedTourLogId);
            controller.report.setText(controller.selectedTourLog.getReport());
            controller.totalTime.setText(String.valueOf(controller.selectedTourLog.getTotalTime()));
            controller.distance.setText(String.valueOf(controller.selectedTourLog.getDistance()));
            controller.rating.setText(String.valueOf(controller.selectedTourLog.getRating()));
            controller.averageSpeed.setText(String.valueOf(controller.selectedTourLog.getAverageSpeed()));
            controller.typeOfTransport.setText(String.valueOf(controller.selectedTourLog.getTypeOfTransport()));
            controller.difficulty.setText(String.valueOf(controller.selectedTourLog.getDifficulty()));
            controller.recommendedPeopleCount.setText(String.valueOf(controller.selectedTourLog.getRecommendedPeopleCount()));
            controller.toiletOnThePath.setSelected(controller.selectedTourLog.getToiletOnThePath());
        }


    }

    @Override
    public IViewController getController() {
        return controller;
    }
}
