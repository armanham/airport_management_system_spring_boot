package com.bdg.airport_management_system_spring_boot.validator;

public class Validator {

    public static void checkId(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("'id' must be a positive number: ");
        }
    }

    public static void checkNull(Object item) {
        if (item == null) {
            throw new NullPointerException("Passed null value as 'item': ");
        }
    }

    public static void checkNonNullAndNonEmptyString(String str) {
        checkNull(str);
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Passed an empty string: ");
        }
    }
}