package BusinessLogic.Services.MapService;

import BusinessLogic.Services.Config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MapPictureServiceProvider {
    private final Logger log = LogManager.getLogger("standardLogger");

    private IMapPictureService mapPictureService;

    public static MapPictureServiceProvider instance;

    private MapPictureServiceProvider() {
        //load from config
        if(Config.MapPictureServiceType.equals("MapQuest")) {
            log.info("using in MapQuestPictureService");
            mapPictureService = new MapQuestPictureService(Config.MapPictureFolderPath);
        }
        else if(Config.MapPictureServiceType.equals("Mock"))
        {
            log.info("using in Mock");
            mapPictureService = new MockMapPictureService();
        }
        else
        {
            log.fatal("Wrong Config");
        }

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
