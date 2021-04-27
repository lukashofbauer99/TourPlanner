package BusinessLogic.Services.ReportingService;

import Models.Tour;

import java.util.List;

public interface IReportingService {

    boolean generateReport(List<Tour> tours , String path);

}
