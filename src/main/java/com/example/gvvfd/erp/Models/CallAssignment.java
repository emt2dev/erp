package com.example.gvvfd.erp.Models;

import com.example.gvvfd.erp.Models.interfaces.Base;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name="CallAssignments")
@NoArgsConstructor
@AllArgsConstructor
public class CallAssignment implements Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long agencyId;

    private Date created;

    private Boolean active;

    // Many CallAssignments can be associated with one Call
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CallId") // Foreign key to Call table
    @JsonIgnore
    private Call call;

    @Column(name="vehicle", length=20)
    private String vehicle;

    private Byte crewCount;

}
