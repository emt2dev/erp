package com.example.gvvfd.erp.Models;

import com.example.gvvfd.erp.Models.interfaces.Base;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="CallAssignments")
public class CallAssignment implements Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Long agencyId;

    private Date Created;

    private Boolean Active;

    // Many CallAssignments can be associated with one Call
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CallId") // Foreign key to Call table
    private Call call;

    @Column(name="vehicle", length=20)
    private String Vehicle;

    private Byte CrewCount;

}
