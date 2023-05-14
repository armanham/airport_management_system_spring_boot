package com.bdg.airport_management_system_spring_boot.converter.persistent_to_model;

import com.bdg.airport_management_system_spring_boot.model.CompanyMod;
import com.bdg.airport_management_system_spring_boot.persistent.CompanyPer;
import com.bdg.airport_management_system_spring_boot.validator.Validator;
import org.springframework.stereotype.Component;

@Component
public class PerToModCompany extends PerToMod<CompanyPer, CompanyMod> {

    @Override
    public CompanyMod getModelFrom(CompanyPer persistent) {
        Validator.checkNull(persistent);

        CompanyMod model = new CompanyMod();
        model.setId(persistent.getId());
        model.setName(persistent.getName());
        model.setFoundDate(persistent.getFoundDate());

        return model;
    }
}