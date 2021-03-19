package Models.Services;

public class TourService {

    private static final String BASE_URL = "http://www.mapquestapi.com/search/v3/prediction?key="
            + System.getenv("apiKey") +
            "&limit=15&collection=adminArea,poi,address,category" +
            "&location=16.372845,48.208339";



}
