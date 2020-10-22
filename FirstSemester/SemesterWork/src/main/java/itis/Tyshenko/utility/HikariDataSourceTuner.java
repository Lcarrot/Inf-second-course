package itis.Tyshenko.utility;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.util.Properties;

public class HikariDataSourceTuner {

    public static HikariDataSource getDataSource(Properties properties) {
        ConfigurationHikariConfig config = new ConfigurationHikariConfig(properties);
        return new HikariDataSource(config.configureSource());
    }
}

class ConfigurationHikariConfig {

    private final Properties properties;

    ConfigurationHikariConfig(Properties properties) {
        this.properties = properties;
    }

    protected HikariConfig configureSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(properties.getProperty("db.url"));
        config.setDriverClassName(properties.getProperty("db.driver.classname"));
        config.setUsername(properties.getProperty("db.username"));
        config.setPassword(properties.getProperty("db.password"));
        config.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.hikari.max-pool-size")));
        return config;
    }
}


