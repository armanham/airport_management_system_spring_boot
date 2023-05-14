package com.bdg.airport_management_system_spring_boot.persistent;

import jakarta.persistence.*;

@Entity
@Table(name = "passenger")
public class PassengerPer extends BaseEntity {

    @Column(nullable = false, length = 24)
    private String name;

    @Column(nullable = false, length = 24, unique = true)
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private AddressPer address;


    public PassengerPer() {
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public AddressPer getAddress() {
        return address;
    }

    public void setAddress(final AddressPer addressPer) {
        this.address = addressPer;
    }
}