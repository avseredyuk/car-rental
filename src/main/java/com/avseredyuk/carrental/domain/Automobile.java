package com.avseredyuk.carrental.domain;

import java.io.Serializable;

/**
 * Created by lenfer on 12/21/16.
 */
public class Automobile implements BasicEntity, Serializable {
    private Integer id = null;
    private String manufacturer;
    private String model;
    private Integer yearOfProduction;
    private DeliveryPlace deliveryPlace;
    private Category category;
    private Fuel fuel;
    private Transmission transmission;
    private Integer passengerCapacity;
    private Integer cargoCapacity;
    private Integer doorsCount;
    private Integer pricePerDay;

    public enum Category {
        SEDAN, HATCHBACK, SUV, PICKUP, SUPERCAR
    }

    public enum Fuel {
        ELECTRO, DIESEL, GAS
    }

    public enum Transmission {
        AUTOMATIC, MANUAL
    }

    public Automobile() {
        // do nothing
    }

    public Automobile(Integer id) {
        this.id = id;
    }

    public Automobile(String manufacturer, String model, Integer yearOfProduction,
                      DeliveryPlace deliveryPlace, Category category, Fuel fuel, Transmission transmission,
                      Integer passengerCapacity, Integer cargoCapacity, Integer doorsCount, Integer pricePerDay) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.yearOfProduction = yearOfProduction;
        this.deliveryPlace = deliveryPlace;
        this.category = category;
        this.fuel = fuel;
        this.transmission = transmission;
        this.passengerCapacity = passengerCapacity;
        this.cargoCapacity = cargoCapacity;
        this.doorsCount = doorsCount;
        this.pricePerDay = pricePerDay;
    }

    public Automobile(Integer id, String manufacturer, String model, Integer yearOfProduction,
                      DeliveryPlace deliveryPlace, Category category, Fuel fuel, Transmission transmission,
                      Integer passengerCapacity, Integer cargoCapacity, Integer doorsCount, Integer pricePerDay) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.yearOfProduction = yearOfProduction;
        this.deliveryPlace = deliveryPlace;
        this.category = category;
        this.fuel = fuel;
        this.transmission = transmission;
        this.passengerCapacity = passengerCapacity;
        this.cargoCapacity = cargoCapacity;
        this.doorsCount = doorsCount;
        this.pricePerDay = pricePerDay;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(Integer yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public DeliveryPlace getDeliveryPlace() {
        return deliveryPlace;
    }

    public void setDeliveryPlace(DeliveryPlace deliveryPlace) {
        this.deliveryPlace = deliveryPlace;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Integer getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(Integer passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public Integer getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(Integer cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    public Integer getDoorsCount() {
        return doorsCount;
    }

    public void setDoorsCount(Integer doorsCount) {
        this.doorsCount = doorsCount;
    }

    public Integer getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Integer pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Automobile that = (Automobile) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (!getManufacturer().equals(that.getManufacturer())) return false;
        if (!getModel().equals(that.getModel())) return false;
        if (!getYearOfProduction().equals(that.getYearOfProduction())) return false;
        if (!getDeliveryPlace().equals(that.getDeliveryPlace())) return false;
        if (getCategory() != that.getCategory()) return false;
        if (getFuel() != that.getFuel()) return false;
        if (getTransmission() != that.getTransmission()) return false;
        if (!getPassengerCapacity().equals(that.getPassengerCapacity())) return false;
        if (!getCargoCapacity().equals(that.getCargoCapacity())) return false;
        if (!getDoorsCount().equals(that.getDoorsCount())) return false;
        return getPricePerDay().equals(that.getPricePerDay());
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getManufacturer().hashCode();
        result = 31 * result + getModel().hashCode();
        result = 31 * result + getYearOfProduction().hashCode();
        result = 31 * result + getDeliveryPlace().hashCode();
        result = 31 * result + getCategory().hashCode();
        result = 31 * result + getFuel().hashCode();
        result = 31 * result + getTransmission().hashCode();
        result = 31 * result + getPassengerCapacity().hashCode();
        result = 31 * result + getCargoCapacity().hashCode();
        result = 31 * result + getDoorsCount().hashCode();
        result = 31 * result + getPricePerDay().hashCode();
        return result;
    }
}
