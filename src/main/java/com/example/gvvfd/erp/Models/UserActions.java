package com.example.gvvfd.erp.Models;

import com.example.gvvfd.erp.Models.interfaces.Base;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="UserActions")
public class UserActions implements Base {
    private Date Created;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Long agencyId;
    private Boolean Active;
    private Long RosterId;
    @Enumerated(EnumType.STRING)
    private com.example.gvvfd.erp.Models.enums.Action Action;
    private Long SubmittedUserId;
}
