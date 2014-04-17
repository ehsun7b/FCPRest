package com.ehsunbehravesh.fcpersepolisrest.cdi;

import com.ehsunbehravesh.fcpersepolisrest.ejb.VideoBean;
import com.ehsunbehravesh.persepolis.entity.Video;
import com.ehsunbehravesh.persepolis.entity.VideoCategory;
import java.util.List;
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

  @Inject
  private VideoBean videoBean;

  private Video video;
  private List<Video> videoList;
  private List<VideoCategory> categories;

  private Part image;
  
  @PostConstruct
  private void init() {
    video = new Video();
    categories = videoBean.readCategories();
  }
  
  public void save() {
    FacesContext context = FacesContext.getCurrentInstance();
    try {
      
      String filename = image.getName();
      video.setImage(filename);
      videoBean.save(video);
      context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Video saved successfully.", ""));
    } catch (Exception ex) {
      log.log(Level.SEVERE, "Video save failed. {0}", ex.getMessage());
      context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Video save faild.", ""));
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
  
  
}
