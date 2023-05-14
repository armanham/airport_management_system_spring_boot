package com.bdg.airport_management_system_spring_boot.persistent;

import jakarta.persistence.*;

@Entity
@Table(
        name = "address",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"country", "city"})}
)
public class AddressPer extends BaseEntity {

    @Column(nullable = false, length = 50)
    private String country;

    @Column(nullable = false, length = 50)
    private String city;

    public AddressPer() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }
}