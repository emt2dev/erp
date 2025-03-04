package com.example.gvvfd.erp.Models.enums;

import lombok.Getter;

@Getter
public enum Platform {
    Roblox(0);

    private final int value;

    Platform(int val) {
        this.value = val;
    }
    }
