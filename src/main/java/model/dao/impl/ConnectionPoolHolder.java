package model.dao.impl;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConnectionPoolHolder {
    static final Logger logger = Logger.getLogger(ConnectionPoolHolder.class);
    private static volatile DataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    try {
                        Properties properties = new Properties();
                        properties.load(new FileInputStream("D:\\Sasha\\Work\\Training\\FinalProject\\JavaEE\\StudentTesting\\src\\main\\resources\\db.properties"));
                        Class.forName(properties.getProperty("db.connection.driver"));
                        BasicDataSource bds = new BasicDataSource();
                        bds.setUrl(properties.getProperty("db.connection.url"));
                        bds.setUsername(properties.getProperty("db.connection.username"));
                        bds.setPassword(properties.getProperty("db.connection.password"));
                        bds.setMinIdle(10);
                        bds.setMaxIdle(20);
                        bds.setMaxOpenPreparedStatements(100);
                        dataSource = bds;
                    } catch (FileNotFoundException e) {
                        logger.error("FileNotFoundException in ConnectionPoolHolder: getDataSource", e);
                    } catch (IOException e) {
                        logger.error("IOException in ConnectionPoolHolder: getDataSource", e);
                    } catch (ClassNotFoundException e) {
                        logger.error("ClassNotFoundException in ConnectionPoolHolder: getDataSource", e);
                    }
                }
            }
        }
        return dataSource;
    }
}