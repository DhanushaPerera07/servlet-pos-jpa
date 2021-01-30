/**
 * MIT License
 * <p>
 * Copyright (c) 2020 Dhanusha Perera
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * @author : Dhanusha Perera
 * @author : Dhanusha Perera
 * @author : Dhanusha Perera
 * @since : 29/01/2021
 * @since : 29/01/2021
 * @since : 29/01/2021
 **/
/**
 * @author : Dhanusha Perera
 * @since : 29/01/2021
 **/
package lk.ijse.dep.web.util;

import lk.ijse.dep.web.entity.Customer;
import lk.ijse.dep.web.entity.Item;
import lk.ijse.dep.web.entity.Order;
import lk.ijse.dep.web.entity.OrderDetail;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

public class HibernateUtil {

    static org.slf4j.Logger logger = LoggerFactory.getLogger(HibernateUtil.class);

    EntityManagerFactory emf = null;
    EntityManager em = null;

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .loadProperties("application.properties")
                .applySetting(Environment.DATASOURCE, getDataSource())
                .build();

        Metadata metadata = new MetadataSources(standardRegistry)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(OrderDetail.class)
                .getMetadataBuilder()
                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
                .build();

        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static DataSource getDataSource() {

        BasicDataSource bds = null;
        try {
            Properties prop = new Properties();
            prop.load(HibernateUtil.class.getResourceAsStream("/application.properties"));
            bds = new BasicDataSource();
            bds.setInitialSize(5);
            bds.setMaxTotal(10);
            bds.setDriverClassName(prop.getProperty("dbcp.connection.driver.class"));
            bds.setUrl(prop.getProperty("dbcp.connection.url"));
            bds.setUsername(prop.getProperty("dbcp.connection.username"));
            bds.setPassword(prop.getProperty("dbcp.connection.password"));

            return bds;
        } catch (IOException e) {
//            e.printStackTrace();
            logger.warn("Error occurred in Connection settings");
            throw new RuntimeException(e);
        }


    }
}
