package com.ehsunbehravesh.fcpersepolisrest.cdi;

import com.ehsunbehravesh.fcpersepolisrest.ejb.VideoBean;
import com.ehsunbehravesh.persepolis.entity.Video;
import com.ehsunbehravesh.persepolis.entity.VideoCategory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

/**
 *
 * @author ehsun7b
 */
@Named("videoWeb")
@ViewScoped
public class VideoWebBean {

  private static final Logger log = Logger.getLogger(VideoWebBean.class.getName());
  private static final String IMAGE_FOLDER = "/root/video/image";
  private static final int BUFFER_SIZE = 2048;

  @Inject
  private VideoBean videoBean;

  private Video video;
  private VideoCategory videoCategory;
  private List<Video> videoList;
  private List<VideoCategory> categories;

  private Part image;

  @PostConstruct
  private void init() {
    video = new Video();
    videoCategory = new VideoCategory();
    categories = videoBean.readCategories();
  }

  public void save() {
    FacesContext context = FacesContext.getCurrentInstance();
    Properties p = loadProperties();
    try {
      if (image.getSize() > 0) {
        String filename = image.getSubmittedFileName();
        video.setImage(filename);
      } else if (video.getId() == null) {
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, p.getProperty("enterImage"), ""));
      }      

      if (image.getSize() > 0) {
        writeFile(image);
      }

      if (video.getId() != null || image.getSize() > 0) {
        videoBean.save(video);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, p.getProperty("videoSavedSuccessfully"), ""));
      }      
    } catch (Exception ex) {
      log.log(Level.SEVERE, "Video save failed. {0}", ex.getMessage());
      context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, p.getProperty("videoSaveFailed"), ""));
    }
  }
  
  public void saveCategory() {
    FacesContext context = FacesContext.getCurrentInstance();
    Properties p = loadProperties();
    try {
      videoBean.save(videoCategory);
      context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, p.getProperty("videoCategorySavedSuccessfully"), ""));
    } catch (Exception ex) {
      log.log(Level.SEVERE, "VideoCategory save failed. {0}", ex.getMessage());
      context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, p.getProperty("videoCategorySaveFailed"), ""));
    }
  }
  
  public void delete(Video video) {
    FacesContext context = FacesContext.getCurrentInstance();
    Properties p = loadProperties();
    try {
      File file = new File(IMAGE_FOLDER + File.separator + video.getImage());
      if (file.exists()) {
        try {
          file.delete();
        } catch (Exception ex) {
          log.log(Level.WARNING, "Video image could not be deleted! {0} {1}", new Object[] {video.getImage(), ex.getMessage()});
        }
      }

      videoBean.delete(video);
      context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, p.getProperty("deleteSuccessful"), ""));
      videoList = videoBean.readTop(10);
    } catch (Exception ex) {
      log.log(Level.SEVERE, "Video deletion failed. {0}", ex.getMessage());
      context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, p.getProperty("deleteFailed"), ""));
    }
  }
  
  public void deleteCategory(VideoCategory category) {
    FacesContext context = FacesContext.getCurrentInstance();
    Properties p = loadProperties();
    try {      
      videoBean.deleteCategory(category);
      context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, p.getProperty("deleteSuccessful"), ""));
      categories = videoBean.readCategories();
    } catch (Exception ex) {
      log.log(Level.SEVERE, "VideoCategory deletion failed. {0}", ex.getMessage());
      context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, p.getProperty("deleteFailed"), ""));
    }
  }

  public List<Video> getVideoList() {
    if (videoList == null) {
      videoList = videoBean.readTop(10);
    }

    return videoList;
  }

  public void setVideoList(List<Video> videoList) {
    this.videoList = videoList;
  }

  public VideoBean getVideoBean() {
    return videoBean;
  }

  public void setVideoBean(VideoBean videoBean) {
    this.videoBean = videoBean;
  }

  public Video getVideo() {
    return video;
  }

  public void setVideo(Video video) {
    this.video = video;
  }

  public List<VideoCategory> getCategories() {
    return categories;
  }

  public void setCategories(List<VideoCategory> categories) {
    this.categories = categories;
  }

  public Part getImage() {
    return image;
  }

  public void setImage(Part image) {
    this.image = image;
  }

  public VideoCategory getCategory() {
    return videoCategory;
  }

  public void setCategory(VideoCategory videoCategory) {
    this.videoCategory = videoCategory;
  }    

  private void writeFile(Part image) throws IOException {
    File file = new File(IMAGE_FOLDER + File.separator + image.getSubmittedFileName());
    try (OutputStream os = new FileOutputStream(file)) {
      InputStream is = image.getInputStream();
      byte[] buffer = new byte[BUFFER_SIZE];
      int len;

      while ((len = is.read(buffer)) > 0) {
        os.write(buffer, 0, len);
      }

      os.flush();
    }
  }

  private Properties loadProperties() {
    try {
      InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("admin.properties");
      Properties properties = new Properties();
      properties.load(input);
      return properties;
    } catch (IOException ex) {
      log.log(Level.SEVERE, "Can not load admin.properties resource.", ex);
      return null;
    }
  }

}
