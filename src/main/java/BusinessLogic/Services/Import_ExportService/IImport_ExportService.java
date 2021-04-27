package BusinessLogic.Services.Import_ExportService;

import Models.Tour;

import java.util.List;

public interface IImport_ExportService {

    boolean exportData(List<Tour> tours, String path);
    List<Tour >importData(String path);

}
