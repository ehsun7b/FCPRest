package com.ehsunbehravesh.fcpersepolisrest.rest;

import com.ehsunbehravesh.fcpersepolisrest.ejb.NewspaperPDFBean;
import com.ehsunbehravesh.fcpersepolisrest.ejb.NewspaperPhotosBean;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

/**
 *
 * @author ehsun7b
 */
@Stateless
@Path("newspaper")
public class NewspaperPDF {
  
  private static final Logger log = Logger.getLogger(NewspaperPDF.class.getName());

  @Inject
  private NewspaperPDFBean pdf;
  
  @Inject 
  private NewspaperPhotosBean photos;
  
  @Path("pdf")
  @GET
  @Produces("application/pdf")
  public Response getPDF() {
    File pdfFile = pdf.newspapersPDF();        
    ResponseBuilder response = Response.ok((Object) pdfFile);
    response.header("Content-Disposition", "attachment; filename=sports-newspaper.pdf");
    return response.build();    
  }
  
  @Path("randomphoto")
  @GET
  @Produces("text/plain")
  public String randomPhoto() {
    return photos.randomPhoto();
  }
}
