package com.bdg.airport_management_system_spring_boot.converter.persistent_to_model;

import com.bdg.airport_management_system_spring_boot.validator.Validator;

import java.util.List;

public abstract class PerToMod<P, M> {

    public PerToMod() {
    }

    public abstract M getModelFrom(P persistent);

    public List<M> getModelListFrom(List<P> persistentList) {
        Validator.checkNull(persistentList);

        return persistentList
                .stream()
                .map(this::getModelFrom)
                .toList();
    }
}