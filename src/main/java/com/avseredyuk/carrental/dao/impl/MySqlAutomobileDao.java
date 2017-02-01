package com.avseredyuk.carrental.dao.impl;

import com.avseredyuk.carrental.dao.AutomobileDao;
import com.avseredyuk.carrental.dao.impl.factory.MySqlDaoFactory;
import com.avseredyuk.carrental.dao.impl.pool.PoolWorker;
import com.avseredyuk.carrental.exception.DaoException;
import com.avseredyuk.carrental.domain.Automobile;
import com.avseredyuk.carrental.util.PropertiesUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;

/**
 * Created by lenfer on 12/22/16.
 */
public class MySqlAutomobileDao implements AutomobileDao {
    private static final Logger logger = Logger.getLogger(MySqlAutomobileDao.class);

    public boolean persist(Automobile automobile) {
        return persist(automobile, "automobile.create");
    }

    public Automobile read(int key) {
        return read(key, "automobile.read");
    }

    public boolean update(Automobile automobile) {
        return update(automobile, "automobile.update");
    }

    public boolean delete(Automobile automobile) {
        return delete(automobile, "automobile.delete");
    }

    public List<Automobile> findAll() {
        return findAll("automobile.findall");
    }

    public List<Automobile> findAll(int startIndex, int size) {
        return findAll(startIndex, size, "automobile.findallrange");
    }

    public int getCount() {
        return getCount("automobile.getcount");
    }

    @Override
    public List<Automobile> getFreeAutomobilesInDateRange(java.util.Date dateFrom, java.util.Date dateTo) {
        String query1 = PropertiesUtil.getProperty("automobile.findallfreebetweendates1", PropertiesUtil.Source.QUERIES);
        String query2 = PropertiesUtil.getProperty("automobile.findallfreebetweendates2", PropertiesUtil.Source.QUERIES);
        String query3 = PropertiesUtil.getProperty("automobile.findallfreebetweendates3", PropertiesUtil.Source.QUERIES);
        List<Automobile> result = new ArrayList<>();
        try (Connection connection = PoolWorker.getInstance().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query1)) {
                statement.setTimestamp(1, new Timestamp(dateFrom.getTime()));
                statement.execute();
            }
            try (PreparedStatement statement = connection.prepareStatement(query2)) {
                statement.setTimestamp(1, new Timestamp(dateTo.getTime()));
                statement.execute();
            }
            try (PreparedStatement statement = connection.prepareStatement(query3);
                 ResultSet resultSet = statement.executeQuery()) {
                result = parseResultSet(resultSet);
            }
        } catch (SQLException | DaoException e) {
            logger.error(String.format("Error retrieving automobiles in date range [%s:%s]", dateFrom, dateTo), e);
        }
        return result;
    }

    @Override
    public void prepareStatement(PreparedStatement statement, Automobile automobile, boolean isUpdate) throws SQLException {
        statement.setString(1, automobile.getManufacturer());
        statement.setString(2, automobile.getModel());
        statement.setInt(3, automobile.getYearOfProduction());
        statement.setInt(4, automobile.getDeliveryPlace().getId());
        statement.setString(5, automobile.getCategory().name());
        statement.setString(6, automobile.getFuel().name());
        statement.setString(7, automobile.getTransmission().name());
        statement.setInt(8, automobile.getPassengerCapacity());
        statement.setInt(9, automobile.getCargoCapacity());
        statement.setInt(10, automobile.getDoorsCount());
        statement.setInt(11, automobile.getPricePerDay());
        if (isUpdate) {
            statement.setInt(12, automobile.getId());
        }
    }

    @Override
    public List<Automobile> parseResultSet(ResultSet resultSet) throws DaoException {
        LinkedList<Automobile> result = new LinkedList<>();
        try {
            while (resultSet.next()) {
                Automobile automobile = new Automobile();
                automobile.setId(resultSet.getInt("id"));
                automobile.setManufacturer(resultSet.getString("manufacturer"));
                automobile.setModel(resultSet.getString("model"));
                automobile.setYearOfProduction(resultSet.getInt("yearOfProduction"));
                automobile.setCategory(Automobile.Category.valueOf(resultSet.getString("category")));
                automobile.setFuel(Automobile.Fuel.valueOf(resultSet.getString("fuel_type")));
                automobile.setTransmission(Automobile.Transmission.valueOf(resultSet.getString("transmission")));
                automobile.setPassengerCapacity(resultSet.getInt("passenger_capacity"));
                automobile.setCargoCapacity(resultSet.getInt("cargo_capacity"));
                automobile.setDoorsCount(resultSet.getInt("doors_count"));
                automobile.setPricePerDay(resultSet.getInt("price_per_day"));
                automobile.setDeliveryPlace(MySqlDaoFactory.getInstance().getDeliveryPlaceDao().read(
                        resultSet.getInt("place_id")
                ));
                result.add(automobile);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }
}
