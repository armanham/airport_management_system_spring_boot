package com.bdg.airport_management_system_spring_boot.converter.persistent_to_model;

import com.bdg.airport_management_system_spring_boot.model.AddressMod;
import com.bdg.airport_management_system_spring_boot.persistent.AddressPer;
import com.bdg.airport_management_system_spring_boot.validator.Validator;
import org.springframework.stereotype.Component;

@Component
public class PerToModAddress extends PerToMod<AddressPer, AddressMod> {

    @Override
    public AddressMod getModelFrom(AddressPer persistent) {
        Validator.checkNull(persistent);

        AddressMod model = new AddressMod();
        model.setId(persistent.getId());
        model.setCountry(persistent.getCountry());
        model.setCity(persistent.getCity());

        return model;
    }
}