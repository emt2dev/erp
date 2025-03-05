package com.example.gvvfd.erp.Models;


import com.example.gvvfd.erp.Models.interfaces.Base;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "Location")
@NoArgsConstructor
@AllArgsConstructor
public class Location implements Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long agencyId;

    @Column(name = "created", nullable = false)
    private Date created;

    @Column(name = "active")
    private Boolean active;

    private String coordinates;
    private String name;
}
