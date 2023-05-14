package com.bdg.airport_management_system_spring_boot.service.impl;

import com.bdg.airport_management_system_spring_boot.converter.model_to_persistent.ModToPerAddress;
import com.bdg.airport_management_system_spring_boot.converter.persistent_to_model.PerToModAddress;
import com.bdg.airport_management_system_spring_boot.model.AddressMod;
import com.bdg.airport_management_system_spring_boot.persistent.AddressPer;
import com.bdg.airport_management_system_spring_boot.repository.AddressRepository;
import com.bdg.airport_management_system_spring_boot.repository.PassengerRepository;
import com.bdg.airport_management_system_spring_boot.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.bdg.airport_management_system_spring_boot.validator.Validator.*;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final PassengerRepository passengerRepository;
    private final PerToModAddress perToModAddress;
    private final ModToPerAddress modToPerAddress;


    @Autowired
    public AddressServiceImpl(
            AddressRepository addressRepository,
            PassengerRepository passengerRepository,
            PerToModAddress perToModAddress,
            ModToPerAddress modToPerAddress) {
        this.addressRepository = addressRepository;
        this.passengerRepository = passengerRepository;
        this.perToModAddress = perToModAddress;
        this.modToPerAddress = modToPerAddress;
    }


    @Override
    public AddressMod save(AddressMod address) {
        checkNull(address);

        if (addressRepository.existsByCountryAndCity(address.getCountry(), address.getCity())) {
            return null;
        }

        AddressPer addressToSave = addressRepository.save(modToPerAddress.getPersistentFrom(address));
        address.setId(addressToSave.getId());
        return address;
    }


    @Override
    public Optional<AddressMod> findById(Long id) {
        checkId(id);
        return addressRepository.findById(id).map(perToModAddress::getModelFrom);
    }


    @Override
    public List<AddressMod> findAll() {
        return perToModAddress.getModelListFrom(addressRepository.findAll());
    }


    @Override
    public List<AddressMod> findAllByCity(String city) {
        checkNonNullAndNonEmptyString(city);
        return perToModAddress.getModelListFrom(addressRepository.findAllByCity(city));
    }


    @Override
    public List<AddressMod> findAllByCountry(String country) {
        checkNonNullAndNonEmptyString(country);
        return perToModAddress.getModelListFrom(addressRepository.findAllByCountry(country));
    }


    @Override
    public AddressMod updateById(Long id, AddressMod newAddress) {
        checkId(id);
        checkNull(newAddress);

        Optional<AddressPer> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()) {
            System.out.println("Address with " + id + " does not exists: ");
            return null;
        }

        Optional<AddressPer> newAddressOptional = addressRepository.findByCountryAndCity(newAddress.getCountry(), newAddress.getCity());
        if (newAddressOptional.isPresent()) {
            System.out.println(newAddress + " already exists: ");
            return null;
        }

        AddressPer addressToUpdate = optionalAddress.get();
        addressToUpdate.setCountry(newAddress.getCountry());
        addressToUpdate.setCity(newAddress.getCity());

        addressRepository.save(addressToUpdate);

        return newAddress;
    }


    @Override
    public boolean deleteById(Long id) {
        checkId(id);

        if (passengerRepository.existsByAddress_Id(id)){
            return false;
        }

        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
            return true;
        }
        return false;
    }


    @Override
    public long deleteAllByCountry(String country) {
        checkNonNullAndNonEmptyString(country);

        if (passengerRepository.existsByAddress_Country(country)){
            return 0;
        }

        return addressRepository.deleteAllByCountry(country);
    }


    @Override
    public long deleteAllByCity(String city) {
        checkNonNullAndNonEmptyString(city);

        if (passengerRepository.existsByAddress_City(city)){
            return 0;
        }

        return addressRepository.deleteAllByCity(city);
    }
}