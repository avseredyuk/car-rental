package com.avseredyuk.carrental.dao.impl.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.avseredyuk.carrental.util.PropertiesUtil;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

/**
 * Created by lenfer on 12/28/16.
 */
public class PoolWorker {
    private static final Logger logger = Logger.getLogger(PoolWorker.class);
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

    public void close() {
        try {
            DriverManager.deregisterDriver(dataSource.getDriver());
            dataSource.close();
        } catch (SQLException e) {
            logger.error("Error on closing datasource", e);
        }
    }

}
