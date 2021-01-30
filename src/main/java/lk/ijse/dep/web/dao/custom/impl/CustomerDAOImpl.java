package lk.ijse.dep.web.dao.custom.impl;

import lk.ijse.dep.web.dao.CrudDAOImpl;
import lk.ijse.dep.web.dao.custom.CustomerDAO;
import lk.ijse.dep.web.entity.Customer;

import java.util.List;

public class CustomerDAOImpl extends CrudDAOImpl<Customer, String> implements CustomerDAO {


    @Override
    public List<Customer> searchCustomersByName(String name) throws Exception {
        return getEntityManager().createQuery("FROM Customer c WHERE c.name LIKE ?1")
                .setParameter(1, name + "%").getResultList();
    }

    /*private Connection connection;

    @Override
    public void setConnection(Connection connection) throws Exception {
        this.connection = connection;
    }

    @Override
    public boolean save(Customer customer) throws Exception {
        return CrudUtil.execute(connection,"INSERT INTO customer VALUES (?,?,?)",customer.getId(), customer.getName(), customer.getAddress() );
    }

    @Override
    public boolean update(Customer customer) throws Exception {
        return CrudUtil.execute(connection,"UPDATE customer SET name=?, address=? WHERE id=?", customer.getName(), customer.getAddress(), customer.getId());
    }

    @Override
    public boolean delete(String key) throws Exception {
        return CrudUtil.execute(connection,"DELETE FROM customer WHERE id=?", key);
    }

    @Override
    public List<Customer> getAll() throws Exception {
        List<Customer> customers = new ArrayList<>();
        ResultSet rst = CrudUtil.execute(connection, "SELECT * FROM customer");
        while (rst.next()) {
            customers.add(new Customer(rst.getString("id"),
                    rst.getString("name"),
                    rst.getString("address")));
        }
        return customers;
    }

    @Override
    public Customer get(String key) throws Exception {
        ResultSet rst = CrudUtil.execute(connection,"SELECT * FROM customer WHERE id=?", key );
        if (rst.next()) {
            return new Customer(rst.getString("id"),
                    rst.getString("name"),
                    rst.getString("address"));
        } else {
            return null;
        }
    }*/

}
