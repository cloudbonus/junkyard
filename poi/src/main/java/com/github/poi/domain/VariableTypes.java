package com.github.poi.domain;

import lombok.Getter;

/**
 * @author Raman Haurylau
 */
@Getter
public enum VariableTypes {
    FIRST_NAME("#firstName"),
    LAST_NAME("#lastName"),
    POSITION("#position"),
    GENDER("#gender"),
    DATE_OF_BIRTH("#birthDate"),
    ADDRESS("#address"),
    EMPLOYEE_ID("#employeeId");

    private final String name;

    VariableTypes(String name) {
        this.name = name;
    }
}

