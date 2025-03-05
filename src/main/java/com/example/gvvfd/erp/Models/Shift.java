package com.example.gvvfd.erp.Models;

import com.example.gvvfd.erp.Models.interfaces.Base;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name="Shifts")
@NoArgsConstructor
@AllArgsConstructor
public class Shift implements Base {
    private Date created;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long agencyId;
    private Boolean active;
    private Byte rosterCount;
    private Byte callCount;
    private Long commanderUserId;
    private Long hostUserId;
    private Date start;
    private Date end;
}
