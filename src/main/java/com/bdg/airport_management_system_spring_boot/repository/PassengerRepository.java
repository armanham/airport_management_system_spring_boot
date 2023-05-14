package com.bdg.airport_management_system_spring_boot.repository;

import com.bdg.airport_management_system_spring_boot.persistent.PassengerPer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<PassengerPer, Long> {

    Optional<PassengerPer> findByPhone(String phone);

    List<PassengerPer> findAllByAddress_Id(Long addressId);

    List<PassengerPer> findAllByAddress_Country(String country);

    List<PassengerPer> findAllByAddress_City(String city);

    List<PassengerPer> findAllByAddress_CountryAndAddress_City(String country, String city);

    List<PassengerPer> findAllByName(String name);

    void deleteByPhone(String phone);

    long deleteAllByName(String name);

    boolean existsByPhone(String phone);

    boolean existsByName(String name);

    boolean existsByAddress_Id(Long id);

    boolean existsByAddress_Country(String country);

    boolean existsByAddress_City(String city);
}