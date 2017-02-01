package com.avseredyuk.carrental.domain;

import java.util.Date;

/**
 * Created by lenfer on 12/21/16.
 */
public class Order implements BasicEntity {
    private Integer id = null;
    private DeliveryPlace placeFrom;
    private DeliveryPlace placeTo;
    private Automobile automobile;
    private User user;
    private Damage damage;
    private Date dateFrom;
    private Date dateTo;
    private Date created;
    private OrderStatus status;
    private Integer sum;

    public enum OrderStatus {
        CREATED, REJECTED, PAID, GIVEN_OUT, RETURNED;
    }

    public Order() {
    }

    public Order(Integer id) {
        this.id = id;
    }

    public Order(DeliveryPlace placeFrom, DeliveryPlace placeTo, Automobile automobile, User user, Date dateFrom,
                 Date dateTo, OrderStatus status, Integer sum) {
        this.placeFrom = placeFrom;
        this.placeTo = placeTo;
        this.automobile = automobile;
        this.user = user;
        this.dateFrom = new Date(dateFrom.getTime());
        this.dateTo = new Date(dateTo.getTime());
        this.status = status;
        this.sum = sum;
    }

    public Order(DeliveryPlace placeFrom, DeliveryPlace placeTo, Automobile automobile, User user, Damage damage,
                 Date dateFrom, Date dateTo, Date created, OrderStatus status, Integer sum) {
        this.placeFrom = placeFrom;
        this.placeTo = placeTo;
        this.automobile = automobile;
        this.user = user;
        this.damage = damage;
        this.dateFrom = new Date(dateFrom.getTime());
        this.dateTo = new Date(dateTo.getTime());
        this.created = new Date(created.getTime());
        this.status = status;
        this.sum = sum;
    }

    public Order(Integer id, DeliveryPlace placeFrom, DeliveryPlace placeTo, Automobile automobile, User user,
                 Damage damage, Date dateFrom, Date dateTo, OrderStatus status, Integer sum) {
        this.id = id;
        this.placeFrom = placeFrom;
        this.placeTo = placeTo;
        this.automobile = automobile;
        this.user = user;
        this.damage = damage;
        this.dateFrom = new Date(dateFrom.getTime());
        this.dateTo = new Date(dateTo.getTime());
        this.status = status;
        this.sum = sum;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Automobile getAutomobile() {
        return automobile;
    }

    public void setAutomobile(Automobile automobile) {
        this.automobile = automobile;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Damage getDamage() {
        return damage;
    }

    public void setDamage(Damage damage) {
        this.damage = damage;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public DeliveryPlace getPlaceFrom() {
        return placeFrom;
    }

    public void setPlaceFrom(DeliveryPlace placeFrom) {
        this.placeFrom = placeFrom;
    }

    public DeliveryPlace getPlaceTo() {
        return placeTo;
    }

    public void setPlaceTo(DeliveryPlace placeTo) {
        this.placeTo = placeTo;
    }

    public Date getDateFrom() {
        return dateFrom == null ? null : new Date(dateFrom.getTime());
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom == null ? null : new Date(dateFrom.getTime());
    }

    public Date getDateTo() {
        return dateTo == null ? null : new Date(dateTo.getTime());
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo == null ? null : new Date(dateTo.getTime());
    }

    public Date getCreated() {
        return created == null ? null : new Date(created.getTime());
    }

    public void setCreated(Date created) {
        this.created = created == null ? null : new Date(created.getTime());
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (getId() != null ? !getId().equals(order.getId()) : order.getId() != null) return false;
        if (!getPlaceFrom().equals(order.getPlaceFrom())) return false;
        if (!getPlaceTo().equals(order.getPlaceTo())) return false;
        if (!getAutomobile().equals(order.getAutomobile())) return false;
        if (!getUser().equals(order.getUser())) return false;
        if (getDamage() != null ? !getDamage().equals(order.getDamage()) : order.getDamage() != null) return false;
        if (!getDateFrom().equals(order.getDateFrom())) return false;
        if (!getDateTo().equals(order.getDateTo())) return false;
        if (!getCreated().equals(order.getCreated())) return false;
        if (getStatus() != order.getStatus()) return false;
        return getSum().equals(order.getSum());
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getPlaceFrom().hashCode();
        result = 31 * result + getPlaceTo().hashCode();
        result = 31 * result + getAutomobile().hashCode();
        result = 31 * result + getUser().hashCode();
        result = 31 * result + (getDamage() != null ? getDamage().hashCode() : 0);
        result = 31 * result + (getDateFrom() != null ? getDateFrom().hashCode() : 0);
        result = 31 * result + (getDateTo() != null ? getDateTo().hashCode() : 0);
        result = 31 * result + (getCreated() != null ? getCreated().hashCode() : 0);
        result = 31 * result + getStatus().hashCode();
        result = 31 * result + getSum().hashCode();
        return result;
    }
}
