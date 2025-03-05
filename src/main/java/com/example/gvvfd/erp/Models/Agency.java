package com.example.gvvfd.erp.Models;

import com.example.gvvfd.erp.Models.enums.Platform;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name="Agencies")
@NoArgsConstructor
@AllArgsConstructor
public class Agency {

    // Fields with private visibility and annotations
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created", nullable = false)
    private Date created;

    @Column(name = "active")
    private Boolean active;

    @Column(name="admin")
    private String admin;

    @Column(name="name")
    private String name;

    @Column(name="platform")
    @Enumerated(EnumType.STRING)
    private Platform platform;
}
