package BusinessLogic.Services.ReportingService;

import Models.Tour;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.TextField;
import com.sun.scenario.effect.ImageData;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class ITextReportingService implements IReportingService{
    @Override
    public void generateReport(List<Tour> tours, String path) {

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
                    e.printStackTrace();
                }
            });

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
