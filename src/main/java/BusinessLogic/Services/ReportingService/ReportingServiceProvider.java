package BusinessLogic.Services.ReportingService;

import BusinessLogic.Services.Config.Config;
import BusinessLogic.Services.MapService.IMapPictureService;
import BusinessLogic.Services.MapService.MapQuestPictureService;
import BusinessLogic.Services.MapService.MockMapPictureService;
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
        if(Config.ReportServiceType.equals("IText")) {
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

    public void generateReport(List<Tour> tours, String path) {
        reportingService.generateReport(tours,path);
    }
}
