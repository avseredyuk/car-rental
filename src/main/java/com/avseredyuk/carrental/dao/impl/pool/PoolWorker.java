package com.avseredyuk.carrental.dao.impl.pool;

import java.sql.Connection;
import java.sql.SQLException;

import com.avseredyuk.carrental.util.PropertiesUtil;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 * Created by lenfer on 12/28/16.
 */
public class PoolWorker {
    private static final PoolWorker instance = new PoolWorker();
    private BasicDataSource dataSource;

    private PoolWorker() {
        initializeDataSource();
    }

    public static PoolWorker getInstance() {
        return instance;
    }

    private void initializeDataSource() {
        dataSource = new BasicDataSource();
        dataSource.setUsername(PropertiesUtil.getProperty("dbuser", PropertiesUtil.Source.DBCONN));
        dataSource.setPassword(PropertiesUtil.getProperty("dbpassword", PropertiesUtil.Source.DBCONN));
        dataSource.setDriverClassName(PropertiesUtil.getProperty("dbdriver", PropertiesUtil.Source.DBCONN));
        dataSource.setUrl(String.format(PropertiesUtil.getProperty("dburl", PropertiesUtil.Source.DBCONN),
                PropertiesUtil.getProperty("dbname", PropertiesUtil.Source.DBCONN)));
        dataSource.setMaxWaitMillis(Long.parseLong(PropertiesUtil.getProperty("dbtimeout", PropertiesUtil.Source.DBCONN)));
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
