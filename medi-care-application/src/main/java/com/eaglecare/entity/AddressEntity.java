package com.eaglecare.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="address_id")
    private Long id;

    @Column(name = "address_category")
    private AddressCategoryEnum addressCategory;

    @Column(name = "country")
    private String country;

    @Column(name = "state")
    private String state;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "pin")
    private String pin;

    @Column(name = "geo_location")
    private String geoLocation;

    public Long getAddressId() {
        return id;
    }

    public void setAddressId(Long addressId) {
        this.id = addressId;
    }

    public AddressCategoryEnum getAddressCategory() {
        return addressCategory;
    }

    public void setAddressCategory(AddressCategoryEnum addressCategory) {
        this.addressCategory = addressCategory;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
    }

    public AddressEntity() {
    }

    public AddressEntity(Long addressId, AddressCategoryEnum addressCategory, String country, String state, String city, String street, String pin, String geoLocation) {
        this.id = addressId;
        this.addressCategory = addressCategory;
        this.country = country;
        this.state = state;
        this.city = city;
        this.street = street;
        this.pin = pin;
        this.geoLocation = geoLocation;
    }
}
