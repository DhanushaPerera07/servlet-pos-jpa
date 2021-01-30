package lk.ijse.dep.web.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author : Dhanusha Perera
 * @since : 29/01/2021
 **/
public class HibernateUtilTest {

    @Test
    public void testGetDataSource(){
        assertNotNull(HibernateUtil.getSessionFactory());
        assertNotNull(HibernateUtil.getSessionFactory().openSession());
    }

}
