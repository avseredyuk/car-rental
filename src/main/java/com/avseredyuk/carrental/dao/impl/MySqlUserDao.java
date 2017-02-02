package com.avseredyuk.carrental.dao.impl;

import com.avseredyuk.carrental.dao.impl.pool.PoolWorker;
import com.avseredyuk.carrental.exception.DaoException;
import com.avseredyuk.carrental.dao.UserDao;
import com.avseredyuk.carrental.domain.User;
import com.avseredyuk.carrental.util.PropertiesUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lenfer on 12/22/16.
 */
public class MySqlUserDao implements UserDao {
    private static final Logger logger = Logger.getLogger(MySqlUserDao.class);

    public boolean persist(User user)  {
        return persist(user, "user.create");
    }

    public User read(int key) {
        return read(key, "user.read");
    }

    public boolean update(User user) {
        return update(user, "user.update");
    }

    public boolean delete(User user) {
        return delete(user, "user.delete");
    }

    public List<User> findAll() {
        return findAll("user.findall");
    }

    public List<User> findAll(int startIndex, int size) {
        return findAll(startIndex, size, "user.findallrange");
    }

    public int getCount() {
        return getCount("user.getcount");
    }

    /**
     * Fetch User by login
     * @param login login of the User
     * @return <tt>User</tt> if there is such User or null if there are no users in database with such login
     */
    @Override
    public User getByLogin(String login) {
        User result = null;

        String query = PropertiesUtil.getProperty("user.getbylogin", PropertiesUtil.Source.QUERIES);
        try (Connection connection = PoolWorker.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<User> resultList = parseResultSet(resultSet);
                if (resultList == null || resultList.isEmpty() || resultList.size() != 1) {
                    throw new DaoException("Trouble reading record with login = " + login + "");
                }
                result = resultList.iterator().next();
            }
        } catch (SQLException | DaoException e) {
            logger.error(String.format("Error retrieving user by login %s", login), e);
        }
        return result;
    }

    @Override
    public List<User> findAllLastRange(int startIndex, int size) {
        return findAll(startIndex, size, "user.find.all.last.range");
    }

    @Override
    public void prepareStatement(PreparedStatement statement, User user, boolean isUpdate) throws SQLException {
        statement.setString(1, user.getLogin());
        statement.setString(2, user.getEmail());
        statement.setString(3, user.getPassword());
        statement.setString(4, user.getName());
        statement.setString(5, user.getSurname());
        statement.setString(6, user.getRole().name());
        if(isUpdate) {
            statement.setInt(7, user.getId());
        }
    }

    @Override
    public List<User> parseResultSet(ResultSet resultSet) throws DaoException {
        List<User> result = new LinkedList<>();
        try {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setEmail(resultSet.getString("email"));
                user.setRegistered(resultSet.getTimestamp("registered_datetime"));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setRole(User.Role.valueOf(resultSet.getString("role")));
                result.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

}
