package com.avseredyuk.carrental.dao.impl;

import com.avseredyuk.carrental.dao.DamageDao;
import com.avseredyuk.carrental.exception.DaoException;
import com.avseredyuk.carrental.domain.Damage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lenfer on 12/22/16.
 */
public class MySqlDamageDao implements DamageDao {

    public boolean persist(Damage damage) {
        return persist(damage, "damage.create");
    }

    public Damage read(int key) {
        return read(key, "damage.read");
    }

    public boolean update(Damage damage) {
        return update(damage, "damage.udpate");
    }

    public boolean delete(Damage damage) {
        return delete(damage, "damage.delete");
    }

    public List<Damage> findAll() {
        return findAll("damage.findall");
    }

    public List<Damage> findAll(int startIndex, int size) {
        return findAll(startIndex, size, "damage.findallrange");
    }

    public int getCount() {
        return getCount("damage.getcount");
    }

    @Override
    public void prepareStatement(PreparedStatement statement, Damage damage, boolean isUpdate) throws SQLException {
        statement.setInt(1, damage.getDamageSum());
        statement.setString(2, damage.getDescription());
        statement.setBoolean(3, damage.getPaid());
        if(isUpdate) {
            statement.setInt(4, damage.getId());
        }
    }

    @Override
    public List<Damage> parseResultSet(ResultSet resultSet) throws DaoException {
        List<Damage> result = new LinkedList<>();
        try {
            while (resultSet.next()) {
                Damage damage = new Damage();
                damage.setId(resultSet.getInt("id"));
                damage.setDamageSum(resultSet.getInt("damageSum"));
                damage.setDescription(resultSet.getString("description"));
                damage.setPaid(resultSet.getBoolean("paid"));
                result.add(damage);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }
}
