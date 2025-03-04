package com.example.gvvfd.erp.Models;

import com.example.gvvfd.erp.Models.interfaces.Base;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="Shifts")
public class Shift implements Base {
    private Date Created;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Long agencyId;
    private Boolean Active;
    private Byte RosterCount;
    private Byte CallCount;
    private Long CommanderUserId;
    private Long HostUserId;
    private Date Start;
    private Date End;
}
