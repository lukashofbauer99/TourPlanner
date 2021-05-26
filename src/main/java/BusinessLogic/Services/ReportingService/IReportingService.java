package BusinessLogic.Services.ReportingService;

import Models.Tour;

import java.util.List;

public interface IReportingService {

    boolean generateMultiReport(List<Tour> tours , String path);
    boolean generateReport(Tour tour , String path);

}
