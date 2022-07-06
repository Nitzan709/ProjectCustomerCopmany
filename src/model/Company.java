package model;

import java.util.HashSet;
import java.util.Set;

public class Company {
    private final long id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;

    private Set<Coupon> coupons;

    public Company(long id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        coupons = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<Coupon> getCoupons() {
        return coupons;
    }
}
