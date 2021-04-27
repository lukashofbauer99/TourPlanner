package BusinessLogic.Services.Import_ExportService;

import BusinessLogic.Services.ConfigService.ConfigService;
import Models.Tour;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Import_ExportServiceProvider {
    private final Logger log = LogManager.getLogger("standardLogger");

    private IImport_ExportService import_exportService;

    public static Import_ExportServiceProvider instance;

    private Import_ExportServiceProvider() {
        //load from config
        if(ConfigService.Export_ImportServiceType.equals("JSON")) {
            log.info("using in JSON format");
            import_exportService = new JSONImport_ExportService();
        }
        else
        {
            log.fatal("Wrong Config");
        }

    }


    public static Import_ExportServiceProvider getInstance()
    {
        if(instance==null)
            instance=new Import_ExportServiceProvider();

        return instance;
    }

    public boolean exportData(List<Tour> tours, String path) {
        return import_exportService.exportData(tours,path);
    }

    public List<Tour> importData(String path) {
        return import_exportService.importData(path);
    }
}
