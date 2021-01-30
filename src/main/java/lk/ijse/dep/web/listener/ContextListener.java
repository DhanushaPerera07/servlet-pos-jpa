package lk.ijse.dep.web.listener;

import lk.ijse.dep.web.util.HibernateUtil;
import lk.ijse.dep.web.util.HibernateUtil2;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.*;

@WebListener
public class ContextListener implements ServletContextListener {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(ContextListener.class);

    public ContextListener() {

    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        Properties prop = new Properties();
        System.out.println("Connection pool is being initialized...!");
        try {
            logger.info("Connection pool is initialized...!");

            EntityManagerFactory entityManagerFactory = HibernateUtil2.getEntityManagerFactory();
            sce.getServletContext().setAttribute("emf", entityManagerFactory);

//            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//            sce.getServletContext().setAttribute("sf", sessionFactory);

//            prop.load(this.getClass().getResourceAsStream("/application.properties"));
//            BasicDataSource bds = new BasicDataSource();
//            bds.setUsername(prop.getProperty("mysql.username"));
//            bds.setPassword(prop.getProperty("mysql.password"));
//            bds.setUrl(prop.getProperty("mysql.url"));
//            bds.setDriverClassName(prop.getProperty("mysql.driver_classname"));
//            bds.setInitialSize(5);
//            sce.getServletContext().setAttribute("cp", bds);

//            Properties properties = System.getProperties();
//            for (Object o : properties.keySet()) {
//                System.out.println(o);
//            }

//            System.out.println(System.getProperty("catalina.home"));

            String logFilePath;
            if (prop.getProperty("app.log_dir")!= null){
                logFilePath = prop.getProperty("app.log_dir") + "/back-end.log";
            }else{
                logFilePath = System.getProperty("catalina.home") + "/logs/back-end.log";
            }
            FileHandler fileHandler = new FileHandler(logFilePath, true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.INFO);
            Logger.getLogger("").addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        EntityManagerFactory emf = (EntityManagerFactory) sce.getServletContext().getAttribute("emf");
        emf.close();
        logger.info("EntityManagerFactory is closed...!");
        logger.info("Connection pool is closed...!");
    }
}
