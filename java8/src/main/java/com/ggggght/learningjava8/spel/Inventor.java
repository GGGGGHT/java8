package com.ggggght.learningjava8.spel;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;

public class Inventor {

  private String name;
  private String nationality;
  private String[] inventions;
  private Date birthdate;
  private PlaceOfBirth placeOfBirth;
  
  
  public Inventor(String name, String nationality)
  {
    GregorianCalendar c= new GregorianCalendar();
    this.name = name;
    this.nationality = nationality;
    this.birthdate = c.getTime();
  }
  public Inventor(String name, Date birthdate, String nationality) {
    this.name = name;
    this.nationality = nationality;
    this.birthdate = birthdate;
  }
  
  public Inventor() {
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getNationality() {
    return nationality;
  }
  public void setNationality(String nationality) {
    this.nationality = nationality;
  }
  public Date getBirthdate() {
    return birthdate;
  }
  public void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
  }
  public PlaceOfBirth getPlaceOfBirth() {
    return placeOfBirth;
  }
  public void setPlaceOfBirth(PlaceOfBirth placeOfBirth) {
    this.placeOfBirth = placeOfBirth;
  }
  public void setInventions(String[] inventions) {
    this.inventions = inventions;
  }
  public String[] getInventions() {
    return inventions;
  }

  @Override public String toString() {
    return "Inventor{" +
        "name='" + name + '\'' +
        ", nationality='" + nationality + '\'' +
        ", inventions=" + Arrays.toString(inventions) +
        ", birthdate=" + birthdate +
        ", placeOfBirth=" + placeOfBirth +
        '}';
  }
}