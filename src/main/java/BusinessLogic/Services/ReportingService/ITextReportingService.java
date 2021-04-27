package BusinessLogic.Services.ReportingService;

import Models.Tour;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ITextReportingService implements IReportingService{
    private final Logger log = LogManager.getLogger("standardLogger");

    @Override
    public boolean generateReport(List<Tour> tours, String path) {

        AtomicBoolean success = new AtomicBoolean(true);

        try {
            // AGPL License! https://youtu.be/QHF3xcWnSD4
            // https://kb.itextpdf.com/home/it7kb/examples/itext-7-jump-start-tutorial-chapter-1
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(path+".pdf"));
            document.open();
            document.add(new Paragraph("Tours:"));
            tours.forEach(x->
            {
                try {
                    Paragraph tourParagraph= new Paragraph(x.getName()+": ");
                    tourParagraph.setIndentationLeft(10);

                    Paragraph tourFieldsParagraph= new Paragraph();
                    tourFieldsParagraph.add("- Description: "+x.getTourDescription());
                    tourFieldsParagraph.add("\n");
                    tourFieldsParagraph.add("- Distance: "+x.getTourDistance()+"km");

                    // Creating an ImageData object
                    Paragraph picParagraph= new Paragraph();
                    String imageFile =x.getRouteInformation();
                    Image  image = Image.getInstance(imageFile);
                    image.scaleToFit(100f, 200f);
                    picParagraph.add(image);

                    tourFieldsParagraph.add(picParagraph);

                    Paragraph logParagraph = new Paragraph("- Logs: ");
                    com.itextpdf.text.List logList= new com.itextpdf.text.List();
                    logList.setIndentationLeft(10);
                    x.getLogs().forEach(y->
                            logList.add(y.getReport()+" | "+y.getDistance()+"km"));

                    logParagraph.add(logList);
                    tourFieldsParagraph.add(logParagraph);
                    tourFieldsParagraph.add("\n");

                    tourParagraph.add(tourFieldsParagraph);
                    document.add(tourParagraph);
                } catch (DocumentException | IOException e) {
                    log.fatal(e.getMessage());
                    success.set(false);
                }
            });

            document.close();
        } catch (Exception e) {
            log.fatal(e.getMessage());
            success.set(false);
        }
        return success.get();
    }
}
