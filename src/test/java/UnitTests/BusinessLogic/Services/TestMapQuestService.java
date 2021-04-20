package UnitTests.BusinessLogic.Services;

import BusinessLogic.Services.MapService.IMapPictureService;
import BusinessLogic.Services.MapService.MapQuestPictureService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestMapQuestService {

    IMapPictureService mapPictureService = new MapQuestPictureService("testMapReqTourPics/");


    @AfterEach
    void ClearDir()
    {
        File outputfile = new File("testMapReqTourPics/");
        for(File file: outputfile.listFiles())
            if (!file.isDirectory())
                file.delete();
    }

    @Test
    void getRoutePicture() {
        //arrange
        String start = "Vienna";
        String end = "Paris";

        //act
        mapPictureService.getPathOfCreatedPicture(start,end);
        File f = new File("testMapReqTourPics/Vienna_Paris.jpg");


        //assert
        assertTrue(f.exists());
    }

    @Test
    void getRoutePictureSpace() {
        //arrange
        String start = "Gars am Kamp";
        String end = "Vienna";

        //act
        mapPictureService.getPathOfCreatedPicture(start,end);
        File f = new File("testMapReqTourPics/Gars+am+Kamp_Vienna.jpg");


        //assert
        assertTrue(f.exists());
    }

}