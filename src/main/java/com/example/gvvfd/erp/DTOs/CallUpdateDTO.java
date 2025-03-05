package com.example.gvvfd.erp.DTOs;

import com.example.gvvfd.erp.Models.enums.CallType;
import lombok.Data;

import java.util.Date;

@Data
public class CallUpdateDTO {
    private Long id;
    private Long agencyId;
    private CallType type;
    private String cadDisplay;
    private String location;
}
