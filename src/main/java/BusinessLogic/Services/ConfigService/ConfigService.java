package BusinessLogic.Services.ConfigService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigService {
    private static final Logger log = LogManager.getLogger("standardLogger");

    public static String RepoType;
    public static String ConnectionString;
    public static String DBUser;
    public static String DBPassword;

    public static String MapPictureServiceType;
    public static String MapPictureFolderPath;

    public static String ReportServiceType;

    public static String Export_ImportServiceType;

    public static void load() {
        try {
            Properties appSettings = new Properties();
            FileInputStream fis = new FileInputStream("src/main/java/BusinessLogic/Services/ConfigService/config.properties"); //put config properties file to buffer
            appSettings.load(fis);
            RepoType = (String) appSettings.get("RepoType");
            ConnectionString = (String) appSettings.get("ConnectionString");
            DBUser = (String) appSettings.get("DBUser");
            DBPassword = (String) appSettings.get("DBPassword");

            MapPictureServiceType = (String) appSettings.get("MapPictureServiceType");
            MapPictureFolderPath = (String) appSettings.get("MapPictureFolderPath");

            ReportServiceType = (String) appSettings.get("ReportServiceType");

            Export_ImportServiceType = (String) appSettings.get("Export_ImportServiceType");

            fis.close();
        } catch (IOException e) {
            log.fatal("Could not load settings file.");
            log.fatal(e.getMessage());
        }
    }
}