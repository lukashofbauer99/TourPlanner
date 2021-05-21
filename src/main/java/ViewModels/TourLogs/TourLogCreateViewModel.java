package ViewModels.TourLogs;

import DataAccess.Repositories.DAOs.ITourDAO;
import DataAccess.Repositories.DAOs.ITourLogDAO;
import DataAccess.Repositories.DAOs.TourDAO;
import DataAccess.Repositories.DAOs.TourLogDAO;
import Models.Tour;
import Models.TourLog;
import ViewModels.IViewModel;
import Views.IViewController;
import Views.TourLogs.TourLogCreateViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;


@NoArgsConstructor
public class TourLogCreateViewModel implements IViewModel {

    ITourLogDAO tourLogDAO = TourLogDAO.getInstance();
    ITourDAO tourDAO = TourDAO.getInstance();

    TourLogCreateViewController controller;

    public TourLogCreateViewModel(TourLogCreateViewController controller) {
        this.controller = controller;
    }

    public void initSelectedTour()
    {
        controller.selectedTour= tourDAO.read(controller.selectedTourId);

    }

    public void createLog() {
        double distanceDouble=0;
        if(!controller.distance.getText().equals(""))
            distanceDouble = parseDouble(controller.distance.getText());

        double totalTimeDouble=0;
        if(!controller.totalTime.getText().equals(""))
            totalTimeDouble = parseDouble(controller.totalTime.getText());
        double averageSpeedDouble=0;
        if(!controller.averageSpeed.getText().equals(""))
            averageSpeedDouble = parseDouble(controller.averageSpeed.getText());

        int ratingInt=1;
        if(!controller.rating.getText().equals(""))
            ratingInt= parseInt(controller.rating.getText());

        int difficultyInt=1;
        if(!controller.difficulty.getText().equals(""))
            difficultyInt= parseInt(controller.difficulty.getText());

        int recommendedPeopleCountInt=1;
        if(!controller.recommendedPeopleCount.getText().equals(""))
            recommendedPeopleCountInt= parseInt(controller.recommendedPeopleCount.getText());

        TourLog tourLog = new TourLog(new Date(),controller.report.getText(),distanceDouble,totalTimeDouble,ratingInt, averageSpeedDouble
                ,controller.typeOfTransport.getText(),difficultyInt, recommendedPeopleCountInt, controller.toiletOnThePath.isSelected());
        tourLog.setTourId(controller.selectedTour.getId());
        tourLog.setId(tourLogDAO.create(tourLog));

        //selectedTour.getLogs().add(tourLog);
        //tourDAO.update(selectedTour);

        Stage stage = (Stage) controller.report.getScene().getWindow();
        stage.close();

    }

    public void cancelCreation() {
        Stage stage = (Stage) controller.report.getScene().getWindow();
        stage.close();
    }

    @Override
    public IViewController getController() {
        return controller;
    }
}
