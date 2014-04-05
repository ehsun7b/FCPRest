package com.ehsunbehravesh.fcpersepolisrest.ejb;

import com.ehsunbehravesh.persepolis.entity.Newspaper;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ehsun7b
 */
@Stateless
@LocalBean
public class NewspaperPDFBean {

  private static final Logger log = Logger.getLogger(NewspaperPDFBean.class.getName());

  @Inject
  private NewspaperPhotosBean photos;

  public File newspapersPDF() {
    try {
      File temp = new File(System.getProperty("java.io.tmpdir"));
      File pdfFile = new File(temp, System.nanoTime() + ".pdf");

      Document doc = new Document(PageSize.A4.rotate());
      PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(pdfFile));
      writer.setStrictImageSequence(true);
      
      doc.open();
      doc.addTitle("Sports Newspaper");
      doc.add(new Paragraph("Sports Newspapers of IRAN"));
      Anchor anchor = new Anchor("www.fcpersepolis.info");
      anchor.setReference("http://www.fcpersepolis.info");
      doc.add(anchor);
      //doc.add(new Paragraph("روزنامه های ورزشی ایران", new Font(Font.FontFamily.valueOf("tahoma"))));

      List<Newspaper> photoURLs = new ArrayList<>();//photos.getNewspapers();

      int i = 0;
      if (photoURLs.size() > 0) {
        for (Newspaper newspaper : photoURLs) {
          String title = newspaper.getTitle();
          String photoURL = newspaper.getPhotoURL();
          try {
            Image img = Image.getInstance(new URL(photoURL));
            doc.add(new Paragraph(title));
            doc.add(img);
            if (++i < photoURLs.size()) {
              doc.newPage();              
            }
          } catch (Exception ex) {
            log.log(Level.SEVERE, "Error in fetching photo for PDF of newspapers. {0}", ex);
          }
        }
      } else {
        doc.add(new Paragraph("Error! :( We are sorry. Please retry in seconds."));        
      }

      doc.close();     

      return pdfFile;
    } catch (BadElementException ex) {
      log.log(Level.SEVERE, "Error in generating PDF of newspapers. {0}", ex);
    } catch (IOException | DocumentException ex) {
      log.log(Level.SEVERE, "Error in generating PDF of newspapers. {0}", ex);
    }

    return null;
  }
}
