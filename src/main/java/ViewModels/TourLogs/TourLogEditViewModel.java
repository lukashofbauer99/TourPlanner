package ViewModels.TourLogs;

import DataAccess.Repositories.DAOs.ITourLogDAO;
import DataAccess.Repositories.DAOs.TourLogDAO;
import Models.TourLog;
import ViewModels.IViewModel;
import Views.IViewController;
import Views.TourLogs.TourLogEditViewController;
import Views.Tours.TourEditViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;


@NoArgsConstructor
public class TourLogEditViewModel implements IViewModel {

    ITourLogDAO tourLogDAO = TourLogDAO.getInstance();

    TourLogEditViewController controller;

    public TourLogEditViewModel(TourLogEditViewController controller) {
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

    public void saveLog() {
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

        controller.selectedTourLog.setReport(controller.report.getText());
        controller.selectedTourLog.setDistance(distanceDouble);
        controller.selectedTourLog.setTotalTime(totalTimeDouble);
        controller.selectedTourLog.setRating(ratingInt);
        controller.selectedTourLog.setAverageSpeed(averageSpeedDouble);
        controller.selectedTourLog.setTypeOfTransport(controller.typeOfTransport.getText());
        controller.selectedTourLog.setDifficulty(difficultyInt);
        controller.selectedTourLog.setRecommendedPeopleCount(recommendedPeopleCountInt);
        controller.selectedTourLog.setToiletOnThePath(controller.toiletOnThePath.isSelected());

        tourLogDAO.update(controller.selectedTourLog);

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
