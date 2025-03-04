package com.example.gvvfd.erp.Models.enums;

import lombok.Getter;

@Getter
public enum CallType {
    Medical(1),
    CommercialFire(2),
    ResidentialFire(3),
    BrushFire(4),
    Assistance(5),
    AdditionalUnit(6);

    private final int value;

    CallType(int val) {
        this.value = val;
    }

    public static CallType fromInt(int i) {
        for (CallType type: CallType.values()) {
            if (type.getValue() == i) {
                return type;
            }
        }

        return null;
    }
}
