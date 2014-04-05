package com.ehsunbehravesh.fcpersepolisrest.rest;

import com.ehsunbehravesh.fcpersepolisrest.ejb.NewspaperPDFBean;
import com.ehsunbehravesh.fcpersepolisrest.ejb.NewspaperPhotosBean;
import com.ehsunbehravesh.fcpersepolisrest.ejb.NewspaperSetBean;
import com.ehsunbehravesh.persepolis.entity.Newspaper;
import com.ehsunbehravesh.persepolis.entity.NewspaperSet;
import com.google.gson.Gson;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
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

  @Inject
  private NewspaperSetBean nsBean;

  @Path("pdf")
  @GET
  @Produces("application/pdf")
  public Response getPDF() {
    File pdfFile = pdf.newspapersPDF();
    ResponseBuilder response = Response.ok((Object) pdfFile);
    response.header("Content-Disposition", "attachment; filename=sports-newspaper.pdf");
    return response.build();
  }

  @Path("photos")
  @GET
  @Produces("application/json; charset=UTF-8")
  public String photos() {
    NewspaperSet set = nsBean.findLast();
    if (set != null) {
      List<Newspaper> photoURLs = set.getNewspapers();
      List<String> result = new ArrayList<>();

      for (Newspaper n : photoURLs) {
        String photoURL = n.getPhotoURL();
        if (photoURL != null && photoURL.length() > 0) {
          result.add(photoURL);
        }
      }

      Gson gson = new Gson();
      return gson.toJson(result);
    } else {
      return "[]";
    }
  }
}
