package model;

import java.util.Date;

public class Coupon {
    private final long id;
    private final long companyId;
    private final String title;
    private final int category;
    private final Date startDate;
    private final Date endDate;
    private final String description;
    private final double price;
    private final String imageUrl;

    public Coupon(long id, long companyId, String title, int category, Date startDate, Date endDate, int anInt, String description, double price, String imageUrl) {
        this.id = id;
        this.companyId = companyId;
        this.title = title;
        this.category = category;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public long getId() {
        return id;
    }

    public long getCompanyId() {
        return companyId;
    }

    public String getTitle() {
        return title;
    }

    public int getCategory() {
        return category;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
