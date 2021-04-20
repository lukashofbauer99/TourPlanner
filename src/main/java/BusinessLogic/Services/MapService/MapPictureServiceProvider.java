package BusinessLogic.Services.MapService;

import DataAccess.Repositories.Repositories.InMemory.InMemoryDatabase;
import DataAccess.Repositories.Repositories.InMemory.Repos.InMemoryTourRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MapPictureServiceProvider {
    private final Logger log = LogManager.getLogger("standardLogger");

    private IMapPictureService mapPictureService;

    public static MapPictureServiceProvider instance;

    private MapPictureServiceProvider() {
        //load from config ?
        log.info("using in MapQuestPictureService");
        mapPictureService = new MapQuestPictureService("MapReqTourPics/");
    }


    public static MapPictureServiceProvider getInstance()
    {
        if(instance==null)
            instance=new MapPictureServiceProvider();

        return instance;
    }

    public String getPathOfCreatedPicture(String start, String end) {
        return mapPictureService.getPathOfCreatedPicture(start,end);
    }
}
