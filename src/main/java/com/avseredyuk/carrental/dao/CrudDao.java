package com.avseredyuk.carrental.dao;

import com.avseredyuk.carrental.dao.impl.pool.PoolWorker;
import com.avseredyuk.carrental.domain.BasicEntity;
import com.avseredyuk.carrental.exception.DaoException;
import com.avseredyuk.carrental.util.PropertiesUtil;
import com.mysql.jdbc.Statement;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenfer on 12/22/16.
 */
public interface CrudDao<T extends BasicEntity> {
    Logger logger = Logger.getLogger(CrudDao.class);

    List<T> parseResultSet(ResultSet resultSet) throws DaoException;
    void prepareStatement(PreparedStatement statement, T object, boolean isUpdate) throws SQLException;

    default boolean persist(T object, String propertyKeyQuery)  {
        String query = PropertiesUtil.getProperty(propertyKeyQuery, PropertiesUtil.Source.QUERIES);
        try (Connection connection = PoolWorker.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatement(statement, object, false);
            if (statement.executeUpdate() != 1) {
                throw new DaoException(String.format("Error during persisting object: %s", object));
            }
            ResultSet keys = statement.getGeneratedKeys();
            keys.next();
            object.setId(keys.getInt(1));
            return true;
        } catch (DaoException | SQLException e) {
            logger.error(e);
            return false;
        }
    }

    default T read(int key, String propertyKeyQuery) {
        T result = null;
        String query = PropertiesUtil.getProperty(propertyKeyQuery, PropertiesUtil.Source.QUERIES);
        try (Connection connection = PoolWorker.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, key);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<T> resultList = parseResultSet(resultSet);
                if (resultList == null || resultList.isEmpty() || resultList.size() != 1) {
                    throw new DaoException(String.format("Error during reading with key %s", key));
                }
                result = resultList.iterator().next();
            }

        } catch (SQLException | DaoException e) {
            logger.error(e);
        }
        return result;
    }

    default boolean update(T object, String propertyKeyQuery) {
        String query = PropertiesUtil.getProperty(propertyKeyQuery, PropertiesUtil.Source.QUERIES);
        try (Connection connection = PoolWorker.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query);) {
            prepareStatement(statement, object, true);
            if (statement.executeUpdate() != 1) {
                throw new DaoException(String.format("Error during updating object %s", object));
            }
            return true;
        } catch (SQLException | DaoException e) {
            logger.error(e);
            return false;
        }
    }

    default boolean delete(BasicEntity object, String propertyKeyQuery) {
        String query = PropertiesUtil.getProperty(propertyKeyQuery, PropertiesUtil.Source.QUERIES);
        try (Connection connection = PoolWorker.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, object.getId());
            if (statement.executeUpdate() != 1) {
                throw new DaoException(String.format("Error during deleting object %s", object));
            }
            return true;
        } catch (SQLException | DaoException e) {
            logger.error(e);
            return false;
        }
    }

    default int getCount(String propertyKeyQuery) {
        String query = PropertiesUtil.getProperty(propertyKeyQuery, PropertiesUtil.Source.QUERIES);
        int result = 0;
        try (Connection connection = PoolWorker.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                result = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(String.format("Error counting objects by query %s", propertyKeyQuery), e);
        }
        return result;
    }

    default List<T> findAll(String propertyKeyQuery) {
        String query = PropertiesUtil.getProperty(propertyKeyQuery, PropertiesUtil.Source.QUERIES);
        List<T> result = new ArrayList<>();
        try (Connection connection = PoolWorker.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                result = parseResultSet(resultSet);
            }
        } catch (SQLException | DaoException e) {
            logger.error(String.format("Error finding all objects by query %s", propertyKeyQuery), e);
        }
        return result;
    }

    default List<T> findAll(int startIndex, int size, String propertyKeyQuery) {
        String query = PropertiesUtil.getProperty(propertyKeyQuery, PropertiesUtil.Source.QUERIES);
        List<T> result = new ArrayList<>();
        try (Connection connection = PoolWorker.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, startIndex);
            statement.setInt(2, size);
            try (ResultSet resultSet = statement.executeQuery()) {
                result = parseResultSet(resultSet);
            }
        } catch (SQLException | DaoException e) {
            logger.error(String.format("Error finding all objects in range by query %s in range %s,%s",
                    propertyKeyQuery, startIndex, size), e);
        }
        return result;
    }
}
