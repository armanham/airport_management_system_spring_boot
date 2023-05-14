package com.bdg.airport_management_system_spring_boot.converter.persistent_to_model;

import com.bdg.airport_management_system_spring_boot.model.TripMod;
import com.bdg.airport_management_system_spring_boot.persistent.TripPer;
import com.bdg.airport_management_system_spring_boot.validator.Validator;

public class PerToModTrip extends PerToMod<TripPer, TripMod> {

    private static final PerToModCompany PER_TO_MOD_COMPANY = new PerToModCompany();

    @Override
    public TripMod getModelFrom(TripPer persistent) {
        Validator.checkNull(persistent);

        TripMod model = new TripMod();
        model.setId(persistent.getId());
        model.setCompany(PER_TO_MOD_COMPANY.getModelFrom(persistent.getCompany()));
        model.setAirplane(persistent.getAirplane());
        model.setTownFrom(persistent.getTownFrom());
        model.setTownTo(persistent.getTownTo());
        model.setTimeIn(persistent.getTimeIn());
        model.setTimeOut(persistent.getTimeOut());

        return model;
    }
}