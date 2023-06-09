package com.bdg.airport_management_system_spring_boot.model;

import java.sql.Timestamp;

import static com.bdg.airport_management_system_spring_boot.validator.Validator.checkNonNullAndNonEmptyString;
import static com.bdg.airport_management_system_spring_boot.validator.Validator.checkNull;

public class TripMod extends BaseMod {

    private CompanyMod company;
    private String airplane;
    private String townFrom;
    private String townTo;
    private Timestamp timeOut;
    private Timestamp timeIn;

    public TripMod(
            final Long id,
            final CompanyMod company,
            final String airplane,
            final String townFrom,
            final String townTo,
            final Timestamp timeOut,
            final Timestamp timeIn) {
        super(id);
        setCompany(company);
        setAirplane(airplane);
        setTownFrom(townFrom);
        setTownTo(townTo);
        setTimeOut(timeOut);
        setTimeIn(timeIn);
    }

    public TripMod() {
    }

    public CompanyMod getCompany() {
        return company;
    }

    public void setCompany(final CompanyMod companyMod) {
        checkNull(companyMod);
        this.company = companyMod;
    }

    public String getAirplane() {
        return airplane;
    }

    public void setAirplane(final String airplane) {
        checkNonNullAndNonEmptyString(airplane);
        this.airplane = airplane;
    }

    public String getTownFrom() {
        return townFrom;
    }

    public void setTownFrom(final String townFrom) {
        checkNonNullAndNonEmptyString(townFrom);
        this.townFrom = townFrom;
    }

    public String getTownTo() {
        return townTo;
    }

    public void setTownTo(final String townTo) {
        checkNonNullAndNonEmptyString(townTo);
        this.townTo = townTo;
    }

    public Timestamp getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(final Timestamp timeOut) {
        checkNull(timeOut);
        this.timeOut = timeOut;
    }

    public Timestamp getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(final Timestamp timeIn) {
        checkNull(timeIn);
        this.timeIn = timeIn;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + getId() +
                ", company=" + company +
                ", airplane='" + airplane + '\'' +
                ", townFrom='" + townFrom + '\'' +
                ", townTo='" + townTo + '\'' +
                ", timeOut=" + timeOut +
                ", timeIn=" + timeIn +
                "}\n";
    }
}