package BusinessLogic.Services.Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    public static String RepoType;
    public static String MapPictureServiceType;

    public static void load() {
        try {
            Properties appSettings = new Properties();
            FileInputStream fis = new FileInputStream("src/main/java/BusinessLogic/Services/Config/config.properties"); //put config properties file to buffer
            appSettings.load(fis); //load config.properties file

            //This is where you add your config variables:
            RepoType = (String) appSettings.get("RepoType");
            MapPictureServiceType = (String) appSettings.get("MapPictureServiceType");

            fis.close();
        } catch (IOException e) {
            System.out.println("Could not load settings file.");
            System.out.println(e.getMessage());
        }
    }
}