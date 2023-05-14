package com.bdg.airport_management_system_spring_boot.model;

import static com.bdg.airport_management_system_spring_boot.validator.Validator.checkId;

public abstract class BaseMod {

    private Long id;

    public BaseMod(final Long id) {
        setId(id);
    }

    public BaseMod() {
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        checkId(id);
        this.id = id;
    }
}