package com.example.gvvfd.erp.Models.enums;

import lombok.Getter;

@Getter
public enum Action {
    Promoted(1),
    Demoted(2),
    Strike(3),
    Leave(4),
    Retired(5),
    Accepted(6),
    Dismissal(7),
    Certification(8),
    Training(9),
    Ticket(10);

    private final int value;

    Action(int value) {
        this.value = value;
    }

    // Optionally, you can add a method to get an Action from its integer value
    public static Action fromInt(int i) {
        for (Action action : Action.values()) {
            if (action.getValue() == i) {
                return action;
            }
        }

        return null;  // Or throw an exception if invalid
    }
}
