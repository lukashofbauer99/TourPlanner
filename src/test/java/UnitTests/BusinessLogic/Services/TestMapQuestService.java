package UnitTests.BusinessLogic.Services;

import BusinessLogic.Services.MapService.IMapPictureService;
import BusinessLogic.Services.MapService.MapQuestPictureService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestMapQuestService {

    String testFolderPath= "src/test/java/UnitTests/BusinessLogic/Services/TestFolder/";

    IMapPictureService mapPictureService = new MapQuestPictureService(testFolderPath);


    @AfterEach
    void ClearDir()
    {
        File outputfile = new File(testFolderPath);
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
        File f = new File(testFolderPath+"Vienna_Paris.jpg");


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
        File f = new File(testFolderPath+"Gars+am+Kamp_Vienna.jpg");


        //assert
        assertTrue(f.exists());
    }

}