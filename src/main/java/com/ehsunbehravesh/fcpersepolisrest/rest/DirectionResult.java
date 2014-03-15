package com.ehsunbehravesh.fcpersepolisrest.rest;

/**
 *
 * @author ehsun7b
 */
public class DirectionResult {

  private Integer meters;
  private Integer seconds;
  private Boolean success;
  private String errorMessage;

  public Boolean isSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public Integer getMeters() {
    return meters;
  }

  public void setMeters(Integer meters) {
    this.meters = meters;
  }

  public Integer getSeconds() {
    return seconds;
  }

  public void setSeconds(Integer seconds) {
    this.seconds = seconds;
  }

}
