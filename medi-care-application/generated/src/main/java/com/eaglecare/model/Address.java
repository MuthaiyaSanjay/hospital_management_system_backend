package com.medicare.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Address
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-09-12T00:43:21.532621800+05:30[Asia/Calcutta]")
public class Address {

  @JsonProperty("id")
  private String id;

  /**
   * Gets or Sets addressCategory
   */
  public enum AddressCategoryEnum {
    CURRENT("CURRENT"),
    
    OFFICE("OFFICE"),
    
    PERMANENT("PERMANENT");

    private String value;

    AddressCategoryEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static AddressCategoryEnum fromValue(String value) {
      for (AddressCategoryEnum b : AddressCategoryEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("address_category")
  private AddressCategoryEnum addressCategory;

  @JsonProperty("country")
  private String country;

  @JsonProperty("state")
  private String state;

  @JsonProperty("city")
  private String city;

  @JsonProperty("street")
  private String street;

  @JsonProperty("pin")
  private String pin;

  @JsonProperty("geo_location")
  private String geoLocation;

  public Address id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", example = "4acd0b30-0ac1-4d0f-938b-c7b86d804375", required = false)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Address addressCategory(AddressCategoryEnum addressCategory) {
    this.addressCategory = addressCategory;
    return this;
  }

  /**
   * Get addressCategory
   * @return addressCategory
  */
  
  @Schema(name = "address_category", example = "Personal", required = false)
  public AddressCategoryEnum getAddressCategory() {
    return addressCategory;
  }

  public void setAddressCategory(AddressCategoryEnum addressCategory) {
    this.addressCategory = addressCategory;
  }

  public Address country(String country) {
    this.country = country;
    return this;
  }

  /**
   * Get country
   * @return country
  */
  
  @Schema(name = "country", example = "India", required = false)
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Address state(String state) {
    this.state = state;
    return this;
  }

  /**
   * Get state
   * @return state
  */
  
  @Schema(name = "state", example = "Tamilnadu", required = false)
  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public Address city(String city) {
    this.city = city;
    return this;
  }

  /**
   * Get city
   * @return city
  */
  
  @Schema(name = "city", example = "Coimabtore", required = false)
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Address street(String street) {
    this.street = street;
    return this;
  }

  /**
   * Get street
   * @return street
  */
  
  @Schema(name = "street", example = "121,Balaji Nagar", required = false)
  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public Address pin(String pin) {
    this.pin = pin;
    return this;
  }

  /**
   * Get pin
   * @return pin
  */
  
  @Schema(name = "pin", example = "624601", required = false)
  public String getPin() {
    return pin;
  }

  public void setPin(String pin) {
    this.pin = pin;
  }

  public Address geoLocation(String geoLocation) {
    this.geoLocation = geoLocation;
    return this;
  }

  /**
   * Get geoLocation
   * @return geoLocation
  */
  
  @Schema(name = "geo_location", example = "India", required = false)
  public String getGeoLocation() {
    return geoLocation;
  }

  public void setGeoLocation(String geoLocation) {
    this.geoLocation = geoLocation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Address address = (Address) o;
    return Objects.equals(this.id, address.id) &&
        Objects.equals(this.addressCategory, address.addressCategory) &&
        Objects.equals(this.country, address.country) &&
        Objects.equals(this.state, address.state) &&
        Objects.equals(this.city, address.city) &&
        Objects.equals(this.street, address.street) &&
        Objects.equals(this.pin, address.pin) &&
        Objects.equals(this.geoLocation, address.geoLocation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, addressCategory, country, state, city, street, pin, geoLocation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Address {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    addressCategory: ").append(toIndentedString(addressCategory)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    street: ").append(toIndentedString(street)).append("\n");
    sb.append("    pin: ").append(toIndentedString(pin)).append("\n");
    sb.append("    geoLocation: ").append(toIndentedString(geoLocation)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

