package db;

import model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {
    void insert(Customer customer);

    boolean delete(long id);

    boolean updateEmail(long id, String email);

    Optional<Customer> findById(long id);

    List<Customer> findAll();

    void purchase(long couponId, long customerId);

    Optional<Customer> findByEmailAndPassword(String email, String password);
}
