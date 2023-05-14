package com.bdg.airport_management_system_spring_boot.converter.model_to_persistent;

import com.bdg.airport_management_system_spring_boot.model.PassengerMod;
import com.bdg.airport_management_system_spring_boot.persistent.PassengerPer;
import com.bdg.airport_management_system_spring_boot.validator.Validator;
import org.springframework.stereotype.Component;

@Component
public class ModToPerPassenger extends ModToPer<PassengerMod, PassengerPer> {

    private final static ModToPerAddress MOD_TO_PER_ADDRESS = new ModToPerAddress();

    @Override
    public PassengerPer getPersistentFrom(PassengerMod model) {
        Validator.checkNull(model);

        PassengerPer persistent = new PassengerPer();
        persistent.setName(model.getName());
        persistent.setPhone(model.getPhone());
        persistent.setAddress(MOD_TO_PER_ADDRESS.getPersistentFrom(model.getAddress()));

        return persistent;
    }
}