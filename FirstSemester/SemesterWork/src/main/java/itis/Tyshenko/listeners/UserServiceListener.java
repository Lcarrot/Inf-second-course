package itis.Tyshenko.listeners;

import itis.Tyshenko.repositories.ads.ReflectionServiceAdRepository;
import itis.Tyshenko.repositories.ads.ReflectionWorkAdRepository;
import itis.Tyshenko.repositories.ads.ServiceAdRepository;
import itis.Tyshenko.repositories.ads.WorkAdRepository;
import itis.Tyshenko.repositories.users.ReflectionUserRepository;
import itis.Tyshenko.repositories.users.UserRepository;
import itis.Tyshenko.services.UserService;
import itis.Tyshenko.services.UserServiceImpl;
import itis.Tyshenko.utility.HikariDataSourceTuner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        servletContext.setAttribute("passwordEncoder", passwordEncoder);

        DataSource dataSource = HikariDataSourceTuner.getDataSource(properties);
//        ServiceAdRepository serviceAdRepository = new ReflectionServiceAdRepository(dataSource);
//        WorkAdRepository workAdRepository = new ReflectionWorkAdRepository(dataSource);
        UserRepository userRepository = new ReflectionUserRepository(dataSource);
        UserService userService = new UserServiceImpl(userRepository, passwordEncoder);
        servletContext.setAttribute("userService", userService);
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }
}
