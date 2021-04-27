package UnitTests.BusinessLogic.Services;

import BusinessLogic.Services.Import_ExportService.IImport_ExportService;
import BusinessLogic.Services.Import_ExportService.JSONImport_ExportService;
import BusinessLogic.Services.ReportingService.IReportingService;
import BusinessLogic.Services.ReportingService.ITextReportingService;
import Models.Tour;
import Models.TourLog;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestJSONImport_ExportService {

    String testFolderPath= "src/test/java/UnitTests/BusinessLogic/Services/TestFolder/";

    IImport_ExportService import_exportService = new JSONImport_ExportService();


    @AfterEach
    void clearDir()
    {
        File outputfile = new File(testFolderPath);
        for(File file: outputfile.listFiles())
            if (!file.isDirectory())
                file.delete();
    }

    @Test
    void exportDataAsJSONWithoutLogs() {
        //arrange
        List<Tour> tourList = new ArrayList<>();

        tourList.add(new Tour("Tour1","TourDesc",null,22));
        tourList.add(new Tour("Tour2","TourDesc",null,1));
        tourList.add(new Tour("Tour3","TourDesc",null,21));

        boolean worked;

        //act

        worked = import_exportService.exportData(tourList,testFolderPath+"Report");

        File f = new File(testFolderPath+"Report.json");


        //assert
        assertTrue(worked);
        assertTrue(f.exists());
    }

    @Test
    void importDataAsJSONWithoutLogs() {
        //arrange
        List<Tour> tourList = new ArrayList<>();

        tourList.add(new Tour("Tour1","TourDesc",null,22));
        tourList.add(new Tour("Tour2","TourDesc",null,1));
        tourList.add(new Tour("Tour3","TourDesc",null,21));

        boolean worked;

        //act

        worked = import_exportService.exportData(tourList,testFolderPath+"Report");
        File f = new File(testFolderPath+"Report.json");
        List<Tour> tourListImported = import_exportService.importData(testFolderPath+"Report.json");




        //assert
        assertEquals(tourListImported.size(), tourList.size());
        assertTrue(worked);
        assertTrue(f.exists());
    }

    @Test
    void exportDataAsJSONWithLogs() {
        //arrange
        List<Tour> tourList = new ArrayList<>();

        Tour tour1 = new Tour("Tour1","TourDesc",null,22);
        tour1.getLogs().add(new TourLog(new Date(), "report", 1D , 1D, 2));
        tourList.add(tour1);

        Tour tour2=new Tour("Tour2","TourDesc",null,1);
        tour1.getLogs().add(new TourLog(new Date(), "report1", 1.2D , 1.2D, 2));
        tour1.getLogs().add(new TourLog(new Date(), "report2", 2.12D , 10D, 2));
        tourList.add(tour2);


        tourList.add(new Tour("Tour3","TourDesc",null,21));
        boolean worked;

        //act

        worked = import_exportService.exportData(tourList,testFolderPath+"Report");

        File f = new File(testFolderPath+"Report.json");


        //assert
        assertTrue(worked);
        assertTrue(f.exists());
    }

    @Test
    void importDataAsJSONWithLogs() {
        //arrange
        List<Tour> tourList = new ArrayList<>();

        Tour tour1 = new Tour("Tour1","TourDesc",null,22);
        tour1.getLogs().add(new TourLog(new Date(), "report", 1D , 1D, 2));
        tourList.add(tour1);

        Tour tour2=new Tour("Tour2","TourDesc",null,1);
        tour1.getLogs().add(new TourLog(new Date(), "report1", 1.2D , 1.2D, 2));
        tour1.getLogs().add(new TourLog(new Date(), "report2", 2.12D , 10D, 2));
        tourList.add(tour2);


        tourList.add(new Tour("Tour3","TourDesc",null,21));
        boolean worked;

        //act

        worked = import_exportService.exportData(tourList,testFolderPath+"Report");
        File f = new File(testFolderPath+"Report.json");
        List<Tour> tourListImported = import_exportService.importData(testFolderPath+"Report.json");




        //assert
        assertEquals(tourListImported.size(), tourList.size());
        assertTrue(worked);
        assertTrue(f.exists());
    }


}
