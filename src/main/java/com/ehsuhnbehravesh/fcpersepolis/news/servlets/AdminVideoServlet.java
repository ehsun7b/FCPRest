package com.ehsuhnbehravesh.fcpersepolis.news.servlets;

import com.ehsunbehravesh.fcpersepolisrest.ejb.VideoBean;
import com.ehsunbehravesh.persepolis.entity.Video;
import com.ehsunbehravesh.persepolis.entity.VideoCategory;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author ehsun7b
 */
@WebServlet(name = "AdminVideoServlet", urlPatterns = {"/admins/video"})
@MultipartConfig(location = "/root/video/image", fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class AdminVideoServlet extends AdminPage {

  private static final Logger log = Logger.getLogger(AdminVideoServlet.class.getName());

  @Inject
  private VideoBean videoBean;

  public AdminVideoServlet() {
    super("admin_video.jsp");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    super.doGet(req, resp);
    
    if (admin != null) {
      List<Video> videos = videoBean.readTop(10);
      List<VideoCategory> categories = videoBean.readCategories();
      setAttr("videos", videos);
      setAttr("categories", categories);
      showTemplate(req, resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    super.doPost(req, resp);
    if (admin != null) {

      String fileName = null;
      for (Part part : req.getParts()) {
        fileName = getFileName(part);
        if (fileName != null) {
          try {
            part.write(fileName);
            break;
          } catch (IOException ex) {
            log.log(Level.SEVERE, "Error in writing the video image. {0}", ex.getMessage());
          }
        }
      }
      
      if (fileName != null) {
        String title = getUTF8Parameter(req, "title");
        String embedCode = req.getParameter("embedCode");
        String strCategoryCode = req.getParameter("category");
        
        if ((title != null && embedCode != null && strCategoryCode != null) && 
                (title.length() > 0 && strCategoryCode.length() > 0 && embedCode.length() > 0)) {
          VideoCategory category = videoBean.findCategory(Long.parseLong(strCategoryCode));
          
          if (category != null) {
            Video video = new Video();
            video.setTitle(title);
            video.setEmbedCode(embedCode);
            video.setCategory(category);
            video.setImage(fileName);
            
            videoBean.save(video);
            setAttr("info", "Video saved successfully.");
          } else {
            setAttr("error", "Category not found!");
          }
        } else {
          setAttr("warning", "Please enter all fields.");
        }                
      } else {
        setAttr("warning", "Please choose a image.");
      }

      List<Video> videos = videoBean.readTop(10);
      List<VideoCategory> categories = videoBean.readCategories();
      setAttr("videos", videos);
      setAttr("categories", categories);
      showTemplate(req, resp);
    }
  }

  private String getFileName(Part part) {
    String contentDisp = part.getHeader("content-disposition");
    //log.log(Level.INFO, "content-disposition header={0}", contentDisp);
    String[] tokens = contentDisp.split(";");
    for (String token : tokens) {
      //System.out.println("token ***" + token);
      if (token.trim().startsWith("filename")) {
        return token.substring(token.indexOf("=") + 2, token.length() - 1);
      }
    }
    return null;
  }
}
