package com.bdg.airport_management_system_spring_boot.converter.model_to_persistent;

import com.bdg.airport_management_system_spring_boot.model.CompanyMod;
import com.bdg.airport_management_system_spring_boot.persistent.CompanyPer;
import com.bdg.airport_management_system_spring_boot.validator.Validator;
import org.springframework.stereotype.Component;

@Component
public class ModToPerCompany extends ModToPer<CompanyMod, CompanyPer> {

    @Override
    public CompanyPer getPersistentFrom(CompanyMod model) {
        Validator.checkNull(model);

        CompanyPer persistent = new CompanyPer();
        persistent.setName(model.getName());
        persistent.setFoundDate(model.getFoundDate());

        return persistent;
    }
}