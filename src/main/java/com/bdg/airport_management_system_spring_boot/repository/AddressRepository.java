package com.bdg.airport_management_system_spring_boot.repository;

import com.bdg.airport_management_system_spring_boot.persistent.AddressPer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressPer, Long> {

    List<AddressPer> findAllByCountry(String country);

    List<AddressPer> findAllByCity(String city);

    Optional<AddressPer> findByCountryAndCity(String country, String city);

    long deleteAllByCountry(String country);

    long deleteAllByCity(String city);

    boolean existsByCountry(String country);

    boolean existsByCity(String city);

    boolean existsByCountryAndCity(String country, String city);
}