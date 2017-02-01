package com.avseredyuk.carrental.domain;

/**
 * Created by lenfer on 12/21/16.
 */
public class Damage implements BasicEntity {
    private Integer id = null;
    private Integer damageSum;
    private String description;
    private Boolean paid;

    public Damage() {
    }

    public Damage(Integer damageSum, String description, Boolean paid) {
        this.damageSum = damageSum;
        this.description = description;
        this.paid = paid;
    }

    public Damage(Integer id, Integer damageSum, String description, Boolean paid) {
        this.id = id;
        this.damageSum = damageSum;
        this.description = description;
        this.paid = paid;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDamageSum() {
        return damageSum;
    }

    public void setDamageSum(Integer damageSum) {
        this.damageSum = damageSum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Damage damage = (Damage) o;

        if (getId() != null ? !getId().equals(damage.getId()) : damage.getId() != null) return false;
        if (!getDamageSum().equals(damage.getDamageSum())) return false;
        if (!getDescription().equals(damage.getDescription())) return false;
        return getPaid().equals(damage.getPaid());
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getDamageSum().hashCode();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + getPaid().hashCode();
        return result;
    }
}
