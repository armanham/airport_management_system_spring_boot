package com.bdg.airport_management_system_spring_boot.converter.model_to_persistent;

import com.bdg.airport_management_system_spring_boot.validator.Validator;

import java.util.List;

public abstract class ModToPer<M, P> {

    public ModToPer() {
    }

    public abstract P getPersistentFrom(M model);

    public List<P> getPersistentListFrom(List<M> modelList) {
        Validator.checkNull(modelList);

        return modelList.stream()
                .map(this::getPersistentFrom)
                .toList();
    }
}