package BusinessLogic.Services.MapService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MockMapPictureService implements IMapPictureService {

    private final Logger log=LogManager.getLogger("standardLogger");;

    @Override
    public String getPathOfCreatedPicture(String start, String end) {
        log.info("returning path to sample picture");
        return "MapReqTourPics/Download.jpeg";
    }
}
