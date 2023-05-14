package com.bdg.airport_management_system_spring_boot.converter.model_to_persistent;

import com.bdg.airport_management_system_spring_boot.model.AddressMod;
import com.bdg.airport_management_system_spring_boot.persistent.AddressPer;
import com.bdg.airport_management_system_spring_boot.validator.Validator;
import org.springframework.stereotype.Component;

@Component
public class ModToPerAddress extends ModToPer<AddressMod, AddressPer> {

    @Override
    public AddressPer getPersistentFrom(AddressMod model) {
        Validator.checkNull(model);

        AddressPer persistent = new AddressPer();
        persistent.setCountry(model.getCountry());
        persistent.setCity(model.getCity());

        return persistent;
    }
}