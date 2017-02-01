package com.avseredyuk.carrental.dao.impl;

import com.avseredyuk.carrental.dao.DeliveryPlaceDao;
import com.avseredyuk.carrental.domain.DeliveryPlace;
import com.avseredyuk.carrental.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lenfer on 1/13/17.
 */
public class MySqlDeliveryPlaceDao implements DeliveryPlaceDao {

    public boolean persist(DeliveryPlace deliveryPlace) {
        return persist(deliveryPlace, "deliveryplace.create");
    }

    public DeliveryPlace read(int key) {
        return read(key, "deliveryplace.read");
    }

    public boolean update(DeliveryPlace deliveryPlace) {
        return update(deliveryPlace, "deliveryplace.update");
    }

    public boolean delete(DeliveryPlace deliveryPlace) {
        return delete(deliveryPlace, "deliveryplace.delete");
    }

    public List<DeliveryPlace> findAll() {
        return findAll("deliveryplace.findall");
    }

    public List<DeliveryPlace> findAll(int startIndex, int size) {
        return findAll(startIndex, size, "deliveryplace.findallrange");
    }

    public int getCount() {
        return getCount("deliveryplace.getcount");
    }

    @Override
    public void prepareStatement(PreparedStatement statement, DeliveryPlace deliveryPlace, boolean isUpdate) throws SQLException {
        statement.setString(1, deliveryPlace.getName());
        statement.setString(2, deliveryPlace.getAddress());
        statement.setString(3, deliveryPlace.getType().name());
        if(isUpdate) {
            statement.setInt(4, deliveryPlace.getId());
        }
    }

    @Override
    public List<DeliveryPlace> parseResultSet(ResultSet resultSet) throws DaoException {
        List<DeliveryPlace> result = new LinkedList<>();
        try {
            while (resultSet.next()) {
                DeliveryPlace deliveryPlace = new DeliveryPlace(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        DeliveryPlace.DeliveryPlaceType.valueOf(resultSet.getString("place_type"))
                );
                result.add(deliveryPlace);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }
}
