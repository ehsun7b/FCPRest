package com.ehsunbehravesh.fcpersepolisrest.rest;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author ehsun7b
 */
@Path("direction")
public class Direction {

  private static final Logger log = Logger.getLogger(Direction.class.getName());

  @GET
  @Produces("application/json; charset=utf-8")
  @Path("json/{latitude1}/{longitude1}/{latitude2}/{longitude2}/{sensor}")
  public DirectionResult json(@PathParam("latitude1") String latitude1, @PathParam("longitude1") String longitude1,
          @PathParam("latitude2") String latitude2, @PathParam("longitude2") String longitude2,
          @PathParam("sensor") String sensor) {
    DirectionResult result = new DirectionResult();
    result.setSuccess(Boolean.TRUE);
    try {
      /*String strUrl = MessageFormat.format("https://maps.googleapis.com/maps/api/directions/xml?sensor={4}&origin={0},{1}&destination={2},{3}&key={5}",
              latitude1, longitude1, latitude2, longitude2, sensor, "AIzaSyC3Wf4hJLRvAHsqLL_X6r42TWkuIFSlXjA");*/
      String strUrl = MessageFormat.format("http://maps.googleapis.com/maps/api/directions/xml?sensor={4}&origin={0},{1}&destination={2},{3}",
              latitude1, longitude1, latitude2, longitude2, sensor);

      URL url = new URL(strUrl);
      URLConnection urlConnection = url.openConnection();
      InputStream in = new BufferedInputStream(urlConnection.getInputStream());

      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(in);

      doc.normalizeDocument();

      Element documentElement = doc.getDocumentElement();
      Element status = (Element) documentElement.getElementsByTagName("status").item(0);

      if (status.getTextContent().trim().toLowerCase().equalsIgnoreCase("ok")) {
        Element route = (Element) documentElement.getElementsByTagName("route").item(0);
        Element leg = (Element) route.getElementsByTagName("leg").item(0);

        Element duration = null;
        Element distance = null;

        NodeList childNodes = leg.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); ++i) {
          Node node = childNodes.item(i);

          try {
            Element child = (Element) node;

            if (child.getTagName().toLowerCase().equals("distance")) {
              distance = child;
            }

            if (child.getTagName().toLowerCase().equals("duration")) {
              duration = child;
            }
          } catch (Exception ex) {
            
          }
        }

        if (distance != null) {
          Element valDistance = (Element) distance.getElementsByTagName("value").item(0);
          result.setMeters(Integer.parseInt(valDistance.getTextContent()));
        }

        if (duration != null) {
          Element valDuration = (Element) duration.getElementsByTagName("value").item(0);
          result.setSeconds(Integer.parseInt(valDuration.getTextContent()));
        }
        
      } else {
        result.setSuccess(Boolean.FALSE);
        Element error = (Element) documentElement.getElementsByTagName("error_message").item(0);
        result.setErrorMessage("Status is not OK." + error.getTextContent());
      }
    } catch (MalformedURLException ex) {
      log.log(Level.SEVERE, "Error: {0}", ex.getMessage());
      result.setSuccess(Boolean.FALSE);
      result.setErrorMessage(ex.getMessage());
    } catch (IOException | ParserConfigurationException | SAXException ex) {
      log.log(Level.SEVERE, "Error: {0}", ex.getMessage());
      result.setSuccess(Boolean.FALSE);
      result.setErrorMessage(ex.getMessage());
    }

    return result;
  }
}
