package db;

import model.Coupon;

import java.sql.Date;
import java.util.List;

public interface CouponDao {
    Coupon insert(Coupon coupon);

    List<Coupon> findAll();

    List<Coupon> findAllCustomerCoupon(long customerId);

    List<Coupon> findAllExpired();

    List<Coupon> findAllBefore(Date date);

}
