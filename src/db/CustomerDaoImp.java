package db;

import common.ConnectionPool;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDaoImp implements  CustomerDao{
    @Override
    public void insert(Customer customer) {

        if (customer == null) {
            throw new IllegalArgumentException("null customer!");
        }
        Connection connection = ConnectionPool.getInstance().take();

        try {
            PreparedStatement ps = connection.prepareStatement(Schema.INSERT_INTO_CUSTOMER);
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getPassword());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(String.format("Unable to prepare statement: %s", e.getMessage()));
        } finally {
            ConnectionPool.getInstance().put(connection);
        }
    }

    @Override
    public boolean delete(long id) {
        Connection connection = ConnectionPool.getInstance().take();

        try {
            PreparedStatement ps = connection.prepareStatement(Schema.DELETE_FROM_CUSTOMER);
            ps.setLong(1, id);

            return ps.executeUpdate() != 0;

        } catch (SQLException e) {
            throw new RuntimeException(String.format("unable to prepare statment: %s", e.getMessage()));
        } finally {
            ConnectionPool.getInstance().put(connection);
        }
    }

    @Override
    public boolean updateEmail(long id, String email) {
        Connection connection = ConnectionPool.getInstance().take();


        try {
            PreparedStatement ps = connection.prepareStatement(Schema.UPDATE_CUSTOMER_EMAIL);

            ps.setString(1, email);
            ps.setLong(2, id);

            return ps.executeUpdate() != 0;

        } catch (SQLException e) {
            throw new RuntimeException(String.format("unable to prepare statment: %s", e.getMessage()));
        } finally {
            ConnectionPool.getInstance().put(connection);
        }
    }

    @Override
    public Optional<Customer> findById(long id) {

        Connection connection = ConnectionPool.getInstance().take();

        try {
            PreparedStatement ps = connection.prepareStatement(Schema.SELECT_CUSTOMER_BY_ID);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String firstName = rs.getString(Schema.COL_FIRST_NAME);
                String lastName = rs.getString(Schema.COL_LAST_NAME);
                String email = rs.getString(Schema.COL_EMAIL);
                String password = rs.getString(Schema.COL_PASSWORD);

                return Optional.of(new Customer(id, firstName, lastName, email, password));
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(String.format("unable to prepare statment: %s", e.getMessage()));
        } finally {
            ConnectionPool.getInstance().put(connection);
        }

    }

    @Override
    public List<Customer> findAll() {

        Connection connection = ConnectionPool.getInstance().take();

        try {
            PreparedStatement ps = connection.prepareStatement(Schema.SELECT_ALL_CUSTOMERS);

            ResultSet rs = ps.executeQuery();

            List<Customer> customers = new ArrayList<>();

            while (rs.next()) {
                long id = rs.getLong(Schema.COL_ID);
                String firstName = rs.getString(Schema.COL_FIRST_NAME);
                String lastName = rs.getString(Schema.COL_LAST_NAME);
                String email = rs.getString(Schema.COL_EMAIL);
                String password = rs.getString(Schema.COL_PASSWORD);

                Customer customer = new Customer(id, firstName, lastName, email, password);
                customers.add(customer);
            }

            return customers;

        } catch (SQLException e) {
            throw new RuntimeException(String.format("unable to prepare statment: %s", e.getMessage()));
        } finally {
            ConnectionPool.getInstance().put(connection);
        }
    }

    @Override
    public void purchase(long couponId, long customerId) {

    }

    @Override
    public Optional<Customer> findByEmailAndPassword(String email, String password) {
        return Optional.empty();
    }

}
