package itis.Tyshenko.listeners;

import itis.Tyshenko.repositories.ads.AdRepositoryReflection;
import itis.Tyshenko.repositories.users.UserRepositoryReflection;
import itis.Tyshenko.services.AdServiceImpl;
import itis.Tyshenko.services.UserServiceImpl;
import itis.Tyshenko.utility.HikariDataSourceTuner;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@WebListener
public class UserServiceListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        Properties properties = new Properties();
        try {
            properties.load(servletContext.getResourceAsStream("/WEB-INF/db.properties"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        DataSource dataSource = HikariDataSourceTuner.getDataSource(properties);

//        AdRepository adRepository = new AdRepositoryReflection(dataSource);
//        UserRepository userRepository = new UserRepositoryReflection(dataSource);
//
//        AdService adService = new AdServiceImpl(adRepository);
//        UserService userService = new UserServiceImpl(userRepository);
//
//        servletContext.setAttribute("adService", adService);
//        servletContext.setAttribute("userService", userService);
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }
}
