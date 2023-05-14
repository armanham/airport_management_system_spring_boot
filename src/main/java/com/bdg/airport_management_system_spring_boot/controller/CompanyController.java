package com.bdg.airport_management_system_spring_boot.controller;

import com.bdg.airport_management_system_spring_boot.model.CompanyMod;
import com.bdg.airport_management_system_spring_boot.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/company")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @PostMapping(value = "/new")
    public CompanyMod add(@RequestBody CompanyMod company) {
        return companyService.save(company);
    }


    @GetMapping(value = "/list/byId/{id}")
    public Optional<CompanyMod> getById(@PathVariable("id") Long id) {
        return companyService.findById(id);
    }


    @GetMapping(value = "/list/byName/{name}")
    public Optional<CompanyMod> getByName(@PathVariable("name") String name) {
        return companyService.findByName(name);
    }


    @GetMapping(value = "/list/byFoundDate/{foundDate}")
    public List<CompanyMod> getAllByFoundDate(@PathVariable("foundDate") Date foundDate) {
        return companyService.findAllByFoundDate(foundDate);
    }


    @PutMapping(value = "/update/{id}")
    public CompanyMod updateById(@PathVariable("id") Long id, @RequestBody String newName){
        return companyService.updateById(id, newName);
    }


    @DeleteMapping(value = "/delete/byId/{id}")
    public boolean deleteById(@PathVariable("id") Long id){
        return companyService.deleteById(id);
    }


    @DeleteMapping(value = "/delete/byName/{name}")
    public boolean deleteByName(@PathVariable("name") String name){
        return companyService.deleteByName(name);
    }


    @DeleteMapping(value = "/delete/byFoundDate/{foundDate}")
    public int deleteAllByFoundDate(@PathVariable("foundDate") Date foundDate){
        return companyService.deleteAllByFoundDate(foundDate);
    }
}