package BusinessLogic.Services.ReportingService;

import Models.Tour;

import java.util.List;

public interface IReportingService {

    void generateReport(List<Tour> tours , String path);

}
