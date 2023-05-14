package com.bdg.airport_management_system_spring_boot.service.impl;

import com.bdg.airport_management_system_spring_boot.converter.model_to_persistent.ModToPerAddress;
import com.bdg.airport_management_system_spring_boot.converter.model_to_persistent.ModToPerPassenger;
import com.bdg.airport_management_system_spring_boot.converter.persistent_to_model.PerToModPassenger;
import com.bdg.airport_management_system_spring_boot.model.PassengerMod;
import com.bdg.airport_management_system_spring_boot.persistent.AddressPer;
import com.bdg.airport_management_system_spring_boot.persistent.PassengerPer;
import com.bdg.airport_management_system_spring_boot.repository.AddressRepository;
import com.bdg.airport_management_system_spring_boot.repository.PassInTripRepository;
import com.bdg.airport_management_system_spring_boot.repository.PassengerRepository;
import com.bdg.airport_management_system_spring_boot.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.bdg.airport_management_system_spring_boot.validator.Validator.*;

@Service
@Transactional
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;
    private final AddressRepository addressRepository;
    private final PassInTripRepository passInTripRepository;
    private final PerToModPassenger perToModPassenger;
    private final ModToPerPassenger modToPerPassenger;
    private final ModToPerAddress modToPerAddress;

    @Autowired
    public PassengerServiceImpl(
            PassengerRepository passengerRepository,
            AddressRepository addressRepository,
            PassInTripRepository passInTripRepository,
            PerToModPassenger perToModPassenger,
            ModToPerPassenger modToPerPassenger,
            ModToPerAddress modToPerAddress) {
        this.passengerRepository = passengerRepository;
        this.addressRepository = addressRepository;
        this.passInTripRepository = passInTripRepository;
        this.perToModPassenger = perToModPassenger;
        this.modToPerPassenger = modToPerPassenger;
        this.modToPerAddress = modToPerAddress;
    }


    @Override
    public PassengerMod save(PassengerMod passenger) {
        checkNull(passenger);

        if (passengerRepository.existsByPhone(passenger.getPhone())) {
            return null;
        }

        Optional<AddressPer> optionalAddressPer =
                addressRepository.findByCountryAndCity(passenger.getAddress().getCountry(), passenger.getAddress().getCity());

        PassengerPer passengertoSave = modToPerPassenger.getPersistentFrom(passenger);

        if (optionalAddressPer.isPresent()) {
            passengertoSave.setAddress(optionalAddressPer.get());
            passenger.getAddress().setId(optionalAddressPer.get().getId());
        }else {
            AddressPer newSavedAddress = addressRepository.save(modToPerAddress.getPersistentFrom(passenger.getAddress()));
            passengertoSave.setAddress(newSavedAddress);
            passenger.getAddress().setId(newSavedAddress.getId());
        }

        passengerRepository.save(passengertoSave);
        passenger.getAddress().setId(passengertoSave.getAddress().getId());
        passenger.setId(passengertoSave.getId());
        return passenger;
    }


    @Override
    public Optional<PassengerMod> findById(Long id) {
        checkId(id);
        return passengerRepository.findById(id).map(perToModPassenger::getModelFrom);
    }


    @Override
    public Optional<PassengerMod> findByPhone(String phone) {
        checkNonNullAndNonEmptyString(phone);
        return passengerRepository.findByPhone(phone).map(perToModPassenger::getModelFrom);
    }


    @Override
    public List<PassengerMod> findAll() {
        return perToModPassenger.getModelListFrom(passengerRepository.findAll());
    }


    @Override
    public List<PassengerMod> findAllByAddressId(Long addressId) {
        checkId(addressId);
        return perToModPassenger.getModelListFrom(passengerRepository.findAllByAddress_Id(addressId));
    }


    @Override
    public List<PassengerMod> findAllByCountry(String country) {
        checkNonNullAndNonEmptyString(country);
        return perToModPassenger.getModelListFrom(passengerRepository.findAllByAddress_Country(country));
    }


    @Override
    public List<PassengerMod> findAllByCity(String city) {
        checkNonNullAndNonEmptyString(city);
        return perToModPassenger.getModelListFrom(passengerRepository.findAllByAddress_City(city));
    }


    @Override
    public List<PassengerMod> findAllByCountryAndCity(String country, String city) {
        checkNonNullAndNonEmptyString(country);
        checkNonNullAndNonEmptyString(city);

        return perToModPassenger.getModelListFrom(passengerRepository.findAllByAddress_CountryAndAddress_City(country, city));
    }


    @Override
    public List<PassengerMod> findAllByName(String name) {
        checkNonNullAndNonEmptyString(name);

        return perToModPassenger.getModelListFrom(passengerRepository.findAllByName(name));
    }


    @Override
    public PassengerMod updateById(Long id, PassengerMod newPassenger) {
        checkId(id);
        checkNull(newPassenger);

        Optional<PassengerPer> optionalPassenger = passengerRepository.findById(id);
        if (optionalPassenger.isEmpty()) {
            return null;
        }

        if (passengerRepository.existsByPhone(newPassenger.getPhone())) {
            return null;
        }

        Optional<AddressPer> optionalAddressPer =
                addressRepository.findByCountryAndCity(newPassenger.getAddress().getCountry(), newPassenger.getAddress().getCity());

        PassengerPer passengerToUpdate = optionalPassenger.get();
        passengerToUpdate.setName(newPassenger.getName());
        passengerToUpdate.setPhone(newPassenger.getPhone());

        if (optionalAddressPer.isPresent()) {
            passengerToUpdate.setAddress(optionalAddressPer.get());
            newPassenger.getAddress().setId(optionalAddressPer.get().getId());
        } else {
            AddressPer newSavedAddress = addressRepository.save(modToPerAddress.getPersistentFrom(newPassenger.getAddress()));
            passengerToUpdate.setAddress(newSavedAddress);
            newPassenger.getAddress().setId(newSavedAddress.getId());
        }

        passengerRepository.save(passengerToUpdate);
        newPassenger.getAddress().setId(passengerToUpdate.getAddress().getId());
        newPassenger.setId(passengerToUpdate.getId());
        return newPassenger;
    }


    @Override
    public boolean deleteById(Long id) {
        checkId(id);

        if (passInTripRepository.existsByPassenger_Id(id)) {
            return false;
        }

        if (passengerRepository.existsById(id)) {
            passengerRepository.deleteById(id);
            return true;
        }
        return false;
    }


    @Override
    public boolean deleteByPhone(String phone) {
        checkNonNullAndNonEmptyString(phone);

        if (passInTripRepository.existsByPassenger_Phone(phone)) {
            return false;
        }

        if (passengerRepository.existsByPhone(phone)) {
            passengerRepository.deleteByPhone(phone);
            return true;
        }
        return false;
    }


    @Override
    public long deleteAllByName(String name) {
        checkNonNullAndNonEmptyString(name);

        if (passInTripRepository.existsByPassenger_Name(name)) {
            return 0;
        }

        return passengerRepository.deleteAllByName(name);
    }


    @Override
    public boolean existsByAddressId(Long id) {
        checkId(id);
        return passengerRepository.existsByAddress_Id(id);
    }
}