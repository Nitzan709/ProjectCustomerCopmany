package db;

public final class Schema {

    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_EMAIL = "email";
    public static final String COL_PASSWORD = "password";
    public static final String COL_FIRST_NAME = "first_name";
    public static final String COL_LAST_NAME = "last_name";
    public static final String COL_COMPANY_ID = "company_id";
    public static final String COL_CATEGORY = "category";
    public static final String COL_TITLE = "title";
    public static final String COL_START_DATE = "start_date";
    public static final String COL_END_DATE = "end_date";
    public static final String COL_AMOUNT = "amount";
    public static final String COL_DESC = "description";
    public static final String COL_PRICE = "price";
    public static final String COL_IMAGE_URL = "image_url";
    private static final String COL_COUPON_ID = "coupon_id";
    private static final String COL_CUSTOMER_ID = "customer_id";

    private static final String TABLE_NAME_COUPON = "coupon";
    private static final String TABLE_NAME_CUSTOMER_COUPON = "customer_coupon";
    public static final String TABLE_NAME_CUSTOMER = "customer";

    public static final String SELECT_ALL_CUSTOMERS = "select * from " + TABLE_NAME_CUSTOMER;
    public static final String SELECT_CUSTOMER_BY_ID = "select * from "
            + TABLE_NAME_CUSTOMER +
            " where " +
            COL_ID + "=?";
    public static final String INSERT_INTO_CUSTOMER = "insert into " + TABLE_NAME_CUSTOMER +
            "(" +
            COL_FIRST_NAME + ", " +
            COL_LAST_NAME + ", " +
            COL_EMAIL + ", " +
            COL_PASSWORD +
            ") " +
            "values (?,?,?,?)";

    public static final String UPDATE_CUSTOMER_EMAIL = "update " + TABLE_NAME_CUSTOMER +
            "set " + COL_EMAIL + "=? "
            + " where " +
            COL_ID + "=?";

    public static final String DELETE_FROM_CUSTOMER = "delete from " + TABLE_NAME_CUSTOMER
            + "where " +
            COL_ID + "=?";


    public static final String INSERT_INTO_COMPANY = "insert into company(" +
            COL_NAME + "," + COL_EMAIL + "," + COL_PASSWORD +
            ")" +
            " values" +
            " (?,?,?)";

    public static final String INSERT_INTO_COUPON = "insert into coupon("
            + COL_COMPANY_ID + ","
            + COL_CATEGORY + ","
            + COL_TITLE + ","
            + COL_START_DATE + ","
            + COL_END_DATE + ","
            + COL_AMOUNT + ","
            + COL_DESC + ","
            + COL_PRICE + ","
            + COL_IMAGE_URL
            + ") "
            + "values (?,?,?,?,?,?,?,?,?)";

    public static final String SELECT_ALL_CUSTOMER_COUPONS = "select * from "
            + TABLE_NAME_COUPON
            + " inner join "
            + TABLE_NAME_CUSTOMER_COUPON
            + " on "
            + TABLE_NAME_COUPON + "." + COL_ID
            + " = "
            + TABLE_NAME_CUSTOMER_COUPON + "." + COL_COUPON_ID
            + " where "
            + TABLE_NAME_CUSTOMER_COUPON + "." + COL_CUSTOMER_ID + " = ?";

    private Schema() {
        throw new AssertionError("Enforce non instantiability");
    }


}
