package com.bdg.airport_management_system_spring_boot.converter.persistent_to_model;

import com.bdg.airport_management_system_spring_boot.model.PassengerMod;
import com.bdg.airport_management_system_spring_boot.persistent.PassengerPer;
import com.bdg.airport_management_system_spring_boot.validator.Validator;
import org.springframework.stereotype.Component;

@Component
public class PerToModPassenger extends PerToMod<PassengerPer, PassengerMod> {

    private final static PerToModAddress PER_TO_MOD_ADDRESS = new PerToModAddress();

    @Override
    public PassengerMod getModelFrom(PassengerPer persistent) {
        Validator.checkNull(persistent);

        PassengerMod model = new PassengerMod();
        model.setId(persistent.getId());
        model.setName(persistent.getName());
        model.setPhone(persistent.getPhone());
        model.setAddress(PER_TO_MOD_ADDRESS.getModelFrom(persistent.getAddress()));

        return model;
    }
}