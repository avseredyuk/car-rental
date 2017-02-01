package com.avseredyuk.carrental.domain;

import java.io.Serializable;

/**
 * Created by lenfer on 1/13/17.
 */
public class DeliveryPlace implements BasicEntity, Serializable {
    private Integer id = null;
    private String name;
    private String address;
    private DeliveryPlaceType type;

    public enum DeliveryPlaceType {
        OFFICE, PUBLIC_PLACE
    }

    public DeliveryPlace(Integer id) {
        this.id = id;
    }

    public DeliveryPlace(String name, String address, DeliveryPlaceType type) {
        this.name = name;
        this.address = address;
        this.type = type;
    }

    public DeliveryPlace(Integer id, String name, String address, DeliveryPlaceType type) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.type = type;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public DeliveryPlaceType getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setType(DeliveryPlaceType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeliveryPlace that = (DeliveryPlace) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (!getName().equals(that.getName())) return false;
        if (!getAddress().equals(that.getAddress())) return false;
        return getType() == that.getType();
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getName().hashCode();
        result = 31 * result + getAddress().hashCode();
        result = 31 * result + getType().hashCode();
        return result;
    }
}
