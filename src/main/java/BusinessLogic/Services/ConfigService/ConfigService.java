package BusinessLogic.Services.ConfigService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigService {
    public static String RepoType;
    public static String MapPictureServiceType;
    public static String ReportServiceType;
    public static String MapPictureFolderPath;
    public static String Export_ImportServiceType;

    public static void load() {
        try {
            Properties appSettings = new Properties();
            FileInputStream fis = new FileInputStream("src/main/java/BusinessLogic/Services/ConfigService/config.properties"); //put config properties file to buffer
            appSettings.load(fis); //load config.properties file

            //This is where you add your config variables:
            RepoType = (String) appSettings.get("RepoType");
            MapPictureServiceType = (String) appSettings.get("MapPictureServiceType");
            ReportServiceType = (String) appSettings.get("ReportServiceType");
            MapPictureFolderPath = (String) appSettings.get("MapPictureFolderPath");
            Export_ImportServiceType = (String) appSettings.get("Export_ImportServiceType");

            fis.close();
        } catch (IOException e) {
            System.out.println("Could not load settings file.");
            System.out.println(e.getMessage());
        }
    }
}