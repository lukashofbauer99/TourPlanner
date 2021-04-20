package BusinessLogic.Services.MapService;

import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


@NoArgsConstructor
public class MapQuestPictureService implements IMapPictureService {

    private final Logger log=LogManager.getLogger("standardLogger");;

    private String basePath="";

    public MapQuestPictureService(String basePath) {
        this.basePath = basePath;
    }

    @Override
    public String getPathOfCreatedPicture(String start, String end){
        log.info("returning path from "+ start +" to "+ end+" map");

        start =start.replace(" ", "+");
        end =end.replace(" ", "+");

        BufferedImage image = null;
        URL url = null;
        try {
            url = new URL("https://www.mapquestapi.com/staticmap/v5/map?start="+ start +"&end="+ end +"&size=600,400@2x&key=Q3TcvRVGJDgysIQpXo2pi40E9hvpg2Gp");
        } catch (MalformedURLException e) {
            log.fatal("Fatal Error with URL");
        }
        try {
            image = ImageIO.read(url);
            File outputfile = new File(basePath+start+"_"+end+".jpg");
            ImageIO.write(image, "jpg", outputfile);
        } catch (IOException e) {

            log.fatal("Fatal Error with Request on URL");
        }
        return basePath+start+"_"+end+".jpg";
    }
}
