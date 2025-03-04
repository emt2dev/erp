package com.example.gvvfd.erp.Models;

import com.example.gvvfd.erp.Models.interfaces.Base;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name="UserActions")
@NoArgsConstructor
@AllArgsConstructor
public class UserActions implements Base {
    private Date created;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long agencyId;
    private Boolean active;
    private Long rosterId;
    @Enumerated(EnumType.STRING)
    private com.example.gvvfd.erp.Models.enums.Action action;
    private Long submittedUserId;
}
