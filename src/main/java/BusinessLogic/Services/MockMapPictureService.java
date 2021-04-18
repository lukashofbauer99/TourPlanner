package BusinessLogic.Services;

public class MockMapPictureService implements IMapPictureService {
    @Override
    public String getPathOfCreatedPicture(String start, String end) {
        return "MapReqTourPics/Download.jpeg";
    }
}
