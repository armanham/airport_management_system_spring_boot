package com.bdg.airport_management_system_spring_boot.controller;

import com.bdg.airport_management_system_spring_boot.model.AddressMod;
import com.bdg.airport_management_system_spring_boot.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/address")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }


    @PostMapping(value = "/new")
    public AddressMod add(@RequestBody AddressMod address) {
        return addressService.save(address);
    }


    @GetMapping(value = "/list/byId/{id}")
    public Optional<AddressMod> getById(@PathVariable("id") Long id) {
        return addressService.findById(id);
    }


    @GetMapping(value = "/list")
    public List<AddressMod> getAll() {
        return addressService.findAll();
    }


    @GetMapping(value = "/list/byCountry/{country}")
    public List<AddressMod> getAllByCountry(@PathVariable("country") String country) {
        return addressService.findAllByCountry(country);
    }


    @GetMapping(value = "/list/byCity/{city}")
    public List<AddressMod> getAllByCity(@PathVariable("city") String city) {
        return addressService.findAllByCity(city);
    }


    @PutMapping(value = "/update/{id}")
    public AddressMod updateBy(@PathVariable("id") Long id, @RequestBody AddressMod newAddress) {
        return addressService.updateById(id, newAddress);
    }



    @DeleteMapping(value = "/delete/byId/{id}")
    public boolean deleteById(@PathVariable("id") Long id) {
        return addressService.deleteById(id);
    }


    @DeleteMapping(value = "/delete/byCountry/{country}")
    public long deleteAllByCountry(@PathVariable("country") String country) {
        return addressService.deleteAllByCountry(country);
    }


    @DeleteMapping(value = "/delete/byCity/{city}")
    public long deleteAllByCity(@PathVariable("city") String city) {
        return addressService.deleteAllByCity(city);
    }
}