package db;

import common.ConnectionPool;
import model.Coupon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CouponDaoImp implements CouponDao {
    @Override
    public Coupon insert(Coupon coupon) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public List<Coupon> findAll() {
        throw new RuntimeException("not implemented");
    }

    @Override
    public List<Coupon> findAllCustomerCoupon(long customerId) {
        Connection connection = ConnectionPool.getInstance().take();
        List<Coupon> coupons = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(Schema.SELECT_ALL_CUSTOMER_COUPONS);
            ps.setLong(1, customerId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Coupon coupon = new Coupon(resultSet.getLong(Schema.COL_ID),
                        resultSet.getLong(Schema.COL_COMPANY_ID),
                        resultSet.getString(Schema.COL_TITLE),
                        resultSet.getInt(Schema.COL_CATEGORY),
                        resultSet.getDate(Schema.COL_START_DATE),
                        resultSet.getDate(Schema.COL_END_DATE),
                        resultSet.getInt(Schema.COL_AMOUNT),
                        resultSet.getString(Schema.COL_DESC),
                        resultSet.getDouble(Schema.COL_PRICE),
                        resultSet.getString(Schema.COL_IMAGE_URL));

                coupons.add(coupon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().put(connection);
        }
        return coupons;
    }

    @Override
    public List<Coupon> findAllExpired() {
        throw new RuntimeException("not implemented");
    }

    @Override
    public List<Coupon> findAllBefore(Date date) {
        throw new RuntimeException("not implemented");
    }

}
