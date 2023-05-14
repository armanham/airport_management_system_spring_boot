package com.bdg.airport_management_system_spring_boot.service.impl;

import com.bdg.airport_management_system_spring_boot.converter.model_to_persistent.ModToPerCompany;
import com.bdg.airport_management_system_spring_boot.converter.persistent_to_model.PerToModCompany;
import com.bdg.airport_management_system_spring_boot.model.CompanyMod;
import com.bdg.airport_management_system_spring_boot.persistent.CompanyPer;
import com.bdg.airport_management_system_spring_boot.repository.CompanyRepository;
import com.bdg.airport_management_system_spring_boot.repository.TripRepository;
import com.bdg.airport_management_system_spring_boot.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.bdg.airport_management_system_spring_boot.validator.Validator.*;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final TripRepository tripRepository;
    private final ModToPerCompany modToPerCompany;
    private final PerToModCompany perToModCompany;

    @Autowired
    public CompanyServiceImpl(
            CompanyRepository companyRepository,
            TripRepository tripRepository,
            ModToPerCompany modToPerCompany,
            PerToModCompany perToModCompany) {
        this.companyRepository = companyRepository;
        this.tripRepository = tripRepository;
        this.modToPerCompany = modToPerCompany;
        this.perToModCompany = perToModCompany;
    }


    public CompanyMod save(CompanyMod company) {
        checkNull(company);

        if (companyRepository.existsByName(company.getName())) {
            return null;
        }

        CompanyPer savedCompany = companyRepository.save(modToPerCompany.getPersistentFrom(company));
        company.setId(savedCompany.getId());

        return company;
    }


    @Override
    public Optional<CompanyMod> findById(Long id) {
        checkId(id);
        return companyRepository
                .findById(id)
                .map(perToModCompany::getModelFrom);
    }


    @Override
    public Optional<CompanyMod> findByName(String name) {
        checkNonNullAndNonEmptyString(name);
        return companyRepository
                .findByName(name)
                .map(perToModCompany::getModelFrom);
    }


    @Override
    public List<CompanyMod> findAllByFoundDate(Date date) {
        checkNull(date);
        return companyRepository
                .findAllByFoundDate(date)
                .stream()
                .map(perToModCompany::getModelFrom)
                .toList();
    }


    @Override
    public CompanyMod updateById(Long id, String newName) {
        checkId(id);
        checkNonNullAndNonEmptyString(newName);

        Optional<CompanyPer> optionalCompanyPer = companyRepository.findById(id);
        if (optionalCompanyPer.isEmpty()) {
            return null;
        }

        if (companyRepository.existsByName(newName)) {
            return null;
        }

        CompanyPer companyToUpdate = optionalCompanyPer.get();
        companyToUpdate.setName(newName);
        companyToUpdate.setFoundDate(Date.valueOf(LocalDate.now()));

        return perToModCompany.getModelFrom(companyRepository.save(companyToUpdate));
    }


    @Override
    public boolean deleteById(Long id) {
        checkId(id);

        if (tripRepository.existsByCompany_Id(id)) {
            return false;
        }

        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }


    @Override
    public boolean deleteByName(String name) {
        checkNonNullAndNonEmptyString(name);

        if (tripRepository.existsByCompany_Name(name)) {
            return false;
        }

        if (tripRepository.existsByCompany_Name(name)) {
            companyRepository.deleteByName(name);
            return true;
        }
        return false;
    }


    @Override
    public int deleteAllByFoundDate(Date date) {
        checkNull(date);

        if (tripRepository.existsByCompany_FoundDate(date)) {
            return 0;
        }

        return companyRepository.deleteAllByFoundDate(date);
    }
}