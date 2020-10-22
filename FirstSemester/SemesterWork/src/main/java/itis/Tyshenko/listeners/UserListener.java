package itis.Tyshenko.listeners;

import itis.Tyshenko.repositories.HiredRepository;
import itis.Tyshenko.repositories.HiredRepositoryImpl;
import itis.Tyshenko.repositories.HirerRepository;
import itis.Tyshenko.repositories.HirerRepositoryImpl;
import itis.Tyshenko.services.HiredService;
import itis.Tyshenko.services.HiredServiceImpl;
import itis.Tyshenko.services.HirerService;
import itis.Tyshenko.services.HirerServiceImpl;
import itis.Tyshenko.utility.HikariDataSourceTuner;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@WebListener
public class UserListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        Properties properties = new Properties();
        try {
            properties.load(servletContext.getResourceAsStream("/WEB-INF/db.properties"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        DataSource dataSource = HikariDataSourceTuner.getDataSource(properties);
        HiredRepository hiredRepository = new HiredRepositoryImpl(dataSource);
        HirerRepository hirerRepository = new HirerRepositoryImpl(dataSource);
        HirerService hirerService = new HirerServiceImpl(hirerRepository);
        HiredService hiredService = new HiredServiceImpl(hiredRepository);
        servletContext.setAttribute("hirerService", hirerService);
        servletContext.setAttribute("hiredService", hiredService);
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }
}
