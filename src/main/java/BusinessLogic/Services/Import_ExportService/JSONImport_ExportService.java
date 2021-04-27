package BusinessLogic.Services.Import_ExportService;

import Models.Tour;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class JSONImport_ExportService implements IImport_ExportService {
    private final Logger log = LogManager.getLogger("standardLogger");

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean exportData(List<Tour> tours, String path) {

        boolean success= true;

        try {
            Files.writeString(Path.of(path+".json"),
                    mapper.writerFor(new TypeReference<List<Tour>>() {})
                    .with(new DefaultPrettyPrinter())
                    .writeValueAsString(tours),
                    StandardCharsets.UTF_8);
        } catch (IOException e) {

            log.fatal(e.getMessage());
            success=false;
        }
        return success;
    }

    @Override
    public List<Tour> importData(String path) {
        try {

            return mapper.readValue(Files.readString(Path.of(path)), new TypeReference<>() {
            });
        } catch (IOException e) {
            log.fatal(e.getMessage());
        }
        return null;
    }
}
