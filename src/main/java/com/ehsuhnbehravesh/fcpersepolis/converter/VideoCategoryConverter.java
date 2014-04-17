package com.ehsuhnbehravesh.fcpersepolis.converter;

import com.ehsunbehravesh.fcpersepolisrest.ejb.VideoBean;
import com.ehsunbehravesh.persepolis.entity.VideoCategory;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ehsun7b
 */
@Named
@ApplicationScoped
public class VideoCategoryConverter implements Converter {

  @Inject
  private VideoBean videoBean;

  @Override
  public VideoCategory getAsObject(FacesContext context, UIComponent component, String value) {
    Long code = 0L;
    
    try {
      code = value != null ? Long.parseLong(value.trim()) : 0;
    } catch (NumberFormatException ex) {
      throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Can not parse to Long. " + value, ""));
    }
    
    VideoCategory result = videoBean.findCategory(code);
    return result;
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Object value) {
    if (value == null) {
      throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Can not convert to String. NULL", ""));
    }

    if (value instanceof VideoCategory) {
      VideoCategory vc = (VideoCategory) value;
      return vc.getCode() + "";
    } else {
      throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Can not convert to String. " + value, ""));
    }
  }

}
