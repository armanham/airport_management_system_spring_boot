package com.bdg.airport_management_system_spring_boot.controller;

import com.bdg.airport_management_system_spring_boot.model.AddressMod;
import com.bdg.airport_management_system_spring_boot.model.PassengerMod;
import com.bdg.airport_management_system_spring_boot.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/passenger")
public class PassengerController {

    private final PassengerService passengerService;

    @Autowired
    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }


    @PostMapping(value = "/new")
    public PassengerMod add(@RequestBody PassengerMod passenger){
        return passengerService.save(passenger);
    }


    @GetMapping(value = "/list/byId/{id}")
    public Optional<PassengerMod> getById(@PathVariable("id") Long id) {
        return passengerService.findById(id);
    }


    @GetMapping(value = "/list/byPhone/{phone}")
    public Optional<PassengerMod> getByPhone(@PathVariable("phone") String phone) {
        return passengerService.findByPhone(phone);
    }


    @GetMapping(value = "/list")
    public List<PassengerMod> getAll() {
        return passengerService.findAll();
    }


    @GetMapping(value = "/list/byCountry/{country}")
    List<PassengerMod> getAllByCountry(@PathVariable("country") String country){
        return passengerService.findAllByCountry(country);
    }


    @GetMapping(value = "/list/byCity/{city}")
    List<PassengerMod> getAllByCity(@PathVariable("city") String city){
        return passengerService.findAllByCity(city);
    }


    @GetMapping(value = "/list/byCountryAndCity")
    List<PassengerMod> getAllByCountryAndCity(@RequestBody AddressMod address){
        return passengerService.findAllByCountryAndCity(address.getCountry(), address.getCity());
    }


    @GetMapping(value = "/list/byName/{name}")
    List<PassengerMod> getAllByName(@PathVariable("name") String name){
        return passengerService.findAllByName(name);
    }


    @PutMapping(value = "/update/{id}")
    public PassengerMod updateById(@PathVariable("id") Long id, @RequestBody PassengerMod newPassenger){
        return passengerService.updateById(id, newPassenger);
    }


    @DeleteMapping(value = "/delete/byId/{id}")
    public boolean deleteById(@PathVariable("id") Long id){
        return passengerService.deleteById(id);
    }


    @DeleteMapping(value = "/delete/byPhone/{phone}")
    public boolean deleteByPhone(@PathVariable("phone") String phone){
        return passengerService.deleteByPhone(phone);
    }


    @DeleteMapping(value = "/delete/byName/{name}")
    public long deleteAllByName(@PathVariable("name") String name){
        return passengerService.deleteAllByName(name);
    }
}