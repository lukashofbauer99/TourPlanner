package BusinessLogic.Services.ReportingService;

import BusinessLogic.Services.ConfigService.ConfigService;
import Models.Tour;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ReportingServiceProvider {
    private final Logger log = LogManager.getLogger("standardLogger");

    private IReportingService reportingService;

    public static ReportingServiceProvider instance;

    private ReportingServiceProvider() {
        //load from config ?
        if(ConfigService.ReportServiceType.equals("IText")) {
            log.info("using in ITextReportingService");
            reportingService = new ITextReportingService();
        }
        else
        {
            log.fatal("Wrong Config");
        }

    }


    public static ReportingServiceProvider getInstance()
    {
        if(instance==null)
            instance=new ReportingServiceProvider();

        return instance;
    }

    public boolean generateReport(List<Tour> tours, String path) {
        return reportingService.generateReport(tours,path);
    }
}
