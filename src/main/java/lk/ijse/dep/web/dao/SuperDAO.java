package lk.ijse.dep.web.dao;

import javax.persistence.EntityManager;

public interface SuperDAO {

/*    public default void setConnection(Connection connection) throws Exception{

    };*/

    void setEntityManager(EntityManager entityManager) throws Exception;

}
