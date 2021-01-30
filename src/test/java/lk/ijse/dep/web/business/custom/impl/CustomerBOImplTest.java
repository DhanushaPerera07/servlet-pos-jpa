package lk.ijse.dep.web.business.custom.impl;

import lk.ijse.dep.web.dao.CrudDAOImpl;
import lk.ijse.dep.web.dao.SuperDAO;
import lk.ijse.dep.web.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.dep.web.dto.CustomerDTO;
import lk.ijse.dep.web.entity.Customer;
import lk.ijse.dep.web.util.HibernateUtil;
import org.junit.Assert;
import org.junit.Test;

public class CustomerBOImplTest {

//    private static BasicDataSource pool;
//    private Connection connection;
//    private CustomerBO customerBO;
//
//    @BeforeClass
//    public static void executeClassBefore(){
//        pool = ConnectionPool.getInstance().getPool();
//    }
//
//    @Before
//    public void prepareBeforeTest() throws Exception {
//        connection = pool.getConnection();
//        connection.setAutoCommit(false);
//        customerBO = BOFactory.getInstance().getBO(BOTypes.CUSTOMER);
//        customerBO.setConnection(connection);
//    }
//
//    @After
//    public void finalizeAfterTest() throws SQLException {
//        connection.rollback();
//        connection.setAutoCommit(true);
//        connection.close();
//    }
//
//    @AfterClass
//    public static void executeClassAfter(){
//        try {
//            pool.close();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }

//    @Test
//    public void saveCustomer() throws Exception {
//        assertTrue(customerBO.saveCustomer(new CustomerDTO("C007","Nipun","Galle")));
//    }

    @Test
    public void testSaveCustomer() throws Exception {
//        CustomerBOImpl customerBOImpl = new CustomerBOImpl();
//        customerBOImpl.setSession();
        CustomerBOImpl customerBOImpl = new CustomerBOImpl();
        customerBOImpl.setSession(HibernateUtil.getSessionFactory().openSession());
//        new CrudDAOImpl().setSession(HibernateUtil.getSessionFactory().openSession());
        customerBOImpl.saveCustomer(new CustomerDTO("C001", "Nipun", "Galle"));
    }
}
