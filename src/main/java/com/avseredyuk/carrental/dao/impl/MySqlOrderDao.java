package com.avseredyuk.carrental.dao.impl;

import com.avseredyuk.carrental.dao.impl.factory.MySqlDaoFactory;
import com.avseredyuk.carrental.dao.impl.pool.PoolWorker;
import com.avseredyuk.carrental.domain.User;
import com.avseredyuk.carrental.exception.DaoException;
import com.avseredyuk.carrental.dao.OrderDao;
import com.avseredyuk.carrental.domain.Order;
import com.avseredyuk.carrental.util.PropertiesUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;

/**
 * Created by lenfer on 12/23/16.
 */
public class MySqlOrderDao implements OrderDao {
    private static final Logger logger = Logger.getLogger(MySqlOrderDao.class);

    public boolean persist(Order order) {
        return persist(order, "order.create");
    }

    public Order read(int key){
        return read(key, "order.read");
    }

    public boolean update(Order order) {
        return update(order, "order.update");
    }

    public boolean delete(Order order) {
        return delete(order, "order.delete");
    }

    public List<Order> findAll() {
        return findAll("order.findall");
    }

    public List<Order> findAll(int startIndex, int size) {
        return findAll(startIndex, size, "order.findallrange");
    }

    public int getCount() {
        return getCount("order.getcount");
    }

    @Override
    public List<Order> findAllLastRange(int startIndex, int size) {
        return findAll(startIndex, size, "order.find.all.last.range");
    }

    @Override
    public List<Order> findAllByUserSortedByDateRange(User user, int startIndex, int size) {
        String query = PropertiesUtil.getProperty("order.find.all.by.user.range", PropertiesUtil.Source.QUERIES);
        List<Order> result = new ArrayList<>();
        try (Connection connection = PoolWorker.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, user.getId());
            statement.setInt(2, startIndex);
            statement.setInt(3, size);
            try (ResultSet resultSet = statement.executeQuery()) {
                result = parseResultSet(resultSet);
            }
        } catch (SQLException | DaoException e) {
            logger.error(String.format("Error finding sorted orders in range [%s:%s]", startIndex, size), e);
        }
        return result;
    }

    @Override
    public int countAllByUser(User user) {
        String query = PropertiesUtil.getProperty("order.find.all.by.user.count", PropertiesUtil.Source.QUERIES);
        int result = 0;
        try (Connection connection = PoolWorker.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, user.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                result = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(String.format("Error counting orders by user %s", user), e);
        }
        return result;
    }

    @Override
    public void prepareStatement(PreparedStatement statement, Order order, boolean isUpdate) throws SQLException {
        statement.setInt(1, order.getAutomobile().getId());
        statement.setInt(2, order.getPlaceFrom().getId());
        statement.setInt(3, order.getPlaceTo().getId());
        statement.setInt(4, order.getUser().getId());
        if (order.getDamage() == null) {
            statement.setNull(5, Types.INTEGER);
        } else {
            statement.setInt(5, order.getDamage().getId());
        }
        statement.setTimestamp(6, new Timestamp(order.getDateFrom().getTime()));
        statement.setTimestamp(7, new Timestamp(order.getDateTo().getTime()));
        statement.setString(8, order.getStatus().name());
        statement.setInt(9, order.getSum());
        if(isUpdate) {
            statement.setInt(10, order.getId());
        }
    }

    @Override
    public List<Order> parseResultSet(ResultSet resultSet) throws DaoException {
        List<Order> result = new LinkedList<>();
        try {
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setPlaceFrom(MySqlDaoFactory.getInstance().getDeliveryPlaceDao().read(
                        resultSet.getInt("place_from_id")
                ));
                order.setPlaceTo(MySqlDaoFactory.getInstance().getDeliveryPlaceDao().read(
                        resultSet.getInt("place_to_id")
                ));
                order.setAutomobile(MySqlDaoFactory.getInstance().getAutomobileDao().read(
                        resultSet.getInt("automobileId")
                ));
                order.setUser(MySqlDaoFactory.getInstance().getUserDao().read(
                        resultSet.getInt("userId")
                ));
                order.setDamage(
                        (resultSet.getObject("damageId") == null) ?
                                null :
                                MySqlDaoFactory.getInstance().getDamageDao().read(
                                        resultSet.getInt("damageId")
                                )
                );
                order.setDateFrom(resultSet.getTimestamp("dateFrom"));
                order.setDateTo(resultSet.getTimestamp("dateTo"));
                order.setCreated(resultSet.getTimestamp("dateCreated"));
                order.setStatus(Order.OrderStatus.valueOf(resultSet.getString("status")));
                order.setSum(resultSet.getInt("sum"));

                result.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }
}
