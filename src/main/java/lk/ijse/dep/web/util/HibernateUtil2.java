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
 * @author : Dhanusha Perera
 * @author : Dhanusha Perera
 * @since : 29/01/2021
 * @since : 29/01/2021
 * @since : 29/01/2021
 * @since : 29/01/2021
 * @since : 29/01/2021
 **/
/**
 * @author : Dhanusha Perera
 * @since : 29/01/2021
 **/
package lk.ijse.dep.web.util;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

public class HibernateUtil2 {

    static org.slf4j.Logger logger = LoggerFactory.getLogger(HibernateUtil2.class);

    private static EntityManagerFactory emf = buildEntityManagerFactory();

    private static EntityManagerFactory buildEntityManagerFactory() {
        Properties prop = new Properties();

        try {
            prop.load(HibernateUtil2.class.getResourceAsStream("/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        emf = Persistence.createEntityManagerFactory("dep-6", prop);
//        em = emf.createEntityManager();

        return emf;
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    private static DataSource getDataSource() {

        BasicDataSource bds = null;
        try {
            Properties prop = new Properties();
            prop.load(HibernateUtil2.class.getResourceAsStream("/application.properties"));
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

//    protected DataSource getMysqlDataSource() {
//        Properties prop = new Properties();
//        try {
//            prop.load(HibernateUtil2.class.getResourceAsStream("/application.properties"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }̥
//        MysqlDataSource mysqlDataSource = new MysqlDataSource();
//        mysqlDataSource.setURL(prop.getProperty("dbcp.connection.url"));
//        mysqlDataSource.setUser(prop.getProperty("dbcp.connection.username"));
//        mysqlDataSource.setPassword(prop.getProperty("dbcp.connection.password"));
//        return mysqlDataSource;
//    }
}
